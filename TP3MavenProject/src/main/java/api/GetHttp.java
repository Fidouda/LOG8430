package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class GetHttp
 * Gere les requete HTTP
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */

public class GetHttp {
	//http://www.journaldev.com/7148/java-httpurlconnection-example-to-send-http-getpost-requests
	 public static void getHTML() throws Exception {
		 
		 StringBuilder result = new StringBuilder();
	      URL url = new URL("http://localhost:8080");
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      fileManager(result.toString());
	        
	 }
	 
	 private static void fileManager(String fileList) throws IOException
	 {
		//fileList = fileList.replaceAll("\\?", " ?");
		String[] files = fileList.split("\\?");
		 
		 for(int i = 0; i < files.length; i= i+3)
		 {
			 if(files[i+2].equals("Folder"))
			 {
				 File dir = new File( files[i+1] + "/" + files[i]);
	        	 dir.mkdirs();
			 }
			 else
			 {
				 File tmp = new File(files[i+1] + "/", files[i]);
	        	 tmp.createNewFile();
			 }
		 }
	 }
}
