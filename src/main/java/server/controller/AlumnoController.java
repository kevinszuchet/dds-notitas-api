package server.controller;

import spark.Response;
import spark.Spark;
import json.JSONParser;
import model.Alumno;
import model.Asignacion;
import repositorio.RepoAlumnos;
import spark.Request;

public class AlumnoController {
	
	private static final JSONParser<Alumno> parserAlumnos = new JSONParser<Alumno>();
	private static final JSONParser<Asignacion> parserAsignaciones = new JSONParser<Asignacion>();

	
	
	private static Alumno obtenerAlumnoSiExiste(Long id) {
		Alumno alumno = RepoAlumnos.getInstance().obtenerXId(id);
		
		if (alumno == null) {
			Spark.halt(401, "Me mandaste cualquier cosaa, no te hagas el gil");
		}
		
		return alumno;		
	}
	
	public static String getAlumno(Request req, Response res) {
		
		Alumno alumno = obtenerAlumnoSiExiste((Long) req.session().attribute("userIdSession"));		
		return parserAlumnos.objectToJson(alumno);
	}
	
	public static String modificarAlumno(Request req, Response res) {
		Alumno alumnoActual = obtenerAlumnoSiExiste(req.session().attribute("userIdSession"));		
		Alumno alumnoNuevo = parserAlumnos.jsonToObject(req.body(), Alumno.class);
		
		alumnoActual.setNombre(alumnoNuevo.getNombre() != null ? alumnoNuevo.getNombre() : alumnoActual.getNombre());
		alumnoActual.setEmail(alumnoNuevo.getEmail() != null ? alumnoNuevo.getEmail() : alumnoActual.getEmail());
		alumnoActual.setApellido(alumnoNuevo.getApellido() != null ? alumnoNuevo.getApellido() : alumnoActual.getApellido());
		alumnoActual.setGithubUser(alumnoNuevo.getGithubUser() != null ? alumnoNuevo.getGithubUser() : alumnoActual.getGithubUser());
		
		return "OK";
	}
	
	public static String getAsignaciones(Request req, Response res) {
		Alumno alumno = obtenerAlumnoSiExiste(req.session().attribute("userIdSession"));
		
		return parserAsignaciones.listToJson(alumno.getAsignaciones());
	}
	
}

