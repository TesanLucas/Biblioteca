package paquetePrincipal;

import java.time.LocalDate;

public class Multa {

	private LocalDate inicio;
	private LocalDate fin;
	private long nroSocio;
	
	public Multa(LocalDate inicio, LocalDate fin, long nroSocio) {
		super();
		this.inicio = inicio;
		this.fin = fin;
		this.nroSocio = nroSocio;
	}
	public long getNroSocio() {
		return nroSocio;
	}
	public LocalDate getInicio() {
		return inicio;
	}
	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}
	public LocalDate getFin() {
		return fin;
	}
	public void setFin(LocalDate fin) {
		this.fin = fin;
	}
	
	public void agregarDias(long dias) {
		this.fin.plusDays(dias);
	}
	
}
