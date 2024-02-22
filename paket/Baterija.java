package paket;

public class Baterija {

	private int trenutnaEnergija;
	private int maxKapacitet;
	
	public Baterija(int kap) {
		maxKapacitet = trenutnaEnergija = kap;
	}
	
	public synchronized void dodajEnergiju(int e) {
		trenutnaEnergija += e;
		if (trenutnaEnergija > maxKapacitet) trenutnaEnergija = maxKapacitet;
		
		System.out.println(trenutnaEnergija);
	}
	
	public synchronized void isprazniBateriju() {
		trenutnaEnergija = 0;
	}
	
	public synchronized boolean jeLiBaterijaPuna() {
		return trenutnaEnergija == maxKapacitet;
	}
	
	
}
