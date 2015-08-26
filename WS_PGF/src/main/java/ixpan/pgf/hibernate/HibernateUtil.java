package ixpan.pgf.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory; 
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{  
    private static final SessionFactory sessionFactory;   

    static 
    { 
        try 
        { 
        	Configuration configuration = new Configuration(); 
        	//configuration.configure("src/hibernate.cfg.xml");
        	
            //sessionFactory = new Configuration().configure().buildSessionFactory(); 
        	//sessionFactory = configuration.configure().buildSessionFactory(); 
        	sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (HibernateException he) 
        { 
           System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he+"<--"); 
           throw new ExceptionInInitializerError(he); 
        } 
    }  

    public static SessionFactory getSessionFactory() 
    { 
        return sessionFactory; 
    }
    
}