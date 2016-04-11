package com.log3430;

import java.util.Vector;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
@EnableAutoConfiguration
public class SampleController {

    @RequestMapping("/")
    @ResponseBody
    String home() {
    	
    	Vector<Fichier> vector = GetFilesFromRoot.retrieveFiles();
    	String http = "";
    	
    	for(int i = 0; i < vector.size(); i++)
    	{
    		http += vector.get(i).getNom(); 
    		http += "?"; 
    		http += vector.get(i).getPathParents();
    		http += "?";
    		http += vector.get(i).getFolderOrFile();
    		if(i != vector.size() -1)
    			http += "?";
    	}
    	
        return http;
    }
}
