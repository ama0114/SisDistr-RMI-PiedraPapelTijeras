package es.ubu.lsi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import es.ubu.lsi.common.ElementType;
import es.ubu.lsi.common.GameResult;

/**
 * Remote client.
 * 
 * @author Raúl Marticorena
 * @author Joaquin P. Seco
 * @author Mario Erro
 *
 */
public interface GameClient extends Remote {
	
	
	/**
	 * Gets current id.
	 * 
	 * @return id
	 * @see #setId
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract int getId() throws RemoteException;;
	
	/**
	 * Sets current id.
	 * 
	 * @param id id
	 * @see #getId
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract void setId(int id) throws RemoteException;;
	
	/**
	 * Receives a new message.
	 * 
	 * @param msg message
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract void receive(GameResult result) throws RemoteException;

	
	/**
	 * Gets the current nickname.
	 * 
	 * @return nickname
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract String getNickName() throws RemoteException;

	/**
	 * Gets the current idRoom.
	 * 
	 * @return idRoom
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract int getIdRoom() throws RemoteException;

	/**
	 * Sets current idRoom.
	 * 
	 * @param idRoom idRoom
	 * @see #getIdRoom
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract void setIdRoom(int idRoom) throws RemoteException;

	/**
	 * Sets last element.
	 * 
	 * @param lastElement lastElement
	 * @see #getLastElement
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract void setLastElement(ElementType lastElement) throws RemoteException;

	/**
	 * Gets the current last element.
	 * 
	 * @return lastElement
	 * @see #setLastElement
	 * @throws RemoteException if remote communication has problems
	 */
	public abstract ElementType getLastElement() throws RemoteException;
}