/**
 * 
 */
package es.ubu.lsi.server;

import java.rmi.RemoteException;

import es.ubu.lsi.client.GameClient;
import es.ubu.lsi.common.GameElement;

/**
 * @author Miguel Ángel León
 *
 */
public class GameServerImpl implements GameServer {

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#checkIn(es.ubu.lsi.client.GameClient)
	 */
	public int checkIn(GameClient client) throws RemoteException {
		// TODO Auto-generated method stub
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
	public void shutdown(GameClient client) throws RemoteException { }

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#broadcastRoom(es.ubu.lsi.common.GameElement)
	 */
	public void broadcastRoom(GameElement element) throws RemoteException {
		// TODO Auto-generated method stub

	}

}
