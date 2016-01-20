import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SSJ_MusicStreamer_GUI {

	private JFrame frame;
	private JTextField fieldClientId;
	private JTextField fieldClientSecret;
	
	private String clientId;
	private String clientSecret;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SSJ_MusicStreamer_GUI window = new SSJ_MusicStreamer_GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void authentication(String clientId, String clientSecret) {
		//final String clientId = "IdHere";
	    //final String clientSecret = "SecretHere";

	    final Api api = Api.builder()
	            .clientId(clientId)
	            .clientSecret(clientSecret)
	            .build();

	    /* Create a request object. */
	    final ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

	    /* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
	    final SettableFuture<ClientCredentials> responseFuture = request.getAsync();

	    /* Add callbacks to handle success and failure */
	    Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
	      @Override
	      public void onSuccess(ClientCredentials clientCredentials) {
	        /* The tokens were retrieved successfully! */
	        System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
	        System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");

	        /* Please note that this flow does not return a refresh token.
	         * That's only for the Authorization code flow */
	      }

	      @Override
	      public void onFailure(Throwable throwable) {
	        /* An error occurred while getting the access token. This is probably caused by the client id or
	         * client secret is invalid. */
	    	  System.out.println("Failed to resolve future: " + throwable.getMessage());
	      }
	    });
	}

	/**
	 * Create the application.
	 */
	public SSJ_MusicStreamer_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		fieldClientId = new JTextField();
		fieldClientId.setBounds(176, 92, 86, 20);
		frame.getContentPane().add(fieldClientId);
		fieldClientId.setColumns(10);
		
		fieldClientSecret = new JTextField();
		fieldClientSecret.setBounds(176, 139, 86, 20);
		frame.getContentPane().add(fieldClientSecret);
		fieldClientSecret.setColumns(10);
		
		JLabel labelClientId = new JLabel("Client ID");
		labelClientId.setBounds(62, 95, 68, 14);
		frame.getContentPane().add(labelClientId);
		
		JLabel labelClientSecret = new JLabel("Client Secret");
		labelClientSecret.setBounds(41, 142, 89, 14);
		frame.getContentPane().add(labelClientSecret);
		
		JButton btnConnexion = new JButton("Connexion");
		btnConnexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clientId = fieldClientId.getText();
				clientSecret = fieldClientSecret.getText();
				authentication(clientId, clientSecret);
			}
		});
		btnConnexion.setBounds(152, 185, 142, 23);
		frame.getContentPane().add(btnConnexion);
	}
}
