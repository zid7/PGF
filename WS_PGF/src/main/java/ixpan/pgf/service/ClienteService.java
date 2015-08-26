package ixpan.pgf.service;


import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Usuario;

public interface ClienteService {
	public int registrarNuevoCliente(Usuario usuario, Cliente cliente) throws ExceptionPGF;
}
