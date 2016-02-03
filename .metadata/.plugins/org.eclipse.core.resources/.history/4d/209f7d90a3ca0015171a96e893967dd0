package Commands;

import java.io.File;

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
		
		System.out.println("Executer Nom Repertoir");
		
		if(!isValid())
			throw new Exception("Erreur, pas un repertoir");
		
		int index = chemin_.lastIndexOf("\\");
		String cheminAffichage = chemin_;
		if(index >= 0)
			cheminAffichage = chemin_.substring(index+1, chemin_.length());
		
		setAffichage(cheminAffichage);
		
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
