package util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.util.Iterator;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dominio.Criterio;
import dominio.Grupo;
import persistencia.Repositorio;


public class FicheroUtils {
	
	public static void leerFichero(String path, Repositorio repositorio) {
		try (FileInputStream file = new FileInputStream(new File(path))) {
			// leer archivo excel
			
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			boolean esPrimera = true;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				if(esPrimera) {
					rowIterator.next();
					esPrimera = false;
				}
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				int i = 0;
				String nombreCriterio = "";
				double porcentajeCriterio = 0;
				String nombreGrupo = "";
				double porcentajeGrupo = 0;
				double nota = 0;
				
				//se recorre cada celda
				while (cellIterator.hasNext()) { //nombre criterio, %c, grupo, %g, nota
						cell = cellIterator.next();
						switch (cell.getCellType()) {
	                    	case STRING:
	                    		String str = cell.getStringCellValue();
	                    		if(i == 0) {
	                    			nombreCriterio = str;
	                    		} else if(i == 2) {
	                    			nombreGrupo = str;
	                    		}
	                    		break;
	                    	case NUMERIC:
	                    		DataFormatter dataFormatter = new DataFormatter(Locale.ENGLISH);
	                            Format format = dataFormatter.createFormat(cell);
	                            String value = format.format(cell.getNumericCellValue());
	                    		//int nmb = (int) cell.getNumericCellValue();
	                    		if(i == 1) {
	                    			porcentajeCriterio = Double.parseDouble(value);
	                    		} else if(i == 3) {
	                    			porcentajeGrupo = Double.parseDouble(value);
	                    		} else if(i == 4) {
	                    			nota = Double.parseDouble(value);
	                    		}
	                    		break;
	                    	default:
	                    		break;
						}
						i++;
				}
				
				Criterio c = new Criterio(nombreCriterio, porcentajeCriterio);
				Grupo g;
				g = repositorio.getGrupo(nombreGrupo);
				if(g == null) {
					g = new Grupo(nombreGrupo, porcentajeGrupo);
					repositorio.addGrupo(g);
				}
				
				g.addCriterio(c, nota);
			}
			worbook.close();
		} catch (Exception e) {}
	}

	
	private static FileWriter fichero = null;
	private static PrintWriter pw = null;
	
	public static void iniciarCalificaciones(String path) throws IOException {
		fichero = new FileWriter(path);
		pw = new PrintWriter(fichero);
	}
	
	public static void agregarCalificacion(String nombre, String calificacion) {
		pw.printf("%-35s%-10s\n", nombre + ":", calificacion);
	}
	
	public static void cerrarCalificaciones() throws IOException {
		pw.close();
		fichero.close();
	}	
}
