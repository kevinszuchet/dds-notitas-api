package server.controller;

import spark.Response;
import spark.Spark;
import json.JSONParser;
import model.Alumno;
import repositorio.RepoAlumnos;
import spark.Request;

public class AlumnoController {
	
	private static final JSONParser<Alumno> parserAlumnos = new JSONParser<Alumno>();
	
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
		Alumno alumnoNuevo = parserAlumnos.jsonToObject("", Alumno.class);
		
		alumnoActual.setNombre(alumnoNuevo.getNombre());
		alumnoActual.setApellido(alumnoNuevo.getApellido());
		alumnoActual.setGithubUser(alumnoNuevo.getGithubUser());
		
		return null;
	}
}
