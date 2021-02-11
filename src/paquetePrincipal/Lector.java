package paquetePrincipal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Lector {

	private long nroSocio;
	private String nombre;
	private String telefono;
	private String direccion;
	private Multa multa;

	public Lector(long nroSocio, String nombre, String telefono, String direccion) {
		super();
		this.nroSocio = nroSocio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.multa = null;
	}

	private boolean puedeAlquilar() {
		LocalDate fechaActual = LocalDate.now();

		if (this.multa != null) // si hay multa
		{
			if (this.multa.getFin().isBefore(fechaActual) == true) // si todavia no termino la multa
			{
				return false;
			} else { // si ya termino la multa, retorno true y borro la multa (ya que no existe un
						// historico de multas)
				this.multa = null;
				return true;
			}
		} else // no hay multa
			return true;
	}

	// en realidad, cuando vas a una blblioteca vos pedis un libro, y ellos te dan
	// una copia
	// vos realmente no elegis una copia, elegis un libro.
	// cuando vas a devolver, ahi si devolves la copia
	public Prestamo prestar(ArrayList<Libro> libros) {
		if (puedeAlquilar() == false) {
			return null;
		}

		Scanner sc = new Scanner(System.in);
		Libro libroAPrestar = null;
		Copia copiaAPrestar = null;

		while (libroAPrestar == null) {

			System.out.println("ingrese el libro que quiere alquilar: ");
			String nombreLibro = sc.nextLine();

			// busco el libro
			for (int i = 0; i < libros.size(); i++) {
				if (libros.get(i).getTitulo() == nombreLibro) {
					libroAPrestar = libros.get(i);
					break;
				}
			}

			if (libroAPrestar == null)
				System.out.println("ERROR: el libro ingresado no existe");
		}
		sc.close();

		// busco la primera copia que pueda prestar
		for (int i = 0; i < libroAPrestar.getCopias().size(); i++) {
			if (libroAPrestar.getCopias().get(i).getEstado() == estadoCopia.BIBLIOTECA) {
				copiaAPrestar = libroAPrestar.getCopias().get(i);
				break;
			}
		} 

		if (copiaAPrestar == null) {
			System.out.println("no hay disponible una copia para el libro pedido");
			return null;
		}

		System.out.println("le vamos a prestar la siguiente copia del libro: " + copiaAPrestar.getIdCopia());

		// fechas:

		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaFin = fechaHoy.plusDays(30);

		Prestamo prestamo = new Prestamo(libroAPrestar.getISBN(), copiaAPrestar.getIdCopia(), this.nroSocio);
		// para no tener constructores tan largos uso los sets
		prestamo.setInicio(fechaHoy);
		prestamo.setFin(fechaFin);

		return prestamo;
	}

	public Prestamo devolver(ArrayList<Prestamo> prestamos) {

		boolean tieneAlMenosUnPrestamo = false;
		System.out.println("LISTA DE LIBROS ALQUILADOS: ");
		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.get(i).getNroSocio() == this.nroSocio) {
				System.out.println(prestamos.get(i).toString());
				tieneAlMenosUnPrestamo = true;
			}
		}

		if (tieneAlMenosUnPrestamo == false) {
			System.out.println("ERROR: no registra ningun libro alquilado");
			return null;
		}

		Scanner sc = new Scanner(System.in);
		System.out.println("ingrese el libro que quiere devolver: ");
		String isbnLibro = sc.nextLine();
		System.out.println("ingrese la copia que quiere devolver: ");
		long idCopia = sc.nextLong();
		sc.close();

		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.get(i).getNroSocio() == this.nroSocio && prestamos.get(i).getIsbnLibro() == isbnLibro
					&& prestamos.get(i).getNroCopia() == idCopia) {
				this.multar(prestamos.get(i)); // verificamos si hace falta multar, y si es asi, lo hacemos
				return prestamos.get(i);
			}
		}
		System.out.println("no se ha encontrado el prestamo a devolver, intentelo denuevo");
		return null;
	}

	private void multar(Prestamo prestamo) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaFin;

		// calculamos la cantidad de dias de multa
		long cantDiasDeMulta = fechaActual.until(prestamo.getFin(), ChronoUnit.DAYS) * 2;

		if (this.multa == null) {
			fechaFin = fechaActual.plusDays(cantDiasDeMulta);
			this.multa = new Multa(fechaActual, fechaFin);
		} else {
			this.multa.agregarDias(cantDiasDeMulta);
		}
	}

}
