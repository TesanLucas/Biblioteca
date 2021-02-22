package paquetePrincipal;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

@Entity
public class Prestamo implements Serializable {

	@Id
	private String isbnLibro;
	@Id
	private long nroCopia;
	@Id
	private long nroSocio;
	
	private LocalDate inicio;
	private LocalDate fin;	// fecha maxima para devolver y no tener multa
	private LocalDate finReal;	// fecha que se devolvio

	public Prestamo(String isbnLibro, long nroCopia, long nroSocio) {
		super();
		this.isbnLibro = isbnLibro;
		this.nroCopia = nroCopia;
		this.nroSocio = nroSocio;
	}
	
	public LocalDate getInicio() {
		return inicio;
	}

	public LocalDate getFin() {
		return fin;
	}

	public LocalDate getFinReal() {
		return finReal;
	}

	public String getIsbnLibro() {
		return isbnLibro;
	}

	public long getNroCopia() {
		return nroCopia;
	}

	public long getNroSocio() {
		return nroSocio;
	}
	

	public void setInicio(LocalDate inicio) {
		this.inicio = inicio;
	}

	public void setFin(LocalDate fin) {
		this.fin = fin;
	}

	public void setFinReal(LocalDate finReal) {
		this.finReal = finReal;
	}
	
	@Override
	public String toString() {
		return "Lector: " + this.nroSocio + "  --  libro: " +  this.isbnLibro + "  /  copia: " + this.nroCopia;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prestamo other = (Prestamo) obj;
		if (inicio == null) {
			if (other.inicio != null)
				return false;
		} else if (!inicio.equals(other.inicio))
			return false;
		if (isbnLibro == null) {
			if (other.isbnLibro != null)
				return false;
		} else if (!isbnLibro.equals(other.isbnLibro))
			return false;
		if (nroCopia != other.nroCopia)
			return false;
		if (nroSocio != other.nroSocio)
			return false;
		return true;
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
			System.out.println("error al persistir el prestamo");
			//System.out.println(e.getCause());
		}
	}
	
}
