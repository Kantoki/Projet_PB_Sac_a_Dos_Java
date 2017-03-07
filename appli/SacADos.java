package appli;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import methodes.Gloutonne;
import methodes.IMethode;
import methodes.ProgDynamique;
import methodes.Pse;
import outils.Objet;

public class SacADos {
	private final static String METHODE1 = "gloutonne";
	private final static String METHODE2 = "prog.dynamique";
	private final static String METHODE3 = "pse";
	private IMethode méthode;
	private ArrayList<Objet> objets;
	private Scanner scan;
	private Double poidsMax;
	private Double poids;
	
	
	public SacADos(){
		objets = new ArrayList<Objet>();
	}
	
	public SacADos(String fichier, Double poidsMax){
		this();
		this.poidsMax = poidsMax;
		this.poids = 0.;
		File fich = new File(fichier);
		
		try {
			scan = new Scanner(new FileInputStream(fich));
		} 
		catch (FileNotFoundException e) {
			System.err.println("Impossible d'ouvrir le fichier "+ fich.getAbsolutePath() + ", verifiez son emplacement.");
			System.exit(-1);
		}
		

		while(scan.hasNextLine()){
			String line = scan.nextLine().trim();
			String[] tab = line.split(";");
			objets.add(new Objet(tab[0], Double.valueOf(tab[1]), Double.valueOf(tab[2])));
		}
	}
	
	public void resoudre(String méthode){
		if(méthode.equals(METHODE1)){
			this.méthode = new Gloutonne(this.objets, this.poidsMax);
			this.méthode.résoudre();
			this.poids = this.méthode.getPoids();
			this.objets = this.méthode.getResultat();
			
		}
		
		else if(méthode.equals(METHODE2)){
			this.méthode = new ProgDynamique(this.objets, this.poidsMax);
			this.méthode.résoudre();
			this.poids = this.méthode.getPoids();
			this.objets = this.méthode.getResultat();
		}
		
		else if(méthode.equals(METHODE3)){
			this.méthode = new Pse(this.objets, this.poidsMax);
			this.méthode.résoudre();
			this.poids = this.méthode.getPoids();
			this.objets = this.méthode.getResultat();
			
		}
		
		else
			System.err.println("Aucune méthode valide n'est selectionnée, veuillez entrer une méthode valide.");
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		Double totalValeur = 0.;
		for(int i = 0; i < objets.size(); i++){
			Objet o = objets.get(i);
			sb.append(o);
			int limite = 50 - o.toString().length();
			for(int j = 0;  j < limite; j++){
				sb.append(" ");
			}
			if(o.isValide()){
				sb.append("=>        "+o);
				totalValeur += o.getValeur();
			}
			sb.append(System.lineSeparator());
		}
		sb.append(System.lineSeparator());
		sb.append("Valeur totale: "+ totalValeur + System.lineSeparator() + "Poids total: " + this.poids);
		return sb.toString();
	}

	public long getTemps() {
		return this.méthode.getTemps();
	}
}
