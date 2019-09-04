package dominio;
import java.util.HashMap;
import java.util.Map.Entry;

public class Grupo {
	String nombre;
	double porcentaje;
	HashMap<Criterio, Double> calificaciones;
	
	public Grupo(String nombre, double porcentaje) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
		calificaciones = new HashMap<Criterio, Double>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public double getPorcentaje() {
		return porcentaje;
	}
	
	public HashMap<Criterio, Double> getCriterios(){
		return calificaciones;
	}
	
	public void addCriterio(Criterio criterio, double nota) {
		if(calificaciones.containsKey(criterio))
			calificaciones.remove(criterio);
		calificaciones.put(criterio, nota);
	}
	
	/**Calcula la nota que este grupo aporta a la nota final
	 * @return la nota que este grupo aporta a la nota final**/
	public double getCalificacionGrupo() {
		double resultado = 0;
		for (Entry<Criterio, Double> entry : calificaciones.entrySet()) {
			Criterio criterio = entry.getKey();
			Double calificacion = entry.getValue();
			resultado += calificacion * criterio.getPorcentaje() / 100;
		}
		return resultado * porcentaje / 100;
	}
	
}
