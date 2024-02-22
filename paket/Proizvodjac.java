package paket;

import java.awt.Color;
import java.awt.HeadlessException;

public abstract class Proizvodjac extends Parcela implements Runnable {

	private int osnovnoVreme;
	protected Baterija baterija;
	private int ukupnoVreme;
	private Thread thread;
	protected boolean uspesnaProizvodnja = false;
	
	public Proizvodjac(char oznaka, Color bojaPozadnie, int osnVreme, Baterija b) throws HeadlessException {
		super(oznaka, bojaPozadnie);
		osnovnoVreme = osnVreme;
		baterija = b;
		ukupnoVreme = osnovnoVreme + (int)((Math.random() * 300));
		
		thread = new Thread(this);
	}
	
	protected abstract void proizvediEnergiju(); // menjace boolean

	@Override
	public void run() {
		try {
			
			while(!Thread.interrupted()) {
				
				Thread.sleep(ukupnoVreme);
				this.proizvediEnergiju();
				if(uspesnaProizvodnja) this.setForeground(Color.RED);
				revalidate();
				Thread.sleep(300);
				this.setForeground(Color.WHITE);
				revalidate();
				
			}
			
		}catch(InterruptedException e) {}

	}
	
	public synchronized void prekiniSaRadom() {
		thread.interrupt();
	}
	
	public synchronized void kreniSaRadom() {
		thread.start();
	}

}
