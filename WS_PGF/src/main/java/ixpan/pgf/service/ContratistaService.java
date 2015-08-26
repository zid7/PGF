package ixpan.pgf.service;


import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.Contratista;
import ixpan.pgf.model.Usuario;

public interface ContratistaService {
	public int registrarNuevoContratista(Usuario usuario, Contratista contratista, int []actividades) throws ExceptionPGF;
}
