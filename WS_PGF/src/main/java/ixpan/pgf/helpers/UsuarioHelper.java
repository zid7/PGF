package ixpan.pgf.helpers;

import ixpan.pgf.daoPrincipal.CRolUsuarioDAOImp;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.CRolUsuario;
import ixpan.pgf.model.Usuario;

public class UsuarioHelper {
	
	public static enum tipo_Usuario{Administrador,Interno, Cliente, Contratista};
	
	public static tipo_Usuario asignarRol(Usuario u, String rol) throws ExceptionPGF{
		CRolUsuarioDAOImp cRolUsuarioDao = new CRolUsuarioDAOImp();
		
		CRolUsuario rolBase = cRolUsuarioDao.obtenerCRolUsuarioPorRol(rol);
		
		if(rolBase == null){
			ExceptionPGF myException = new ExceptionPGF("Rol de tipo: " + rol + " no existe en la BD" ,ExceptionPGF.Errores.Entidad_No_Existente);
        	throw myException;
		}
		u.setIdRolUsuario(rolBase.getIdCRolUsuario());
		return tipo_Usuario.valueOf(rolBase.getNombre());
	}
	
	
	
}
