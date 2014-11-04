package client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import message.MessageImp1;

/**
 * The client for this system.
 * 
 * @author Tom Maxwell
 */
public class Client{
	
	public static void main(String[] args){
		Client c = new Client();
	}
	
	public Client(){
	
		UserIO io = new UserIO();
		String s = io.getString();
		
		Socket sock;
		
		try {
			//Connect
			sock = new Socket("127.0.0.1", 6100);
			
			//write
			PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
			pw.println(s);
			
			
			//read
			InputStream in = sock.getInputStream();			
			ObjectInputStream oi = new ObjectInputStream(in);
			Object o;
			
			while( (o = oi.readObject()) == null);
			
			MessageImp1 m;
			int charCount = 0;
			int digitCount = 0;
			if ( o instanceof MessageImp1){
				m = (MessageImp1) o;
				
				charCount = m.getCharacterCount();
				digitCount = m.getDigitCount();
				
				System.out.println(charCount + ":" + digitCount);
			}else{
				System.out.println("SOMTHING HAS GONE HORRIBLY WRONG!!!!! AHHHHHHHHHHH!!!!");
				System.out.println("I am sure the server is sorry for sending the wrong thing back");
			}
			
			sock.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
