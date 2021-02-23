package testPaquetePrincipal;

import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.LibroException;
import excepciones.PrestamoException;
import paquetePrincipal.Autor;
import paquetePrincipal.Biblioteca;
import paquetePrincipal.Copia;
import paquetePrincipal.Lector;
import paquetePrincipal.Libro;
import paquetePrincipal.Prestamo;
import paquetePrincipal.estadoCopia;
import paquetePrincipal.tipoLibro;

public class BibliotecaTest {

	Biblioteca biblioteca = new Biblioteca();
	Lector lectorA;
	Lector lectorB;
	Lector lectorC;

	Autor borges;
	Autor garciaMarquez;
	Autor poe;

	Libro libro1;
	Libro libro2;
	Libro libro3;

	Copia copia11;
	Copia copia12;
	Copia copia13;
	Copia copia14;

	Copia copia21;
	Copia copia22;
	Copia copia23;
	Copia copia24;

	Copia copia31;
	Copia copia32;
	Copia copia33;
	Copia copia34;

	@Before
	public void setUp() throws Exception {
		biblioteca = new Biblioteca();
		lectorA = new Lector(0, "lectorA", null, null);
		lectorB = new Lector(1, "lectorB", null, null);
		lectorC = new Lector(2, "lectorC", null, null);

		borges = new Autor("Borges", "Argentina", LocalDate.of(1899, 1, 1));
		garciaMarquez = new Autor("Garcia Marquez", "Colombia", LocalDate.of(1927, 2, 2));
		poe = new Autor("Edgar Allan Poe", "EE-UU", LocalDate.of(1809, 3, 3));

		libro1 = new Libro("000-001", "libro1", tipoLibro.ENSAYO, "alba", 1999, borges);
		libro2 = new Libro("000-002", "libro2", tipoLibro.NOVELA, "Aguila", 1998, garciaMarquez);
		libro3 = new Libro("000-003", "libro3", tipoLibro.POESIA, "Anagrama", 1997, poe);

		copia11 = new Copia(1, estadoCopia.BIBLIOTECA);
		copia12 = new Copia(2, estadoCopia.BIBLIOTECA);
		copia13 = new Copia(3, estadoCopia.REPARACION);
		copia14 = new Copia(4, estadoCopia.RETRASO);

		copia21 = new Copia(1, estadoCopia.BIBLIOTECA);
		copia22 = new Copia(2, estadoCopia.BIBLIOTECA);
		copia23 = new Copia(3, estadoCopia.REPARACION);
		copia24 = new Copia(4, estadoCopia.RETRASO);

		copia31 = new Copia(1, estadoCopia.BIBLIOTECA);
		copia32 = new Copia(2, estadoCopia.BIBLIOTECA);
		copia33 = new Copia(3, estadoCopia.REPARACION);
		copia34 = new Copia(4, estadoCopia.RETRASO);

		libro1.agregarCopia(copia11);
		libro1.agregarCopia(copia12);
		libro1.agregarCopia(copia13);
		libro1.agregarCopia(copia14);

		libro2.agregarCopia(copia21);
		libro2.agregarCopia(copia22);
		libro2.agregarCopia(copia23);
		libro2.agregarCopia(copia24);

		libro3.agregarCopia(copia31);
		libro3.agregarCopia(copia32);
		libro3.agregarCopia(copia33);
		libro3.agregarCopia(copia34);

		biblioteca.agregarLector(lectorA);
		biblioteca.agregarLector(lectorB);
		biblioteca.agregarLector(lectorC);

		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarLibro(libro3);

	}

	// input: libro1
	@Test
	public void seRegistraPrestamoCorrectamente() throws LibroException, PrestamoException {

		Prestamo prestamo = new Prestamo(libro1.getISBN(), libro1.getCopias().get(0).getIdCopia(),
				lectorA.getNroSocio());
		this.biblioteca.agregarPrestamo(prestamo);
		Assert.assertEquals(estadoCopia.PRESTADO, copia11.getEstado());
	}

	@Test
	public void seRechazaPrestamoCorrectamente() {

		Prestamo prestamo = new Prestamo(libro1.getISBN(), libro1.getCopias().get(0).getIdCopia(),
				lectorA.getNroSocio());
		try {
			biblioteca.agregarPrestamo(prestamo);
		} catch (PrestamoException e) {
			Assert.assertTrue(true);
			return;
		}
	}

	@Test
	public void finalizaPrestamoCorrectamente() {

		Prestamo prestamo = new Prestamo(libro1.getISBN(), libro1.getCopias().get(0).getIdCopia(),
				lectorA.getNroSocio()); // agregamos el unico prestamo a la lista de prestamos
		try {
			biblioteca.agregarPrestamo(prestamo);
		} catch (PrestamoException e) {
			Assert.assertTrue(true);
			return;
		}
		biblioteca.finalizarPrestamo(prestamo);

		if (copia11.getEstado().equals(estadoCopia.BIBLIOTECA)) {

		} else {
			Assert.assertTrue(false);
		}
	}
	
	@Test
	public void seAcabanLasCopiasCorrectamente() {
		copia11.setEstado(estadoCopia.PRESTADO);
		copia12.setEstado(estadoCopia.PRESTADO);
		try {
			lectorA.prestar(biblioteca.getLibros(), "libro1");
		} catch (LibroException e) {
			Assert.assertEquals(e.getMessage(), "no hay disponible una copia para el libro pedido");
			return;
		}	
		Assert.assertTrue(false);
	}
	

}
