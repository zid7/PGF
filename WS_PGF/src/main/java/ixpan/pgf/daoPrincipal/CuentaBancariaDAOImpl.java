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


public class CuentaBancariaDAOImpl {
	
	private Session sesion;
	
    private Transaction tx;  
    
    private static Logger log = Logger.getLogger(UsuarioDAOImp.class);
    
 
    
    
    public CuentaBancaria obtenerCuentaBancaria(int id) throws ExceptionPGF 
    { 
    	CuentaBancaria cuentaBancaria = null;  
        try 
        { 
            iniciaOperacion(); 
            cuentaBancaria = (CuentaBancaria)sesion.get(CuentaBancaria.class, id);
            
            
        } catch(HibernateException he){
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;
        	 
        }finally { 
            sesion.close(); 
        }  

        return cuentaBancaria; 
    }
    
    
    public List<CuentaBancaria> obtenerCuentaBancarias() throws ExceptionPGF{
    	String myquery = "select * from Cuenta_Bancaria;" ;
        try{
        	LinkedList<CuentaBancaria> cuentaBancarias = new LinkedList<CuentaBancaria>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CuentaBancaria cuentaBancaria = new CuentaBancaria();
        		cuentaBancaria.setId(new CuentaBancariaId(Integer.valueOf(row[0].toString()),Integer.valueOf(row[1].toString()),Integer.valueOf(row[4].toString())));
        		cuentaBancaria.setNumeroCuenta(row[2].toString());
        		cuentaBancaria.setClabeInterbancaria(row[3].toString());
        		cuentaBancaria.setSucursal(row[4].toString());
        		cuentaBancarias.add(cuentaBancaria);
        	}
        	if(cuentaBancarias.size() > 0){
        		return cuentaBancarias;
        	}
        	return null;  
        }catch(Exception e){
        	
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public LinkedList<CuentaBancaria> obtenerCuentaBancariasPorUsuario(int idDireccion, int tipo) throws ExceptionPGF{
        String myquery = "select * from Cuenta_Bancaria where Direccion_idDireccion=" + idDireccion +";";
    	try{
        	LinkedList<CuentaBancaria> cuentaBancarias = new LinkedList<CuentaBancaria>();
        	iniciaOperacion();
        	SQLQuery query = sesion.createSQLQuery(myquery);
        	List<Object[]> rows = query.list();
        	for(Object[] row : rows){
        		CuentaBancaria cuentaBancaria = new CuentaBancaria();
        		cuentaBancaria.setId(new CuentaBancariaId(Integer.valueOf(row[0].toString()),Integer.valueOf(row[1].toString()),Integer.valueOf(row[5].toString())));
        		cuentaBancaria.setNumeroCuenta(row[2].toString());
        		cuentaBancaria.setClabeInterbancaria(row[3].toString());
        		cuentaBancaria.setSucursal(row[4].toString());
        		cuentaBancaria.setTitular(row[6].toString());
        		cuentaBancarias.add(cuentaBancaria);
        	}
        	if(cuentaBancarias.size() > 0){
        		return cuentaBancarias;
        	}
        	return null;  
        }catch(Exception e){
        	ExceptionPGF myException = new ExceptionPGF("Error al ejecutar: " + myquery ,ExceptionPGF.Errores.Error_Acceso_BD);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public CuentaBancariaId guardarCuentaBancaria(CuentaBancaria cuentaBancaria) throws ExceptionPGF 
    { 
    	CuentaBancariaId id;  

        try 
        { 
            iniciaOperacion(); 
            id = (CuentaBancariaId) sesion.save(cuentaBancaria); 
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
    
    public void eleiminarCuentaBancaria(CuentaBancaria cuentaBancaria) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.delete(cuentaBancaria); 
            tx.commit(); 
        } catch (HibernateException he) { 
        	manejaExcepcion(he); 
            ExceptionPGF myException = new ExceptionPGF(he.getMessage(),ExceptionPGF.Errores.Error_Hibernate);
        	throw myException;  
        }finally { 
            sesion.close(); 
        }
    }
    
    public void actualizarCuentaBancaria(CuentaBancaria cuentaBancaria) throws ExceptionPGF 
    { 
        try 
        { 
            iniciaOperacion(); 
            sesion.update(cuentaBancaria); 
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
