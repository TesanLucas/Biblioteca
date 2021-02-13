package paquetePrincipal;

import java.util.ArrayList;

public class Libro {
	private String ISBN;
	private String titulo;
	private tipoLibro tipo;
	private String editorial;
	private int anio;
	private Autor autor;
	private ArrayList<Copia> copias;

	
	public Libro(String iSBN, String titulo, tipoLibro tipo, String editorial, int anio, Autor autor) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.tipo = tipo;
		this.editorial = editorial;
		this.anio = anio;
		this.autor = autor;
		this.copias = new ArrayList<Copia>();
	}
	
	public String getISBN() {
		return ISBN;
	}
	public String getTitulo() {
		return titulo;
	}
	public tipoLibro getTipo() {
		return tipo;
	}
	public String getEditorial() {
		return editorial;
	}
	public int getAnio() {
		return anio;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public ArrayList<Copia> getCopias() {
		return copias;
	}

	public void agregarCopia(Copia copiaNueva) {	
		this.copias.add(copiaNueva);
	}
	
	public Copia getCopiaPorId(long idCopia) {
		for (int i = 0; i < this.copias.size(); i++) {
			if (this.copias.get(i).getIdCopia() == idCopia) {
				return this.copias.get(i);
			}
		}
		return null;
	}
	
	
	
	
}
