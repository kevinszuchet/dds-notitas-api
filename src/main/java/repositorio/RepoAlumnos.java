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
	
	public Alumno obtenerXtoken(String token) {
		try {
			return (Alumno) em.createQuery("FROM Alumno a WHERE a.secretCode = :token", Alumno.class).setParameter("token", token).getSingleResult();
		} catch(NoResultException e) {
			return null;
		}
	}
}