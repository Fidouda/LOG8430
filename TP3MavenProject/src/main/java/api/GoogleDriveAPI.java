package api;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.*;
import com.google.api.services.drive.Drive;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoogleDriveAPI {
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/drive-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart.json
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    private Map<String, String> mapFolder;
    private Drive service;
    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            GoogleDriveAPI.class.getResourceAsStream("client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Drive client service.
     * @return an authorized Drive client service
     * @throws IOException
     */
    public static Drive getDriveService() throws IOException {
        Credential credential = authorize();
        return new Drive.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public void startApi() throws IOException {
        // Build a new authorized API client service.
    	mapFolder = new HashMap<String, String>();
        service = getDriveService();
        createFolder("root", "root");
        
        createFilesInFoler();
        
        String n = "test";
        System.out.println(n);
    }
        
       // List<File> result = service.files().list().setQ("mimeType = 'application/vnd.google-apps.folder' and 'root' in parents and trashed=false").execute().getFiles();
      
        private void createFilesInFoler() throws IOException {
		
        	 FileList result = service.files().list()
        	           .setQ("mimeType!='application/vnd.google-apps.folder' and trashed=false")
        	           .setSpaces("drive")
        	           .setPageSize(1000)
        	           .setFields("nextPageToken, files(id, name, parents)")
        	           .execute();
        	        

        	       List<com.google.api.services.drive.model.File> files = result.getFiles();
        	       if (files == null || files.size() == 0) {
        	            System.out.println("No files found.");
        	       } else {
        	    	   
        	            for (com.google.api.services.drive.model.File file : files) {
        	            			
        	            		if(file.getParents()!=null && mapFolder.containsKey(file.getParents().get(0)))
        	            		{
        	            			File tmp = new File(mapFolder.get(file.getParents().get(0)), file.getName());
        	            			tmp.createNewFile();
        	            		}
        	            		
        	            		else
        	            		{
        	
        	            			File tmp = new File("root", file.getName());
        	            			System.out.println( file.getName());
        	            			tmp.createNewFile();
        	            		}
        	            	
        	        		
        	            }
        	       }
		
	}

	// Print the names and IDs for up to 10 files.
    private void createFolder(String id, String path) throws IOException{
    	
    	//Ajout dans le liste map
    	if(id != "root")
    		mapFolder.put(id, path);
    	
        FileList result = service.files().list()
           .setQ("'" +id + "' in parents and mimeType='application/vnd.google-apps.folder' and trashed=false")
           .setSpaces("drive")
           .setPageSize(1000)
           .setFields("nextPageToken, files(id, name, parents)")
           .execute();
        
        

       List<com.google.api.services.drive.model.File> files = result.getFiles();
       if (files == null || files.size() == 0) {
            System.out.println("No files found.");
       } else {
    	   
            for (com.google.api.services.drive.model.File file : files) {
            	File dir = new File(path +"/" +file.getName());
        		dir.mkdirs();
        		createFolder(file.getId(), path +"/" +file.getName());
            }
       }
       
      
      
    }

}