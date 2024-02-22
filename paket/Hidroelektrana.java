package paket;

import java.awt.Color;
import java.awt.HeadlessException;

public class Hidroelektrana extends Proizvodjac {

	private int brVodenihPovrsi;
	
	public Hidroelektrana(Baterija b) throws HeadlessException {
		super('H', Color.BLUE, 1500, b);
		brVodenihPovrsi = 0;
	}
	
	public void postaviBrVodenihPovrsi(int n) {
		//System.out.println(n);
		brVodenihPovrsi = n;
	}

	@Override
	protected synchronized void proizvediEnergiju() {
		if(brVodenihPovrsi != 0) {
			baterija.dodajEnergiju(brVodenihPovrsi);
			uspesnaProizvodnja = true;
		}
		else uspesnaProizvodnja = false;
		
	}

}
