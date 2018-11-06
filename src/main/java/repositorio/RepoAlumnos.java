package repositorio;

import javax.persistence.NoResultException;

import model.Alumno;

public class RepoAlumnos extends RepoEnDB<Alumno> {
	private static RepoAlumnos instancia;	

	public RepoAlumnos(String tabla, Class<Alumno> entidad) {
		super(tabla);
		this.tabla = tabla;
	}
  
	public static RepoAlumnos getInstance() {
		if (instancia == null) {
			instancia = new RepoAlumnos("Alumno", Alumno.class);
		}
		return instancia;
	}
	
	public Alumno obtenerXId(Long id) {
		try {
			return (Alumno) em.createQuery("FROM Alumno a WHERE a.id = :id", Alumno.class).setParameter("id", id).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}