package ixpan.pgf.hibernate;

import java.util.LinkedList;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.RUsuarioActividad;

public class SaveUtils {
	
	private Session sesion;
	private Transaction tx;  
    public static enum operaciones{save, delete, desactualizar}
    public LinkedList<Object> objetos;
    public LinkedList<Class<?>> clases;
    public LinkedList<operaciones> tipoOperacion;

    
	
	public void addoperacion(operaciones operacion, Object obj, Class<?> cls){
		if(tipoOperacion==null){
			objetos = new LinkedList<Object>();
			clases = new LinkedList<Class<?>>();
			tipoOperacion = new LinkedList<operaciones>();
		}	
		
		objetos.add(obj);
		clases.add(cls);
		tipoOperacion.add(operacion);
		System.out.println("Guardando rollBack con:"+cls);
		
	}
	
	public void ejecutarOperacion()throws ExceptionPGF{
		try{
			RUsuarioActividad actividad;
			for( int i = 0; i < objetos.size(); i++){
				System.out.println("Ejecutando rollBack con:"+clases.get(i));
				iniciaOperacion();
				switch(tipoOperacion.get(i)){
				case save :
					sesion.save(clases.get(i).cast(objetos.get(i)) );
				break;
				case delete:
					sesion.delete(clases.get(i).cast(objetos.get(i)) );
					break;
				case desactualizar:
					sesion.update(clases.get(i).cast(objetos.get(i)) );
					break;
				}
				tx.commit();
			}
			
			
		}catch(HibernateException he){
			manejaExcepcion(he);
			ExceptionPGF myException = new ExceptionPGF("Error al hacer rollBack", ExceptionPGF.Errores.Error_RollBack);
			throw myException;
		}finally{
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
