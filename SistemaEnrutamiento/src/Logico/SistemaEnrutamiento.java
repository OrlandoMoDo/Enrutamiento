package Logico;

import java.util.ArrayList;

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


}
