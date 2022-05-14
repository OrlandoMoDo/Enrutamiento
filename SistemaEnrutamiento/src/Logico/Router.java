package Logico;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Router {
	
	private String nombreRuter;
	private ArrayList<Red> misRedes;
	private ArrayList<String> nextHops;
	private ArrayList<String> interfaces;
	private ArrayList<Enrutamiento> enrutamientos;
	
	public Router(String nombreRuter) {
		this.nombreRuter = nombreRuter;
		this.misRedes = new ArrayList<Red>();
		this.nextHops = new ArrayList<String>();
		this.interfaces = new ArrayList<String>();
		this.enrutamientos = new ArrayList<Enrutamiento>();
	}

	public ArrayList<Enrutamiento> getEnrutamientos() {
		return enrutamientos;
	}

	public void setEnrutamientos(ArrayList<Enrutamiento> enrutamientos) {
		this.enrutamientos = enrutamientos;
	}

	public ArrayList<Red> getMisRedes() {
		return misRedes;
	}

	public void setMisRedes(ArrayList<Red> misRedes) {
		this.misRedes = misRedes;
	}

	public String getNombreRuter() {
		return nombreRuter;
	}

	public void setNombreRuter(String nombreRuter) {
		this.nombreRuter = nombreRuter;
	}

	public ArrayList<String> getNextHops() {
		return nextHops;
	}

	public void setNextHops(ArrayList<String> nextHops) {
		this.nextHops = nextHops;
	}

	public ArrayList<String> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(ArrayList<String> interfaces) {
		this.interfaces = interfaces;
	}
	
	public void ingresarRed(Red redIngresar) {
			
			misRedes.add(redIngresar);
			
	}
	
	public void ingresarEnrutamiento(Enrutamiento enrutamientoAux) {
		enrutamientos.add(enrutamientoAux);
	}
		
	public void ingresarNextHop(String nextHopIngresar) {
		
		nextHops.add(nextHopIngresar);
		
	}
	
	public void ingresarInterfaces(String interfacesIngresar) {
		
		interfaces.add(interfacesIngresar);
		
	}
	
	public ArrayList<String> redesEnrutadas(){
		ArrayList<String> misRedesEnrutadas = new ArrayList<String>();
		int cantRedes = 1;
		
		for (int i = 0; i < SistemaEnrutamiento.getInstance().getMisRouters().get(0).getEnrutamientos().size(); i++) {
			if(i==0) {
				misRedesEnrutadas.add(SistemaEnrutamiento.getInstance().getMisRouters().get(0).getEnrutamientos().get(i).getRedDestino().getDireccionIpMask());
			}else {
				if(!misRedesEnrutadas.contains(SistemaEnrutamiento.getInstance().getMisRouters().get(0).getEnrutamientos().get(i).getRedDestino().getDireccionIpMask())) {
					misRedesEnrutadas.add(SistemaEnrutamiento.getInstance().getMisRouters().get(0).getEnrutamientos().get(i).getRedDestino().getDireccionIpMask());
				}

			}
			
		}
		return misRedesEnrutadas;
		
		
	}


	
}
