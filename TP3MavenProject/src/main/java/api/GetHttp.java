package api;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class GetHttp Gere les requete HTTP
 * 
 * @author Sylvester Vuong, Julien Aymong, Samuel Gaudreau
 */
public class GetHttp {

	/**
	 * Retourne la réponse de la requête HTTP provenant du serveur Basé sur
	 * l'exemple suivant :
	 * http://www.journaldev.com/7148/java-httpurlconnection-example-to-send-
	 * http-getpost-requests
	 * 
	 * @throws Exception
	 */
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
		gestionnaireFichiers(result.toString());

	}

	/**
	 * Parser la réponse reçu du serveur
	 * 
	 * @param fileList
	 * @throws IOException
	 */
	private static void gestionnaireFichiers(String fileList) throws IOException {
		String[] files = fileList.split("\\?");

		for (int i = 0; i < files.length; i = i + 3) {
			if (files[i + 2].equals("Folder")) {
				File dir = new File(files[i + 1] + "/" + files[i]);
				dir.mkdirs();
			} else {
				File tmp = new File(files[i + 1] + "/", files[i]);
				tmp.createNewFile();
			}
		}
	}
}
