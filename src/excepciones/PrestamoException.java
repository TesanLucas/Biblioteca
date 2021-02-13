package excepciones;

public class PrestamoException extends Exception {

	
	private static final long serialVersionUID = 2L;
	public PrestamoException() {
		super();
	}
	
	public PrestamoException(String mensaje) {
		super(mensaje);
	}
	
}