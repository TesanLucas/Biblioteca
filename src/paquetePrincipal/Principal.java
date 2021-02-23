package paquetePrincipal;

import java.time.LocalDate;
import excepciones.LibroException;
import excepciones.PrestamoException;

public class Principal {

	public static void main(String[] args) throws LibroException, PrestamoException {

		Biblioteca biblioteca = new Biblioteca();
		Lector lectorA = new Lector(0, "lectorA", null, null);
		Lector lectorB = new Lector(1, "lectorB", null, null);
		Lector lectorC = new Lector(2, "lectorC", null, null);
		
		Autor borges = new Autor("Borges", "Argentina", LocalDate.of(1899, 1, 1));
		Autor garciaMarquez = new Autor("Garcia Marquez", "Colombia", LocalDate.of(1927, 2, 2));
		Autor poe = new Autor("Edgar Allan Poe", "EE-UU", LocalDate.of(1809, 3, 3));
				
		Libro libro1 = new Libro("000-001", "libro1", tipoLibro.ENSAYO, "alba", 1999, borges);
		Libro libro2 = new Libro("000-002", "libro2", tipoLibro.NOVELA, "Aguila", 1998, garciaMarquez);
		Libro libro3 = new Libro("000-003", "libro3", tipoLibro.POESIA, "Anagrama", 1997, poe);
		
		Copia copia11 = new Copia(1, estadoCopia.BIBLIOTECA);
		Copia copia12 = new Copia(2, estadoCopia.BIBLIOTECA);
		Copia copia13 = new Copia(3, estadoCopia.REPARACION);
		Copia copia14 = new Copia(4, estadoCopia.RETRASO);
		
		Copia copia21 = new Copia(1, estadoCopia.BIBLIOTECA);
		Copia copia22 = new Copia(2, estadoCopia.BIBLIOTECA);
		Copia copia23 = new Copia(3, estadoCopia.REPARACION);
		Copia copia24 = new Copia(4, estadoCopia.RETRASO);
		
		Copia copia31 = new Copia(1, estadoCopia.BIBLIOTECA);
		Copia copia32 = new Copia(2, estadoCopia.BIBLIOTECA);
		Copia copia33 = new Copia(3, estadoCopia.REPARACION);
		Copia copia34 = new Copia(4, estadoCopia.RETRASO);
		
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
		
		/*
		biblioteca.agregarLector(lectorA);
		biblioteca.agregarLector(lectorB);
		biblioteca.agregarLector(lectorC);
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarLibro(libro3);
		*/
		
		/*	// ejemplo de prueba
		Prestamo prestamo1;
		Prestamo prestamo2;
		Prestamo prestamo3;
				
		String libroDeseado = lectorA.obtenerNombreDeLibro();
		prestamo1 = lectorA.prestar(biblioteca.getLibros(), libroDeseado);
		if(prestamo1 != null) {			
			biblioteca.agregarPrestamo(prestamo1);
		}
		else
			System.out.println("error al agregar prestamo");		
		
		libroDeseado = lectorB.obtenerNombreDeLibro();
		prestamo2 = lectorB.prestar(biblioteca.getLibros(), libroDeseado);
		if(prestamo2 != null) {			
			biblioteca.agregarPrestamo(prestamo2);
		}
		else
			System.out.println("error al agregar prestamo");
		
		
		libroDeseado = lectorB.obtenerNombreDeLibro();
		prestamo3 = lectorC.prestar(biblioteca.getLibros(), libroDeseado);
		if(prestamo3 != null) {			
			biblioteca.agregarPrestamo(prestamo3);
		}
		else
			System.out.println("error al agregar prestamo");
		
		
		biblioteca.mostrarPrestamos();
	*/
		/*
		lectorA.persistir();
		lectorB.persistir();
		lectorC.persistir();
		
		//borges.persistir();	
		//garciaMarquez.persistir();
		//poe.persistir();
		
		copia11.persistir();
		copia12.persistir();
		copia13.persistir();
		copia14.persistir();
		
		copia21.persistir();
		copia22.persistir();
		copia23.persistir();
		copia24.persistir();
		
		copia31.persistir();
		copia32.persistir();
		copia33.persistir();
		copia34.persistir();
		
		libro1.persistir();
		libro2.persistir();
		libro3.persistir();
		*/
		
		biblioteca.obtenerInstanciasDeLibro();
		biblioteca.obtenerInstanciasDeLectores();
		biblioteca.obtenerInstanciasDePrestamos();
		funcionesPrincipal funciones = new funcionesPrincipal();
		funciones.alquilarLibro(lectorA, biblioteca);
	}
	
	public static boolean alquilarLibro(Lector lector, Biblioteca biblioteca) {
		
		String libroDeseado = lector.obtenerNombreDeLibro();
		try {
			
			//obtenemos el prestamo deseado y la copia que se va a prestar
			
			System.out.println(biblioteca.getLibros().get(0).getTitulo());
			Prestamo prestamoDeseado = lector.prestar(biblioteca.getLibros(), libroDeseado);
			Copia copiaPrestada = biblioteca.agregarPrestamo(prestamoDeseado);
			
			//persistimos prestamo y actualizamos la copia
			prestamoDeseado.persistir();
			copiaPrestada.actualizarCopiaEnBD();	//updateamos en la BD

			
		} catch (LibroException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (PrestamoException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	
	public static boolean devolverLibro(Lector lector, Biblioteca biblioteca) {
		
		String isbn = lector.obtenerIsbnADevolver();
		long idCopia = lector.obtenerIdCopiaADevolver(biblioteca.getPrestamos());
		
		try {
			Prestamo prestamoADevolver = lector.devolver(biblioteca.getPrestamos(), isbn, idCopia);
			prestamoADevolver.borrarDeLaBD();
			
			Copia copiaPrestada = biblioteca.finalizarPrestamo(prestamoADevolver);
			copiaPrestada.actualizarCopiaEnBD();
			
		} catch (LibroException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	
	
	

}
