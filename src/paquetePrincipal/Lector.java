package paquetePrincipal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import excepciones.LibroException;
import utiles.ScannerConsola;

@Entity
public class Lector implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private long nroSocio;
	
	private String nombre;
	private String telefono;
	private String direccion;
	@Embedded
	private Multa multa;

	public Lector() {
		
	}
	
	public Lector(long nroSocio, String nombre, String telefono, String direccion) {
		super();
		this.nroSocio = nroSocio;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
		this.multa = new Multa(null, null, this.nroSocio);
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}

	public long getNroSocio() {
		return nroSocio;
	}

	public long getCantidadDiasDeMulta() {
		return LocalDate.now().until(multa.getFin(), ChronoUnit.DAYS);
	}

	public String obtenerNombreDeLibro() {

		ScannerConsola scannerConsola = new ScannerConsola();
		System.out.println("ingrese el libro que quiere alquilar: ");
		String nombreLibro = scannerConsola.nextLine();
		return nombreLibro;
	}
	
	public String obtenerIsbnADevolver() {
		ScannerConsola sc = new ScannerConsola();
		System.out.println("ingrese el libro que quiere devolver: ");
		String isbnLibro = sc.nextLine();
		return isbnLibro;
	}
	
	
	public long obtenerIdCopiaADevolver(ArrayList<Prestamo> prestamos) {	
		ScannerConsola sc = new ScannerConsola();
		long idCopia;
		System.out.println("ingrese la copia que quiere devolver: ");
		idCopia = sc.nextLong();
		return idCopia;
	}
	
	public void mostrarLibrosAlquilados(ArrayList<Prestamo> prestamos) {
		boolean tieneAlMenosUnPrestamo = false;
		System.out.println("LISTA DE LIBROS ALQUILADOS: ");
		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.get(i).getNroSocio() == this.nroSocio) {
				System.out.println(prestamos.get(i).toString());
				tieneAlMenosUnPrestamo = true;
			}
		}
		if (tieneAlMenosUnPrestamo == false) 
			System.out.println("sin libros alquilados");
	}
	
	

	public boolean puedeAlquilar() {
		LocalDate fechaActual = LocalDate.now();

		if (this.multa.getInicio() != null) // si hay multa
		{
			if (this.multa.getFin().isBefore(fechaActual) == true) // si todavia no termino la multa
			{
				return false;
			} else { // si ya termino la multa, retorno true y borro la multa (ya que no existe un
				// historico de multas)
				this.multa = new Multa(null, null, this.nroSocio);
				return true;
			}
		} else // no hay multa
			return true;
	}

	/**
	* en realidad, cuando vas a una blblioteca vos pedis un libro, y ellos te dan
	* una copia
	* vos realmente no elegis una copia, elegis un libro.
	* cuando vas a devolver, ahi si devolves la copia
	**/
	public Prestamo prestar(ArrayList<Libro> libros, String libroDeseado) throws LibroException {
		if (puedeAlquilar() == false) {
			System.out.println("usted esta multado, no puede alquilar");
			return null;
		}
		Libro libroAPrestar = null;
		Copia copiaAPrestar = null;

		// busco el libro
		for (int i = 0; i < libros.size(); i++) {
			if (libros.get(i).getTitulo().equals(libroDeseado)) {
				libroAPrestar = libros.get(i);
				break;
			}
		}

		if (libroAPrestar == null)
			throw new LibroException("libro inexistente, intentalo denuevo");

		// busco la primera copia que pueda prestar
		for(int i = 0;i<libroAPrestar.getCopias().size();i++)
		{
			if (libroAPrestar.getCopias().get(i).getEstado() == estadoCopia.BIBLIOTECA) {
				copiaAPrestar = libroAPrestar.getCopias().get(i);
				break;
			}
		}

		if(copiaAPrestar==null)
			throw new LibroException("no hay disponible una copia para el libro pedido");

		System.out.println("le vamos a prestar la siguiente copia del libro: "+copiaAPrestar.getIdCopia());

		// fechas:
		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaFin = fechaHoy.plusDays(30);

		// preparamos el prestamo
		Prestamo prestamo = new Prestamo(libroAPrestar.getISBN(), copiaAPrestar.getIdCopia(), this.nroSocio);
		// para no tener constructores tan largos uso los sets
		prestamo.setInicio(fechaHoy);prestamo.setFin(fechaFin);

		return prestamo;
	}

	public Prestamo devolver(ArrayList<Prestamo> prestamos, String isbnLibro, long idCopia) throws LibroException {

		for (int i = 0; i < prestamos.size(); i++) {
			if (prestamos.get(i).getNroSocio() == this.nroSocio && prestamos.get(i).getIsbnLibro().equals(isbnLibro)
					&& prestamos.get(i).getNroCopia() == idCopia) {
				this.multar(prestamos.get(i)); // verificamos si hace falta multar, y si es asi, lo hacemos
				return prestamos.get(i);
			}
		}
		throw new LibroException("no se ha encontrado el prestamo a devolver, intentelo denuevo");
	}

	
	private void multar(Prestamo prestamo) {
		LocalDate fechaActual = LocalDate.now();
		LocalDate fechaFin;

		// calculamos la cantidad de dias de multa
		long cantDiasDeMulta = fechaActual.until(prestamo.getFin(), ChronoUnit.DAYS) * 2;
		if (cantDiasDeMulta < 0)
			cantDiasDeMulta *= -1;

		if (this.multa == null) {
			fechaFin = fechaActual.plusDays(cantDiasDeMulta);
			this.multa = new Multa(fechaActual, fechaFin, this.nroSocio);
		} else {
			this.multa.agregarDias(cantDiasDeMulta);
		}
	}
	
	public void persistir() {
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		
		try {

			EntityTransaction tran = em.getTransaction();
			tran.begin();
			em.persist(this);
			tran.commit();
			em.close();

		} catch (RollbackException e) {
			System.out.println("error al persistir el lector: " + this.nombre);
			//System.out.println(e.getCause());
		}
	}
	
}
