package dominio;

public class Criterio {
	private String nombre;
	private double porcentaje;
	
	public Criterio(String nombre, double porcentaje) {
		this.nombre = nombre;
		this.porcentaje = porcentaje;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(porcentaje);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criterio other = (Criterio) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
	
}
