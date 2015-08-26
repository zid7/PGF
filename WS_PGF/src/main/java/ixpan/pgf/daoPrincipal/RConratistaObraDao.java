package ixpan.pgf.daoPrincipal;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ixpan.pgf.model.*;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.*;


public class RConratistaObraDao {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public RContratistaObra obtenerRContratistaObra(RContratistaObraId id) throws ExceptionPGF 
    { 
    	RContratistaObra rContratistaObra = null;  
        try 
        { 
            iniciaOperacion(); 
            rContratistaObra = (RContratistaObra)sesion.get(RContratistaObra.class, id);
            
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	
        }finally { 
            sesion.close(); 
        }

        return rContratistaObra; 
    }
    
    /*public List<RContratistaObra> obtenerRContratistaObraPorContratista(int idContratista) throws ExceptionPGF{
        try{
        	List<RContratistaObra> rContratistaObras = null;
        	iniciaOperacion();
        	DetachedCriteria criteria = DetachedCriteria.forClass(RContratistaObra.class);
        	criteria.add(Restrictions.eq("Contratista_idContratista", idContratista));
        	rContratistaObras = (List<RContratistaObra>)criteria.getExecutableCriteria(sesion).list();
        	tx.commit();
        	sesion.close();
        	return rContratistaObras;
        }catch(Exception e){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }*/
    
    
    public List<RContratistaObra> obtenerRContratistaObras() throws ExceptionPGF{
    	String myquery = "select * from RContratistaObra;";
        try{
        	LinkedList<RContratistaObra> rContratistaObras = new LinkedList<RContratistaObra>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		RContratistaObra rContratistaObra = new RContratistaObra();
        		rContratistaObra.setId(new RContratistaObraId(  Integer.valueOf(row[0].toString()), Integer.valueOf(row[1].toString()) , Integer.valueOf(row[2].toString()) ));
        		rContratistaObras.add(rContratistaObra);
        	}
        	if(rContratistaObras.size() > 0){
        		return rContratistaObras;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery +" :: - >"+e.getMessage(),ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public RContratistaObraId guardarRContratistaObra(RContratistaObra rContratistaObra) throws ExceptionPGF 
    { 
    	RContratistaObraId id;  

        try 
        { 
            iniciaOperacion(); 
            id = (RContratistaObraId) sesion.save(rContratistaObra); 
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
    
    public void actualizarRContratistaObra(RContratistaObra rContratistaObra) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(rContratistaObra); 
            tx.commit(); 
        } catch (HibernateException he)  { 
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
