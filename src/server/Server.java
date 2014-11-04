package server;

import java.io.*;
import java.net.*;

import message.*;

/**
 * 
 * The server class.
 * 
 * @author Tom
 *
 */
public class Server{
	
	/**
	 * Its starts here
	 * @param args none
	 */
	public static void main(String[] args){
		Server s = new Server();
	}
	
	/**
	 * 
	 */
	public Server(){
		
		try{
			
			ServerSocket sock = new ServerSocket(6100);
			
			while(true){
				
				Socket client = sock.accept();
				InputStream in = client.getInputStream();
				BufferedReader br = new BufferedReader( new InputStreamReader(in));
				
				String line;
				
				while ( (line = br.readLine()) != null){
					
					MessageImp m = new MessageImp();
					m.setCounts(line);
					
					ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
					os.writeObject(m);
				}
				
				client.close();
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
}
