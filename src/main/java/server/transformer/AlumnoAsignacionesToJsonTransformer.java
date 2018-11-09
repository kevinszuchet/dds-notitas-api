package server.transformer;

import java.util.List;

import json.JSONParser;
import model.Asignacion;
import spark.ResponseTransformer;

public class AlumnoAsignacionesToJsonTransformer implements ResponseTransformer{

	@Override
	public String render(Object asignaciones) {
		return new JSONParser<Asignacion>().listToJson((List<Asignacion>) asignaciones);
	}
}