package com.log3430;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.Arrays;
import java.util.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class DropBoxAPI {
	

	public static class Main {
	    private static final String ACCESS_TOKEN = "BEmi3psTGxkAAAAAAAAAE8nmTqNcJB3Dg6wSlCj4bsAwos0pjpSaHmEYq1h-sGKG";
	    //https://www.dropbox.com/developers/documentation/java#tutorial
	    public static void main(String args[]) throws DbxException, IOException {
	        // Create Dropbox client
	        DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
	        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

	        // Get current account info
	        FullAccount account = client.users().getCurrentAccount();
	        System.out.println(account.getName().getDisplayName());

	        // Get files and folder metadata from Dropbox root directory
	        ListFolderResult result = client.files().listFolder("");
	        
	        while (true) {
	            for (Metadata metadata : result.getEntries()) {
	                System.out.println(metadata.getPathLower());
	            }

	            if (!result.getHasMore()) {
	                break;
	            }

	            result = client.files().listFolderContinue(result.getCursor());
	        }
	        List<String> lines = Arrays.asList("The first line", "The second line");
	        Path file = Paths.get("test.txt");
	        Files.write(file, lines, Charset.forName("UTF-8"));
	        
	        // Upload "test.txt" to Dropbox
	        try (InputStream in = new FileInputStream("test.txt")) {
	            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
	                .uploadAndFinish(in);
	        }
	    }
	}
}