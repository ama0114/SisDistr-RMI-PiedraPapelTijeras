/**
 * 
 */
package es.ubu.lsi.server;

import java.rmi.Naming;

/**
 * @author Miguel Angel Leon
 *
 */
public class GameServerStarter {
	
	public GameServerStarter(){
		start();
	}
	
	private void start(){
		try {
			//Inicia el proceso de exportación del servidor remoto
			//Y su registro en RMI
			
			GameServer gameServer = new GameServerImpl();
			
			//GameServer stub = (GameServer) UnicastRemoteObject.exportObject(gameServer, 0);
			//Registry registro = LocateRegistry.getRegistry("localhost",23000);
	    	//registro.bind("gameServer", stub);
			
			Naming.rebind("gameServer", gameServer);
			System.out.println("Servidor preparado...");
			
		} catch (Exception e) {
			System.err.println("Excepcion en starter del servidor " + e.toString());
		}
	}
}
