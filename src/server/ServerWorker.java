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

	Socket client = null;
	
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
	public void run() {
		
		try {
			
			InputStream in = client.getInputStream();
			BufferedReader br = new BufferedReader( new InputStreamReader(in));
			String line;
			
			
			while ( (line = br.readLine()) == null){ //wait for the the client to fill buffer
				Thread.yield();
			}
			
			/*
			// This is far from the best way to test if the server will accept more than one
			// connection at a time, but it works. 
			//
			// This is in no way part of my submission, this code is here to make testing
			// easier.
			// 
			// To use this delete the outer comments
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			*/
			
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
