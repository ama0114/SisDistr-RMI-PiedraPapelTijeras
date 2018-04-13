/**
 * 
 */
package es.ubu.lsi.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.ubu.lsi.common.ElementType;
import es.ubu.lsi.common.GameResult;

/**
 * Implementacion de cliente remoto.
 * 
 * @author Miguel Angel Leon Bardavio
 * @author Antonio de los Mozos Alonso
 *
 */
public class GameClientImpl extends UnicastRemoteObject implements GameClient {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nickname del jugador.
	 */
	private String nickname;
	
	/**
	 * ID del cliente.
	 * <p>
	 * Inicialmente ID invalido.
	 */
	private int idClient = -1;
	
	/**
	 * ID de la sala en la que juega el cliente.
	 * <p>
	 * Inicialmente invalido.
	 */
	private int idRoom = -1;
	
	/**
	 * Ultima jugada del jugador.
	 * <p>
	 * Inicialmente nula.
	 */
	private ElementType lastElement = null;
	
	/**
	 * Constructor de cliente remoto.
	 * 
	 * @param nickname Nickname del jugador.
	 * @throws RemoteException if remote communication has problems
	 */
	public GameClientImpl(String nickname) throws RemoteException {
		super();
		this.nickname = nickname;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getId()
	 */
	public int getId() throws RemoteException {
		return this.idClient;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setId(int)
	 */
	public void setId(int id) throws RemoteException {
		this.idClient = id;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#receive(es.ubu.lsi.common.GameResult)
	 */
	public void receive(GameResult result) throws RemoteException {
		System.out.println(nickname + " " + result.toString());
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getNickName()
	 */
	public String getNickName() throws RemoteException {
		return this.nickname;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getIdRoom()
	 */
	public int getIdRoom() throws RemoteException {
		return this.idRoom;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setIdRoom(int)
	 */
	public void setIdRoom(int idRoom) throws RemoteException {
		this.idRoom = idRoom;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setLastElement(es.ubu.lsi.common.ElementType)
	 */
	public void setLastElement(ElementType lastElement) throws RemoteException {
		this.lastElement = lastElement;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getLastElement()
	 */
	public ElementType getLastElement() throws RemoteException {
		return this.lastElement;
	}

}
