package paquetePrincipal;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

@Entity
public class Copia implements Serializable{

	//private String isbnLibro;		// si uso persistencia creo que no hace falta
	@Id
	private long idCopia;
	private estadoCopia estado;
	
	public Copia(long idCopia, estadoCopia estado) {
		super();
		this.idCopia = idCopia;
		this.estado = estado;
	}
	
	public long getIdCopia() {
		return idCopia;
	}
	
	public estadoCopia getEstado() {
		return estado;
	}
	
	public void cambiarEstadoCopia(estadoCopia estadoNuevo) {
		this.estado = estadoNuevo;
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
			System.out.println("error al persistir la copia: " + this.getIdCopia());
			//System.out.println(e.getCause());
		}
	}
	
	
}
