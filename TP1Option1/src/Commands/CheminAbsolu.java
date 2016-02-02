package Commands;

import java.io.File;

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

	@Override
	public void faireCommande() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFile(File file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearCommand() {
		// TODO Auto-generated method stub
		
	}
}
