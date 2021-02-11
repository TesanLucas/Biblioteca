package paquetePrincipal;

import java.time.LocalDate;

public class Prestamo {

	private LocalDate inicio;
	private LocalDate fin;	// fecha maxima para devolver y no tener multa
	private LocalDate finReal;	// fecha que se devolvio
	
	
	private String isbnLibro;
	private long nroCopia;
	private long nroSocio;
	public LocalDate getInicio() {
		return inicio;
	}


	public LocalDate getFin() {
		return fin;
	}


	public LocalDate getFinReal() {
		return finReal;
	}


	public String getIsbnLibro() {
		return isbnLibro;
	}


	public long getNroCopia() {
		return nroCopia;
	}


	public long getNroSocio() {
		return nroSocio;
	}
	
	public Prestamo(String isbnLibro, long nroCopia, long nroSocio) {
		super();
		this.isbnLibro = isbnLibro;
		this.nroCopia = nroCopia;
		this.nroSocio = nroSocio;
	}

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public void setFinReal(LocalDate finReal) {
		this.finReal = finReal;
	}
	
	@Override
	public String toString() {
		return "libro: " +  this.isbnLibro + "  /  copia: " + this.nroCopia;
	}

	
}
