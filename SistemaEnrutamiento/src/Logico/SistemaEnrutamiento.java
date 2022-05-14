package Logico;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class SistemaEnrutamiento {
	
	public static SistemaEnrutamiento instanciaGlobal = null;
	private ArrayList<Red> misRedes;
	private ArrayList<Router> misRouters;
	private ArrayList<Enrutamiento> misEnrutamientos;
	
	public SistemaEnrutamiento() {
		this.misRedes = new ArrayList<Red>();
		this.misRouters = new ArrayList<Router>();
		this.setMisEnrutamientos(new ArrayList<Enrutamiento>());
		
	}
	
	//Metodo del patron Singletos.
	public static SistemaEnrutamiento getInstance() {
		if(instanciaGlobal==null) {
			instanciaGlobal=new SistemaEnrutamiento();
		}		
		return instanciaGlobal;
	}

	public ArrayList<Red> getMisRedes() {
		return misRedes;
	}

	public void setMisRedes(ArrayList<Red> misRedes) {
		this.misRedes = misRedes;
	}

	public ArrayList<Router> getMisRouters() {
		return misRouters;
	}

	public void setMisRouters(ArrayList<Router> misRouters) {
		this.misRouters = misRouters;
	}
	
	public void ingresarRed(Red redIngresar) {
		misRedes.add(redIngresar);
	}
	
	public void ingresarRouter(Router routerIngresar) {
		misRouters.add(routerIngresar);
	}
	
	public Red buscarRed(String red) {
		Red aux = null;
		
		for (int i = 0; i < SistemaEnrutamiento.getInstance().getMisRedes().size(); i++) {
			if(SistemaEnrutamiento.getInstance().getMisRedes().get(i).getDireccionIpMask().equalsIgnoreCase(red)) {
				aux = SistemaEnrutamiento.getInstance().getMisRedes().get(i);
			}
		}
		
		return aux;
	}

	public ArrayList<Enrutamiento> getMisEnrutamientos() {
		return misEnrutamientos;
	}

	public void setMisEnrutamientos(ArrayList<Enrutamiento> misEnrutamientos) {
		this.misEnrutamientos = misEnrutamientos;
	}
	
	public void ingresarEnrutamiento(Enrutamiento enrutamiento) {
		misEnrutamientos.add(enrutamiento);
	}
	
	public void modificarRuta(int posicion, Enrutamiento enrutamientoAux) {
		misEnrutamientos.set(posicion-4, enrutamientoAux);
	}
	
	public boolean validarIP(String IP, String Mascara) {
		boolean valida=false;
		boolean mask=false;
		boolean direccion=false;
		boolean red=false;
		int numMask=Integer.parseInt(Mascara);
		if((numMask>=8)&&(numMask<=30)) {
			mask=true;
		}else {
			mask=false;
		}
		direccion = validarDireccion(IP);
		if(direccion&&mask) {
			red = validarRed(IP,numMask);
		}
		valida=mask&direccion&red;
		return valida;
	}
	
	public static boolean validarDireccion(String ipAddress) {
	    boolean b1 = false;
	    StringTokenizer t = new StringTokenizer(ipAddress, ".");
	    int a = Integer.parseInt(t.nextToken());
	    int b = Integer.parseInt(t.nextToken());
	    int c = Integer.parseInt(t.nextToken());
	    int d = Integer.parseInt(t.nextToken());
	    if ((a >= 0 && a <= 255) && (b >= 0 && b <= 255)
	        && (c >= 0 && c <= 255) && (d >= 0 && d <= 255))
	      b1 = true;
	    return b1;
	  }
	
	public static boolean validarRed(String ipAddress,int Mask) {
	    boolean valid = false;
	    StringTokenizer t = new StringTokenizer(ipAddress, ".");
	    int a = Integer.parseInt(t.nextToken());
	    int b = Integer.parseInt(t.nextToken());
	    int c = Integer.parseInt(t.nextToken());
	    int d = Integer.parseInt(t.nextToken());
	    int num=0;
	    int red=0;
	    if(Mask<16) {
	    	num=16-Mask;
	    	while((red<255)||(!valid)) {
	    		if(b==red) {
	    			valid=true;
	    		}
	    		red+=(int)Math.pow(2,num);
	    	}
	    }else if(Mask<24) {
	    	num=24-Mask;
	    	while((red<255)||(!valid)) {
	    		if(c==red) {
	    			valid=true;
	    		}
	    		red+=(int)Math.pow(2,num);
	    	}
	    }else {
	    	num=32-Mask;
	    	while((red<255)||(!valid)) {
	    		if(d==red) {
	    			valid=true;
	    		}
	    		red+=(int)Math.pow(2,num);
	    	}
	    }
	    return valid;
	  }

	public ArrayList<Enrutamiento> rutasDireccion(String red) {
		ArrayList<Enrutamiento> rutasDireccion = new ArrayList<Enrutamiento>();
		for (int i = 0; i <SistemaEnrutamiento.getInstance().getMisEnrutamientos().size(); i++) {
			if(SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i).getRedDestino().getDireccionIpMask().equalsIgnoreCase(red)) {
				rutasDireccion.add(SistemaEnrutamiento.getInstance().getMisEnrutamientos().get(i));
			}
		}
		
		return rutasDireccion;
		
		
	}

}
