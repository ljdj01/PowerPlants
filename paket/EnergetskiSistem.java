package paket;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	private int brRedova, brKolona, kapBaterije;
	
	private Panel topPanel = new Panel();
	private Button button = new Button("Dodaj");
	private Plac centerPanel;
	private Baterija baterija;
	
	private void populateWindow() {
		
		topPanel.add(button);
		add(topPanel, BorderLayout.NORTH);
		
		add(centerPanel, BorderLayout.CENTER);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Baterija b = new Baterija(kapBaterije);//za proveru samo
				b.isprazniBateriju();//
				centerPanel.dodajProizvodjaca(new Hidroelektrana(b));
				super.mouseClicked(e);
			}
		});
		
	}
	
	public EnergetskiSistem(int red, int kol, int kap) {
		
		brKolona = kol;
		brRedova = red;
		kapBaterije = kap;
		baterija = new Baterija(kap);
		
		centerPanel = new Plac(brRedova, brKolona);
		
		setVisible(true);
		setResizable(false);
		setBounds(700,200,500,500);
		
		populateWindow();
		//pack();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				centerPanel.zaustaviSveProizvodjace();
				dispose();
			}
		});
		
		
	}
	
	public static void main(String[] args) {
		new EnergetskiSistem(5,5, 15);
	}

}
