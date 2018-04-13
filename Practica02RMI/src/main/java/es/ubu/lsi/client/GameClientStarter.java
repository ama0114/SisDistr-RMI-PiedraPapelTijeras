/**
 * 
 */
package es.ubu.lsi.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;

import es.ubu.lsi.common.ElementType;
import es.ubu.lsi.common.GameElement;
import es.ubu.lsi.server.GameServer;

/**
 * Arranca el cliente dinamicamente.
 * 
 * @author Miguel Angel Leon Bardavio
 * @author Antonio de Los Mozos Alonso
 *
 */
public class GameClientStarter {

	/**
	 * Nickname del juador.
	 */
	private String nickname = "";

	
	/**
	 * Recibe el nickname como argumento e inicia el cliente.
	 * 
	 * @param args Recibe nickname como argumento
	 */
	public GameClientStarter(String[] args) {
		this.nickname = args[0];
		start();
	}

	/**
	 * Exporta a cliente remoto y lo enlaza con el servidor.
	 * <p>
	 * En bucle recibe jugadas del usuario y actua en consecuencia.
	 */
	private void start() {
		try {
			//Inicia el proceso de exportacion del cliente remoto 
			//y de la resolucion del servidor remoto, enlazando ambos objetos (método checkIn ).
			System.out.println("Registrando...");
			GameClient cliente = new GameClientImpl(this.nickname);

			//Registry registro = LocateRegistry.getRegistry(this.host);
			//Registry registro = LocateRegistry.getRegistry(host, 23000);
			//GameServer stub = (GameServer)registro.lookup("gameServer");
			System.out.println("Conectando con servidor...");
			GameServer stub = (GameServer) Naming.lookup("gameServer");
			if (stub != null) {System.out.println("Conectado!");}
			int idClient = stub.checkIn(cliente);
			//En un bucle, recibe las entradas de texto del usuario y en función del mismo, 
			//invoca a los métodos remotos del servidor.
			if( idClient > 0){
				System.out.println("'" + this.nickname + "' jugando con id [" + idClient + "]");
				BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in));
				String userInput;
				System.out.println("¡Que comience la partida!");
				while ((userInput = stdIn.readLine().toUpperCase()) != null) {
					if (userInput.equals("PIEDRA") || userInput.equals("PAPEL") || userInput.equals("TIJERA")) {
						stub.broadcastRoom(new GameElement(idClient, ElementType.valueOf(userInput)));
					}else if (userInput.equals("LOGOUT")) {
						//Cuando el usuario introduce el texto logout finaliza su ejecución.
						stub.logout(cliente);
						System.exit(0);
					}else {
						System.err.println("INVALID COMMAND");
					}
				}
			}else{
				System.err.println("Error: Nickname en uso");
				System.exit(2);
			}
		} catch (Exception e) {
			System.err.println("Error en cliente: " + e.toString());
			System.exit(1);
		}
	}
}
