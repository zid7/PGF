package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ixpan.pgf.model.*;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.*;


public class ClienteDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Cliente obtenerCliente(int id) throws ExceptionPGF 
    { 
    	Cliente cliente = null;  
        try 
        { 
            iniciaOperacion(); 
            cliente = (Cliente)sesion.get(Cliente.class, id);
            
        } catch(HibernateException he){
        	
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        } 
        finally { 
            sesion.close(); 
        }  

        return cliente; 
    }
    
    
    
    
    public List<Cliente> obtenerClientes() throws ExceptionPGF{
    	String myquery = "select * from Cliente;";
        try{
        	LinkedList<Cliente> clientes = new LinkedList<Cliente>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Cliente cliente = new Cliente();
        	    cliente.setIdCliente(Integer.valueOf(row[0].toString()));
        	    cliente.setIdUsuario(Integer.valueOf(row[1].toString()));
        	    cliente.setClase(row[2].toString());
        	    cliente.setSubcliente(row[3].toString());
        	    clientes.add(cliente);
        	}
        	if(clientes.size() > 0){
        		return clientes;
        	}
        	return null;
        	
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        }
    }
    
    public Cliente obtenerClientes(Usuario u) throws ExceptionPGF{
    	String myquery = "select * from Cliente;";
        try{
        	LinkedList<Cliente> clientes = new LinkedList<Cliente>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        	    Cliente cliente = new Cliente();
        	    cliente.setIdCliente(Integer.valueOf(row[0].toString()));
        	    cliente.setIdUsuario(Integer.valueOf(row[1].toString()));
        	    cliente.setClase(row[2].toString());
        	    cliente.setSubcliente(row[3].toString());
        	    clientes.add(cliente);
        	}
        	if(clientes.size() > 0){
        		return clientes.get(0);
        	}
        	return null;
        	
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery + " : " + e,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;
        }
    }
    
    public int guardarCliente(Cliente cliente) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (Integer) sesion.save(cliente); 
            tx.commit(); 
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        } finally{ 
            sesion.close(); 
        }  

        return id; 
    }
    
    public void actualizarCliente(Cliente cliente) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(cliente); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        } finally{ 
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