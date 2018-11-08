package model;

import java.util.ArrayList; 
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Asignacion {

	@Id @GeneratedValue
	private Long id;
	
	String nombre;
	
	@ElementCollection
	private List<String> notas;	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Tarea tarea;
	public Asignacion() {}
	public Asignacion(String nombre, Tarea tarea) {
		this.nombre = nombre;
		this.tarea = tarea;
		this.notas = new ArrayList<>();
	}

	public List<String> getNotas() {
		return notas;
	}

	public void setNotas(List<String> notas) {
		this.notas = notas;
	}

	public Tarea getTarea() {
		return tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}
	
}
