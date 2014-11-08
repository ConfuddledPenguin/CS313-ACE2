package client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;

import userIO.UserIO;
import message.Message;

/**
 * The client for this system.
 * 
 * @author Tom Maxwell
 */
public class Client{
	
	UserIO io;
	
	/**
	 * It start here
	 * @param args none
	 */
	public static void main(String[] args){
		@SuppressWarnings("unused")
		Client c = new Client();
	}
	
	/**
	 * The constructor for the Client.
	 * Since I possess a high degree of laziness most of the is code in here.
	 * 
	 * This method gets input to the user in the form of a string, which 
	 * is then passed to the server.
	 * 
	 * The client then waits for a response getting more and more 
	 * agitated until it gets one. It then prints out the results 
	 */
	public Client(){
	
		io = new UserIO();
		io.print("Please enter the string to send to the server");
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
			
			//wait for a response
			while( (o = oi.readObject()) == null){
				Thread.yield();
			}
			
			processreturn(o);
			
			sock.close();
			
		}catch(WrongObjectReturned e){
			
			io.print("SOMTHING HAS GONE HORRIBLY WRONG!!!!! AHHHHHHHHHHH!!!!");
			io.print("I am sure the server is sorry for sending the wrong thing back");
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Handles the object returned from the server
	 * 
	 * @param o The object to handle
	 * @throws WrongObjectReturned If the object returned is not of the expected type
	 */
	private void processreturn(Object o) throws WrongObjectReturned {
		
		if ( o instanceof Message){
			
			Message m = (Message) o;
			
			io.print("The number of characters in the entered string is: " + m.getCharacterCount());
			io.print("The number of digits in the endered string is: " + m.getDigitCount());
			
		}else{
			
			throw new WrongObjectReturned();
		}
	}
}
