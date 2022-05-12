package Logico;

import java.util.ArrayList;

public class SistemaEnrutamiento {
	
	public static SistemaEnrutamiento instanciaGlobal = null;
	private ArrayList<Red> misRedes;
	private ArrayList<Router> misRouters;
	
	public SistemaEnrutamiento() {
		this.misRedes = new ArrayList<Red>();
		this.misRouters = new ArrayList<Router>();
		
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
	
	
	


}
