package com.log3430;

public class Fichier {
	
	private String nom_;
	private String pathParents_;

	Fichier(String nom, String pathParents){
		nom_  = nom;
		pathParents_ = pathParents;
	}
	
	public String getNom()
	{
		return nom_;
	}
	
	public String getPathParents(){
		return pathParents_;
	}
	
}
