package paquetePrincipal;

public enum estadoCopia {

	PRESTADO("prestado"), RETRASO("retraso"), BIBLIOTECA("biblioteca"), REPARACION("reparacion");
	
	String estado;
	
	private estadoCopia(String estado){
		this.estado = estado;
	}
	
	public String getEstado() {
		return this.estado;
	}
	
}
