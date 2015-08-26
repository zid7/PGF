package ixpan.pgf.helpers;

import java.util.Date;
import java.util.LinkedList;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import ixpan.pgf.daoPrincipal.BancoDAOImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.model.Banco;
import ixpan.pgf.model.CuentaBancaria;
import ixpan.pgf.model.CuentaBancariaId;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Obra;
import ixpan.pgf.model.RContratistaObra;
import ixpan.pgf.model.RContratistaObraId;
import ixpan.pgf.model.RUsuarioActividad;
import ixpan.pgf.model.UnidadNegocio;
import ixpan.pgf.model.Usuario;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Contratista;

public class JsonAObjetoHelpers {
	
	JsonObject jsonObjeto;
	
	
	public JsonObject getJsonObjeto() {
		return jsonObjeto;
	}

	public void setJsonObjeto(JsonObject jsonObjeto) {
		this.jsonObjeto = jsonObjeto;
	}

	public static Direccion jsonADireccion(JsonValue jsonValue) throws ExceptionPGF{
		Direccion direccion = new Direccion();
		String parametro ="";
		if(jsonValue.equals(JsonValue.NULL)){
			return null;
		}else{
			JsonObject direccionObject = MiJson.cargarObjecto(jsonValue.toString());
			try{
				parametro="calle";
				direccion.setCalle((String)  jsonAObjeto(direccionObject.get("calle"),String.class));
				parametro="numExt";
				direccion.setNumExt((String)  jsonAObjeto(direccionObject.get("numExt"),String.class));
				parametro="numInt";
				direccion.setNumInt((String)  jsonAObjeto(direccionObject.get("numInt"),String.class));
				parametro="colonia";
				direccion.setColonia((String)  jsonAObjeto(direccionObject.get("colonia"),String.class));
				parametro="delegacion";
				direccion.setDelegacion((String)  jsonAObjeto(direccionObject.get("delegacion"),String.class));
				parametro="cp";
				direccion.setCp((String)  jsonAObjeto(direccionObject.get("cp"),String.class));
				return direccion;
			}catch(NullPointerException e){
				ExceptionPGF myException = new ExceptionPGF("Campo : "+ parametro + " no existe" , ExceptionPGF.Errores.Json_Mal_Formado);
				throw myException;
			}
		}
	}
	
	
	
	
	public static CuentaBancaria jsonACuentaBancaria(JsonValue jsonValue) throws ExceptionPGF{
		if(jsonValue.equals(JsonValue.NULL)){
			return null;
		}else{
			JsonObject cuentaObject = MiJson.cargarObjecto(jsonValue.toString());
			CuentaBancaria cuenta = new CuentaBancaria();
			BancoDAOImpl bancoDao = new BancoDAOImpl();
			Banco banco = bancoDao.obtenerBancosPorNombre(cuentaObject.getString("banco"));
		
			if(banco == null ){
				ExceptionPGF myException = new ExceptionPGF("Banco con el nombre " + cuentaObject.getString("banco") + " no existe en la Tabla Banco" ,ExceptionPGF.Errores.Entidad_No_Existente);
				throw myException;
			}			
		
			CuentaBancariaId id = new CuentaBancariaId();
			id.setBancoIdBanco(banco.getIdBanco());
			cuenta.setId(id);
			cuenta.setTitular( (String) jsonAObjeto(cuentaObject.get("nombreTitular"),String.class) );
			cuenta.setNumeroCuenta( (String) jsonAObjeto(cuentaObject.get("numCuenta"),String.class) );
			cuenta.setClabeInterbancaria( (String) jsonAObjeto(cuentaObject.get("clabe"),String.class) );
			cuenta.setSucursal( (String) jsonAObjeto(cuentaObject.get("sucursal"),String.class) );
			return cuenta;
		}
	}
	
	public static <T> Object jsonAObjeto(JsonValue jsonValue,Class<?> cls) throws ExceptionPGF{
		if(jsonValue.equals(JsonValue.NULL)){
			if(cls == Integer.class)
				return 0;
			return null;
			
		}else{
			String value = jsonValue.toString().substring(1, jsonValue.toString().length()-1);
			if( cls == String.class )
				return value;
			else if(cls == Integer.class)
				return Integer.valueOf(value);
			else if(cls == JsonArray.class){
				String aux = "{\"aux\":["+value+"]}";
				JsonObject auxObject = MiJson.cargarObjecto(aux);
				System.out.println((JsonArray) auxObject.getJsonArray("aux"));
				return (JsonArray) auxObject.getJsonArray("aux");
			}
			
		}
		return null;
	}
	
	public <T> Object jsonAObjeto(String parametro,Class<?> cls) throws ExceptionPGF{
		JsonValue jsonValue = jsonObjeto.get(parametro);
		System.out.println("Parametro: "+parametro+", Que se esta buscando en el jsonObject :" +  jsonObjeto.toString());
		
		if(jsonValue ==null){
			ExceptionPGF myException = new ExceptionPGF("Campo : "+ parametro + " no existe" , ExceptionPGF.Errores.Json_Mal_Formado);
			throw myException;
		}
			
		
		if(jsonValue.equals(JsonValue.NULL)){
			if(cls == Integer.class)
				return 0;
			if( cls == String.class )
				return " ";
			return null;
			
		}else{
			String value = jsonValue.toString().substring(1, jsonValue.toString().length()-1);
			if( cls == String.class )
				return value;
			else if(cls == Integer.class)
				return Integer.valueOf(value);
			else if(cls == JsonArray.class){
				System.out.println("\n\n\nEntro");
				String aux = "{\"aux\":["+value+"]}";
				JsonObject auxObject = MiJson.cargarObjecto(aux);
				System.out.println((JsonArray) auxObject.getJsonArray("aux"));
				return (JsonArray) auxObject.getJsonArray("aux");
			}else if(cls == JsonObject.class){
				System.out.println("Value"+ value.toString());
				JsonObject objectoaux = MiJson.cargarObjecto("{"+value+"}");
				return objectoaux;
				
			}
			
		}
		return null;
	}

	public Direccion jsonADireccion() throws ExceptionPGF {
		Direccion direccion = new Direccion();
		JsonAObjetoHelpers jsonHelper = this; 
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		
		
		
		jsonHelperAux.setJsonObjeto((JsonObject) jsonHelper.jsonAObjeto("direccion", JsonObject.class));
		
		direccion.setCalle((String) jsonHelperAux.jsonAObjeto("calle",String.class));
		direccion.setNumExt((String) jsonHelperAux.jsonAObjeto("numExt", String.class));
		direccion.setNumInt((String) jsonHelperAux.jsonAObjeto("numInt", String.class));
		direccion.setColonia((String) jsonHelperAux.jsonAObjeto("colonia", String.class));
		direccion.setDelegacion((String) jsonHelperAux.jsonAObjeto("delegacion", String.class));
		direccion.setCp((String) jsonHelperAux.jsonAObjeto("cp", String.class));
		return direccion;

	}
	
	
	public  Usuario jsonAUsuario() throws ExceptionPGF {
		Usuario usuario = new Usuario();
		JsonAObjetoHelpers jsonHelper = this; 
		usuario.setIdUsuario((Integer)  jsonHelper.jsonAObjeto("idUsuario",Integer.class));
		usuario.setNombre( (String)  jsonHelper.jsonAObjeto("nombre",String.class));
		usuario.setUserName( usuario.getNombre());
		usuario.setApellido( (String)  jsonHelper.jsonAObjeto("apellido",String.class));
		
		UsuarioHelper.tipo_Usuario tipoUsuario = UsuarioHelper.asignarRol(usuario, (String)  jsonHelper.jsonAObjeto("tipo",String.class));
		
		usuario.setEmail( (String)  jsonHelper.jsonAObjeto("correo",String.class));
		
		usuario.setIfe( (String)  jsonHelper.jsonAObjeto("ife",String.class));
		
		usuario.setActaHacienda( (String)  jsonHelper.jsonAObjeto("actaHacienda",String.class));
	    usuario.setActaConstitutiva( (String)  jsonHelper.jsonAObjeto("actaConstitutiva",String.class));
	    
		usuario.setInfraestructura( (String)  jsonHelper.jsonAObjeto("infraestructura",String.class));
	    usuario.setDatosFiscales( (String)  jsonHelper.jsonAObjeto("datosFiscales",String.class));
	    usuario.setCapacidadGeneral( (String)  jsonHelper.jsonAObjeto("capacidadGeneral",String.class));
	    usuario.setCapacidadFinanciera( (String)  jsonHelper.jsonAObjeto("capacidadFinanciera",String.class));
	    usuario.setCalificacionGlobal( (Integer)  jsonHelper.jsonAObjeto("calificacionGlobal",Integer.class));
	    usuario.setContrasena( (String)  jsonHelper.jsonAObjeto("contraseña",String.class));
	    usuario.setStatus( (String)  jsonHelper.jsonAObjeto("estatus",String.class));
		
		return usuario;
		
	}
	
	
	public  Obra jsonAObra() throws ExceptionPGF {
		Obra obra = new Obra();
		JsonAObjetoHelpers jsonHelper = this; 
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		
		//obra.setFechaInicio((Date)  jsonHelper.jsonAObjeto("fechaInicio",Date.class));
		//obra.setFechaFin((Date)  jsonHelper.jsonAObjeto("fechaFin",Date.class));
		
		
		jsonHelperAux.setJsonObjeto((JsonObject)jsonHelper.jsonAObjeto("gastosAdmin", JsonObject.class));
		
		obra.setTipo((String)  jsonHelperAux.jsonAObjeto("tipo",String.class));
		obra.setTiempo((String)  jsonHelperAux.jsonAObjeto("tiempo",String.class));
		obra.setMontoMensual((String)  jsonHelperAux.jsonAObjeto("montoMensual",String.class));
		obra.setPorcentaje((String)  jsonHelperAux.jsonAObjeto("porcentaje",String.class));
		obra.setMontoTotal((String)  jsonHelperAux.jsonAObjeto("montoTotal",String.class));
		
		obra.setStatus((String)  jsonHelper.jsonAObjeto("estatus",String.class));
		return obra;
		
	}
	
	public  Cliente jsonACliente() throws ExceptionPGF {
		Cliente cliente = new Cliente();
		
		JsonAObjetoHelpers jsonHelper = this;
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		
		
		
		jsonHelperAux.setJsonObjeto((JsonObject)jsonHelper.jsonAObjeto("cliente", JsonObject.class));
		
		cliente.setIdCliente((Integer)  jsonHelperAux.jsonAObjeto("id",Integer.class));
		cliente.setClase((String)  jsonHelperAux.jsonAObjeto("clase",String.class));
		cliente.setSubcliente((String)  jsonHelperAux.jsonAObjeto("subcliente",String.class));
		
		return cliente;
	}
	
	public  UnidadNegocio jsonAUnidadNegocio() throws ExceptionPGF {
		UnidadNegocio unidad = new UnidadNegocio();
		
		JsonAObjetoHelpers jsonHelper = this;
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		
		
		
		jsonHelperAux.setJsonObjeto((JsonObject)jsonHelper.jsonAObjeto("cliente", JsonObject.class));
		
		unidad.setIdUnidadNegocio((Integer)  jsonHelperAux.jsonAObjeto("id",Integer.class));
		unidad.setNombre((String)  jsonHelperAux.jsonAObjeto("nombre",String.class));
		
		return unidad;
	}
	
	public  LinkedList<Interno> jsonAInternos(String tipo) throws ExceptionPGF {
		LinkedList<Interno> internos = new LinkedList<Interno>();
		
		JsonAObjetoHelpers jsonHelper = this;
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		
		JsonArray internosArray = (JsonArray) jsonHelper.jsonAObjeto(tipo,JsonArray.class);
		
		for(int i = 0; i < internosArray.size() ; i++){
			Interno interno;
			JsonObject internoObject = internosArray.getJsonObject(i);
			jsonHelperAux.setJsonObjeto(internoObject);
			interno = jsonHelperAux.jsonAInterno();
			internos.add(interno);
		}
		return internos;
	}
	
	public  LinkedList<RContratistaObra> jsonARContratistaObra() throws ExceptionPGF {
		LinkedList<RContratistaObra> contratistas = new LinkedList<RContratistaObra>();
		
		JsonAObjetoHelpers jsonHelper = this;
		JsonAObjetoHelpers jsonHelperAux = new JsonAObjetoHelpers();
		JsonAObjetoHelpers jsonHelperAuxActividad = new JsonAObjetoHelpers();
		
		JsonArray contratistaArray = (JsonArray) jsonHelper.jsonAObjeto("proveedores",JsonArray.class);
		
		for(int i = 0; i < contratistaArray.size() ; i++){
			RContratistaObra rContratistaObra = new RContratistaObra();
			RContratistaObraId rContratistaObraId = new RContratistaObraId();
			
			JsonObject contratistaObject = contratistaArray.getJsonObject(i);
			jsonHelperAux.setJsonObjeto(contratistaObject);
			
			jsonHelperAuxActividad.setJsonObjeto((JsonObject)jsonHelperAux.jsonAObjeto("actividad", JsonObject.class));
			
			
			rContratistaObraId.setIdContratista((Integer)  jsonHelperAux.jsonAObjeto("id",Integer.class));
			rContratistaObraId.setIdActividad((Integer)  jsonHelperAuxActividad.jsonAObjeto("id",Integer.class));
			
			rContratistaObra.setId(rContratistaObraId);
			
		}
		return contratistas;
	}
	
	public Interno jsonAInterno()throws ExceptionPGF{
		Interno interno = new Interno();
		JsonAObjetoHelpers jsonHelper = this;
		interno.setIdInternos((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
		return interno;
	}
	
	public Contratista jsonAContratista()throws ExceptionPGF{
		Contratista contratista = new Contratista();
		JsonAObjetoHelpers jsonHelper = this;
		contratista.setIdContratista((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
		return contratista;
	}
	
	/*public RContratistaObra jsonARContratistaObra()throws ExceptionPGF{
		RContratistaObra rContratistaObra = new RContratistaObra();
		RContratistaObraId rContratistaObraId = new RContratistaObraId();
		JsonAObjetoHelpers jsonHelper = this;
		
		rContratistaObraId.setIdContratista((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
		rContratistaObraId.setIdActividad((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
		
		rContratistaObra.setId(rContratistaObraId);
		//contratista.s((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
		return contratista;
	}*/
	
	

	
	
	
}
