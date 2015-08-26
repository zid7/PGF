package ixpan.pgf.ws;

import java.io.IOException;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ixpan.pgf.dao.*;
import ixpan.pgf.model.*;
import ixpan.pgf.service.UsuarioService;
import ixpan.pgf.daoPrincipal.*;
import ixpan.pgf.delegate.UsuariosDelegateImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.helpers.JsonAObjetoHelpers;
import ixpan.pgf.helpers.UsuarioHelper;
import ixpan.pgf.jsonutilities.MiJson;



@Controller
public class UsuariosWs {
	
	
	@RequestMapping(value="/usuarios", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity obtenerUsuarios(Locale locale, Model model,
    		@RequestBody String request) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
		
		
		UsuariosDelegateImpl ud = new UsuariosDelegateImpl();
		String json;
		String type="";
		try {
			JsonObject jsonRequest = MiJson.cargarObjecto(request);
			
			try{
				String sessionToken = jsonRequest.getString("sessionToken");
				String sort = jsonRequest.getString("sort");
				type = jsonRequest.getString("type");
			}catch(Exception e){
				try{
					String idUsuario = jsonRequest.getString("idUsuario");
					UsuarioService us = new UsuarioService();
					try {
						System.out.println(idUsuario);
						json = us.obtenerUsuariosPorId(idUsuario);
					} catch (ExceptionPGF eee) {
						return new ResponseEntity<String>(eee.getMessage(),HttpStatus.BAD_REQUEST);
					}
			  	  
			  	  return new ResponseEntity<String>(json,HttpStatus.OK);
				}catch(Exception ee){
					return new ResponseEntity<String>("Parametros mal formados",HttpStatus.BAD_REQUEST);
				}
			}
			json = ud.obtenerUsuarios(type);
		} catch (ExceptionPGF e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>(json,HttpStatus.OK);
  	
    }
	
	
	
	
	
	@RequestMapping(value="/usuarios/idUsuario", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity obtenerUsuariosPorId(Locale locale, Model model,@RequestBody String idUsuario) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
  	  	
		UsuarioService us = new UsuarioService();
		String json;
		try {
			System.out.println(idUsuario);
			json = us.obtenerUsuariosPorId(idUsuario);
		} catch (ExceptionPGF e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>(json,HttpStatus.OK);
  	
    }
	
	@RequestMapping(value="/usuarios/nuevo", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity registrarNuevoUsuario(Locale locale, Model model,@RequestBody String request) throws ExceptionPGF {
		UsuarioService us = new UsuarioService();
		UsuariosDelegateImpl ud = new UsuariosDelegateImpl();
		String json;
		try {
			json = ud.registrarUusario(request);
		} catch (ExceptionPGF e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>(json,HttpStatus.OK);
  	
    }
	
	@RequestMapping(value="/usuarios/idUsuario/editar", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity actualizarUsuario(Locale locale, Model model,@RequestParam("nuevoUsuario") String nuevoUsuario) throws ExceptionPGF {

		UsuarioService us = new UsuarioService();
		String json;
		UsuariosDelegateImpl ud = new UsuariosDelegateImpl();
		try {
			ud.actualizarUsuario(nuevoUsuario);
		} catch (ExceptionPGF e) {
			e.printStackTrace();
			return new ResponseEntity<String>("No se pudo actualizar la informacion del Usuario :" + e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>("",HttpStatus.OK);
  	
    }
	
	@RequestMapping(value="/usuarios/idUsuario/eliminar", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity eliminarUsuario(Locale locale, Model model,@RequestBody String idUsuario) throws ExceptionPGF {

		UsuarioService us = new UsuarioService();
		String json;
		try {
			us.eliminarUsuario(idUsuario);
		} catch (ExceptionPGF e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>("OK",HttpStatus.OK);
  	
    }

}
