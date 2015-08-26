package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ixpan.pgf.model.*;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.*;


public class RUsuarioActividadDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
    public static enum tipo_Direccion{personal,fiscal};
    
    public LinkedList<RUsuarioActividad> obtenerActividadesPorUsuario(int idContratista) throws ExceptionPGF{
    	String myquery = "SELECT R_Usuario_Actividad.* from R_Usuario_Actividad where "
    			+ "R_Usuario_Actividad.idContratista = "+idContratista+";";
    	try{
        	LinkedList<RUsuarioActividad> actividades = new LinkedList<RUsuarioActividad>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		RUsuarioActividad actividad = new RUsuarioActividad();
        		RUsuarioActividadId actividadid = new RUsuarioActividadId(); 
        		actividadid.setIdContratista(Integer.valueOf(row[0].toString()));
        		actividadid.setIdActividad(Integer.valueOf(row[1].toString()));
        		actividad.setId(actividadid);
        		actividades.add(actividad);
        	}
        	if(actividades.size() > 0){
        		return actividades;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        }
    }
    
    
    public void eliminarRUsuarioActividad(RUsuarioActividad rUsuarioActividad) throws ExceptionPGF
    { 
    	try 
        { 
            iniciaOperacion(); 
            sesion.delete(rUsuarioActividad); 
            tx.commit(); 
        } catch (Exception he) {
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } finally { 
            sesion.close(); 
        }
    }
    
    public RUsuarioActividadId guardarRUsuarioActividad(RUsuarioActividad rUsuarioActividad) throws ExceptionPGF
    { 
    	RUsuarioActividadId id;
        try 
        { 
            iniciaOperacion();
            id = (RUsuarioActividadId) sesion.save(rUsuarioActividad);
            tx.commit(); 
        } catch (Exception he) {
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } finally { 
            sesion.close(); 
        }  

        return id; 
    }
    
    
   
    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  
    
    private void manejaExcepcion(Exception he){
    
        tx.rollback(); 
        //throw  he; 
    } 
}
