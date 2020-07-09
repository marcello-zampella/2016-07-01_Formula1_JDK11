package it.polito.tdp.formulaone.model;

public class Risultato {
	
	int gara;
	int autista;
	int posizione;
	public Risultato(int gara, int autista, int posizione) {
		super();
		this.gara = gara;
		this.autista = autista;
		this.posizione = posizione;
	}
	public int getGara() {
		return gara;
	}
	public void setGara(int gara) {
		this.gara = gara;
	}
	public int getAutista() {
		return autista;
	}
	public void setAutista(int autista) {
		this.autista = autista;
	}
	public int getPosizione() {
		return posizione;
	}
	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}
	
	

}
