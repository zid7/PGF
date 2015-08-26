package ixpan.pgf.service;

import java.util.LinkedList;

import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.model.Obra;
import ixpan.pgf.model.RContratistaObra;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.Interno;
import ixpan.pgf.model.Cliente;

public interface ObraService {
	public int altaObra(Obra obra, Cliente cliente, Direccion direccion,LinkedList<Interno> gerentes, LinkedList<Interno> residentes, LinkedList<RContratistaObra> contratistas) throws ExceptionPGF;

}
