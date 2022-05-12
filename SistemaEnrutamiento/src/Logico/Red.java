package Logico;

public class Red {
	
	private int primerOcteto;
	private int segundoOcteto;
	private int tercerOcteto;
	private int cuartoOcteto;
	private int mascara;
	
	public Red(int primerOcteto, int segundoOcteto, int tercerOcteto, int cuartoOcteto, int mascara) {
		this.primerOcteto = primerOcteto;
		this.segundoOcteto = segundoOcteto;
		this.tercerOcteto = tercerOcteto;
		this.cuartoOcteto = cuartoOcteto;
		this.mascara = mascara;
	}
	public int getPrimerOcteto() {
		return primerOcteto;
	}
	public void setPrimerOcteto(int primerOcteto) {
		this.primerOcteto = primerOcteto;
	}
	
	public int getSegundoOcteto() {
		return segundoOcteto;
	}
	public void setSegundoOcteto(int segundoOcteto) {
		this.segundoOcteto = segundoOcteto;
	}
	
	public int getTercerOcteto() {
		return tercerOcteto;
	}
	public void setTercerOcteto(int tercerOcteto) {
		this.tercerOcteto = tercerOcteto;
	}
	
	public int getCuartoOcteto() {
		return cuartoOcteto;
	}
	public void setCuartoOcteto(int cuartoOcteto) {
		this.cuartoOcteto = cuartoOcteto;
	}
	
	public int getMascara() {
		return mascara;
	}
	public void setMascara(int mascara) {
		this.mascara = mascara;
	}
	public String getDireccionIp() {
		String direccion = primerOcteto+"."+segundoOcteto+"."+tercerOcteto+"."+cuartoOcteto;
		return direccion;
	}
	public String getDireccionIpMask() {
		String direccion = primerOcteto+"."+segundoOcteto+"."+tercerOcteto+"."+cuartoOcteto+"/"+mascara;
		return direccion;
	}

}
