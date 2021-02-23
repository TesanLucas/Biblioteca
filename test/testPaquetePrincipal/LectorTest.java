package testPaquetePrincipal;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excepciones.LibroException;
import excepciones.PrestamoException;
import paquetePrincipal.Autor;
import paquetePrincipal.Copia;
import paquetePrincipal.Lector;
import paquetePrincipal.Libro;
import paquetePrincipal.Multa;
import paquetePrincipal.Prestamo;
import paquetePrincipal.estadoCopia;
import paquetePrincipal.tipoLibro;

public class LectorTest {

	Lector lectorA;

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

	Prestamo prestamo1;
	
	ArrayList<Libro> libros;
	ArrayList<Prestamo> prestamos;
	

	Multa multa;

	@Before
	public void setUp() throws Exception {
		lectorA = new Lector(0, "lectorA", null, null);
		lectorA.setMulta(new Multa(false, null, null, lectorA.getNroSocio()));

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

		libros = new ArrayList<Libro>();
		libros.add(libro1);
		libros.add(libro2);
		libros.add(libro3);

		Prestamo prestamo1 = new Prestamo(libro1.getISBN(), libro1.getCopias().get(0).getIdCopia(),
				lectorA.getNroSocio());
		prestamos = new ArrayList<Prestamo>();
		prestamos.add(prestamo1);

		multa = new Multa(true, LocalDate.now(), LocalDate.of(2020, 1, 1), lectorA.getNroSocio()); // multa vencida en 1999
	}

	@Test
	public void alquilaCorrectamente() {
		Prestamo prestamo = null;
		try {
			prestamo = lectorA.prestar(libros, "libro1");
		} catch (LibroException e) {
			Assert.fail();
		}
		if (prestamo == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}

	@Test
	public void noPuedeAlquilar() {
		
		lectorA.setMulta(multa);
		if (lectorA.puedeAlquilar() == true)
			Assert.fail();
		else
			Assert.assertTrue(true);
	}

	@Test
	public void puedeAlquilar() {
		if (lectorA.puedeAlquilar() == true)
			Assert.assertTrue(true);
		else
			Assert.fail();
	}

	@Test
	public void devuelveCorrectamente() {
		prestamos.get(0).setFin(LocalDate.of(2025,1,1));	// estamos en 2021, tenia tiempo para devolverla!
		try {
			lectorA.devolver(prestamos, "000-001", 1);
		} catch (LibroException e) {
			e.printStackTrace();
			Assert.fail();
		}	
	}

	@Test
	public void multaCorrectamente() {
		prestamos.get(0).setFin(LocalDate.of(2020,1,1));	//fecha de retorno vencida, deberia multar!
		long cantDiasAMultar = LocalDate.now().until(LocalDate.of(2020,1,1), ChronoUnit.DAYS) * -2;
		try {
			lectorA.devolver(prestamos, "000-001", 1);
		} catch (LibroException e) {
			e.printStackTrace();
			Assert.fail();
		}	
		Assert.assertEquals(cantDiasAMultar, lectorA.getCantidadDiasDeMulta());
	}
	
	@Test
	public void alquila2Correctamente() {
		Prestamo prestamo1 = null;
		Prestamo prestamo2 = null;
		try {
			prestamo1 = lectorA.prestar(libros, "libro1");
			prestamo2 = lectorA.prestar(libros, "libro1");
		} catch (LibroException e) {
			Assert.fail();
		}
		if (prestamo1 == null && prestamo2 == null)
			Assert.assertTrue(false);
		else
			Assert.assertTrue(true);
	}
	
	
	
	// deberia hacerse en bibliotecatest

}
