package server;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Alumno;
import server.security.SecurityService;

public class DatosDePrueba implements TransactionalOps, WithGlobalEntityManager{
	public void init() {
		EntityManager em = entityManager();
		
		Alumno alumno = new Alumno ("158.946-5", "Federico", "fedekiwo@gmail.com", "fedekiwo", "Kiwowicz");
				
		withTransaction(() -> {
			
			em.persist(alumno);
			
			System.out.println(new SecurityService().generateTokenFor(alumno.getId()));
		});
	}
}
