package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Parcela extends Label {

	private char oznaka;
	private Color bojaPozadine;
	//private Plac roditeljPlac;
	
	public Parcela(char oznaka, Color bojaPozadnie) throws HeadlessException {
		super(new StringBuilder().append("").append(oznaka).toString());
		this.oznaka = oznaka;
		this.bojaPozadine = bojaPozadnie;
		
		setFont(new Font(Font.SERIF, Font.BOLD, 14));
		setBackground(bojaPozadnie);
		setForeground(Color.WHITE);
		setAlignment(CENTER);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Plac p = (Plac)getParent();
				p.izaberiParcelu(Parcela.this);
				
				super.mouseClicked(e);
			}
		});
	}
	
	private void promeniBojuPozdine(Color c) {
		bojaPozadine = c;
	}
	
	
}
