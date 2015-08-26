package ixpan.pgf.exception;
public class ExceptionPGF extends Exception{
	public Errores errorCode;
	public String mensaje = "";
	
	//public static final int Entidad_Existente = 601; 
	public static enum Errores{Error_Acceso_BD, Error_Hibernate,Entidad_Existente, Entidad_No_Existente, Error_Desconocido, Json_Mal_Formado, Erro_Leer_Json,Guardar_Entidad, Error_RollBack, Cuenta_Bancaria_Direccion,
		Rol_Usuario, Objeto_Requerido};
	
	
	
	
	public ExceptionPGF(String msgDetalles, Errores errorCode) {
		super(String.valueOf(errorCode) +" : " + getMensajeError(errorCode) + msgDetalles);
		mensaje="Error: "+String.valueOf(errorCode) +
				" \nMensaje:  " + getMensajeError(errorCode) +" ( "+ msgDetalles+"). ";
		this.errorCode = errorCode;
	}
	
	public static String getMensajeError(Errores errorCode){
		switch(errorCode){
			case Error_Acceso_BD:
				return "Ocurrio un error en la consulta a la BD: ";
			case Error_Hibernate:
				return "Ocurrió un error en la capa de acceso a datos: ";
			case Entidad_Existente:
				return "La entidad que se desea crear ya existe: ";
			case Entidad_No_Existente:
				return "La entidad que se desea acceder no existe: ";
			case Error_Desconocido:
				return "Error desconocido: ";
			case Json_Mal_Formado:
				return "Campos inexistentes en el objeto json: ";
			case Erro_Leer_Json:
				return "Error al leer Json: ";
			case Guardar_Entidad:
				return "Error al guardar Entidad";
			case Cuenta_Bancaria_Direccion:
				return "No existe una direccion: ";
			case Rol_Usuario:
				return "No coinciden Rol Usuario: ";
			case Objeto_Requerido:
				return "Elemento no Null: ";
			default:
				return "Error aun mas desconocido: ";
		}
		
	}
}