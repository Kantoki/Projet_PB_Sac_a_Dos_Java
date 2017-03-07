package methodes;

import java.util.ArrayList;

import outils.Objet;

public class ProgDynamique implements IMethode {
	private long temps = 0;
	private ArrayList<Objet> objets;
	private Double poids;
	private Double poidsMax;
	
	public ProgDynamique(ArrayList<Objet> objets, Double poidsMax){
		this.poids = 0.;
		this.poidsMax = poidsMax;
		this.objets = objets;
	}

	

	@Override
	public void résoudre() {
		this.temps = System.nanoTime();
		Double[][] matrice = new Double[objets.size()][poidsMax.intValue() + 2];
		
		for (int j = 0; j < matrice[0].length; ++j){
			if(objets.get(0).getPoids() > j)
				matrice[0][j] = 0.;
			else
				matrice[0][j] = objets.get(0).getValeur();			
		}
		
		for (int i = 1; i < matrice.length; ++i){
			Objet o = objets.get(i);
			for(int j = 0 ; j <  matrice[0].length; ++j){
				if(o.getPoids() > j)
					matrice[i][j] = matrice[i-1][j];
				else
					matrice[i][j] = Math.max(matrice[i-1][j] , matrice[i-1][j-o.getPoids().intValue()] + o.getValeur());
			}
		}
		
		for (int i = 0; i < matrice.length; ++i){
			for(int j = 0 ; j <  matrice[0].length; ++j){
				System.out.print("   "+ matrice[i][j]+ "   ");
			}
			System.out.println();
		}
		
		int selection = matrice[0].length - 1;
		int ligne = matrice.length - 1;
		
		while( matrice[matrice.length - 1][selection] == matrice[matrice.length - 1][selection - 1])
			selection--;
		
		
		while (selection >= 0){
			while(ligne > 0 && matrice[ligne][selection] == matrice[ligne-1][selection])
				ligne--;
			selection -= objets.get(ligne).getPoids();
			if ( selection > 0){
				objets.get(ligne).setValide(true);
				this.poids += objets.get(ligne).getPoids();
			}
			ligne--;
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
