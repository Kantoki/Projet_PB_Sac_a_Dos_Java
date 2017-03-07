package outils;

import java.util.ArrayList;

public class ABR {
	
	private Double poidsMax ;
	private ArrayList<Objet> valeur ; // la valeur a la racine de léarbre ( null si arbre vide )
	private ABR sousArbreGauche ; // le sous - arbre gauche ( null si arbre vide )
	private ABR sousArbreDroit ; // le sous - arbre droit ( null si arbre vide )
	private boolean nouvelle_entrée;
	
	public ABR (){
		/* constructeur déarbre vide */
		this.sousArbreGauche = null ;
		this.sousArbreDroit = null ;
	}
	
	public ABR (ArrayList<Objet> valeur, Double poidsMax, boolean nouvelle_entrée){
		this();
		this.valeur = valeur;
		this.poidsMax = poidsMax;
		this.nouvelle_entrée = nouvelle_entrée;
	}
	
	public ArrayList<Objet> getValeur (){
		if(this.hauteur() == 1){
			return this.valeur;
		}
		else{
			ArrayList<Objet> valeurG = sousArbreGauche.getValeur();
			ArrayList<Objet> valeurD = sousArbreDroit.getValeur();
			if(ABR.getValeurTotale(valeurG) > ABR.getValeurTotale(valeurD))
				return valeurG;
			else
				return valeurD;
		}
	}
	
	

	public void ajouter (Objet o, boolean pourMoi){
		/* ajout déun element dans léarbre */

			
		if (this.nouvelle_entrée && (sousArbreDroit == null || sousArbreGauche == null)){
			//this.valeur = new ArrayList<Objet>();
			ArrayList<Objet> valeurA = new ArrayList<Objet>();
			valeurA.addAll(valeur);
			ArrayList<Objet> valeurB = new ArrayList<Objet>();
			valeurB.addAll(valeur);
					
			this.sousArbreGauche = new ABR (valeurA, poidsMax, false);
			this.sousArbreDroit = new ABR (valeurB, poidsMax, true);
			
			this.sousArbreGauche.ajouter(o, true);
		}
		else if(!this.nouvelle_entrée && pourMoi){
			if(o.getPoids() + this.getPoids() > poidsMax)
				return;
				valeur.add(o);
				this.nouvelle_entrée = true;
		}
		else if(this.nouvelle_entrée){
			this.sousArbreGauche.ajouter(o, true);
			this.sousArbreDroit.ajouter(o, false);
		}
}
	private String toStringInd(){
		String affichage ;
		if ( this.valeur == null ){
			affichage = "";
		}
		else {
			String affichageGauche = "";
			if ( sousArbreGauche != null ){
				affichageGauche = sousArbreGauche.toString ();
			}
			affichage = affichageGauche + this.valeur + "\n";
			String affichageDroit = "";
			if ( sousArbreDroit != null ){
				affichageDroit = sousArbreDroit.toString ();
			}
			affichage += affichageDroit ;
		}
		return affichage ;
	}
	
	public String toString(){
		if (this.hauteur() == 0)
			return this.toStringInd();
		else{
			return sousArbreGauche.toString() + "  " + sousArbreDroit.toString();
		}
	}
	public int hauteur (){
		if (this.sousArbreDroit == null && this.sousArbreGauche == null){
			return 1;
		} else {
			int hSAG = sousArbreGauche.hauteur ();
			int hSAD = sousArbreDroit.hauteur ();
			return 1 + (( hSAD > hSAG )? hSAD : hSAG );
		}
	}
	
	public Double getPoids(){
		if(valeur == null)
			return 0.;
		Double res = 0.;
		for(int i = 0 ; i < valeur.size(); ++i)
			res += valeur.get(i).getPoids();
		return res;
	}
	
	public static Double getValeurTotale(ArrayList<Objet> o){
		if(o == null)
			return 0.;
		Double res = 0.;
		for(int i = 0 ; i < o.size(); ++i)
			res += o.get(i).getValeur();
		return res;
	}
}


