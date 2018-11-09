package server;

import spark.Spark;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import server.controller.AlumnoController;
import server.security.InvalidTokenException;
import server.security.SecurityService;
import server.transformer.AlumnoAsignacionesToJsonTransformer;
import server.transformer.AlumnoToJsonTransformer;

public class Router implements TransactionalOps, WithGlobalEntityManager {
	
	EntityManager em = entityManager();
	
	public void configure() {		
//		SecurityService securityService = new SecurityService("god");
		SecurityService securityService = new SecurityService();
		Spark.before((req, res) -> {
			if (req.requestMethod() != "GET") {
				beginTransaction();
			}
			
			try {
				Long userId = securityService.user(req.headers("Authorization").replace("Bearer ", ""));
				req.session().attribute("userIdSession", userId);
			} catch (InvalidTokenException e) {
				if (req.requestMethod() != "GET") {
					rollbackTransaction();
				}
				
				Spark.halt(401, "<h1><a href='https://www.youtube.com/watch?v=0Jx8Eay5fWQ'>Hack me </a></h1><br/><br/><br/><a href='https://www.youtube.com/watch?v=PtLmEARfStE'> El aleph </a>");
			}
		});

		Spark.get("/", (req, res) -> "Hello world!");
		
		Spark.get("/student", AlumnoController::getAlumno, new AlumnoToJsonTransformer());		
		
		Spark.get("/student/asignaciones", AlumnoController::getAsignaciones, new AlumnoAsignacionesToJsonTransformer());
		
		Spark.patch("/student", AlumnoController::modificarAlumno);
		
		Spark.after((req, res) -> {
			if(req.requestMethod() != "GET") {
				try {
					commitTransaction();
					em.clear();
				} catch (RollbackException e) {
					rollbackTransaction();
				}				
			}
		});
	}
}