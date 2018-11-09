package server.transformer;

import json.JSONParser;
import model.Alumno;
import spark.ResponseTransformer;

public class AlumnoToJsonTransformer implements ResponseTransformer{

	@Override
	public String render(Object alumno){
		return new JSONParser<Alumno>().objectToJson((Alumno) alumno);
	}
}