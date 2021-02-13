package paquetePrincipal;

import java.time.LocalDate;

public class Prestamo {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inicio == null) ? 0 : inicio.hashCode());
		result = prime * result + ((isbnLibro == null) ? 0 : isbnLibro.hashCode());
		result = prime * result + (int) (nroCopia ^ (nroCopia >>> 32));
		result = prime * result + (int) (nroSocio ^ (nroSocio >>> 32));
		return result;
	}

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
		return "Lector: " + this.nroSocio + "  --  libro: " +  this.isbnLibro + "  /  copia: " + this.nroCopia;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (isbnLibro == null) {
			if (other.isbnLibro != null)
				return false;
		} else if (!isbnLibro.equals(other.isbnLibro))
			return false;
		if (nroCopia != other.nroCopia)
			return false;
		if (nroSocio != other.nroSocio)
			return false;
		return true;
	}
	
	
	
}
