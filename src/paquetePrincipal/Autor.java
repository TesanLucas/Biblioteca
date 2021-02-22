package paquetePrincipal;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

@Entity
public class Autor implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id_autor;
	private String nombre;
	private String nacionalidad;
	private LocalDate fechaNac;

	public Autor(String nombre, String nacionalidad, LocalDate fechaNac) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.fechaNac = fechaNac;
	}

	public long getId() {
		return this.id_autor;
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
			System.out.println("error al persistir el autor: " + this.getNombre());
			//System.out.println(e.getCause());
		}

	}

}
