/**
 * 
 */
package es.ubu.lsi.server;

import java.rmi.Naming;

/**
 * Inicializa el servidor.
 * 
 * @author Miguel Angel Leon Bardavio
 * @author Antonio de los Mozos Alonso
 *
 */
public class GameServerStarter {
	
	/**
	 * Exporta servidor remoto y lo registra en rmiregister.
	 */
	public GameServerStarter(){
		try {
			//GameServer stub = (GameServer) UnicastRemoteObject.exportObject(gameServer, 0);
			//Registry registro = LocateRegistry.getRegistry("localhost",23000);
			//registro.bind("gameServer", stub);
			
			//Inicia el proceso de exportación del servidor remoto
			System.out.println("Exportando servidor...");
			GameServer gameServer = new GameServerImpl();
			
			//Y su registro en RMI
			System.out.println("Registrando servidor..");
			Naming.rebind("gameServer", gameServer);
			
			System.out.println("Servidor preparado!");
			
		} catch (Exception e) {
			System.err.println("Error al iniciar servidor: " + e.toString());
		}
	}
}