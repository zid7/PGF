package ixpan.pgf.service;

import ixpan.pgf.daoPrincipal.ClienteDAOImpl;
import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.hibernate.SaveUtils;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Usuario;

public class ClienteServiceImpl implements ClienteService {

	SaveUtils saveUtils;
	
	public SaveUtils getSaveUtils() {
		return saveUtils;
	}

	public int registrarNuevoCliente(Usuario usuario, Cliente cliente) throws ExceptionPGF {
		saveUtils = new SaveUtils(); 
		ClienteDAOImpl clienteDao = new ClienteDAOImpl();
		cliente.setIdUsuario(usuario.getIdUsuario());
		cliente.setIdCliente(clienteDao.guardarCliente(cliente));
		saveUtils.addoperacion(SaveUtils.operaciones.delete, cliente, cliente.getClass() );
		return 1;
		
	}
	public int actualizarCliente(Usuario usuario, Cliente cliente) throws ExceptionPGF {
		Cliente clienteAux;
		saveUtils = new SaveUtils();
		
		
		ClienteDAOImpl clienteDao = new ClienteDAOImpl();
		clienteAux = clienteDao.obtenerClientes(usuario);
		
		cliente.setIdUsuario(clienteAux.getIdUsuario());
		cliente.setIdCliente(clienteAux.getIdCliente());
		clienteDao.actualizarCliente(cliente);
		saveUtils.addoperacion(SaveUtils.operaciones.desactualizar, clienteAux, cliente.getClass() );
		return 1;
		
	}

}
