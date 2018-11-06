package server;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Alumno;

public class DatosDePrueba implements TransactionalOps, WithGlobalEntityManager{
	public void init() {
		EntityManager em = entityManager();
		
		Alumno alumno = new Alumno ("158.946-5", "Federico", "fedekiwo@gmail.com", "fedekiwo", "Kiwowicz", "kiwo123");
				
		withTransaction(() -> {
			
			em.persist(alumno);
			
		});
	}
}
