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


public class ActividadDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
    public static enum tipo_Direccion{personal,fiscal};
    
    
    public CActividad obtenerActividad(int id) throws ExceptionPGF 
    { 
    	CActividad actividad = null;  
        try 
        { 
            iniciaOperacion(); 
            actividad = (CActividad)sesion.get(CActividad.class, id);
            
            
        } catch(HibernateException he){ 
        	ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        }
        finally { 
            sesion.close(); 
        }  

        return actividad; 
    }
    
    
    public LinkedList<CActividad> obtenerActividades() throws ExceptionPGF{
    	String myquery = "select * from C_Actividad;";
    	try{
        	LinkedList<CActividad> actividades = new LinkedList<CActividad>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CActividad actividad = new CActividad();
        		actividad.setIdCActividad(Integer.valueOf(row[0].toString()));
        		actividad.setNombre(row[1].toString());
        		
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
    
    public LinkedList<CActividad> obtenerActividadesPorUsuario(int idUsuario) throws ExceptionPGF{
    	String myquery = "SELECT C_Actividad.* from C_Actividad, R_Usuario_Actividad where "
    			+ "R_Usuario_Actividad.idActividad = C_Actividad.idC_Actividad and "
    			+ "R_Usuario_Actividad.idContratista = "+idUsuario+";";
    	try{
        	LinkedList<CActividad> actividades = new LinkedList<CActividad>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CActividad actividad = new CActividad();
        		actividad.setIdCActividad(Integer.valueOf(row[0].toString()));
        		actividad.setNombre(row[1].toString());
        		
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
    
    public int guardarActividad(CActividad actividad) throws ExceptionPGF
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(actividad); 
            tx.commit(); 
        } catch (HibernateException he) {
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } finally { 
            sesion.close(); 
        }  

        return id; 
    }
    public void eliminarActividad(CActividad actividad) throws ExceptionPGF
    {
        try 
        { 
            iniciaOperacion();
            sesion.delete(actividad); 
            tx.commit(); 
        } catch (HibernateException he) {
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } finally { 
            sesion.close(); 
        }
    }
    
    
    public void actualizarActividad(CActividad direccion) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(direccion); 
            tx.commit(); 
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } finally 
        { 
            sesion.close(); 
        } 
    }  
    
    
   
    private void iniciaOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  
    
    private void manejaExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw  he; 
    } 
}
