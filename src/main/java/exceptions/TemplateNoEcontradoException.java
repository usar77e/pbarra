package exceptions;

public class TemplateNoEcontradoException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TemplateNoEcontradoException(String id) {
		super("usuario con ID ".concat(id).concat(" no  existe en el sistema"));
		// TODO Auto-generated constructor stub
	}
	
	

}
