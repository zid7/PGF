package ixpan.pgf.service;



import java.util.LinkedList;

import javax.json.JsonArray;
import javax.json.JsonObject;

import ixpan.pgf.daoPrincipal.ActividadDAOImpl;
import ixpan.pgf.daoPrincipal.BancoDAOImpl;
import ixpan.pgf.daoPrincipal.CRolUsuarioDAOImp;
import ixpan.pgf.daoPrincipal.ClienteDAOImpl;
import ixpan.pgf.daoPrincipal.ContratistaDAOImpl;
import ixpan.pgf.daoPrincipal.CuentaBancariaDAOImpl;
import ixpan.pgf.daoPrincipal.DireccionDAOImpl;
import ixpan.pgf.daoPrincipal.InternoDAOImpl;
import ixpan.pgf.daoPrincipal.ObraDAOImpl;
import ixpan.pgf.daoPrincipal.RUsuarioActividadDAOImpl;
import ixpan.pgf.daoPrincipal.TelefonoDAOImpl;
import ixpan.pgf.daoPrincipal.UsuarioDAOImp;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.helpers.JsonAObjetoHelpers;
import ixpan.pgf.helpers.UsuarioHelper;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.model.CActividad;
import ixpan.pgf.model.CRolUsuario;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Contratista;
import ixpan.pgf.model.CuentaBancaria;
import ixpan.pgf.model.CuentaBancariaId;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.DireccionId;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Obra;
import ixpan.pgf.model.RUsuarioActividad;
import ixpan.pgf.model.RUsuarioActividadId;
import ixpan.pgf.model.Telefono;
import ixpan.pgf.model.TelefonoId;
import ixpan.pgf.model.Usuario;

public class UsuarioService {
	
	SaveUtils saveUtils;
	
	 public String obtenerUsuarios(UsuarioHelper.tipo_Usuario tipoUsuario) throws ExceptionPGF{
	  	  boolean boolObras = false;
	  	  boolean boolObrasActivas = false;
	  	  
	  	  
	  	  //throws org.codehaus.jackson.JsonGenerationException, JsonMappingException, IOException, ExceptionPGF{
	  	  String json = "[";
	  	  try {
	  		  Usuario u;
	  		  Obra o;
	  		  UsuarioDAOImp usuarioDao = new UsuarioDAOImp();
	  		  CRolUsuarioDAOImp  rolDAO= new CRolUsuarioDAOImp();
	  		  
	  		  
	  		
	  		LinkedList<Interno> internos = null;
	  		LinkedList<Cliente> clientes = null;
	  		LinkedList<Contratista> contratistas = null;
	  		LinkedList<Usuario> usuarios = new LinkedList<Usuario>();
	  		LinkedList<Obra> obras;
	  		  
	  		  /*Obtener clientes*/
	  		  ClienteDAOImpl clienteDao = new ClienteDAOImpl();
	  		  ContratistaDAOImpl contratistaDao = new ContratistaDAOImpl();
	  		  InternoDAOImpl internoDao = new InternoDAOImpl();
	  		  ActividadDAOImpl actividadDao = new ActividadDAOImpl();
	  		  
	  		  ObraDAOImpl obraDao = new ObraDAOImpl();
	  		  switch(tipoUsuario){
	  		  case Interno:
	  			  internos = (LinkedList<Interno>) internoDao.obtenerInternos();
	  			  if(internos == null)
	  				  return "";
	  			  for(Interno interno : internos){
	  				usuarios.add(usuarioDao.obtenerUsusario(interno.getIdUsuario()));
	  			  }  
	  			  break;
	  		  case Cliente:
	  			  clientes = (LinkedList<Cliente>) clienteDao.obtenerClientes();
	  			  if(clientes == null)
	  				  return "";
	  			  for(Cliente cliente : clientes){
	  				usuarios.add(usuarioDao.obtenerUsusario(cliente.getIdUsuario()));
	  			  }
	  			  break;
	  		  case Contratista:
	  			  contratistas = (LinkedList<Contratista>) contratistaDao.obtenerContratistas();
	  			  if(contratistas == null)
	  				  return "";
	  			  for(Contratista contratista: contratistas){
	  				usuarios.add(usuarioDao.obtenerUsusario(contratista.getIdUsuario()));
	  			  }
	  			  break;
	  		  case Administrador:
	  			  return "";
	  		  }
	  		  
	  		  if(usuarios!=null)
	  		  for(int i=0;i<usuarios.size();i++){
	  			  u = usuarios.get(i);
	  			  json += "{\"idUsuario\":\"" + u.getIdUsuario()+"\",";
				  json += "\"nombre\":\"" + u.getNombre()+"\",";
				  json += "\"apellido\":\"" + u.getApellido()+"\",";
				  switch(tipoUsuario){
		  		  case Interno:
		  			  obras = (LinkedList<Obra>) obraDao.obtenerObrasPorInterno(internos.get(i).getIdUsuario());
		  			  break;
		  		  case Cliente:
		  			  obras = (LinkedList<Obra>) obraDao.obtenerObrasPorClienete(clientes.get(i).getIdCliente());
		  			  break;
		  		  case Contratista:
		  			  obras = (LinkedList<Obra>) obraDao.obtenerObrasPorContratista(contratistas.get(i).getIdUsuario());
		  			  break;
		  			default:
		  				obras= null;
		  				
		  		  }
				  
				  String jsonObras;
				  String jsonObrasActivas;
	  			if(obras == null ){
	  				jsonObras = "\"Obras\":null,";
	  				jsonObrasActivas = "\"ObrasActivas\":null,";
	  			}else{
	  				jsonObras = "\"Obras\":[";
	  				jsonObrasActivas = "\"ObrasActivas\":[";
	  				for(int j = 0; j < obras.size() ; j++){
		  				  o = obras.get(j);
		  				  if(!o.getStatus().equals("Terminada")){
		  					boolObras = boolObrasActivas = true;  
		  					jsonObrasActivas += "{\"idObra\":\"" + o.getIdObra()+"\",";
		  				  	jsonObrasActivas += "\"nombre\":\"" + o.getNombre()+"\"}";
		  				  }
		  				  jsonObras += "{\"idObra\":\"" + o.getIdObra()+"\",";
		  				  jsonObras += "\"nombre\":\"" + o.getNombre()+"\"}";
		  				  
		  			  }
		  			  jsonObras +="],";
		  			  jsonObrasActivas +="],";
		  			  if(!boolObrasActivas){
		  				jsonObrasActivas = "\"ObrasActivas\":null,";
		  			  }
	  			}
	  			json += jsonObras;
	  			json += jsonObrasActivas; 
	  			switch(tipoUsuario){
		  		  case Cliente:
		  			  Cliente cliente = clientes.get(i);  
		  			  json += "\"clase\":\"" + cliente.getClase()+"\",";
		  			  json += "\"subcliente\":\"" + cliente.getSubcliente()+"\",";
		  			  break;
		  		  case Contratista:
		  			  LinkedList<CActividad> actividades = actividadDao.obtenerActividadesPorUsuario(contratistas.get(i).getIdContratista());
					  String jsonActividades= "\"actividades\":";
					if (actividades != null && actividades.size() > 0) {
						jsonActividades += "[";
						for (int j = 0; j < actividades.size(); j++) {
							CActividad actividad = actividades.get(j);
							jsonActividades += "{\"id\":\"" + actividad.getIdCActividad() + "\",";
							jsonActividades += "\"nombre\":\"" + actividad.getNombre() + "\"}";
							if (j + 1 != actividades.size())
								jsonActividades += ",";
						}
						jsonActividades += "],";
					} else
						jsonActividades += "null,";
					  
					  json += jsonActividades;
		  			  break;
		  		  }
	  			json += "\"estatus\":\"" + u.getStatus()+"\"}";
	  			if (i + 1 != usuarios.size())
					json += ",";
	  			  
	  		  }
	  		  json += "]";
	  		  
	  		  	
	  	  }catch (Exception e) {
	  		throw e;
	  	  }
	  	  return json;
	  	
	    }
	 
	 
	 public String obtenerUsuariosPorId(String idUsuario) throws ExceptionPGF  {
	  	  
			int idDireccionPersonal=-1;
			int idDireccionFiscal=-1;
			
	  	 
			String json = "";
	  	 
	  		  
	  		 
	  		  Usuario u;
	  		  Obra o;
	  		  UsuarioDAOImp usuarioDao = new UsuarioDAOImp();
	  		  TelefonoDAOImpl telefonoDao = new TelefonoDAOImpl();
	  		  CRolUsuarioDAOImp  rolDAO= new CRolUsuarioDAOImp();
	  		  DireccionDAOImpl  direcionDao= new DireccionDAOImpl();
	  		  ActividadDAOImpl actividadDao = new ActividadDAOImpl();
	  		  CuentaBancariaDAOImpl cuentaBancariaDao = new CuentaBancariaDAOImpl();
	  		  BancoDAOImpl bancoDao = new BancoDAOImpl();
	  		  
	  		  
	  		  /*Obtener clientes*/
	  		  ClienteDAOImpl clienteDao = new ClienteDAOImpl();
	  		  ContratistaDAOImpl contratistaDao = new ContratistaDAOImpl();
	  		  InternoDAOImpl internoDao = new InternoDAOImpl();
	  		  
	  		  ObraDAOImpl obraDao = new ObraDAOImpl();
	  		  u = usuarioDao.obtenerUsusario(Integer.valueOf(idUsuario));
	  		  if(u == null){
	  			  throw new ExceptionPGF("Usuario con id : " + idUsuario+" ", ExceptionPGF.Errores.Entidad_No_Existente );
	  		  }
	  		  json += "{\"idUsuario\":\"" + u.getIdUsuario()+"\",";
			  json += "\"nombre\":\"" + u.getNombre()+"\",";
			  json += "\"apellido\":\"" + u.getApellido()+"\",";
			  CRolUsuario cru = rolDAO.obtenerCRolUsuario(u.getIdRolUsuario());
			  json += "\"tipo\":\"" + rolDAO.obtenerCRolUsuario(u.getIdRolUsuario()).getNombre()+"\",";
			  if(cru.getNombre()=="Cliente"){
			  	Cliente c = clienteDao.obtenerClientes(u);
			  	json += "\"clase\":\""+ c.getClase()+  "\",";
				json += "\"subcliente\":\""+ c.getClase()+  "\",";
	 		  }
			  json += "\"clase\":null,";
			  json += "\"subcliente\":null,";
			  
			  
			  LinkedList<Telefono> telefonos = telefonoDao.obtenerTelefonosPorUsuario(u.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.telefono);
			  if(telefonos!=null)
				  json += "\"telefono\":\"" +   telefonos.get(0).getNumTelefono() +"\",";
			  else
				  json += "\"telefono\":\"null\",";
			  
			  telefonos = telefonoDao.obtenerTelefonosPorUsuario(u.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.celular);
			  if(telefonos!=null)
				  json += "\"celular\":\"" +   telefonos.get(0).getNumTelefono() +"\",";
			  else
				  json += "\"celular\":\"null\",";
			  telefonos = telefonoDao.obtenerTelefonosPorUsuario(u.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.otroTelefono);
			  if(telefonos!=null)
				  json += "\"otroTelefono\":\"" +   telefonos.get(0).getNumTelefono() +"\",";
			  else
				  json += "\"otroTelefono\":\"null\",";
			  
			  json += "\"correo\":\"" + u.getEmail() + "\",";
			  
			  Direccion direccion;
			  if(u.getDireccionPersonal()==null)
				  direccion=null;
			  else
			  direccion = direcionDao.obtenerDireccion(u.getDireccionPersonal());
			  
			  String jsonDireccion;
			  
			  
			  if(direccion!=null){
				  //idDireccionPersonal = direccion.getId().getIdDireccion();
				  
				  jsonDireccion = "\"direccionPersonal\":{";
				  jsonDireccion += "\"calle\":\"" +  direccion.getCalle() +"\",";
				  jsonDireccion += "\"numExt\":\"" + direccion.getNumExt() +"\",";
				  jsonDireccion += "\"numInt\":\"" +   direccion.getNumInt() +"\",";
				  jsonDireccion += "\"colonia\":\"" +   direccion.getColonia() +"\",";
				  jsonDireccion += "\"delegacion\":\"" +   direccion.getDelegacion() +"\",";
				  jsonDireccion += "\"cp\":\"" +   direccion.getCp() +"\"},";
			  }else
				  jsonDireccion = "\"direccionPersonal\":\"null\",";
			  
			  json += jsonDireccion;
			  
			  
			  if(u.getDireccionPersonal()==null)
				  direccion=null;
			  else
			  direccion = direcionDao.obtenerDireccion(u.getDireccionPersonal());
			  
			  if(direccion!=null){
				  //idDireccionFiscal = direccion.getId().getIdDireccion();
				  jsonDireccion = "\"direccionFiscal\":{";
				  jsonDireccion += "\"calle\":\"" +  direccion.getCalle() +"\",";
				  jsonDireccion += "\"numExt\":\"" + direccion.getNumExt() +"\",";
				  jsonDireccion += "\"numInt\":\"" +   direccion.getNumInt() +"\",";
				  jsonDireccion += "\"colonia\":\"" +   direccion.getColonia() +"\",";
				  jsonDireccion += "\"delegacion\":\"" +   direccion.getDelegacion() +"\",";
				  jsonDireccion += "\"cp\":\"" +   direccion.getCp() +"\"},";
			  }else
				  jsonDireccion = "\"direccionFiscal\":\"null\",";
			  
			  json += jsonDireccion;
			  
			  json += "\"ife\":\"" + u.getIfe() + "\",";
	  		  
			  String jsonActividades ;
			  if(cru.getNombre()!="Contratista"){
				  	jsonActividades = "\"actividades\":null,";
			  }else{
				  Contratista c = contratistaDao.obtenerContratista(u);
				  jsonActividades = "\"actividades\":";
				  LinkedList<CActividad> actividades = actividadDao.obtenerActividadesPorUsuario(c.getIdContratista());
				  if(actividades!=null && actividades.size() >0){
					  jsonActividades +="[";
				  for(int i=0;i<actividades.size();i++){
					  CActividad actividad = actividades.get(i);
					  jsonActividades += "{\"id\":\"" + actividad.getIdCActividad()+"\",";
					  jsonActividades += "\"nombre\":\"" + actividad.getNombre()+"\"}";
					  if(i+1 !=actividades.size())
						  jsonActividades +=",";
		  		  }
				  jsonActividades += "],";
				  }else
					  jsonActividades += "\"null\",";
			  }
			  
			  
			  
			  
			  json += jsonActividades;
			  String jsonCuentaBancaria;
			  if(idDireccionPersonal!=-1){
				  jsonCuentaBancaria  = "\"cuentaPersonal\":{";
			  
				  CuentaBancaria cuenta = cuentaBancariaDao.obtenerCuentaBancaria(idDireccionPersonal);
				  jsonCuentaBancaria += "\"nombreTitular\":\"" +  cuenta.getTitular() +"\",";
				  jsonCuentaBancaria += "\"numCuenta\":\"" +  cuenta.getNumeroCuenta() +"\",";
				  jsonCuentaBancaria += "\"clabe\":\"" +  cuenta.getClabeInterbancaria() +"\",";
				  jsonCuentaBancaria += "\"banco\":\"" +  bancoDao.obtenerBanco(cuenta.getId().getBancoIdBanco()).getNombre() +"\",";
				  jsonCuentaBancaria += "\"sucursal\":\"" +  cuenta.getSucursal() +"\"},";
			  }else
				  jsonCuentaBancaria  = "\"cuentaPersonal\":\"null\"";
			  
			  
			  json += jsonCuentaBancaria;
			  
			  
			  
			  if(idDireccionFiscal!=-1){
				  jsonCuentaBancaria  = "\"cuentaFiscal\":{";
			  
				  CuentaBancaria cuenta = cuentaBancariaDao.obtenerCuentaBancaria(idDireccionFiscal);
				  jsonCuentaBancaria += "\"nombreTitular\":\"" +  cuenta.getTitular() +"\",";
				  jsonCuentaBancaria += "\"numCuenta\":\"" +  cuenta.getNumeroCuenta() +"\",";
				  jsonCuentaBancaria += "\"clabe\":\"" +  cuenta.getClabeInterbancaria() +"\",";
				  jsonCuentaBancaria += "\"banco\":\"" +  bancoDao.obtenerBanco(cuenta.getId().getBancoIdBanco()).getNombre() +"\",";
				  jsonCuentaBancaria += "\"sucursal\":\"" +  cuenta.getSucursal() +"\"},";
			  }else
				  jsonCuentaBancaria  = "\"cuentaFiscal\":\"null\"";
			  
			  json += jsonCuentaBancaria;
			  
			  
			  
			  json += "\"actaHacienda\":\"" + u.getActaHacienda() + "\",";
			  json += "\"actaConstitutiva\":\"" + u.getActaConstitutiva() + "\",";
			  json += "\"infraestructura\":\"" + u.getInfraestructura() + "\",";
			  json += "\"datosFiscales\":\"" + u.getDatosFiscales() + "\",";
			  json += "\"capacidadGeneral\":\"" + u.getCapacidadGeneral() + "\",";
			  json += "\"capacidadFinanciera\":\"" + u.getCapacidadFinanciera() + "\",";
			  json += "\"calificacionGlobal,\":\"" + u.getCalificacionGlobal() + "\",";
			  json += "\"estatus\":\"" + u.getStatus() + "\"}";
			  
			  
			  
	  		  
	  		  	
	  	  return json;
	  	
	    }
	 
	 public int registrarNuevoUsuario(Usuario usuario, LinkedList<Telefono> telefonos, LinkedList<Direccion> direcciones, LinkedList<CuentaBancaria> cuentas) throws ExceptionPGF {
		 UsuarioDAOImp usuarioDao;
		 TelefonoDAOImpl telefonoDao;
		 DireccionDAOImpl direccionDao;
		 CuentaBancariaDAOImpl cuentaDao;
		 
		 usuarioDao = new UsuarioDAOImp();
	     telefonoDao = new TelefonoDAOImpl();
	     direccionDao = new DireccionDAOImpl();
	     cuentaDao = new CuentaBancariaDAOImpl();
	     
	     int usuarioid=0;
	     
	     saveUtils = new SaveUtils();
	     
	     
	     
	     if(cuentas.size() > direcciones.size()){
	    	 saveUtils.ejecutarOperacion();
	    	 ExceptionPGF myexception = new ExceptionPGF("Todas las cuentas bancarias deben de asignarse a una direcion", ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	     
	     /*********************  Guardar Direcciones  *********************/
	     for(int i =0; i < direcciones.size(); i++){
	    	 try{
	    		Direccion direccion = direcciones.get(i);
				if (direccion != null) {
					int id;
					id = direccionDao.guardarDireccion(direccion);
					direccion.setIdDireccion(id);
					saveUtils.addoperacion(SaveUtils.operaciones.delete, direccion, direccion.getClass());
				}else{
					direccion.setIdDireccion(0);
				}
				
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     for(int i =0; i < cuentas.size(); i++){
	    	 try{
	    		 CuentaBancaria cuenta = cuentas.get(i);
	    		 Direccion direccion = direcciones.get(i);
	    		 
	    		 if(cuenta != null & direccion == null){
	    	    	 saveUtils.ejecutarOperacion();
	    	    	 ExceptionPGF myexception = new ExceptionPGF("Todas las cuentas bancarias deben de asignarse a una direcion", ExceptionPGF.Errores.Guardar_Entidad);
	    	    	 throw  myexception;
	    	     }
	    		 
				if (cuenta != null) {
					CuentaBancariaId id = cuenta.getId();
					System.out.println("Direccion " + i + ": " + direcciones.get(i).getIdDireccion());
					id.setDireccionIdDireccion(direcciones.get(i).getIdDireccion());
					cuentaDao.guardarCuentaBancaria(cuenta);

					cuenta.setId(id);
					saveUtils.addoperacion(SaveUtils.operaciones.delete, cuenta, cuenta.getClass());
				}
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     /*********************  Guardar Usuario  *********************/
	     
	     try{
		    usuarioid = usuarioDao.guardarUsuario(usuario);
		    usuario.setIdUsuario(usuarioid);
		    saveUtils.addoperacion(SaveUtils.operaciones.delete, usuario, usuario.getClass() );
	     }catch(Exception e){
	    	 saveUtils.ejecutarOperacion();
	    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	     
	     
	     /*********************  Guardar Telefonos *********************/
	     
	     for(int i =0; i < telefonos.size(); i++){
	    	 try{
	    		 System.out.println("Telefonos");
	    		 Telefono telefono = telefonos.get(i);
	    		 TelefonoId id = new TelefonoId();
	    		 id.setUsuarioIdUsuario(usuarioid);
	    		 telefono.setId(id);
	    		 id = telefonoDao.guardarTelefono(telefono);
	    		 telefono.setId(id);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.delete, telefono, telefono.getClass() );
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     
	     
	     
	     
	     
		 
		 return usuarioid;
	 }
	 
	 public int actualizarNuevoUsuario(Usuario usuario, LinkedList<Telefono> telefonos, LinkedList<Direccion> direcciones, LinkedList<CuentaBancaria> cuentas) throws ExceptionPGF {
		 UsuarioDAOImp usuarioDao;
		 TelefonoDAOImpl telefonoDao;
		 DireccionDAOImpl direccionDao;
		 CuentaBancariaDAOImpl cuentaDao;
		 
		 usuarioDao = new UsuarioDAOImp();
	     telefonoDao = new TelefonoDAOImpl();
	     direccionDao = new DireccionDAOImpl();
	     cuentaDao = new CuentaBancariaDAOImpl();
	     
	     int usuarioid = usuario.getIdUsuario();
	     saveUtils = new SaveUtils();
	     
	     LinkedList<Telefono> telefonosOriginales = new LinkedList<Telefono>();
	     LinkedList<Direccion> direccionesOriginales = new LinkedList<Direccion>();
	     LinkedList<CuentaBancaria> cuentasOriginales = new LinkedList<CuentaBancaria>();
	     
	     
	     /*********************  Guardar Usuario  *********************/
	     Usuario usuarioOriginal = usuarioDao.obtenerUsusario(usuario.getIdUsuario());
	     
	     if(usuarioOriginal.getIdRolUsuario() != usuario.getIdRolUsuario() ){
	    	 ExceptionPGF myexception = new ExceptionPGF("No es posible cambiar el rol del Usuario: ", ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	     try{
		    usuarioDao.actualizarUsuario(usuario);
		    saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, usuario, usuario.getClass() );
	     }catch(Exception e){
	    	 saveUtils.ejecutarOperacion();
	    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	     
	     LinkedList<Telefono> listaTelefonos ;
	     /*********************  Telefono  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.telefono);
		 if(listaTelefonos == null)
			 telefonosOriginales.add(null);
		 else
			 telefonosOriginales.add(listaTelefonos.get(0));
		 
		 
		 
		 
		 /*********************  Celular  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.celular);
		 if(listaTelefonos == null)
			 telefonosOriginales.add(null);
		 else
			 telefonosOriginales.add(listaTelefonos.get(0));
		 
		 
		 /*********************  Otro Telefono  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.otroTelefono);
		 if(listaTelefonos == null)
			 telefonosOriginales.add(null);
		 else
			 telefonosOriginales.add(listaTelefonos.get(0));
		 
		 
		 
	     
	     for(int i =0; i < telefonos.size(); i++){
	    	 try{
	    		 Telefono telefono = telefonos.get(i);
	    		 Telefono telefonoOriginal = telefonosOriginales.get(i);
	    		 
	    		 if(telefonoOriginal == null){
	    			 TelefonoId id = new TelefonoId();
		    		 id.setUsuarioIdUsuario(usuarioid);
		    		 telefono.setId(id);
		    		 id = telefonoDao.guardarTelefono(telefono);
		    		 telefono.setId(id);
	    			 telefonoDao.guardarTelefono(telefono);
	        		 saveUtils.addoperacion(SaveUtils.operaciones.delete, telefono, telefono.getClass() );
	        	 }else{
	        		 telefono.setId(telefonoOriginal.getId());
	        		 telefonoDao.actualizarTelefono(telefono);
	        		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, telefonoOriginal, telefono.getClass() );
	        	 }
	    		 
	    		 
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF("Error al guardar Telefono: " + e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     
	     
	     
	     
	     
	     if(cuentas.size() > direcciones.size()){
	    	 saveUtils.ejecutarOperacion();
	    	 ExceptionPGF myexception = new ExceptionPGF("Todas las cuentas bancarias deben de asignarse a una direcion", ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	     
	     Direccion direccion;
	     /*********************  Direccion Personal  *********************/
	     direccion = direccionDao.obtenerDireccion(usuarioOriginal.getDireccionPersonal());
		 if(direccion == null)
			 direccionesOriginales.add(null);
		 else
			 direccionesOriginales.add(direccion);
		 /*********************  Direccion Fiscal  *********************/
		 direccion = direccionDao.obtenerDireccion(usuarioOriginal.getDireccionFiscal());
		 if(direccion == null)
			 direccionesOriginales.add(null);
		 else
			 direccionesOriginales.add(direccion);
	     
		 
	     for(int i =0; i < direcciones.size(); i++){
	    	 try{
	    		 direccion = direcciones.get(i);
	    		 Direccion direccionOriginal = direccionesOriginales.get(i);
	    		 if(direccionOriginal==null){
	    			 int id;
		    		 id = direccionDao.guardarDireccion(direccion);
		    		 direccion.setIdDireccion(id);
		    		 saveUtils.addoperacion(SaveUtils.operaciones.delete, direccion, direccion.getClass() );
	    		 }else{
	    			 direccion.setIdDireccion(usuario.getIdUsuario());
	    			 direccionDao.actualizarDireccion(direccion);
		    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, direccionOriginal, direccionOriginal.getClass() );
	    		 }
	    		 
	    		 
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     for(int i =0; i < cuentas.size(); i++){
	    	 try{
	    		 CuentaBancaria cuenta = cuentas.get(i);
				if (cuenta != null) {

					CuentaBancariaId id = cuenta.getId();
					System.out.println("Direccion " + i + ": " + direcciones.get(i).getIdDireccion());
					id.setDireccionIdDireccion(direcciones.get(i).getIdDireccion());
					cuentaDao.guardarCuentaBancaria(cuenta);

					cuenta.setId(id);
					saveUtils.addoperacion(SaveUtils.operaciones.delete, cuenta, cuenta.getClass());
				}
	    	 }catch(Exception e){
	    		 saveUtils.ejecutarOperacion();
		    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
		    	 throw  myexception;
		     }
	     }
	     
	     
		 
		 return usuarioid;
	 }
	 
	 public String actualizarUsuario(String nuevoUsuario) throws ExceptionPGF {
			
		 ///Creacion de elementos a gusrdar///

		 //___--¨+++********************
		 UsuarioDAOImp usuarioDao;
		 TelefonoDAOImpl telefonoDao;
		 DireccionDAOImpl direccionDao;
		 ActividadDAOImpl actividadDao;
		 RUsuarioActividadDAOImpl ractividadDao;
		 
		 usuarioDao = new UsuarioDAOImp();
	     telefonoDao = new TelefonoDAOImpl();
	     direccionDao = new DireccionDAOImpl();
	     actividadDao = new ActividadDAOImpl();
	     ractividadDao = new RUsuarioActividadDAOImpl();
		 
	     LinkedList<Telefono> listaTelefonos;
	     LinkedList<Direccion> listaDirecciones;
		 
		 Usuario usuario = new Usuario();
		 Usuario usuarioOriginal;
		 
		 Telefono telefono = new Telefono();
		 Telefono telefonoOriginal;
		 
		 Telefono celular = new Telefono();
		 Telefono celularOriginal;
		 
		 Telefono otroTelefono = new Telefono();
		 Telefono otroTelefonoOriginal;
		 
		 Direccion direccionPersonal = new Direccion();
		 Direccion direccionPersonalOriginal;
		 
		 Direccion direccionFiscal = new Direccion();
		 Direccion direccionFiscalOriginal;
		 
		 
		 int actividades[];
		 LinkedList <RUsuarioActividad> actividadesOriginales;
		 
		 CuentaBancaria cuentaPersonal = new CuentaBancaria();
		 CuentaBancaria cuentaFiscal = new CuentaBancaria();
		 
		 
		 JsonObject usuarioObject = MiJson.cargarObjecto(nuevoUsuario);
		 
		 usuarioOriginal = usuarioDao.obtenerUsusario((Integer)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("idUsuario"),Integer.class));
		 usuario.setIdRolUsuario(usuarioOriginal.getIdUsuario());
		 
		 usuario.setNombre( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("nombre"),String.class));
		 usuario.setApellido( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("apellido"),String.class));
		 
		 UsuarioHelper.asignarRol(usuario, (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("tipo"),String.class));
		 
		 
		 /*********************  Telefono  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.telefono);
		 if(listaTelefonos == null)
			 telefonoOriginal = null;
		 else
			 telefonoOriginal = listaTelefonos.get(0);
		 
		 telefono.setNumTelefono((Integer)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("telefono"),Integer.class));
		 
		 
		 /*********************  Celular  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.celular);
		 if(listaTelefonos == null)
			 celularOriginal = null;
		 else
			 celularOriginal = listaTelefonos.get(0);
		 
		 celular.setNumTelefono((Integer)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("celular"),Integer.class));
		 
		 
		 /*********************  Otro Telefono  *********************/
		 listaTelefonos = telefonoDao.obtenerTelefonosPorUsuario(usuarioOriginal.getIdUsuario(), TelefonoDAOImpl.tipo_Telefono.celular);
		 if(listaTelefonos == null)
			 otroTelefonoOriginal = null;
		 else
			 otroTelefonoOriginal = listaTelefonos.get(0);
		 
		 otroTelefono.setNumTelefono((Integer)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("otroTelefono"),Integer.class));
    
		 usuario.setEmail( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("correo"),String.class));
		 
		 /*********************  Dirección Personal  *********************/
		 listaDirecciones = direccionDao.obtenerDireccionesPorUsuario(usuarioOriginal.getIdUsuario(), DireccionDAOImpl.tipo_Direccion.personal );
		 if(listaDirecciones == null){
			 System.out.println("Direcciones personales es nulo Perosnales");
			 direccionPersonalOriginal = null;
		 }else
			 direccionPersonalOriginal = listaDirecciones.get(0);
		 
		 
		 direccionPersonal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionPersonal"));
		 
		 /*********************  Dirección Fiscal  *********************/
		 listaDirecciones = direccionDao.obtenerDireccionesPorUsuario(usuarioOriginal.getIdUsuario(), DireccionDAOImpl.tipo_Direccion.fiscal );
		 if(listaDirecciones == null){
			 System.out.println("Direcciones personales es nulo Fiscales");
			 direccionFiscalOriginal = null;
		 }else
			 direccionFiscalOriginal = listaDirecciones.get(0);
		 
		 
		 direccionFiscal = JsonAObjetoHelpers.jsonADireccion( usuarioObject.get("direccionFiscal"));
		 
	     usuario.setIfe( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("ife"),String.class));
		 
		 
		 JsonArray actividadesArray = (JsonArray) JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("actividades"),JsonArray.class); //usuarioObject.getJsonArray("actividades");
		 actividadesOriginales = ractividadDao.obtenerActividadesPorUsuario(usuarioOriginal.getIdUsuario());
	     actividades = new int[actividadesArray.size()];
	     for (int i=0;i< actividadesArray.size(); i++){
	    	 JsonObject actividad  = actividadesArray.getJsonObject(i);
	    	 actividades[i] = Integer.valueOf(actividad.getString("id"));
	     }
		 
	     cuentaPersonal = JsonAObjetoHelpers.jsonACuentaBancaria( usuarioObject.get("cuentaPersonal"));
	     cuentaFiscal = JsonAObjetoHelpers.jsonACuentaBancaria( usuarioObject.get("cuentaFiscal"));
	     
	     usuario.setActaHacienda( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("actaHacienda"),String.class));
	     usuario.setActaConstitutiva( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("actaConstitutiva"),String.class));
	     
	     
	     usuario.setInfraestructura( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("infraestructura"),String.class));
	     usuario.setDatosFiscales( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("datosFiscales"),String.class));
	     usuario.setCapacidadGeneral( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("capacidadGeneral"),String.class));
	     usuario.setCapacidadFinanciera( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("capacidadFinanciera"),String.class));
	     usuario.setCalificacionGlobal( (Integer)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("calificacionGlobal"),Integer.class));
	     usuario.setContrasena( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("contraseña"),String.class));
	     usuario.setStatus( (String)  JsonAObjetoHelpers.jsonAObjeto(usuarioObject.get("estatus"),String.class));	  	
	  	 
	     
	     int usuarioid=0;
	     /*********************  Guardar Usuario  *********************/
	     
	     try{
	     	usuario.setIdUsuario(usuarioOriginal.getIdUsuario());
	    	usuarioDao.actualizarUsuario(usuario);
	    	saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, usuarioOriginal, usuario.getClass() );
	     
	     
	     
	     
	     int telefonoid;
	     int celularid;
	     int otroTelefonoid;
	     /*********************  Guardar Telefonos  *********************/
	     
	     if(telefono.getNumTelefono()!=0){
	    	 TelefonoId telefonoId = new TelefonoId();
	    	 telefonoId.setUsuarioIdUsuario(usuario.getIdUsuario());
	    	 telefono.setId(telefonoId);
	    	 telefono.setTipoTelefono("telefono");
	    	 
	    	 if(telefonoOriginal == null){
	    		 telefonoDao.guardarTelefono(telefono);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.delete, telefono, celular.getClass() );
	    	 }else{
	    		 telefono.setId(telefonoOriginal.getId());
	    		 telefonoDao.actualizarTelefono(telefono);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, telefonoOriginal, telefono.getClass() );
	    	 }

	     }
	     
	     
	     if(celular.getNumTelefono()!=0){
	    	 TelefonoId celularId = new TelefonoId();
	    	 celularId.setUsuarioIdUsuario(usuarioid);
	    	 celular.setId(celularId);
	    	 celular.setTipoTelefono("celular");
	    	 
	    	 if(celularOriginal == null){
	    		 telefonoDao.guardarTelefono(celular);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.delete, celular, celular.getClass() );
	    	 }else{
	    		 celular.setId(celularOriginal.getId());
	    		 telefonoDao.actualizarTelefono(celular);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, telefonoOriginal, telefono.getClass() );
	    	 }
	     }
	     
	     if(otroTelefono.getNumTelefono()!=0){
	    	 TelefonoId otroTelefonoId = new TelefonoId();
	    	 otroTelefonoId.setUsuarioIdUsuario(usuarioid);
	    	 otroTelefono.setId(otroTelefonoId);
	    	 otroTelefono.setTipoTelefono("otroTelefono");
	    	 
	    	 if(otroTelefonoOriginal == null){
	    		 telefonoDao.guardarTelefono(celular);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.delete, celular, celular.getClass() );
	    	 }else{
	    		 otroTelefono.setId(otroTelefonoOriginal.getId());
	    		 telefonoDao.actualizarTelefono(otroTelefono);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, otroTelefonoOriginal, telefono.getClass() );
	    	 }
	    }
	     
	     
	     DireccionId direccionPersonalid;
	     DireccionId direccionFiscalid;
	     /*********************  Guardar Direcciones  *********************/
	     if(direccionPersonal != null){
	    	 DireccionId direccionId = new DireccionId();
	    	 direccionId.setCTipoDireccionIdCTipoDireccion(1);
	    	 direccionId.setUsuarioIdUsuario(usuarioOriginal.getIdUsuario());
	    	 
	    	 
	    	 if(direccionPersonalOriginal == null){
	    		 //direccionPersonal.setId(direccionId);
	    		 //direccionPersonalid = direccionDao.guardarDireccion(direccionPersonal);
		    	 saveUtils.addoperacion(SaveUtils.operaciones.delete, direccionPersonal, direccionPersonal.getClass() );
	    	 }else{
	    		 //direccionPersonal.setId(direccionPersonalOriginal.getId());
	    		 direccionDao.actualizarDireccion(direccionPersonal);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, direccionPersonalOriginal, direccionPersonalOriginal.getClass() );
	    	 }
	    	 
	     }
	     
	     if(direccionFiscal != null){
	    	 DireccionId direccionId = new DireccionId();
	    	 direccionId.setCTipoDireccionIdCTipoDireccion(2);
	    	 direccionId.setUsuarioIdUsuario(usuarioOriginal.getIdUsuario());
	    	 
	    	 
	    	 if(direccionFiscalOriginal == null){
	    		 //direccionFiscal.setId(direccionId);
		    	 //direccionFiscalid = direccionDao.guardarDireccion(direccionFiscal);
		    	 saveUtils.addoperacion(SaveUtils.operaciones.delete, direccionFiscal, direccionFiscal.getClass() );
	    	 }else{
	    		 //direccionFiscal.setId(direccionPersonalOriginal.getId());
	    		 direccionDao.actualizarDireccion(direccionFiscal);
	    		 saveUtils.addoperacion(SaveUtils.operaciones.desactualizar	, direccionFiscalOriginal, direccionFiscalOriginal.getClass() );
	    	 }
	     }
	     
	     if(actividadesOriginales!=null)
	     for(int i = 0; i< actividadesOriginales.size() ; i++){
	    	 System.out.println("Eliminando: \nUsuario->"+actividadesOriginales.get(i).getId().getIdContratista()+"\nActividad->"+actividadesOriginales.get(i).getId().getIdActividad());
	    	 ractividadDao.eliminarRUsuarioActividad(actividadesOriginales.get(i));
	    	 saveUtils.addoperacion(SaveUtils.operaciones.save, actividadesOriginales.get(i), actividadesOriginales.get(i).getClass() );
	     }
	     for(int i = 0; i<actividades.length;i++){
	    	 System.out.println(actividades.length);
	    	 RUsuarioActividad ractividad = new RUsuarioActividad();
	    	 RUsuarioActividadId ractividadid = new RUsuarioActividadId();
	    	 
	    	 ractividadid.setIdActividad(actividades[i]);
	    	 ractividadid.setIdContratista(usuario.getIdUsuario());
	    	 
	    	 ractividad.setId(ractividadid);
	    	 
	    	 System.out.println("Guardando: \n\tUsuario->"+ ractividad.getId().getIdContratista()+"\n\tActividad->"+ractividad.getId().getIdActividad());
	    	 ractividadDao.guardarRUsuarioActividad(ractividad);
	    	 saveUtils.addoperacion(SaveUtils.operaciones.delete, ractividad, ractividad.getClass() );
	    	 
	     }
	     return this.obtenerUsuariosPorId(String.valueOf(usuario.getIdUsuario()));
	    // return this.obtenerUsuariosPorId(String.valueOf(usuarioid));
	     }catch(Exception e){
	    	 saveUtils.ejecutarOperacion();
	    	 e.printStackTrace();
	    	 ExceptionPGF myexception = new ExceptionPGF(""+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
	     }
	    }
	 
	 public void eliminarUsuario(String idUsuario) throws ExceptionPGF {
		 UsuarioDAOImp usuarioDao;
		 usuarioDao = new UsuarioDAOImp();
		 try{
		 	Usuario usuario = usuarioDao.obtenerUsusario(Integer.valueOf(idUsuario));
		 	String preStatus = usuario.getStatus();
		 	usuario.setStatus("Eliminado");
		 	usuarioDao.actualizarUsuario(usuario);
		 	usuario.setStatus(preStatus);
		 	saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, usuario, usuario.getClass() );
		 }catch(Exception e){
			 saveUtils.ejecutarOperacion();
	    	 ExceptionPGF myexception = new ExceptionPGF("No se ha podido eliminar el usuario: "+e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
		 }
	 }


	public SaveUtils getSaveUtils() {
		return saveUtils;
	}
	
}
