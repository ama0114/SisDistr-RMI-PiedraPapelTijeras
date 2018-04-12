/**
 * 
 */
package es.ubu.lsi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.ubu.lsi.client.GameClient;
import es.ubu.lsi.common.GameElement;

/**
 * @author Miguel Angel Leon
 *
 */
public class GameServerImpl extends UnicastRemoteObject implements GameServer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameServerImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#checkIn(es.ubu.lsi.client.GameClient)
	 */
	public int checkIn(GameClient client) throws RemoteException {
		// TODO Auto-generated method stub
		System.out.println("CheckIn del cliente con nickname:" + client.getNickName());
		return 0;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#logout(es.ubu.lsi.client.GameClient)
	 */
	public void logout(GameClient client) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#shutdown(es.ubu.lsi.client.GameClient)
	 */
	public void shutdown(GameClient client) throws RemoteException { System.err.println("shutdown");}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#broadcastRoom(es.ubu.lsi.common.GameElement)
	 */
	public void broadcastRoom(GameElement element) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
