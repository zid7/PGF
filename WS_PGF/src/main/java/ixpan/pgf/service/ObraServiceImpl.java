package ixpan.pgf.service;

import java.util.LinkedList;

import ixpan.pgf.daoPrincipal.RConratistaObraDao;
import ixpan.pgf.daoPrincipal.DireccionDAOImpl;
import ixpan.pgf.daoPrincipal.InternoDAOImpl;
import ixpan.pgf.daoPrincipal.ObraDAOImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Obra;
import ixpan.pgf.model.RContratistaObra;
import ixpan.pgf.model.RContratistaObraId;

public class ObraServiceImpl implements ObraService {

	SaveUtils saveUtils;
	
	public int altaObra(Obra obra, Cliente cliente, Direccion direccion, LinkedList<Interno> gerentes, LinkedList<Interno> residentes,LinkedList<RContratistaObra> contratistas) throws ExceptionPGF {
		ObraDAOImpl obraDao;
		DireccionDAOImpl direccionDao;
		InternoDAOImpl internoDao;
		RConratistaObraDao rContratistaObraDao;
		 
		obraDao = new ObraDAOImpl();
		direccionDao = new DireccionDAOImpl();
		internoDao = new InternoDAOImpl();
		rContratistaObraDao = new RConratistaObraDao();
		
		saveUtils = new SaveUtils();
		
		
		/*********************  Guardar Direccion  *********************/
		if (direccion != null) {
			int id;
			id = direccionDao.guardarDireccion(direccion);
			direccion.setIdDireccion(id);
			saveUtils.addoperacion(SaveUtils.operaciones.delete, direccion, direccion.getClass());
		}else{
			ExceptionPGF myexception = new ExceptionPGF("Se requiere una direccion para guardar una obra", ExceptionPGF.Errores.Guardar_Entidad);
	    	 throw  myexception;
		}
		
		obra.setDireccion(direccion.getIdDireccion());
		obra.setClienteIdCliente(cliente.getIdCliente());
		
		/*********************  Guardar Obra  *********************/
		try {
			int id;
			id = obraDao.guardarObra(obra);
			obra.setIdObra(id);
			saveUtils.addoperacion(SaveUtils.operaciones.delete, obra, obra.getClass());
		} catch (ExceptionPGF e) {
			saveUtils.ejecutarOperacion();
			ExceptionPGF myexception = new ExceptionPGF("" + e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
			throw myexception;
		}
		
		/*********************  Guardar Internos  *********************/
		/*********************  Guardar Coontratistas  *********************/
		try {
			for (int i = 0; i < contratistas.size(); i++) {
				RContratistaObraId id;
				RContratistaObra contratista = contratistas.get(i);
				id = contratista.getId();
				id.setIdObra(obra.getIdObra());
				contratista.setId(id);
				id = rContratistaObraDao.guardarRContratistaObra(contratista);
				contratista.setId(id);
				saveUtils.addoperacion(SaveUtils.operaciones.delete, contratista, contratista.getClass());
			}
		} catch (ExceptionPGF e) {
			saveUtils.ejecutarOperacion();
			ExceptionPGF myexception = new ExceptionPGF("" + e.getMessage(), ExceptionPGF.Errores.Guardar_Entidad);
			throw myexception;
		}
		
				
		return 0;
	}

}
	