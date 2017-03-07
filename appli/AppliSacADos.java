/*
 * Projet AAV
 * Victor CHEN
 * Amin GHELIS
 */
package appli;

public class AppliSacADos {
	public static void main(String args[]){
		if(args.length < 1){
			System.out.println("Utilisation: fichier poid-max méthode");
			System.exit(-1);
		}
			
		String fich = args[0];
		Double poidsMax = Double.valueOf(args[1]);
		String methode = args[2];
		
		SacADos sacADos = new SacADos(fich, poidsMax);
		sacADos.resoudre(methode);
		System.out.println(sacADos);
		
		System.out.println("Temps écoulé: " + Long.toString(System.nanoTime() - sacADos.getTemps())+ " nanosecondes");
		
	}
}
