package ixpan.pgf.service;

import java.util.LinkedList;

import ixpan.pgf.daoPrincipal.ContratistaDAOImpl;
import ixpan.pgf.daoPrincipal.RUsuarioActividadDAOImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.model.Contratista;
import ixpan.pgf.model.RUsuarioActividad;
import ixpan.pgf.model.RUsuarioActividadId;
import ixpan.pgf.model.Usuario;

public class ContratistaServiceImpl implements ContratistaService {

	SaveUtils saveUtils;
	
	public int registrarNuevoContratista(Usuario usuario, Contratista contratista, int [] actividades) throws ExceptionPGF {
		saveUtils = new SaveUtils();
		
		ContratistaDAOImpl contratistaDao = new ContratistaDAOImpl();
		RUsuarioActividadDAOImpl ractividadDao = new RUsuarioActividadDAOImpl(); 
		contratista.setIdUsuario(usuario.getIdUsuario());
		contratista.setIdContratista(contratistaDao.guardarContratista(contratista));
		saveUtils.addoperacion(SaveUtils.operaciones.delete, contratista, contratista.getClass() );
		
		System.out.println("Actividades:"+actividades.length);
		for (int i = 0; i < actividades.length; i++) {
			
			RUsuarioActividad ractividad = new RUsuarioActividad();
			RUsuarioActividadId ractividadid = new RUsuarioActividadId();
			
			ractividadid.setIdActividad(actividades[i]);
			ractividadid.setIdContratista(contratista.getIdContratista());
			
			ractividad.setId(ractividadid);
			
			ractividadDao.guardarRUsuarioActividad(ractividad);
			saveUtils.addoperacion(SaveUtils.operaciones.delete, ractividad, ractividad.getClass());
		}
		
		return 0;
	}
	
	public int actualizarContratista(Usuario usuario, Contratista contratista, int [] actividades) throws ExceptionPGF {
		Contratista contratistaAux;
		saveUtils = new SaveUtils();
		LinkedList<RUsuarioActividad> actividadesOriginales;
		
		ContratistaDAOImpl contratistaDao = new ContratistaDAOImpl();
		RUsuarioActividadDAOImpl ractividadDao = new RUsuarioActividadDAOImpl();
		
		
		contratistaAux = contratistaDao.obtenerContratista(usuario);
		 
		contratista.setIdUsuario(usuario.getIdUsuario());
		contratista.setIdContratista(contratistaAux.getIdContratista());
		
		contratistaDao.actualizarContratista(contratista);
		saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, contratistaAux, contratistaAux.getClass() );
		actividadesOriginales = ractividadDao.obtenerActividadesPorUsuario(usuario.getIdUsuario());
		
		if(actividadesOriginales!=null)
		     for(int i = 0; i< actividadesOriginales.size() ; i++){
		    	 System.out.println("Eliminando: \nUsuario->"+actividadesOriginales.get(i).getId().getIdContratista()+"\nActividad->"+actividadesOriginales.get(i).getId().getIdActividad());
		    	 ractividadDao.eliminarRUsuarioActividad(actividadesOriginales.get(i));
		    	 saveUtils.addoperacion(SaveUtils.operaciones.save, actividadesOriginales.get(i), actividadesOriginales.get(i).getClass() );
		     }
		
		
		for (int i = 0; i < actividades.length; i++) {
			RUsuarioActividad ractividad = new RUsuarioActividad();
			RUsuarioActividadId ractividadid = new RUsuarioActividadId();
			
			ractividadid.setIdActividad(actividades[i]);
			ractividadid.setIdContratista(contratista.getIdContratista());
			
			ractividad.setId(ractividadid);
			
			ractividadDao.guardarRUsuarioActividad(ractividad);
			saveUtils.addoperacion(SaveUtils.operaciones.delete, ractividad, ractividad.getClass());
		}
		
		return 0;
	}

	public SaveUtils getSaveUtils() {
		return saveUtils;
	}

}
