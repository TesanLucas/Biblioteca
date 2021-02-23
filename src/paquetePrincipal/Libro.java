package paquetePrincipal;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

@Entity
public class Libro {
	@Id
	private String ISBN;
	private String titulo;
	private tipoLibro tipo;		// arreglar por que hibernate guarda tipoLibro como integer
	private String editorial;
	private int anio;
	
	@OneToOne
	private Autor autor;
	
	@OneToMany
	private List<Copia> copias;

	public Libro() {
		
	}
	
	public Libro(String iSBN, String titulo, tipoLibro tipo, String editorial, int anio, Autor autor) {
		super();
		this.ISBN = iSBN;
		this.titulo = titulo;
		this.tipo = tipo;
		this.editorial = editorial;
		this.anio = anio;
		this.autor = autor;
		this.copias = new ArrayList<Copia>();
	}
	
	public String getISBN() {
		return ISBN;
	}
	public String getTitulo() {
		return titulo;
	}
	public tipoLibro getTipo() {
		return tipo;
	}
	public String getEditorial() {
		return editorial;
	}
	public int getAnio() {
		return anio;
	}
	
	public Autor getAutor() {
		return autor;
	}
	
	public List<Copia> getCopias() {
		return copias;
	}

	public void agregarCopia(Copia copiaNueva) {	
		this.copias.add(copiaNueva);
	}
	
	public Copia getCopiaPorId(long idCopia) {
		for (int i = 0; i < this.copias.size(); i++) {
			if (this.copias.get(i).getIdCopia() == idCopia) {
				return this.copias.get(i);
			}
		}
		return null;
	}
	
	public void persistir() {
		
		EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("ejsHibernate");
		EntityManager em = managerFactory.createEntityManager();
		
		//this.autor.persistir();    // primero persistimos al autor
		try {

			EntityTransaction tran = em.getTransaction();
			tran.begin();
			em.persist(this);
			tran.commit();
			em.close();

		} catch (RollbackException e) {
			System.out.println("error al persistir el libro: " + this.getISBN());
			System.out.println(e.getCause());
		}
	}
	
	
}
