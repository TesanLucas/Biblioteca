package excepciones;

public class LibroException extends Exception {

	
	private static final long serialVersionUID = 1L;
	public LibroException() {
		super();
	}
	
	public LibroException(String mensaje) {
		super(mensaje);
	}
	
}
