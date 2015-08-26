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


public class BancoDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Banco obtenerBanco(int id) throws ExceptionPGF 
    { 
    	Banco banco = null;  
        try 
        { 
            iniciaOperacion(); 
            banco = (Banco)sesion.get(Banco.class, id);
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        }finally { 
            sesion.close(); 
        }  

        return banco; 
    }
    
    
    public List<Banco> obtenerBancos() throws ExceptionPGF{
    	String myquery = "select * from Banco;";
    	try{
        	LinkedList<Banco> bancos = new LinkedList<Banco>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Banco banco = new Banco();
        		banco.setIdBanco(Integer.valueOf(row[0].toString()));
        		banco.setNombre(row[0].toString());
        		bancos.add(banco);
        	}
        	if(bancos.size() > 0){
        		return bancos;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        	
        	
        }
    }
    
    public Banco obtenerBancosPorNombre(String nombre) throws ExceptionPGF{
    	String myquery = "select * from Banco where Nombre ='" + nombre + "';";
    	try{
        	LinkedList<Banco> bancos = new LinkedList<Banco>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Banco banco = new Banco();
        		banco.setIdBanco(Integer.valueOf(row[0].toString()));
        		banco.setNombre(row[0].toString());
        		bancos.add(banco);
        	}
        	if(bancos.size() > 0){
        		return bancos.get(0);
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        	
        	
        }
    }
    
    
    
    public long guardarBanco(Banco banco) throws ExceptionPGF 
    { 
        long id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Long) sesion.save(banco); 
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
    
    public void actualizarBanco(Banco rol) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(rol); 
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
    
    private void manejaExcepcion(Exception he){
        tx.rollback();  
    } 
}
