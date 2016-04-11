package com.log3430;

public class Fichier {
	
	private String nom_;
	private String pathParents_;
	private String folderOrFile_;

	Fichier(String nom, String pathParents, String folderORFile){
		nom_  = nom;
		pathParents_ = pathParents;
		folderOrFile_ = folderORFile;
	}
	
	public String getNom()
	{
		return nom_;
	}
	
	public String getPathParents(){
		return pathParents_;
	}
	
	public String getFolderOrFile(){
		return folderOrFile_;
	}
	
}
