package Commands;

public class NomFichier extends CommandeAbstraite {

	public NomFichier(String chemin) {
		super(chemin); //generer automatiquement par eclipse
		nom_ = "Nom du fichier";
	}
	
	@Override
	public boolean isValid(){
		if(chemin_ == null || chemin_.isEmpty())
			return false;
		
		if(getType() == Type.FICHIER)
			return true;
		
		return false;
	}
	
	@Override
	public String executerCommande() throws Exception{
		if(!isValid())
			throw new Exception("Erreur, pas un fichier");
		
		int index = chemin_.lastIndexOf("\\");
		String cheminAffichage = chemin_;
		if(index != -1)
			cheminAffichage = chemin_.substring(index+1, chemin_.length());
		
		setAffichage("Nom du fichier: " + cheminAffichage);
		
		return getAffichage();
	}
}
