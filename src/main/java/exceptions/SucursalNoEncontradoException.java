package exceptions;

public class SucursalNoEncontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public SucursalNoEncontradoException(String id) {
		super("Sucursal con ID ".concat(id).concat(" no  existe en el sistema"));
		// TODO Auto-generated constructor stub
	}
}
