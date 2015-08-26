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


public class ContratistaDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Contratista obtenerContratista(int id) throws ExceptionPGF 
    { 
    	Contratista cliente = null;  
        try 
        { 
            iniciaOperacion(); 
            cliente = (Contratista)sesion.get(Contratista.class, id);
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        }finally { 
            sesion.close(); 
        }  

        return cliente; 
    }
    
    
    public List<Contratista> obtenerContratistas() throws ExceptionPGF{
    	String myquery = "select * from Contratista;" ;
        try{
        	LinkedList<Contratista> contratistas = new LinkedList<Contratista>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Contratista contratista = new Contratista();
        		contratista.setIdContratista(Integer.valueOf(row[0].toString()));
        		contratista.setIdUsuario(Integer.valueOf(row[1].toString()));
        		contratistas.add(contratista);
        	}
        	if(contratistas.size() > 0){
        		return contratistas;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        }
    }
    
    public Contratista obtenerContratista(Usuario usuario) throws ExceptionPGF{
    	String myquery = "select * from Contratista where idUsuario = "+ usuario.getIdUsuario() + ";" ;
        try{
        	LinkedList<Contratista> contratistas = new LinkedList<Contratista>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Contratista contratista = new Contratista();
        		contratista.setIdContratista(Integer.valueOf(row[0].toString()));
        		contratista.setIdUsuario(Integer.valueOf(row[1].toString()));
        		contratistas.add(contratista);
        	}
        	if(contratistas.size() > 0){
        		return contratistas.get(0);
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        }
    }
    
    public int guardarContratista(Contratista contratista) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Integer) sesion.save(contratista); 
            tx.commit(); 
        } catch (HibernateException he)  { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        } finally { 
            sesion.close(); 
        }  

        return id; 
    }
    
    public void actualizarContratista(Contratista contratista) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(contratista); 
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
    
    private void manejaExcepcion(Exception he){
        tx.rollback();  
    }  


}