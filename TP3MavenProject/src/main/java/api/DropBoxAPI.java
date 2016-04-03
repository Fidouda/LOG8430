package api;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v1.DbxClientV1;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.files.UploadErrorException;
import com.dropbox.core.v2.users.FullAccount;

import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DropBoxAPI {
	
	    private static final String ACCESS_TOKEN = "BEmi3psTGxkAAAAAAAAAE8nmTqNcJB3Dg6wSlCj4bsAwos0pjpSaHmEYq1h-sGKG";
	    private DbxClientV1 client1;
	    private DbxClientV2 client;
	    //https://www.dropbox.com/developers/documentation/java#tutorial
	    public void getFiles(String path) throws DbxException, IOException {
	        // Create Dropbox client
	        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
	        client1 = new DbxClientV1(config,ACCESS_TOKEN );
	        client = new DbxClientV2(config, ACCESS_TOKEN);
	       
	        // Get current account info
	        FullAccount account = client.users().getCurrentAccount();
	        System.out.println(account.getName().getDisplayName());
	        
	        getFiles(client1, path);

	        // Get files and folder metadata from Dropbox root directory
	       /* ListFolderBuilder allFiles = client.files().listFolderBuilder(path); 
	        
	        //getFiles(client, result);
	        
	        allFiles.withRecursive(true);
	        
	        ListFolderResult result = allFiles.start();
	        
	        while (true) {
	            for (Metadata metadata : result.getEntries()) {
	                System.out.println(metadata.getPathLower());
	                
	            }

	            if (!result.getHasMore()) {
	                break;
	            }

	            result = client.files().listFolderContinue(result.getCursor());
	        }
	       */
	        
	        //Metadata meta = client.files().getMetadata("");
	        //System.out.println(meta.toStringMultiline());
	        
	        
	        /*DbxEntry.WithChildren listing = client1.getMetadataWithChildren("/");
	        System.out.println("Files in the root path:");
	        for (DbxEntry child : listing.children) {
	            System.out.println("	" + child.name + ": " + child.toString());
	            if(child.isFolder())
	        }/*
	        
	        
	        
	        /*while (true) {
	            for (Metadata metadata : result.getEntries()) {
	                System.out.println(metadata.getPathLower());
	                
	            }

	            if (!result.getHasMore()) {
	                break;
	            }

	            result = client.files().listFolderContinue(result.getCursor());
	        }*/

	        
	        
	    }
	    
	    private void getFiles(DbxClientV1 client, String path ) throws ListFolderErrorException, DbxException, IOException
	    {
	    	if(path.isEmpty())
	    		path = "/";
	    	
	    	DbxEntry.WithChildren listing = client.getMetadataWithChildren(path);
	 
	        for (DbxEntry child : listing.children) {
	        	if(child.isFolder())
	        	{
	        		File dir = new File("root" + path +"/" + child.name);
	        		dir.mkdirs();
	        		
	        		getFiles(client, path + "/" + child.name);
	        	}
	        	else
	        	{
	        		File tmp = new File("root" + path, child.name);
	        		tmp.createNewFile();
	        	}
	        }
	    }
	    
	    private void subFolder(String path)
	    {
	    	
	    }
	    
	    public void uploadFile() throws IOException, UploadErrorException, DbxException
	    {
	    
	    	DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
		    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
		    
	        List<String> lines = Arrays.asList("The first line", "The ssweetdyolo");
	        Path file = Paths.get("test.txt");
	        Files.write(file, lines, Charset.forName("UTF-8"));
	        
	        // Upload "test.txt" to Dropbox
	        try (InputStream in = new FileInputStream("test.txt")) {
	            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
	                .uploadAndFinish(in);
	        }
	    }
	    
}