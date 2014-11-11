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
		
		UserIO io = new UserIO();
		while(true){
			
			io.print("Exit server: Y/n");
			if(io.getYesNo()){
				
				s.stop();
				break;
			}
		}
	}

}
