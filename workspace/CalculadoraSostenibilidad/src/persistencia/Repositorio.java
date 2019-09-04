package persistencia;
import java.util.ArrayList;

import dominio.Grupo;

public class Repositorio {
	private static Repositorio instance = null;
	ArrayList<Grupo> grupos;
	
	private Repositorio() {
		instance = this;
		grupos = new ArrayList<Grupo>();
	}
	
	public static Repositorio getRepositorio() {
		if(instance == null)
			instance = new Repositorio();
		return instance;
	}

	
	public Grupo getGrupo(String nombre) {
		for(Grupo g : grupos)
			if(g.getNombre().equals(nombre))
				return g;
		return null;
	}
	
	public double getCalificacionTotal() {
		double nota = 0;
		for(Grupo g : grupos) 
			nota += g.getCalificacionGrupo();
		return nota;
	}
	
	public void addGrupo(Grupo g) {
		grupos.add(g);
	}
	
}
