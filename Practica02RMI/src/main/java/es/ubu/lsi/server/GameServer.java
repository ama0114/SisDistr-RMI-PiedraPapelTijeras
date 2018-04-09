package es.ubu.lsi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.ubu.lsi.client.GameClient;
import es.ubu.lsi.common.GameElement;

/**
 * Remote server.
 * 
 * @author Raúl Marticorena
 * @author Joaquin P. Seco
 * @author Mario Erro
 *
 */
public interface GameServer extends Remote {
	
	/**
	 * Registers a new client.
	 * 
	 * @param client client
	 * @return client id
	 * @throws RemoteException remote error
	 */
	public abstract int checkIn(GameClient client) throws RemoteException;
	
	
	/**
	 * Unregisters a new client.
	 * 
	 * @param client current client
	 * @throws RemoteException remote error
	 */
	public abstract void logout(GameClient client) throws RemoteException;
	
	
	/**
	 * Orders of shutdown server.
	 * 
	 * @param client current client sending the message
	 * @throws RemoteException remote error
	 */
	public abstract void shutdown(GameClient client) throws RemoteException;
	
	/**
	 * Send situation of the game.
	 * 
	 * @param element current element sending the player
	 * @throws RemoteException remote error
	 */
	public abstract void broadcastRoom(GameElement element) throws RemoteException;
}