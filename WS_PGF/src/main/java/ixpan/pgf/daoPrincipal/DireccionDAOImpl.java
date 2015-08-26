package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ixpan.pgf.model.*;
import ixpan.pgf.daoPrincipal.TelefonoDAOImpl.tipo_Telefono;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.*;


public class DireccionDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
    public static enum tipo_Direccion{personal,fiscal};
    
    
    public Direccion obtenerDireccion(int id) throws ExceptionPGF 
    { 
    	Direccion direccion = null;  
        try 
        { 
            iniciaOperacion(); 
            direccion = (Direccion)sesion.get(Direccion.class, id);
            
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
        return direccion; 
    }
    
    
    public LinkedList<Direccion> obtenerDireccionesPorUsuario(int idUsuario, tipo_Direccion tipo) throws ExceptionPGF{
    	String myquery = "SELECT * from Direccion where Usuario_idUsuario = "+idUsuario+" and C_Tipo_Direccion_idC_Tipo_Direccion = '"+tipo.ordinal()+1+"';";
        try{
        	LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
        	iniciaOperacion();
        	
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Direccion direccion = new Direccion();
        		//direccion.setId( new DireccionId(Integer.valueOf(row[0].toString()), Integer.valueOf(row[9].toString()) ,idUsuario ));
        		direccion.setIdDireccion(Integer.valueOf(row[0].toString()));
        		direccion.setCalle(row[1].toString());
        		direccion.setColonia(row[2].toString());
        		direccion.setNumExt(row[3].toString());
        		direccion.setNumInt(row[4].toString());
        		direccion.setDelegacion(row[5].toString());   
        		direccion.setEstado(row[6].toString());
        		direccion.setCp(row[7].toString());
        		direccion.setPais(row[8].toString());
        		direccion.setCTipoDireccionIdCTipoDireccion(Integer.valueOf(row[9].toString()));
        		//direccion.setsetUsuarioIdUsuario(Integer.valueOf(row[10].toString()));
        		direcciones.add(direccion);
        	
        	}
        	if(direcciones.size() > 0){
        		return direcciones;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        } 
    }
    
    public int guardarDireccion(Direccion direccion) throws ExceptionPGF 
    { 
        int id;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(direccion);
            tx.commit();
        } catch (HibernateException he)  { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	  
        }finally { 
            sesion.close(); 
        }  

        return id; 
    }
    public void eliminarDireccion(Direccion direccion) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(direccion); 
            tx.commit(); 
        } catch (HibernateException he)  { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	  
        }finally { 
            sesion.close(); 
        }  
    }
    
    public void actualizarDireccion(Direccion direccion) throws ExceptionPGF 
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