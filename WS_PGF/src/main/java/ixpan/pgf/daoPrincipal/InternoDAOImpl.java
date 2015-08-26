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


public class InternoDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public Interno obtenerInterno(int id) throws ExceptionPGF 
    { 
    	Interno interno = null;  
        try 
        { 
            iniciaOperacion(); 
            interno = (Interno)sesion.get(Interno.class, id);
            
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException; 
        }finally { 
            sesion.close(); 
        }  

        return interno; 
    }
    
    
    public List<Interno> obtenerInternos() throws ExceptionPGF{
    	String myquery =  "select * from Interno;";
        try{
        	LinkedList<Interno> internos = new LinkedList<Interno>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Interno interno = new Interno();
        		interno.setIdInternos(Integer.valueOf(row[0].toString()));
        		interno.setIdUsuario(Integer.valueOf(row[1].toString()));
        		internos.add(interno);
        	}
        	if(internos.size() > 0){
        		return internos;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        } 
    }
    
    public Interno obtenerInternos(Usuario usuario) throws ExceptionPGF{
    	String myquery =  "select * from Interno where idUsuario = "+usuario.getIdUsuario() + ";";
        try{
        	LinkedList<Interno> internos = new LinkedList<Interno>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		Interno interno = new Interno();
        		interno.setIdInternos(Integer.valueOf(row[0].toString()));
        		interno.setIdUsuario(Integer.valueOf(row[1].toString()));
        		internos.add(interno);
        	}
        	if(internos.size() > 0){
        		return internos.get(0);
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        } 
    }
    
    public int guardarInterno(Interno interno) throws ExceptionPGF 
    { 
        int id = 0;  

        try 
        { 
            iniciaOperacion(); 
            System.out.println("Interno idUsuario:"+interno.getIdUsuario());
            
            id = (Integer) sesion.save(interno); 
            tx.commit(); 
        } catch(Exception he){
        	//System.out.println("Entro--->>>>>>>>\n\n\n\n\n\n");
        	//manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF("No se pudo guardar el usuario como Interno : ",ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	 
        }finally { 
            sesion.close(); 
        }  

        return id; 
    }
    
    public void actualizarInterno(Interno interno) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(interno); 
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