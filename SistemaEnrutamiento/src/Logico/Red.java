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
	
	public String obtenerIPBinario() {
		String octetos[] = new String[4];

		octetos[0] = Long.toBinaryString(primerOcteto);
		octetos[1] = Long.toBinaryString(segundoOcteto);
		octetos[2] = Long.toBinaryString(tercerOcteto);
		octetos[3] = Long.toBinaryString(cuartoOcteto);
		
		for (int i = 0; i < 4; i++) {
			int cant=8-octetos[i].length();
			String octetosAux = octetos[i];
			octetos[i]="";
			for (int j = 0; j < cant; j++) {
				octetos[i]=octetos[i]+0;
			}
			octetos[i]=octetos[i]+octetosAux;
		}
		
		String direccion = octetos[0]+octetos[1]+octetos[2]+octetos[3];
		return direccion;
	}
	
	public String direccionRed(Red red) {
		
		String ipBinario = red.obtenerIPBinario();
		char[] chars = ipBinario.toCharArray();
		int mascara = red.getMascara();
		String ipAux = "";
    	String aux = ipAux;
    	int octetos[]= new int[4];
    	int j=0;
		//Todos los bits de host en 0:
		for (int i=0; i<chars.length; i++) {
            if(mascara>0) {
            	mascara--;
            }else {
            	chars[i]='0';
            }
            ipAux=ipAux+chars[i];
            aux = aux+chars[i];
            if(i==7 || i==15 || i==23 || i==31) {
                int decimal=Integer.parseInt(aux,2); 
                aux="";
                octetos[j]=decimal;
                j++;
            }
        }
		
		Red redAux = new Red(octetos[0], octetos[1], octetos[2], octetos[3], red.getMascara());
		return redAux.getDireccionIp();
		
	}
	//10.0.0.
	//10=1010
	//1010
	//00001010000010100000101000001010
	//0=0
	

}
