package ixpan.pgf.service;

import ixpan.pgf.daoPrincipal.InternoDAOImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.SaveUtils;

import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Usuario;

public class InternoServiceImpl implements InternoService {

SaveUtils saveUtils;
	
	public SaveUtils getSaveUtils() {
		return saveUtils;
	}

	
	public int registrarNuevoInterno(Usuario usuario, Interno interno) throws ExceptionPGF {
		saveUtils = new SaveUtils(); 
		InternoDAOImpl internoDao = new InternoDAOImpl();
		interno.setIdUsuario(usuario.getIdUsuario());
		
		
		interno.setIdInternos(internoDao.guardarInterno(interno));
		saveUtils.addoperacion(SaveUtils.operaciones.delete, interno, interno.getClass() );
		return 1;
	}
	
	public int actualizarInterno(Usuario usuario, Interno interno) throws ExceptionPGF {
		Interno internoAux;
		saveUtils = new SaveUtils(); 
		
		InternoDAOImpl internoDao = new InternoDAOImpl();
		internoAux = internoDao.obtenerInternos(usuario);
		interno.setIdUsuario(usuario.getIdUsuario());
		
		interno.setIdInternos(internoAux.getIdInternos());
		internoDao.actualizarInterno(interno);
		saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, internoAux, internoAux.getClass() );
		return 1;
	}

}
