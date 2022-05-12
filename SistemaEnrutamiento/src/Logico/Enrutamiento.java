package Logico;

public class Enrutamiento {
	
	private Red redDestino;
	private String nextHop;
	private float metrica;
	private float distanciaAdministrativa;
	private String tipoConexion;
	private String interfazSalida;
	private String tipoEnrutamiento;
	
	public Enrutamiento(Red redDestino, String nextHop, float metrica, float distanciaAdministrativa,
			String interfazSalida, String tipoEnrutamiento) {
		super();
		this.redDestino = redDestino;
		this.nextHop = nextHop;
		this.metrica = metrica;
		this.distanciaAdministrativa = distanciaAdministrativa;
		this.tipoConexion = "Remota";
		this.interfazSalida = interfazSalida;
		this.tipoEnrutamiento = tipoEnrutamiento;
	}

	public Red getRedDestino() {
		return redDestino;
	}

	public void setRedDestino(Red redDestino) {
		this.redDestino = redDestino;
	}

	public String getNextHop() {
		return nextHop;
	}

	public void setNextHop(String nextHop) {
		this.nextHop = nextHop;
	}

	public float getMetrica() {
		return metrica;
	}

	public void setMetrica(float metrica) {
		this.metrica = metrica;
	}

	public float getDistanciaAdministrativa() {
		return distanciaAdministrativa;
	}

	public void setDistanciaAdministrativa(float distanciaAdministrativa) {
		this.distanciaAdministrativa = distanciaAdministrativa;
	}

	public String getTipoConexion() {
		return tipoConexion;
	}

	public void setTipoConexion(String tipoConexion) {
		this.tipoConexion = tipoConexion;
	}

	public String getInterfazSalida() {
		return interfazSalida;
	}

	public void setInterfazSalida(String interfazSalida) {
		this.interfazSalida = interfazSalida;
	}

	public String getTipoEnrutamiento() {
		return tipoEnrutamiento;
	}

	public void setTipoEnrutamiento(String tipoEnrutamiento) {
		this.tipoEnrutamiento = tipoEnrutamiento;
	}
	


}
