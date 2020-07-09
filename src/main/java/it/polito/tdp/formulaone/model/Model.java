package it.polito.tdp.formulaone.model;

import java.util.ArrayList;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.formulaone.db.FormulaOneDAO;

public class Model {
	
	public Model() {
		dao= new FormulaOneDAO();
	}
	
	private ArrayList<Integer> anni;
	private FormulaOneDAO dao;
	private SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge> grafo;
	private int vincitore;
	private int massimo;
	private ArrayList<Integer> piloti;


	public ArrayList<Integer> getAllYears() {
		anni=dao.getAllYears();
		return anni;
	}

	public void creaGrafo(Integer anno) {
		ArrayList<Risultato> risultati=dao.getAllResultsByYear(anno);
		ArrayList<Sfida> sfide=new ArrayList<Sfida>();
		sfide.clear();
		int gara=-1;
		for(Risultato r1:risultati) {
			for(Risultato r2:risultati) {
				gara=r1.getGara();
				if(gara==r2.getGara()) {
					int autista1=r1.getAutista();
					int autista2=r2.getAutista();
					if(autista1!=autista2) {
						Sfida s=new Sfida(autista1,autista2,1);
						if(s.equals(new Sfida(14,18,-1))) {
							System.out.println("QUI");
						}
						if(sfide.contains(s)) {
							Sfida daCambiare=sfide.get(sfide.indexOf(s));
							if(!daCambiare.garaVista(gara)) {
								daCambiare.aggiungiGara(gara);
							if(r1.getPosizione()<r2.getPosizione()) {								
								daCambiare.setPeso(daCambiare.getPeso()+1);
							} else {
								daCambiare.setPeso(daCambiare.getPeso()-1);
							}
							}
							
						} else {
							s.aggiungiGara(gara);
							if(r1.getPosizione()<r2.getPosizione()) {
								sfide.add(s);
							} else {
								s.setPeso(-1);
								sfide.add(s);
							}
						}
						
					}				
				} 
			}
		}
		//parte di costruzione del grafo
		grafo=new SimpleDirectedWeightedGraph<Integer, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		int peso;
		int a1;
		int a2;
		for(Sfida s:sfide) {
			a1=s.getAutista1();
			a2=s.getAutista2();
			if(!grafo.containsVertex(a1))
				grafo.addVertex(a1);
			if(!grafo.containsVertex(a2))
				grafo.addVertex(a2);
			peso=s.getPeso();
			if(peso>0) {
			Graphs.addEdge(grafo, a1, a2, peso);
			} if(peso<0) {
				Graphs.addEdge(grafo, a2, a1, -peso);
			}
		}
		vincitore=-1;
		massimo=-1;
		int temp=-1;
		piloti=new ArrayList<Integer>(grafo.vertexSet());

		for(int pilota:grafo.vertexSet()) {
			temp=grafo.outDegreeOf(pilota);
			if(temp>massimo) {
				vincitore=pilota;
				massimo=temp;
			}
		}		
		
		System.out.println("Creato grafo con vertici "+grafo.vertexSet().size()+" e archi "+grafo.edgeSet().size());
		
		}

	public int getVincitore() {
		return vincitore;
	}

	public int getMassimo() {
		return massimo;
	}
	
	private int lunghezzaTeam;
	private int max;
	private ArrayList<Integer> dreamTeam;

	public ArrayList<Integer> getDreamTeam(int team) {
		int livello=0;
		ArrayList<Integer> parziale=new ArrayList<Integer>();
		lunghezzaTeam=team;
		max=99999;

		espandi(parziale, livello);
		return dreamTeam;
		
	}

	private void espandi(ArrayList<Integer> parziale, int livello) { //livello e' il punto in cui mi trovo all'interno della lista piloti
		
		
		
		if(parziale.size()==lunghezzaTeam) {
			//condizione di terminazione
			int valore=0;
			for(Integer i:parziale) {
				for(DefaultWeightedEdge e:grafo.incomingEdgesOf(i)) {
					if(!parziale.contains(grafo.getEdgeSource(e)))
						valore++;
				}
			}
			if(valore<max) {
				max=valore;
				dreamTeam=new ArrayList<Integer>(parziale);
			}
			return;
		}
		
		if(livello>=piloti.size()) {
			return;
		}
		
		espandi(parziale,livello+1);
		parziale.add(this.piloti.get(livello));
		espandi(parziale,livello+1);
		parziale.remove(piloti.get(livello));
		
	}
		

}