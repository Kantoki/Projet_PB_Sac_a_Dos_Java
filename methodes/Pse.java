package methodes;

import java.util.ArrayList;

import outils.ABR;
import outils.Objet;

public class Pse implements IMethode {
	private long temps = 0;
	private ArrayList<Objet> objets;
	private Double poids;
	private Double poidsMax;
	
	public Pse(ArrayList<Objet> objets, Double poidsMax){
		this.poids = 0.;
		this.poidsMax = poidsMax;
		this.objets = objets;
	}
	
	@Override
	public void résoudre() {
		this.temps = System.nanoTime();
		ABR arbre = new ABR(new ArrayList<Objet>(), this.poidsMax,true);
		for(int i = 0; i < objets.size(); i++){
			arbre.ajouter(objets.get(i),true);
		}
		ArrayList<Objet> res = arbre.getValeur();
		for(int  i = 0; i < res.size(); ++i){
			res.get(i).setValide(true);
			this.poids += res.get(i).getPoids();
		}	
		
	}
	
	@Override
	public long getTemps() {
		// TODO Auto-generated method stub
		return this.temps;
	}

	@Override
	public ArrayList<Objet> getResultat() {
		// TODO Auto-generated method stub
		return this.objets;
	}

	@Override
	public Double getPoids() {
		// TODO Auto-generated method stub
		return this.poids;
	}




}
