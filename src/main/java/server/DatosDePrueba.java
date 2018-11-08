package server;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.EntityManager;


import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import model.Alumno;
import model.Asignacion;
import model.Tarea;
import server.security.SecurityService;

public class DatosDePrueba implements TransactionalOps, WithGlobalEntityManager{
	public void init() {
		EntityManager em = entityManager();
		
		Alumno alumno = new Alumno ("158.946-5", "Federico", "fedekiwo@gmail.com", "fedekiwo", "Kiwowicz");
		Asignacion dds = new Asignacion("DDS",new Tarea(new Date() , "Tp Arena"));	
		dds.setNotas(Arrays.asList("4", "10"));
		withTransaction(() -> {
			em.persist(dds);
			em.persist(alumno);
			alumno.setAsignacion(dds);
			
			System.out.println(new SecurityService().generateTokenFor(alumno.getId()));
		});
	}
}

