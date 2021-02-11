package paquetePrincipal;

import java.time.LocalDate;
import java.util.Date;

public class Autor {

	private String nombre;
	private String nacionalidad;
	private LocalDate fechaNac;
	
	public Autor(String nombre, String nacionalidad, LocalDate fechaNac) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}
	
	
	
	
	
}
