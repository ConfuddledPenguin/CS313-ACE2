package server;

import java.io.*;
import java.net.*;

import message.*;

public class Server{
	
	
	public static void main(String[] args){
		Server s = new Server();
	}
	
	public Server(){
		
		try{
			
			ServerSocket sock = new ServerSocket(6100);
			
			while(true){
				
				Socket client = sock.accept();
				InputStream in = client.getInputStream();
				BufferedReader br = new BufferedReader( new InputStreamReader(in));
				
				String line;
				
				while ( (line = br.readLine()) != null){
					
					MessageImp1 m = new MessageImp1();
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
