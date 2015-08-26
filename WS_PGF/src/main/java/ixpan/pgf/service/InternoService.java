package ixpan.pgf.service;

import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Usuario;

public interface InternoService {
	public int registrarNuevoInterno(Usuario usuario, Interno interno) throws ExceptionPGF;
}
