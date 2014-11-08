package server;

import userIO.UserIO;

public class ServerDriver {
	
	/**
	 * Its starts here
	 * @param args none
	 */
	public static void main(String[] args){
		Server s = new Server();
		new Thread(s).start();
		
		Boolean run = true;
		UserIO io = new UserIO();
		while(run){
			
			io.print("Exit server: Y/n");
			if(io.getYesNo()){
				
				run = false;
				s.stop();
			}
		}
	}

}
