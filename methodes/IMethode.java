package methodes;

import java.util.ArrayList;

import outils.Objet;

public interface IMethode {
	
	public void résoudre();

	public long getTemps();
	
	public ArrayList<Objet> getResultat();
	
	public Double getPoids();

}
