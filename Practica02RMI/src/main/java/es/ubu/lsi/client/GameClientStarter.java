/**
 * 
 */
package es.ubu.lsi.client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import es.ubu.lsi.server.GameServer;

/**
 * @author Miguel Ángel León
 *
 */
public class GameClientStarter {

	private String nickname = "player";

	private String host;
	
	/**
	 * 
	 */
	public GameClientStarter(String[] args) {// TODO fallan los argumentos
		this.nickname = args[0]; // TODO comprobar si existe
		this.host = (args.length < 2) ? null : args[1];
		start();
	}

	private void start() {
		//Inicia el proceso de exportacion del cliente remoto 
		//y de la resolucion del servidor remoto, enlazando ambos objetos (método checkIn ).
		try {
			System.out.println("Registrando...");
			GameClient cliente = new GameClientImpl(this.nickname);
			
			//Registry registro = LocateRegistry.getRegistry(this.host);
			//Registry registro = LocateRegistry.getRegistry(host, 23000);
			//GameServer stub = (GameServer)registro.lookup("gameServer");
			
			GameServer stub = (GameServer) Naming.lookup("gameServer");
			stub.checkIn(cliente);
			System.out.println("Cliente registrado");
		} catch (Exception e) {
			System.err.println("Excepcion en starter del cliente " + e.toString());
		} 

		//En un bucle, recibe las entradas de texto del usuario y en función del mismo, 
		//invoca a los métodos remotos del servidor.

		//Cuando el usuario introduce el texto logout finaliza su ejecución.
	}

}
