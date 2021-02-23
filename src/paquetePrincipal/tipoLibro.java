package paquetePrincipal;

public enum tipoLibro {

	NOVELA("novela"), TEATRO("teatro"), POESIA("poesia"), ENSAYO("ensayo");
	
	String tipo;
	
	private tipoLibro(String tipo){
		this.tipo = tipo;
	}
	
	public String getEstado() {
		return this.tipo;
	}
}
