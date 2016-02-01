package Commands;

public class NomRepertoire extends CommandeAbstraite{

	public NomRepertoire(String chemin){
		super(chemin); //generer automatiquement par eclipse
		nom_ = "Nom du repertoir";
	}

	@Override
	public boolean isValid() {
		if(chemin_ == null || chemin_.isEmpty())
			return false;
		
		if(getType() == Type.REPERTOIR)
			return true;
		
		return false;
	}

	@Override
	public String executerCommande() throws Exception {
		if(!isValid())
			throw new Exception("Erreur, pas un repertoir");
		
		int index = chemin_.lastIndexOf("\\");
		String cheminAffichage = chemin_;
		if(index >= 0)
			cheminAffichage = chemin_.substring(index+1, chemin_.length());
		
		setAffichage("Nom du repertoir: " + cheminAffichage);
		
		return getAffichage();
	}
	
}
