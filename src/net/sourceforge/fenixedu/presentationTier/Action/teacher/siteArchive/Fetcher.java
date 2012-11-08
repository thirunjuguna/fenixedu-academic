package net.sourceforge.fenixedu.presentationTier.Action.teacher.siteArchive;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.domain.File;
import net.sourceforge.fenixedu.domain.functionalities.FunctionalityContext;
import net.sourceforge.fenixedu.presentationTier.Action.teacher.siteArchive.streams.FetcherRequestWrapper;
import net.sourceforge.fenixedu.presentationTier.Action.teacher.siteArchive.streams.FetcherServletResponseWrapper;
import net.sourceforge.fenixedu.presentationTier.servlets.filters.functionalities.FilterFunctionalityContext;
import pt.ist.fenixframework.pstm.AbstractDomainObject;
import pt.utl.ist.fenix.tools.file.FileManagerFactory;
import pt.utl.ist.fenix.tools.file.IFileManager;

/**
 * The <tt>Fetcher</tt> manages a queue of
 * {@link net.sourceforge.fenixedu.presentationTier.Action.teacher.siteArchive.Resource}
 * and it's responsible for retrieving and transforming each resource in the
 * queue.
 * 
 * <p>
 * Each resource is retrieved by creating a new <tt>RequestDispatcher</tt> to
 * the resource's url and by forwarding the request to that dispatcher. The
 * current request and response are wrapped to avoid unwanted secondary effects
 * and to allow the called to generate it's own content to the user.
 * 
 * <p>
 * If the resource is an HTML page then url's present in the page are
 * transformed using the resource's rules.
 * 
 * @author cfgi
 */
public class Fetcher {

    Queue<Resource> queue;
    Collection<Resource> fetched;

    private Archive archive;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterFunctionalityContext requestContext;

    public Fetcher() {
	super();

	this.queue = new LinkedList<Resource>();
	this.fetched = new HashSet<Resource>();
    }

    public Fetcher(Archive archive, HttpServletRequest request, HttpServletResponse response) {
	this();

	this.archive = archive;
	this.request = request;
	this.response = response;
    }

    /**
     * Queues the given resource. The resource is only queued if it isn't
     * already in the and if it wasn't already feched.
     */
    public void queue(Resource resource) {
	if (this.queue.contains(resource) || this.fetched.contains(resource)) {
	    return;
	}

	this.queue.add(resource);
    }

    /**
     * If the fetcher has more resources to be fetched.
     * 
     * @return <code>true</code> if {@link #next()} can be called safely and
     *         will return a new Resource
     */
    public boolean hasMore() {
	return this.queue.peek() != null;
    }

    /**
     * Obtains the next Resource in the queue.
     * 
     * @return the next resource in the queue
     * 
     * @exception java.util.NoSuchElementException
     *                if the is no Resource queued
     */
    public Resource next() {
	return this.queue.remove();
    }

    /**
     * Processes the current queue retrieving all resources and saving them in
     * the archive.
     */
    public void process(FilterFunctionalityContext context) throws ServletException, IOException {
	this.requestContext = context;
	process();
    }

    /**
     * Processes the current queue retrieving all resources and saving them in
     * the archive.
     */
    public void process() throws ServletException, IOException {
	while (hasMore()) {
	    Resource next = next();

	    OutputStream stream = this.archive.getStream(next);
	    fetch(next, stream);
	}
    }

    private void markAsFetched(Resource resource) {
	this.fetched.add(resource);
    }

    /**
     * Retrieves the given resource and saves it's contents in the given stream.
     * If the resource is an HTML page then this fetcher ensures that all the
     * urls in the page are transformed, using the resource's rules, and all
     * extra resources generated by the rules are queue.
     * 
     * @param resource
     *            the resource to retrieve
     * @param stream
     *            the destination of the contents
     * 
     * @throws ServletException
     *             if the target resource throws and exception
     * @throws IOException
     *             when a streaming error occurs
     */
    public void fetch(Resource resource, OutputStream stream) throws ServletException, IOException {
	markAsFetched(resource);

	String url = prepareUrl(resource);
	if (url.indexOf("dspace") >= 0) {
	    getDspaceFile(stream, url);	    
	} else {
	    getLocalFile(stream, url);
	}
    }

    private void getLocalFile(final OutputStream stream, final String url) throws IOException {
	final File file = getFileFromUrl(url);
	final byte[] contents = file.getContents();
	stream.write(contents);
	stream.close();
    }

    private void getDspaceFile(OutputStream stream, String url) throws IOException {
	IFileManager fileManager = FileManagerFactory.getFactoryInstance().getFileManager();
	InputStream fileStream = fileManager.retrieveFile(getDspaceFileId(url));

	byte[] buffer = new byte[2048];
	int length;

	while ((length = fileStream.read(buffer)) != -1) {
	    stream.write(buffer, 0, length);
	}

	fileStream.close();
	stream.close();
    }

    private File getFileFromUrl(final String url) {
	final int i = url.lastIndexOf('=');
	final String oid = url.substring(i + 1);
	return AbstractDomainObject.fromExternalId(oid);
    }

    private String getDspaceFileId(String url) {
	return url.replaceAll(".*?/bitstream/([0-9]+/[0-9]+/[0-9]+)/.*", "$1");
    }

    private boolean isDspaceFile(String url) {
	return url.matches(".*?/bitstream/[0-9]+/[0-9]+/[0-9]+/.*");
    }

    private String prepareUrl(Resource resource) {
	String url = resource.getUrl();
	String contextPath = this.request.getContextPath();

	if (url.startsWith(contextPath)) {
	    url = url.substring(contextPath.length());
	}

	url = url.replaceAll("&amp;", "&");

	return url;
    }

    private ServletRequest createForwardRequest(FilterFunctionalityContext context) {
	ServletRequest wrapper = createForwardRequest();
	wrapper.removeAttribute(FunctionalityContext.CONTEXT_KEY);
	wrapper.setAttribute(FunctionalityContext.CONTEXT_KEY, context);
	return wrapper;
    }

    private ServletRequest createForwardRequest() {
	return new FetcherRequestWrapper(this.request);
    }

    private FetcherServletResponseWrapper createForwardResponse(Resource resource, OutputStream stream) {
	return new FetcherServletResponseWrapper(this.response, this, resource, stream);
    }

}
