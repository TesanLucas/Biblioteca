package paquetePrincipal;

public class Copia {

	private long idCopia;
	private estadoCopia estado;
	
	public Copia(long idCopia, estadoCopia estado) {
		super();
		this.idCopia = idCopia;
		this.estado = estado;
	}
	
	public long getIdCopia() {
		return idCopia;
	}
	
	public estadoCopia getEstado() {
		return estado;
	}
	
	public void cambiarEstadoCopia(estadoCopia estadoNuevo) {
		this.estado = estadoNuevo;
	}
	
	
}
