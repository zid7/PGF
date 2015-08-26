package ixpan.pgf.ws;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Locale;

import javax.json.JsonObject;

import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ixpan.pgf.daoPrincipal.ActividadDAOImpl;
import ixpan.pgf.daoPrincipal.UsuarioDAOImp;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.helpers.JsonAObjetoHelpers;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.model.CActividad;
import ixpan.pgf.model.Usuario;
import ixpan.pgf.service.UsuarioService;


@Controller
public class ActividadesWS {
	
	@RequestMapping(value="/actividades", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity obtenerActividades(Locale locale, Model model) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
		ActividadDAOImpl actividadDao = new ActividadDAOImpl();
		StringBuilder json = new StringBuilder ("{\"actividades\":");
		LinkedList<CActividad> listaActividades;
		try {
			listaActividades = actividadDao.obtenerActividades();
			if(listaActividades== null)
				return new ResponseEntity<String>(json.append("null}").toString(),HttpStatus.BAD_REQUEST);
			json.append("[");
			for(int i = 0 ; i < listaActividades.size(); i++ ){
				CActividad actividad = listaActividades.get(i);
				json.append("{\"idActividad\":\""+actividad.getIdCActividad()+"\",");
				json.append("\"nombre\":\""+actividad.getNombre()+"\"},");
			}
			json.deleteCharAt(json.length()-1);
			json.append("]}");
		} catch (ExceptionPGF e) {
			System.out.println("Error:" + e.getMessage());
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>(json.toString(),HttpStatus.OK);
  	
    }
	
	@RequestMapping(value="/actividades/eliminar", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity eliminarActividad(Locale locale, Model model,@RequestBody String idActividad) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
		ActividadDAOImpl actividadDao = new ActividadDAOImpl();
		CActividad actividad = new CActividad();
		CActividad actividadAux = new CActividad();
		try{
			JsonObject actividadObject = MiJson.cargarObjecto(idActividad);
			actividad.setIdCActividad((Integer)JsonAObjetoHelpers.jsonAObjeto(actividadObject.get("idActividad"), Integer.class));
			actividad.setNombre((String)JsonAObjetoHelpers.jsonAObjeto(actividadObject.get("Nombre"), String.class));
			actividadDao.eliminarActividad(actividad);
		 	
		 }catch(Exception e){
			 ExceptionPGF myexception = new ExceptionPGF("No se ha podido elimar la Actividad con ID : "+actividad.getIdCActividad() , ExceptionPGF.Errores.Guardar_Entidad);
	    	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		 }
		 return new ResponseEntity<String>("Se elimino la Actividad con ID: "+ idActividad,HttpStatus.OK);
  	
    }
	
	@RequestMapping(value="/actividades/nuevo", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity obtenerActividadesss(Locale locale, Model model, @RequestBody String Actividad) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
		ActividadDAOImpl actividadDao = new ActividadDAOImpl();
		CActividad actividad = new CActividad();
		String json=" ";
		try{
			JsonObject actividadObject = MiJson.cargarObjecto(Actividad);
			actividad.setIdCActividad((Integer)JsonAObjetoHelpers.jsonAObjeto(actividadObject.get("idActividad"), Integer.class));
			actividad.setNombre((String)JsonAObjetoHelpers.jsonAObjeto(actividadObject.get("Nombre"), String.class));
			int i = (int)actividadDao.guardarActividad(actividad);
		 	json= "{\"idActividad\":\""+ i+"\", \"Nombre\":\""+actividad.getNombre()+"\"}";
		 }catch(Exception e){
			 e.printStackTrace();
			 ExceptionPGF myexception = new ExceptionPGF("No se ha podido Guardar: "+actividad.getIdCActividad() , ExceptionPGF.Errores.Guardar_Entidad);
	    	 return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		 }
		 return new ResponseEntity<String>(json,HttpStatus.OK);
  	
    }
}