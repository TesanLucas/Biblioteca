package paquetePrincipal;

import excepciones.LibroException;
import excepciones.PrestamoException;

public class funcionesPrincipal {

	public funcionesPrincipal() {
		
		
	}
	
	
public boolean alquilarLibro(Lector lector, Biblioteca biblioteca) {
		
		String libroDeseado = lector.obtenerNombreDeLibro();
		try {
			
			//obtenemos el prestamo deseado y la copia que se va a prestar
			
			//System.out.println(biblioteca.getLibros().get(0).getTitulo());
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
	
	
	public boolean devolverLibro(Lector lector, Biblioteca biblioteca) {
		
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
