package ixpan.pgf.delegate;

import java.util.LinkedList;

import javax.json.JsonObject;

import ixpan.pgf.exception.ExceptionPGF;
import ixpan.pgf.helpers.JsonAObjetoHelpers;
import ixpan.pgf.jsonutilities.MiJson;
import ixpan.pgf.model.Obra;
import ixpan.pgf.model.RContratistaObra;
import ixpan.pgf.model.Cliente;
import ixpan.pgf.model.Contratista;
import ixpan.pgf.model.Direccion;
import ixpan.pgf.model.Interno;

public class ObraDelegateImpl implements ObraDelegate {

	@Override
	public String altaObra(String nuevaObra) throws ExceptionPGF {
		
		JsonObject obraObject = MiJson.cargarObjecto(nuevaObra);
		JsonAObjetoHelpers jsonHelper = new JsonAObjetoHelpers(); 
		jsonHelper.setJsonObjeto(obraObject);
		 
		Obra obra = jsonHelper.jsonAObra();
		Direccion direccion = jsonHelper.jsonADireccion();
		Cliente clienet = jsonHelper.jsonACliente();
		LinkedList<Interno> gerentes = jsonHelper.jsonAInternos("gerentes");
		LinkedList<Interno> residentes = jsonHelper.jsonAInternos("residentes");
		LinkedList<RContratistaObra> proveedores = jsonHelper.jsonARContratistaObra();
		
		
		/****************** Guardar Direccion ******************/
		
		
		
		/****************** Guardar Obra ******************/
		
		/****************** Guardar Direccion ******************/
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
