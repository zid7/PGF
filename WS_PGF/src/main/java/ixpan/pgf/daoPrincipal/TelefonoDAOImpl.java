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


public class TelefonoDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
    public static enum tipo_Telefono{telefono,celular,otroTelefono};
 
    
    
    public Telefono obtenerTelefono(int id) throws ExceptionPGF 
    { 
    	Telefono telefono = null;  
        try 
        { 
            iniciaOperacion(); 
            telefono = (Telefono)sesion.get(Telefono.class, id);
            
        }catch(HibernateException he){ 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException; 
        }finally { 
            sesion.close(); 
        }  

        return telefono; 
    }
    
    
    public List<Telefono> obtenerTelefonos() throws ExceptionPGF{
    	String myquery =  "select * from Telefono;" ;
        try{
        	LinkedList<Telefono> telefonos = new LinkedList<Telefono>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Telefono telefono = new Telefono();
        		
        		telefono.setId( new TelefonoId( Integer.valueOf(row[0].toString()),Integer.valueOf(row[3].toString()) ) );
        		telefono.setNumTelefono(Integer.valueOf(row[1].toString()));
        		telefono.setTipoTelefono(row[2].toString());
        		telefonos.add(telefono);
        	}
        	if(telefonos.size() > 0){
        		return telefonos;
        	}
        	return null;  
        }catch(Exception e){ 
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public LinkedList<Telefono> obtenerTelefonosPorUsuario(int idUsuario, tipo_Telefono tipo) throws ExceptionPGF{
    	String myquery = "select * from Telefono where Usuario_idUsuario ="+idUsuario+" and TipoTelefono =\""+ tipo +"\"; ";
        try{
        	LinkedList<Telefono> telefonos = new LinkedList<Telefono>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Telefono telefono = new Telefono();
        		
        		telefono.setId( new TelefonoId( Integer.valueOf(row[0].toString()),Integer.valueOf(row[3].toString()) ) );
        		telefono.setNumTelefono(Integer.valueOf(row[1].toString()));
        		telefono.setTipoTelefono(row[2].toString());
        		telefonos.add(telefono);
        	}
        	if(telefonos.size() > 0){
        		return telefonos;
        	}
        	return null;  
        }catch(Exception e){ 
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public TelefonoId guardarTelefono(Telefono telefono) throws ExceptionPGF 
    { 
        TelefonoId id;  

        try 
        { 
            iniciaOperacion(); 
            id = (TelefonoId) sesion.save(telefono); 
            tx.commit(); 
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	 
        }finally { 
            sesion.close(); 
        } 

        return id; 
    }
    public void eliminarTelefono(Telefono telefono) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(telefono); 
            tx.commit(); 
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	 
        }finally { 
            sesion.close(); 
        } 
    }
    
    public void actualizarTelefono(Telefono telefono) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(telefono); 
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
        //throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 


}
