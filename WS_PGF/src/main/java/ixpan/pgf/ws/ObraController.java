package ixpan.pgf.ws;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.json.JsonObject;

import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ixpan.pgf.delegate.ObraDelegateImpl;
import ixpan.pgf.delegate.UsuariosDelegateImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.service.UsuarioService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ObraController {
	
	private static final Logger logger = LoggerFactory.getLogger(ObraController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/obras/nueva", method=RequestMethod.POST, headers = "Accept=application/json", produces={"application/json"})
    @ResponseBody
    public ResponseEntity registrarObra(Locale locale, Model model,
    		@RequestBody String request) throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException {
		
		
		ObraDelegateImpl od = new ObraDelegateImpl();
		String json;
		String type="";
		try {
			JsonObject jsonRequest = MiJson.cargarObjecto(request);
			String sessionToken = jsonRequest.getString("sessionToken");
			/*Vaidacion del SessionToken*/	
			od.altaObra(request);
			
			
			/*
			 * Codigo de operacion
			 * */
		} catch (ExceptionPGF e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
  	  
  	  return new ResponseEntity<String>("",HttpStatus.OK);
  	
    }
	
}
