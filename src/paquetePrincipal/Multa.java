package paquetePrincipal;

import java.time.LocalDate;

public class Multa {

	private LocalDate inicio;
	private LocalDate fin;
	
	public Multa(LocalDate inicio, LocalDate fin) {
		super();
		this.inicio = inicio;
		this.fin = fin;
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
