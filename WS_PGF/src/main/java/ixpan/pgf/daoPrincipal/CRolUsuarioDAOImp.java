package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;



import ixpan.pgf.model.*;
import ixpan.pgf.hibernate.*;
import ixpan.pgf.exception.*;


public class CRolUsuarioDAOImp {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
    public static enum tipo_Rol{Administrador,Cliente,Interno,Contratista};
 
    
    public CRolUsuario obtenerCRolUsuario(int id) throws ExceptionPGF 
    { 
    	CRolUsuario rol = null;  
        try 
        { 
            iniciaOperacion(); 
            rol = (CRolUsuario)sesion.get(CRolUsuario.class, id);
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        }finally { 
            sesion.close(); 
        }  

        return rol; 
    }
    
    
    public List<CRolUsuario> obtenerCRolUsuarios() throws ExceptionPGF{
    	String myquery = "select * from CRolUsuario;";
        try{
        	LinkedList<CRolUsuario> roles = new LinkedList<CRolUsuario>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery("select * from CRolUsuario;");
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CRolUsuario rol = new CRolUsuario();
        		rol.setIdCRolUsuario(Integer.valueOf(row[0].toString()));
        		rol.setNombre(row[1].toString());
        		roles.add(rol);
        	}
        	if(roles.size() > 0){
        		return roles;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException; 
        }finally{
        	sesion.close();
        }
    }
    
    public CRolUsuario obtenerCRolUsuarioPorRol(String rol) throws ExceptionPGF{
    	String myquery = "select * from C_Rol_Usuario where C_Rol_Usuario.Nombre ='"+rol+"';";
        try{
        	LinkedList<CRolUsuario> roles = new LinkedList<CRolUsuario>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CRolUsuario rolEncontrado = new CRolUsuario();
        		rolEncontrado.setIdCRolUsuario(Integer.valueOf(row[0].toString()));
        		rolEncontrado.setNombre(row[1].toString());
        		return rolEncontrado;
        	}
        	return null;
        }catch(Exception e){
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException; 
        }finally{
        	sesion.close();
        }
    }
    
    public long guardarCRolUsuario(CRolUsuario rol) throws ExceptionPGF 
    { 
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Long) sesion.save(rol); 
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
    
    public void actualizarCRolUsuario(CRolUsuario rol) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(rol); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        } finally { 
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
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 
}