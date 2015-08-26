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


public class UsuarioDAOImp {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Usuario obtenerUsusario(int id) throws ExceptionPGF 
    { 
    	Usuario usuario = null;  
        try 
        { 
            iniciaOperacion(); 
            usuario = (Usuario)sesion.get(Usuario.class, id);

            
        } catch(HibernateException he){ 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        }finally { 
            sesion.close(); 
        } 

        return usuario; 
    }
    
    
    public List<Usuario> obtenerUsuarios() throws ExceptionPGF{
    	String myquery = "select * from Usuario;";
        try{
        	LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Usuario usuario = new Usuario();
        		usuario.setIdUsuario(Integer.valueOf(row[0].toString()));
        		usuario.setUserName(row[1].toString());
        		usuario.setContrasena(row[2].toString());
        		usuario.setIdRolUsuario(Integer.valueOf(row[3].toString()));
        		usuario.setNombre(row[4].toString());
        		usuario.setApellido(row[5].toString());
        		usuario.setEmail(row[6].toString());
        		usuario.setIfe(row[7].toString());
        		usuario.setStatus(row[8].toString());
        		usuario.setActaHacienda(row[9].toString());
        		usuario.setActaConstitutiva(row[10].toString());
        		usuario.setInfraestructura(row[11].toString());
        		usuario.setDatosFiscales(row[12].toString());
        		usuario.setCapacidadGeneral(row[13].toString());
        		usuario.setCapacidadFinanciera(row[14].toString());
        		usuario.setCalificacionGlobal(Integer.valueOf(row[15].toString()));
        		usuario.setDireccionFiscal(Integer.valueOf( (row[16]== null) ? "0" : row[16].toString()));
        		usuario.setDireccionFiscal(Integer.valueOf( (row[17]== null) ? "0" : row[16].toString()));
        		
        		usuarios.add(usuario);
        	}
        	if(usuarios.size() > 0){
        		return usuarios;
        	}
        	return null;  
        }catch(Exception e){ 
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        } 
    }
    
    public int guardarUsuario(Usuario usuario) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Integer) sesion.save(usuario); 
            System.out.println("Usuario id: " + id);
            tx.commit(); 
        } catch (HibernateException he){ 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException; 
        }finally { 
            sesion.close(); 
        }   

        return id; 
    }
    
    
    public void eliminarUsuario(Usuario usuario) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            sesion.delete(usuario); 
            tx.commit(); 
        } catch (HibernateException he){ 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException; 
        }finally { 
            sesion.close(); 
        } 
    }
    public void actualizarUsuario(Usuario usuario) throws ExceptionPGF 
    { 
    	int id;
        try 
        { 
            iniciaOperacion(); 
            System.out.println("IdUsuario:"+usuario.getIdUsuario());
            System.out.println("Nombre:"+usuario.getNombre());
            System.out.println("Apellido:"+usuario.getApellido());
            
            sesion.update(usuario); 
            tx.commit();
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        }finally { 
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