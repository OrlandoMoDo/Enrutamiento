package Logico;

import java.util.ArrayList;

public class Router {
	
	private ArrayList<Red> misRedes;
	private ArrayList<String> nextHops;
	private ArrayList<String> interfaces;
	
	public Router() {
		this.misRedes = new ArrayList<Red>();
		this.nextHops = new ArrayList<String>();
		this.interfaces = new ArrayList<String>();
	}

	public ArrayList<Red> getMisRedes() {
		return misRedes;
	}

	public void setMisRedes(ArrayList<Red> misRedes) {
		this.misRedes = misRedes;
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
		
	public void ingresarNextHop(String nextHopIngresar) {
		
		nextHops.add(nextHopIngresar);
		
	}
	
	public void ingresarInterfaces(String interfacesIngresar) {
		
		interfaces.add(interfacesIngresar);
		
	}


	
}
