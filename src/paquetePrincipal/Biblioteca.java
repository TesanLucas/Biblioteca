package paquetePrincipal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import excepciones.LibroException;
import excepciones.PrestamoException;

public class Biblioteca {

	// private long idBiblioteca; // ya que solo modelamos 1 biblioteca, estos no
	// hacen falta
	// private String nombre;
	private ArrayList<Libro> libros;
	private ArrayList<Lector> lectores;
	private ArrayList<Prestamo> prestamos;

	public Biblioteca() {
		this.libros = new ArrayList<Libro>();
		this.lectores = new ArrayList<Lector>();
		this.prestamos = new ArrayList<Prestamo>();
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

	public void agregarPrestamo(Prestamo prestamo) throws PrestamoException {

		if (prestamo == null || this.veriicarPrestamoValido(prestamo) == false) {
			throw new PrestamoException(
					"Error al registrar prestamo, el lector tiene 3 prestamos activos o alguno de sus prestamos esta vencido");
		}

		this.prestamos.add(prestamo);

		// le cambiamos el estado a la copia
		for (int i = 0; i < this.libros.size(); i++) {
			if (this.libros.get(i).getISBN() == prestamo.getIsbnLibro()) {
				this.libros.get(i).getCopiaPorId(prestamo.getNroCopia()).cambiarEstadoCopia(estadoCopia.PRESTADO);
				;
				return;
			}
		}
	}

	public void finalizarPrestamo(Prestamo prestamo) {
		
		for(int i = 0; i < this.prestamos.size(); i++) {	
			if(this.prestamos.get(i).equals(prestamo)) 
				this.prestamos.remove(i);						
		}

		for (int i = 0; i < this.libros.size(); i++) {
			if (this.libros.get(i).getISBN() == prestamo.getIsbnLibro()) {
				this.libros.get(i).getCopiaPorId(prestamo.getNroCopia()).cambiarEstadoCopia(estadoCopia.BIBLIOTECA);
				return;
			}
		}
	}

	public void mostrarPrestamos() {

		Prestamo actual;
		Iterator<Prestamo> iterador = this.prestamos.iterator();

		while (iterador.hasNext()) {
			actual = iterador.next();
			System.out.println(actual.toString());
		}
	}

}
