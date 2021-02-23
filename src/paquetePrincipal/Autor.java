package paquetePrincipal;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;

@Entity
public class Autor implements Serializable{

	@Id
	private String nombre;
	private String nacionalidad;
	private LocalDate fechaNac;
	
	Autor(){
		
	}
	
	public Autor(String nombre, String nacionalidad, LocalDate fechaNac) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void persistir() {
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		
		EntityManager em = managerFactory.createEntityManager();
		
		EntityTransaction tran = em.getTransaction();
		tran.begin();
		em.persist(this);
		tran.commit();
		em.close();

	}
}
