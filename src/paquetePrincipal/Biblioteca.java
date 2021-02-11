package paquetePrincipal;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {

	// private long idBiblioteca; // ya que solo modelamos 1 biblioteca, estos no
	// hacen falta
	// private String nombre;
	private ArrayList<Libro> libros;
	private ArrayList<Lector> lectores;
	private ArrayList<Prestamo> prestamos;

	public Biblioteca() {

	}

	public ArrayList<Libro> getLibros() {
		return libros;
	}

	public ArrayList<Lector> getLectores() {
		return lectores;
	}

	public ArrayList<Prestamo> getPrestamos() {
		return prestamos;
	}

	public void agregarLibro(Libro libroNuevo) {
		this.libros.add(libroNuevo);
	}

	public void agregarLector(Lector lectorNuevo) {
		this.lectores.add(lectorNuevo);
	}

	/**
	 * verificamos que no tenga mas de 2 prestamos activos o si alguno de ellos esta
	 * vencido * @param prestamo * @return
	 */
	public boolean veriicarPrestamoValido(Prestamo prestamo) {
		int cantPrestamos = 0;

		for (int i = 0; i < this.prestamos.size(); i++) {
			if (prestamos.get(i).getNroSocio() == prestamo.getNroSocio()) {
				// si la fecha fin de este prestamo esta vencida, no le dejo alquilar
				if (prestamos.get(i).getFin().isBefore(LocalDate.now()))
					return false;
				cantPrestamos++;
			}
		}

		if (cantPrestamos > 2)
			return false;
		else
			return true;
	}
	
	public void agregarPrestamo(Prestamo prestamo) {
		
		if(this.veriicarPrestamoValido(prestamo) == false) {
			System.out.println("Error al registrar prestamo, el lector tiene 3 prestamos activos o alguno de sus prestamos esta vencido");
			return;
		}
		
		this.prestamos.add(prestamo);
		
		// le cambio el estado a la copia
		for(int i = 0; i < this.libros.size(); i++) {
			if(this.libros.get(i).getISBN() == prestamo.getIsbnLibro()) {
				this.libros.get(i).getCopiaPorId(prestamo.getNroCopia());
				return;
			}
			
		}
	}
	
	

}
