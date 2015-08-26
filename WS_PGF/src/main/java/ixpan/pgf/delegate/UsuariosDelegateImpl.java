package ixpan.pgf.delegate;

import java.util.LinkedList;

import javax.json.JsonArray;
import javax.json.JsonObject;

import ixpan.pgf.daoPrincipal.DireccionDAOImpl;
import ixpan.pgf.daoPrincipal.RUsuarioActividadDAOImpl;
import ixpan.pgf.daoPrincipal.TelefonoDAOImpl;
import ixpan.pgf.daoPrincipal.UsuarioDAOImp;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.helpers.JsonAObjetoHelpers;
import ixpan.pgf.helpers.UsuarioHelper;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Contratista;
import ixpan.pgf.model.CuentaBancaria;
import ixpan.pgf.model.CuentaBancariaId;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.DireccionId;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.RUsuarioActividad;
import ixpan.pgf.model.RUsuarioActividadId;
import ixpan.pgf.model.Telefono;
import ixpan.pgf.model.TelefonoId;
import ixpan.pgf.model.Usuario;
import ixpan.pgf.service.ClienteServiceImpl;
import ixpan.pgf.service.ContratistaServiceImpl;
import ixpan.pgf.service.InternoServiceImpl;
import ixpan.pgf.service.UsuarioService;

public class UsuariosDelegateImpl implements UsuariosDelegate {
	
	LinkedList<SaveUtils> saveUtilss;


	public String registrarUusario(String nuevoUsuario) throws ExceptionPGF {
		saveUtilss = new LinkedList<SaveUtils>();
		
		
		UsuarioDAOImp usuarioDao;
		TelefonoDAOImpl telefonoDao;
		DireccionDAOImpl direccionDao;
		RUsuarioActividadDAOImpl ractividadDao;
		 
		 
		
		Usuario usuario = new Usuario();
		Cliente cliente;
		Contratista contratista;
		Interno interno;
		
		Telefono telefono = new Telefono();
		Telefono celular = new Telefono();
		Telefono otroTelefono = new Telefono();
		Direccion direccionPersonal = new Direccion();
		Direccion direccionFiscal = new Direccion();
		int actividades[];
		CuentaBancaria cuentaPersonal = new CuentaBancaria();
		CuentaBancaria cuentaFiscal = new CuentaBancaria();
		
		
		 
		JsonObject usuarioObject = MiJson.cargarObjecto(nuevoUsuario);
		JsonAObjetoHelpers jsonHelper = new JsonAObjetoHelpers(); 
		jsonHelper.setJsonObjeto(usuarioObject);
		 
		 
		usuario = jsonHelper.jsonAUsuario();
		
		UsuarioHelper.tipo_Usuario tipoUsuario = UsuarioHelper.asignarRol(usuario, (String)  jsonHelper.jsonAObjeto("tipo",String.class)); 
		 
		 
		telefono.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("telefono",Integer.class));
		celular.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("celular",Integer.class));
		otroTelefono.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("otroTelefono",Integer.class));
   
		telefono.setTipoTelefono("telefono");
	    celular.setTipoTelefono("celular");
	    otroTelefono.setTipoTelefono("otroTelefono");
		
		direccionPersonal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionPersonal"));
		direccionFiscal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionFiscal"));
		 
	    
		cuentaPersonal = JsonAObjetoHelpers.jsonACuentaBancaria( usuarioObject.get("cuentaPersonal"));
	    cuentaFiscal = JsonAObjetoHelpers.jsonACuentaBancaria(usuarioObject.get( "cuentaFiscal")); 
	     
	    /* Daos Nenecsarios */
	    usuarioDao = new UsuarioDAOImp();
	    telefonoDao = new TelefonoDAOImpl();
	    direccionDao = new DireccionDAOImpl();
	    ractividadDao = new RUsuarioActividadDAOImpl();
	    
	    
	    /* Servicios Nenecsarios */
	    UsuarioService usuarioS = new UsuarioService();
	    ClienteServiceImpl clienteS = new ClienteServiceImpl();
	    ContratistaServiceImpl contratistaS = new ContratistaServiceImpl();
	    InternoServiceImpl internoS = new InternoServiceImpl();
	    
	    /* Listas Auxiliares */
	    LinkedList<Telefono> telefonos = new LinkedList<Telefono>();
	    LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
	    LinkedList<CuentaBancaria> cuentas = new LinkedList<CuentaBancaria>();
	    telefonos.add(telefono);
	    telefonos.add(celular);
	    telefonos.add(otroTelefono);
	    
	    
	    
	    direcciones.add(direccionPersonal);
	    direcciones.add(direccionFiscal);
	    
	    cuentas.add(cuentaPersonal);
	    cuentas.add(cuentaFiscal);
	    
	    
	    
	    /****************** Guardar Usuario ******************/
	    usuarioS.registrarNuevoUsuario(usuario, telefonos, direcciones, cuentas);
	    saveUtilss.add(usuarioS.getSaveUtils());
		try {
			/****************** Guardar Contratista ******************/
			if (tipoUsuario == UsuarioHelper.tipo_Usuario.Contratista) {
				contratista = new Contratista();
				System.out.println("Entro a registrar Contratista");
				JsonArray actividadesArray = usuarioObject.getJsonArray("actividades");
				actividades = new int[actividadesArray.size()];
				for (int i = 0; i < actividadesArray.size(); i++) {
					JsonObject actividad = actividadesArray.getJsonObject(i);
					jsonHelper.setJsonObjeto(actividad);
					actividades[i] = ((Integer) jsonHelper.jsonAObjeto("id", Integer.class));

				}

				contratistaS.registrarNuevoContratista(usuario, contratista, actividades);
				saveUtilss.add(contratistaS.getSaveUtils());
			}

			/****************** Guardar Cliente ******************/
			if (tipoUsuario == UsuarioHelper.tipo_Usuario.Cliente) {
				cliente = new Cliente();
				cliente.setClase((String) jsonHelper.jsonAObjeto("clase", String.class));
				cliente.setSubcliente((String) jsonHelper.jsonAObjeto("subcliente", String.class));

				clienteS.registrarNuevoCliente(usuario, cliente);
				saveUtilss.add(clienteS.getSaveUtils());
			}
			/****************** Guardar Interno ******************/
			if (tipoUsuario == UsuarioHelper.tipo_Usuario.Interno) {
				interno = new Interno();

				internoS.registrarNuevoInterno(usuario, interno);
				saveUtilss.add(internoS.getSaveUtils());
			}
		} catch (ExceptionPGF e) {
			for (SaveUtils saveutils : saveUtilss) {
				saveutils.ejecutarOperacion();
			}
			throw e;
		}
	    return usuarioS.obtenerUsuariosPorId(String.valueOf(usuario.getIdUsuario()));
	    
	    	
	    
	}
	
	public String actualizarUsuario(String nuevoUsuario) throws ExceptionPGF {
		saveUtilss = new LinkedList<SaveUtils>();
		
		
		UsuarioDAOImp usuarioDao;
		TelefonoDAOImpl telefonoDao;
		DireccionDAOImpl direccionDao;
		RUsuarioActividadDAOImpl ractividadDao;
		 
		 
		
		Usuario usuario = new Usuario();
		Cliente cliente;
		Contratista contratista;
		Interno interno;
		
		Telefono telefono = new Telefono();
		Telefono celular = new Telefono();
		Telefono otroTelefono = new Telefono();
		Direccion direccionPersonal = new Direccion();
		Direccion direccionFiscal = new Direccion();
		int actividades[];
		CuentaBancaria cuentaPersonal = new CuentaBancaria();
		CuentaBancaria cuentaFiscal = new CuentaBancaria();
		
		
		 
		JsonObject usuarioObject = MiJson.cargarObjecto(nuevoUsuario);
		JsonAObjetoHelpers jsonHelper = new JsonAObjetoHelpers(); 
		jsonHelper.setJsonObjeto(usuarioObject);
		 
		 
		usuario = jsonHelper.jsonAUsuario();
		
		UsuarioHelper.tipo_Usuario tipoUsuario = UsuarioHelper.asignarRol(usuario, (String)  jsonHelper.jsonAObjeto("tipo",String.class)); 
		
		 
		telefono.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("telefono",Integer.class));
		celular.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("celular",Integer.class));
		otroTelefono.setNumTelefono((Integer)  jsonHelper.jsonAObjeto("otroTelefono",Integer.class));
   
		telefono.setTipoTelefono("telefono");
	    celular.setTipoTelefono("celular");
	    otroTelefono.setTipoTelefono("otroTelefono");
		
		direccionPersonal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionPersonal"));
		direccionFiscal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionFiscal"));
		 
	    
		cuentaPersonal = JsonAObjetoHelpers.jsonACuentaBancaria( usuarioObject.get("cuentaPersonal"));
	    cuentaFiscal = JsonAObjetoHelpers.jsonACuentaBancaria(usuarioObject.get( "cuentaFiscal")); 
	     
	    /* Daos Nenecsarios */
	    usuarioDao = new UsuarioDAOImp();
	    telefonoDao = new TelefonoDAOImpl();
	    direccionDao = new DireccionDAOImpl();
	    ractividadDao = new RUsuarioActividadDAOImpl();
	    
	    
	    /* Servicios Nenecsarios */
	    UsuarioService usuarioS = new UsuarioService();
	    ClienteServiceImpl clienteS = new ClienteServiceImpl();
	    ContratistaServiceImpl contratistaS = new ContratistaServiceImpl();
	    InternoServiceImpl internoS = new InternoServiceImpl();
	    
	    /* Listas Auxiliares */
	    LinkedList<Telefono> telefonos = new LinkedList<Telefono>();
	    LinkedList<Direccion> direcciones = new LinkedList<Direccion>();
	    LinkedList<CuentaBancaria> cuentas = new LinkedList<CuentaBancaria>();
	    telefonos.add(telefono);
	    telefonos.add(celular);
	    telefonos.add(otroTelefono);
	    
	    
	    
	    direcciones.add(direccionPersonal);
	    direcciones.add(direccionFiscal);
	    
	    cuentas.add(cuentaPersonal);
	    cuentas.add(cuentaFiscal);
	    
	    
	    /****************** Actualizar Usuario ******************/
	    usuarioS.actualizarNuevoUsuario(usuario, telefonos, direcciones, cuentas);
	    saveUtilss.add(usuarioS.getSaveUtils());
	    
	    /****************** Guardar Contratista ******************/
	    if (tipoUsuario == UsuarioHelper.tipo_Usuario.Contratista) {
	    	contratista = new Contratista();
	    	
	    	JsonArray actividadesArray = usuarioObject.getJsonArray("actividades");
			actividades = new int[actividadesArray.size()];
			for (int i = 0; i < actividadesArray.size(); i++) {
				JsonObject actividad = actividadesArray.getJsonObject(i);
				jsonHelper.setJsonObjeto(actividad);
				actividades[i] = ((Integer)  jsonHelper.jsonAObjeto("id",Integer.class));
				
			}
			contratistaS.actualizarContratista(usuario, contratista, actividades);
			saveUtilss.add(contratistaS.getSaveUtils());
		}
	    
	    /****************** Guardar Cliente ******************/
	    if (tipoUsuario == UsuarioHelper.tipo_Usuario.Cliente) {
	    	cliente = new Cliente();
	    	cliente.setClase( (String)  jsonHelper.jsonAObjeto("clase",String.class));
	    	cliente.setSubcliente( (String)  jsonHelper.jsonAObjeto("subcliente",String.class));
	    	
	    	clienteS.actualizarCliente(usuario, cliente);
	    	saveUtilss.add(clienteS.getSaveUtils());
		}
	    /****************** Guardar Interno ******************/
	    if (tipoUsuario == UsuarioHelper.tipo_Usuario.Interno) {
	    	interno = new Interno();	
	    	internoS.actualizarInterno(usuario, interno);
	    	saveUtilss.add(internoS.getSaveUtils());
		}
	    return usuarioS.obtenerUsuariosPorId(String.valueOf(usuario.getIdUsuario()));
	}
	
	
	public String obtenerUsuarios(String tipoPeticion) throws ExceptionPGF{
		
		UsuarioService usuarioS = new UsuarioService();
	    ClienteServiceImpl clienteS = new ClienteServiceImpl();
	    ContratistaServiceImpl contratistaS = new ContratistaServiceImpl();
	    InternoServiceImpl internoS = new InternoServiceImpl();
		
	    UsuarioHelper.tipo_Usuario peticionUsuario;
	    
	    String usuarios;
		try{
			peticionUsuario = UsuarioHelper.tipo_Usuario.valueOf(tipoPeticion);
		}catch(Exception e){
			ExceptionPGF myException = new ExceptionPGF("No existe este tipo de Usuario : " + tipoPeticion ,ExceptionPGF.Errores.Rol_Usuario);
        	throw myException;
		}
		try{
			return usuarioS.obtenerUsuarios(peticionUsuario);
		}catch(Exception e){
			throw e;
			
		}
		
		
		
	}
	
	
	

}
