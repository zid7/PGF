package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import ixpan.pgf.model.*;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.*;


public class ObraDAOImpl{
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Obra obtenerObra(int id) throws ExceptionPGF 
    { 
    	Obra obra = null;  
        try 
        { 
            iniciaOperacion(); 
            obra = (Obra)sesion.get(Obra.class, id);
            
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        }finally { 
            sesion.close(); 
        } 

        return obra; 
    }
    
    
    public List<Obra> obtenerObras() throws ExceptionPGF{
    	String myquery =  "select * from Obra;" ;
        try{
        	LinkedList<Obra> obras = new LinkedList<Obra>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Obra obra = new Obra();
        		obra.setIdObra(Integer.valueOf(row[0].toString()));
        		obra.setClienteIdCliente(Integer.valueOf(row[1].toString()));
        		obra.setStatus(row[2].toString());
        		obra.setNombre(row[3].toString());
        		obras.add(obra);
        	}
        	if(obras.size() > 0){
        		return obras;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public List<Obra> obtenerObrasPorClienete(int idCliente) throws ExceptionPGF{
        String myquery = "select * from Obra where Cliente_idCliente ="+ idCliente+" ;" ;
    	try{
        	LinkedList<Obra> obras = new LinkedList<Obra>();
        	iniciaOperacion();
        	
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Obra obra = new Obra();
        		obra.setIdObra(Integer.valueOf(row[0].toString()));
        		obra.setClienteIdCliente(Integer.valueOf(row[1].toString()));
        		obra.setStatus(row[2].toString());
        		obra.setNombre(row[3].toString());
        		obras.add(obra);
        	}
        	
        	if(obras.size() > 0){
        		return obras;
        	}
        	return null;
        	
        	
        	
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public List<Obra> obtenerObrasPorContratista(int idContratista) throws ExceptionPGF{
    	String myquery = "SELECT idObra , Cliente_idCliente , Status , Nombre from Obra O, R_Contratista_Obra RCO where O.idObra= RCO.Obra_idObra and RCO.Contratista_idContratista="+idContratista+";" ;
        try{
        	LinkedList<Obra> obras = new LinkedList<Obra>();
        	iniciaOperacion();
        	
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Obra obra = new Obra();
        		obra.setIdObra(Integer.valueOf(row[0].toString()));
        		obra.setClienteIdCliente(Integer.valueOf(row[1].toString()));
        		obra.setStatus(row[2].toString());
        		obra.setNombre(row[3].toString());
        		obras.add(obra);
        	}
        	if(obras.size() > 0){
        		return obras;
        	}
        	return null;
        	
        }catch(Exception e){
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public List<Obra> obtenerObrasPorInterno(int idInterno) throws ExceptionPGF{
    	String myquery = "SELECT Obra.* from Obra, R_Internos_Obra  where R_Internos_Obra.Obra_idObra = Obra.idObra and R_Internos_Obra.Internos_idInternos="+idInterno+";" ;
        try{
        	LinkedList<Obra> obras = new LinkedList<Obra>();
        	iniciaOperacion();
        	
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Obra obra = new Obra();
        		obra.setIdObra(Integer.valueOf(row[0].toString()));
        		obra.setClienteIdCliente(Integer.valueOf(row[1].toString()));
        		obra.setStatus(row[2].toString());
        		obra.setNombre(row[3].toString());
        		obras.add(obra);
        	}
        	if(obras.size() > 0){
        		return obras;
        	}
        	return null;
        	
        }catch(Exception e){
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
   
    
    
    public int guardarObra(Obra obra) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            id = (int) sesion.save(obra); 
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
    
    public void actualizarObra(Obra obra) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(obra); 
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
