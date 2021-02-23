package persistencia;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import paquetePrincipal.Autor;

@WebService
public class AutorDAO {

	public AutorDAO() {
		
	}
	// anio-mes-dia para que parsee bien la fecha
	@WebMethod(operationName = "agregar_autor")
	public void agregarAutor(@WebParam(name = "nombre") String nombre_autor, @WebParam(name = "nacionalidad") String nacionalidad, @WebParam(name = "fecha") String fecha_nac) {
		
		System.out.println("nombre de autor: " + nombre_autor);
		System.out.println("nacionalidad: " + nacionalidad);
		System.out.println("fecha nacimiento: " + fecha_nac);
		LocalDate fecha_nacimiento = LocalDate.parse(fecha_nac);
		Autor nuevoAutor = new Autor(nombre_autor, nacionalidad, fecha_nacimiento);
		nuevoAutor.persistir();
	}
	
	@WebMethod(operationName = "consultar_lectores")
	public void agregarAutor() {
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		ArrayList<Autor> lista = (ArrayList<Autor>) em.createQuery("select a from Autor a").getResultList();
		System.out.println(lista.size() + " autor/es recuperados de la BD");
		
	}
	
	
	
}
