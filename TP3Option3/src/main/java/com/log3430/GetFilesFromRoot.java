package com.log3430;

import java.io.File;
import java.util.Vector;

public class GetFilesFromRoot {

	private static Vector<Fichier> vector;
	
	static public Vector<Fichier> retrieveFiles(){
		
		vector = new Vector<Fichier>();
		
		getFileFromFolder("root");
	
		return vector;
	}
		
	private static void getFileFromFolder(String path)
	{
	
		File folder = new File(path);
		
		for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	vector.addElement(new Fichier(fileEntry.getName(), path, "Folder"));
	        	getFileFromFolder(path + "/" + fileEntry.getName());
	        } else {
	        	vector.addElement(new Fichier(fileEntry.getName(), path, "Files"));
	        }
		
		}
	}
	
}
