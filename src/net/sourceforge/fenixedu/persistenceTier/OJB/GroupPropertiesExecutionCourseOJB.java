/*
 * Created on 17/Ago/2004
 *
 */
package net.sourceforge.fenixedu.persistenceTier.OJB;

import java.util.List;

import net.sourceforge.fenixedu.domain.GroupPropertiesExecutionCourse;
import net.sourceforge.fenixedu.domain.IExecutionCourse;
import net.sourceforge.fenixedu.domain.IGroupProperties;
import net.sourceforge.fenixedu.domain.IGroupPropertiesExecutionCourse;
import net.sourceforge.fenixedu.persistenceTier.ExcepcaoPersistencia;
import net.sourceforge.fenixedu.persistenceTier.IPersistentGroupPropertiesExecutionCourse;

import org.apache.ojb.broker.query.Criteria;

/**
 * @author joaosa & rmalo
 */

public class GroupPropertiesExecutionCourseOJB extends ObjectFenixOJB 
				implements IPersistentGroupPropertiesExecutionCourse
				{


    public IGroupPropertiesExecutionCourse readBy(IGroupProperties groupProperties, IExecutionCourse executionCourse)
        throws ExcepcaoPersistencia
    {

        Criteria criteria = new Criteria();

        criteria.addEqualTo("keyGroupProperties", groupProperties.getIdInternal());
        criteria.addEqualTo("keyExecutionCourse", executionCourse.getIdInternal());

        return (IGroupPropertiesExecutionCourse) queryObject(GroupPropertiesExecutionCourse.class, criteria);

    }
    
    public IGroupPropertiesExecutionCourse readByIDs(Integer groupPropertiesID,Integer executionCourseID)
    throws ExcepcaoPersistencia
{

    Criteria criteria = new Criteria();

    criteria.addEqualTo("keyGroupProperties", groupPropertiesID);
    criteria.addEqualTo("keyExecutionCourse", executionCourseID);

    return (IGroupPropertiesExecutionCourse) queryObject(GroupPropertiesExecutionCourse.class, criteria);

}
    
    

    public List readAllByGroupProperties(IGroupProperties groupProperties) throws ExcepcaoPersistencia
    {

        Criteria criteria = new Criteria();

        criteria.addEqualTo("keyGroupProperties", groupProperties.getIdInternal());

        return queryList(GroupPropertiesExecutionCourse.class, criteria);
    }

    public List readAll() throws ExcepcaoPersistencia
    {
        return queryList(GroupPropertiesExecutionCourse.class, new Criteria());

    }

    
    public void delete(IGroupPropertiesExecutionCourse groupPropertiesExecutionCourse) throws ExcepcaoPersistencia
    {
        try
        {
            super.delete(groupPropertiesExecutionCourse);
        }
        catch (ExcepcaoPersistencia ex)
        {
            throw ex;
        }
    }

   
    public List readAllByExecutionCourse(IExecutionCourse executionCourse) throws ExcepcaoPersistencia
    {

        Criteria criteria = new Criteria();

        criteria.addEqualTo("keyExecutionCourse", executionCourse.getIdInternal());

        return queryList(GroupPropertiesExecutionCourse.class, criteria);
    }

    
    
    public List readByGroupPropertiesId(Integer id) throws ExcepcaoPersistencia
    {
        Criteria criteria = new Criteria();

        criteria.addEqualTo("keyGroupProperties", id);
        
        return queryList(GroupPropertiesExecutionCourse.class, criteria);
    }


    public List readAllByExecutionCourseId(Integer id) throws ExcepcaoPersistencia
    {

        Criteria criteria = new Criteria();

        criteria.addEqualTo("keyExecutionCourse", id);

        return queryList(GroupPropertiesExecutionCourse.class, criteria);
    }
}
