package outils;

public class Objet {
	
	private String nom;
	private Double poids;
	private Double valeur;
	private boolean valide;
	
	public Objet(String nom, Double poids, Double valeur) {
		super();
		this.nom = nom;
		this.poids = poids;
		this.valeur = valeur;
		this.valide = false;
	}

	public String getNom() {
		return nom;
	}

	public Double getPoids() {
		return poids;
	}

	public boolean isValide() {
		return valide;
	}

	public void setValide(boolean valide) {
		this.valide = valide;
	}

	public Double getValeur() {
		return valeur;
	}
	
	public String toString(){
		return nom +" - Poids: "+ Double.toString(poids)+ " - Valeur: "+Double.toString(valeur);
	}
	
	
}
