package ixpan.pgf.jsonutilities;

import java.io.StringReader;
import java.util.LinkedList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import ixpan.pgf.exception.ExceptionPGF;

public class MiJson  {
	
	public static void add(StringBuilder b, String parametro, String valor ){
		b.append("\"  "+parametro+" \":\""+ ((valor == null) ? "null" : valor )+"\"");
	}
	
	public static JsonObject cargarObjecto(String json) throws ExceptionPGF{
		JsonReader reader;
		JsonObject jsonObject = null;
		try{
			reader = Json.createReader(new StringReader(json));
			jsonObject = reader.readObject();
			reader.close();
		}catch(Exception e){
			ExceptionPGF myEception = new ExceptionPGF(json,ExceptionPGF.Errores.Erro_Leer_Json);
			throw myEception;
		}
		return jsonObject;
	}
	
    
	
}
