package Commands;

public class CheminAbsolu extends CommandeAbstraite{

	public CheminAbsolu(String chemin){
		super(chemin);//generer automatiquement par eclipse
		nom_ = "chemin absolu du fichier ou du dossier";
	}
	
	@Override
	public boolean isValid() {
		if(chemin_ == null || chemin_.isEmpty())
			return false;
		return true;
	}

	@Override
	public String executerCommande() throws Exception {
		if (!isValid()) {
			throw new Exception("Erreur, chemin null");
		}
		setAffichage(chemin_);
		return getAffichage();
	}
}
