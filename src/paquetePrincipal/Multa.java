package paquetePrincipal;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Multa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private LocalDate inicio;
	private LocalDate fin;
	@Column(name="socio")
	private long nroSocio; // usando esta clase como Embeddable este atributo no haria falta!
	
	public Multa() {
		
	}
	
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
