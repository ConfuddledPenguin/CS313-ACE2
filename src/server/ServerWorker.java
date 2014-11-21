package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

import message.MessageImp;

/**
 * This class is a worker to handle the connection to the client
 * after it has been established.
 * 
 * @author Tom Maxwell
 *
 */
public class ServerWorker implements Runnable{

	private Socket client = null;
	
	/**
	 * The constructor for the worker
	 * 
	 * @param s the connection to the client
	 */
	public ServerWorker(Socket s){
	
		client = s;
	}
	
	/**
	 * Handles the clients request
	 */
	@Override
	public void run() {
		
		try {
			
			InputStream in = client.getInputStream();
			BufferedReader br = new BufferedReader( new InputStreamReader(in));
			String line;
			
			while ( (line = br.readLine()) == null){ //wait for the the client to fill buffer
				Thread.yield();
			}
			
			MessageImp m = new MessageImp();
			m.setCounts(line);
			
			ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
			os.writeObject(m);
			
			client.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
