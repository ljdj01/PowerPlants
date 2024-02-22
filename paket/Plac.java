package paket;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

public class Plac extends Panel {

	private int brRedova;
	private int brKolona;
	private Parcela izabranaParcela;
	private ArrayList<Parcela> listaParcela = new ArrayList<>(); //i proizvodjac je parcela pa mogu svi tuna
	//private ArrayList<Proizvodjac> listaProizvodjaca = new ArrayList<>();
	
	public Plac(int brRedova, int brKolona) {
		super();
		this.brRedova = brRedova;
		this.brKolona = brKolona;
		
		this.setLayout(new GridLayout(brRedova, brKolona,3,3));
		
		popuniListuParcela();
		for(Parcela p:listaParcela) {
			add(p);
		}
		
		revalidate();
		
	}
	
	/*@Override
	public void validate() {
		super.validate();
		for(Parcela p:listaParcela) {
			add(p);
		}
	}*/

	private void popuniListuParcela() {
		for(int i = 0; i<brRedova*brKolona; i++) {
			Parcela p = generisiParcelu(); 
			listaParcela.add(p);
		}
	}
	
	private Parcela generisiParcelu() {
		int a = (int)(Math.random()*10);
		if(a < 3) return new VodenaPovrs();
		else return new TravnataPovrs();
	}
	
	public void izaberiParcelu(Parcela p) {
		if(izabranaParcela != null) { //skalnjanje stare izabrane ako postoji
			Font stari = izabranaParcela.getFont();
			//int staraVel = stari.getSize();
			izabranaParcela.setFont(new Font(stari.getFamily(), Font.BOLD, 14));
		}
		Font novi = p.getFont();
		//int novaVel = novi.getSize();
		//System.out.println(p.getFont().getFamily());
		p.setFont(new Font(novi.getFamily(), Font.BOLD, 20));
		izabranaParcela = p;
		revalidate();
	}
	
	public void dodajProizvodjaca(Proizvodjac p) {
		if(izabranaParcela == null) return;
		
		if(izabranaParcela instanceof Proizvodjac) ((Proizvodjac) izabranaParcela).prekiniSaRadom();
		int indeks = listaParcela.indexOf(izabranaParcela);
		listaParcela.set(indeks, p);//moram zaustaviti nit prethodnog ako je bio proizvodjac
		izabranaParcela = null;
		
		this.removeAll();
		
		for(Parcela parc : listaParcela) {
			if(parc instanceof Hidroelektrana) ((Hidroelektrana) parc).postaviBrVodenihPovrsi(prebrojVodenePovrsine((Hidroelektrana) parc));
		}
		
		//System.out.println("====================================");
		
		for(Parcela parc : listaParcela) {
			this.add(parc);
		}
		revalidate();
		
		p.kreniSaRadom();
	}
	
	private int prebrojVodenePovrsine(Hidroelektrana h) {
		int br = 0;
		int indeks = listaParcela.indexOf(h);
		
		boolean uslovDesneIvice = (( indeks + 1 ) % brKolona == 0);
		boolean uslovLeveIvice = (( indeks ) % brKolona == 0);
		boolean uslovGornjeIvice = (indeks < brKolona);
		boolean uslovDonjeIvice = (indeks > (brRedova - 1) * brKolona - 1);
		
		if(!uslovGornjeIvice && !uslovLeveIvice) {
			if(listaParcela.get(indeks-1-brKolona) instanceof VodenaPovrs) br++;
		}
		if(!uslovGornjeIvice) {
			if(listaParcela.get(indeks-brKolona) instanceof VodenaPovrs) br++;
		}
		if(!uslovGornjeIvice && !uslovDesneIvice) {
			if(listaParcela.get(indeks+1-brKolona) instanceof VodenaPovrs) br++;
		}
		if(!uslovLeveIvice) {
			if(listaParcela.get(indeks-1) instanceof VodenaPovrs) br++;
		}
		
		///////////////////////////element
		
		if(!uslovDesneIvice) {
			if(listaParcela.get(indeks+1) instanceof VodenaPovrs) br++;
		}
		if(!uslovDonjeIvice && !uslovLeveIvice) {
			if(listaParcela.get(indeks-1+brKolona) instanceof VodenaPovrs) br++;
		}
		if(!uslovDonjeIvice) {
			if(listaParcela.get(indeks+brKolona) instanceof VodenaPovrs) br++;
		}
		if(!uslovDonjeIvice && !uslovDesneIvice) {
			if(listaParcela.get(indeks+1+brKolona) instanceof VodenaPovrs) br++;
		}
		
		//System.out.println(br);
		return br;
	}
	
	public void zaustaviSveProizvodjace() {
		for(Parcela parc : listaParcela) {
			if(parc instanceof Proizvodjac) ((Proizvodjac) parc).prekiniSaRadom();
		}
	}
}
