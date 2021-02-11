package paquetePrincipal;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Principal {

	public static void main(String[] args) {

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
		
		biblioteca.agregarLector(lectorA);
		biblioteca.agregarLector(lectorB);
		biblioteca.agregarLector(lectorC);
		
		biblioteca.agregarLibro(libro1);
		biblioteca.agregarLibro(libro2);
		biblioteca.agregarLibro(libro3);
		
		

	}

}
