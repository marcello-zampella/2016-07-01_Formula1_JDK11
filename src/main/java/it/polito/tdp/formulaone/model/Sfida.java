package it.polito.tdp.formulaone.model;

import java.util.ArrayList;

public class Sfida {
	
	int autista1;
	int autista2;
	int peso; //da 1 a 2 se positivo, da 2 a 1 se negativo
	ArrayList<Integer> gare;
	public Sfida(int autista1, int autista2, int peso) {
		super();
		this.autista1 = autista1;
		this.autista2 = autista2;
		this.peso = peso;
		this.gare=new ArrayList<Integer>();
	}
	public int getAutista1() {
		return autista1;
	}
	public void setAutista1(int autista1) {
		this.autista1 = autista1;
	}
	public int getAutista2() {
		return autista2;
	}
	public void setAutista2(int autista2) {
		this.autista2 = autista2;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + autista1;
		result = prime * result + autista2;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sfida other = (Sfida) obj;
		if (autista1 != other.autista1 && autista1!=other.autista2)
			return false;
		if (autista2 != other.autista2 && autista2!=other.autista1)
			return false;
		return true;
	}
	public boolean garaVista(int gara) {
		return this.gare.contains(gara);
	}
	public void aggiungiGara(int gara) {
		this.gare.add(gara);
		
	}
	@Override
	public String toString() {
		return "Sfida " + autista1 + " " + autista2 + " peso=" + peso;
	}
	
	
	
	
	
	
	

}
