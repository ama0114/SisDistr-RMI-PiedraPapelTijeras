/**
 * 
 */
package es.ubu.lsi.client;

import java.rmi.RemoteException;

import es.ubu.lsi.common.ElementType;
import es.ubu.lsi.common.GameResult;

/**
 * @author Miguel Ángel León
 *
 */
public class GameClientImpl implements GameClient {

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getId()
	 */
	public int getId() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setId(int)
	 */
	public void setId(int id) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#receive(es.ubu.lsi.common.GameResult)
	 */
	public void receive(GameResult result) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getNickName()
	 */
	public String getNickName() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getIdRoom()
	 */
	public int getIdRoom() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setIdRoom(int)
	 */
	public void setIdRoom(int idRoom) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#setLastElement(es.ubu.lsi.common.ElementType)
	 */
	public void setLastElement(ElementType lastElement) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.client.GameClient#getLastElement()
	 */
	public ElementType getLastElement() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
