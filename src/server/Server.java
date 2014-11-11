package server;

import java.io.*;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * The server class.
 * 
 * @author Tom Maxwell
 *
 */
public class Server implements Runnable{
	
	/*
	 * Went for a fixed pool as this should prevent the server becoming bogged down
	 * this processes. 10 threads was chosen at random feel free to chnage this
	 * value if it will not decrease performance
	 */
	private ExecutorService threadPool = Executors.newFixedThreadPool(10);
	private Boolean run = true;
	private ServerSocket sock;
	private Thread thread;
	

	/**
	 * 
	 */
	public void run(){
		
		synchronized (this) {
			this.thread = Thread.currentThread();
		}
		
		try{
			
			openServerSocket();
	
			while(run){
				
				Socket client = null;
				
				try{
					client = sock.accept();
				}catch(IOException e){
					if(!run){
						return;
					}else{
						throw new RuntimeException("cant accept client conection" + e);
					}
				}
				
				threadPool.execute(new ServerWorker(client));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}	
	}
	
	/**
	 * Tries to open the socket
	 */
	private void openServerSocket() {
        try{
            sock = new ServerSocket(6100);
        }catch (IOException e){
            throw new RuntimeException("Cannot open port 6100" + e);
        }
    }
	
	/**
	 * Shuts the server down
	 */
	public synchronized void stop(){
        run = false;
        try{
        	//Shut down all running threads
            threadPool.shutdown();
            //Close the socket
            sock.close();
        }catch (IOException e){
            throw new RuntimeException("Cannot close port 6100" + e);
        }
    }
}
