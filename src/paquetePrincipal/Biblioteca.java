package paquetePrincipal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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

	public void cargarLibros() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		this.libros = (ArrayList<Libro>) em.createQuery("select b from Libro b").getResultList();
		System.out.println(this.libros.size() + "libros recuperados de la BD y cargados en memoria correctamente");
		// no se por que me tira el warning, despues lo arreglo
	}

	public void cargarPrestamos() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		this.prestamos = (ArrayList<Prestamo>) em.createQuery("select b from prestamo b").getResultList();
		System.out
				.println(this.prestamos.size() + "prestamos recuperados de la BD y cargados en memoria correctamente");
		// no se por que me tira el warning, despues lo arreglo
	}

	public void cargarLectores() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		this.lectores = (ArrayList<Lector>) em.createQuery("select b from lectores b").getResultList();
		System.out
				.println(this.prestamos.size() + "prestamos recuperados de la BD y cargados en memoria correctamente");
		// no se por que me tira el warning, despues lo arreglo
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

	public Copia agregarPrestamo(Prestamo prestamo) throws PrestamoException {

		if (prestamo == null || this.veriicarPrestamoValido(prestamo) == false) {
			throw new PrestamoException(
					"Error al registrar prestamo, el lector tiene 3 prestamos activos o alguno de sus prestamos esta vencido");
		}
		this.prestamos.add(prestamo);

		// le cambiamos el estado a la copia
		for (int i = 0; i < this.libros.size(); i++) {
			if (this.libros.get(i).getISBN() == prestamo.getIsbnLibro()) {
				this.libros.get(i).getCopiaPorId(prestamo.getNroCopia()).setEstado(estadoCopia.PRESTADO);
				return this.libros.get(i).getCopiaPorId(prestamo.getNroCopia());				
			}
		}
		return null;
	}

	public Copia finalizarPrestamo(Prestamo prestamo) {

		for (int i = 0; i < this.prestamos.size(); i++) {
			if (this.prestamos.get(i).equals(prestamo)) {
				this.prestamos.remove(i);
			}
		}

		for (int i = 0; i < this.libros.size(); i++) {
			if (this.libros.get(i).getISBN() == prestamo.getIsbnLibro()) {
				this.libros.get(i).getCopiaPorId(prestamo.getNroCopia()).setEstado(estadoCopia.BIBLIOTECA);
				return this.libros.get(i).getCopiaPorId(prestamo.getNroCopia());
			}
		}
		return null;
	}

	public void mostrarPrestamos() {

		Prestamo actual;
		Iterator<Prestamo> iterador = this.prestamos.iterator();

		while (iterador.hasNext()) {
			actual = iterador.next();
			System.out.println(actual.toString());
		}
	}

	public void obtenerInstanciasDeLibro() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");

		EntityManager em = managerFactory.createEntityManager();

		this.libros = (ArrayList<Libro>) em.createQuery("select b from Libro b").getResultList();
		System.out.println("cantidad de libros traidos: " + libros.size());
		System.out.println(this.libros.get(0).getTitulo());

	}

	public void obtenerInstanciasDeLectores() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");

		EntityManager em = managerFactory.createEntityManager();

		this.lectores = (ArrayList<Lector>) em.createQuery("select l from Lector l").getResultList();
		System.out.println("cantidad de lectores traidos: " + lectores.size());
		System.out.println(this.lectores.get(0).getNroSocio());

	}

	public void obtenerInstanciasDePrestamos() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");

		EntityManager em = managerFactory.createEntityManager();

		this.prestamos = (ArrayList<Prestamo>) em.createQuery("select p from Prestamo p").getResultList();
		System.out.println("cantidad de prestamos traidos: " + prestamos.size());
		//System.out.println(this.prestamos.get(0).getIsbnLibro());

	}

}
