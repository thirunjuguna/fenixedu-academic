
package middleware.persistentMiddlewareSupport.exceptions;


/**
 * 
 * @author Nuno Nunes (nmsn@rnl.ist.utl.pt)
 * 
 */
public class PersistentMiddlewareSupportException extends java.lang.Exception {
	public static final int OPEN_DATABASE = 1;
	public static final int BEGIN_TRANSACTION = 2;
	public static final int COMMIT_TRANSACTION = 3;
	public static final int ABORT_TRANSACTION = 4;
	public static final int UPGRADE_LOCK = 5;
	public static final int READ_LOCK = 6;
	public static final int QUERY = 7;
	public static final int CLOSE_DATABASE = 8;

	private int _erro;
	private String errorKey;
	/**
	 * Creates a new instance of <code>ExcepcaoPersistencia</code> without detail message.
	 */
	public PersistentMiddlewareSupportException() {
	}

	public PersistentMiddlewareSupportException(int erro, Exception ex) {
		super(ex);
		_erro = erro;
	}

	public PersistentMiddlewareSupportException(String error, Exception ex) {
		super(ex);
		this.errorKey = error;
	}

	public PersistentMiddlewareSupportException(int erro) {
		_erro = erro;
	}

	/**
	 * Constructs an instance of <code>ExcepcaoPersistencia</code> with the specified detail message.
	 * @param msg the detail message.
	 */
	public PersistentMiddlewareSupportException(String msg) {
		super(msg);
	}

	public int getErro() {
		return _erro;
	}

	public String getErrorKey() {
		return this.errorKey;
	}

	public void setErrorKey(int error) {
		switch (error) {
			case 1 :
				this.errorKey = "exception.error.openDatabase";
			case 2 :
				this.errorKey = "exception.error.beginTransaction";
			case 3 :
				this.errorKey = "exception.error.commitTransaction";
			case 4 :
				this.errorKey = "exception.error.abortTransaction";
			case 5 :
				this.errorKey = "exception.error.upgradeLock";
			case 6 :
				this.errorKey = "exception.error.readLock";
			case 7 :
				this.errorKey = "exception.error.query";
			case 8 :
				this.errorKey = "exception.error.closeDatabase";
			default :
				this.errorKey = "exception.error.unknown";
		}
	}
	public String toString() {
		String result = "[ExcepcaoPersistencia\n";
		result += "message" + this.getMessage() + "\n";
		result += "error" + this.getErrorKey() + "\n";
		result += "cause" + this.getCause() + "\n";
		result += "]";
		return result;
	}
}
