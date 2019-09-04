package controlador;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import persistencia.Repositorio;
import util.FicheroUtils;

public class Lanzador {
		
	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.err.println("[Error] Debes especificar la ruta de trabajo.");
			System.exit(1);
		}
		
		String workspace = args[0];
		String calificacionesDir = workspace + System.getProperty("file.separator") + "CalificacionesSostenibilidad";
		File dir = new File(calificacionesDir);
		String[] ficheros = dir.list();
		if (ficheros == null || ficheros.length == 0) {
			System.err.println("[Error] No hay ficheros en el directorio \"CalificacionesSostenibilidad\".");
			System.exit(1);
		}

		
		String calificacionesFich = workspace + System.getProperty("file.separator") + "CalificacionesSostenibilidad.txt";
		
		try {
			FicheroUtils.iniciarCalificaciones(calificacionesFich);
			for (int x = 0; x < ficheros.length; x++) {
				Repositorio repositorio = Repositorio.getRepositorio();
				// Leer fichero:
				FicheroUtils.leerFichero(calificacionesDir + System.getProperty("file.separator") + ficheros[x], repositorio);

				// Calcular la sostenibilidad a partir del repositorio:
				double calificacion = repositorio.getCalificacionTotal();
				DecimalFormat df = new DecimalFormat("#.00");
				String notaFinal = df.format(calificacion);

				// Añadir la calificación final al fichero generado:
				FicheroUtils.agregarCalificacion(ficheros[x].split("\\.")[0], notaFinal);
			}
			FicheroUtils.cerrarCalificaciones();
		} catch (IOException e) {
			System.err.println("[Error] Se ha producido un error de E/S. Excepción: " + e.getMessage());
			System.exit(1);
		}
		

	
	}
}
