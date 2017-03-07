package methodes;

import java.util.ArrayList;
import java.util.Arrays;

import outils.Objet;

public class Gloutonne implements IMethode {
	private long temps = 0;
	private ArrayList<Objet> objets;
	private Double poids;
	private Double poidsMax;
	
	public Gloutonne(ArrayList<Objet> objets, Double poidsMax){
		this.poids = 0.;
		this.poidsMax = poidsMax;
		this.objets = objets;
	}

	@Override
	public void résoudre() {
		this.temps = System.nanoTime();
		Double[] rapports = new Double[objets.size()];
		for(int i = 0; i < rapports.length; i++){
			Objet o = objets.get(i);
			rapports[i] = o.getValeur() / o.getPoids();
		}
		Arrays.sort(rapports);
		for(int i = rapports.length - 1; i >= 0; i--){
			Objet o = objets.get(i);
			if(o.getPoids() + this.poids <= this.poidsMax){
				o.setValide(true);
				this.poids += o.getPoids(); 
			}
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
