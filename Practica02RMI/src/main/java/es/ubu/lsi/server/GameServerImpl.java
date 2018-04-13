/**
 * 
 */
package es.ubu.lsi.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import es.ubu.lsi.client.GameClient;
import es.ubu.lsi.common.ElementType;
import es.ubu.lsi.common.GameElement;
import es.ubu.lsi.common.GameResult;

/**
 * Implementacion de servidor remoto.
 * 
 * @author Miguel Angel Leon Bardavio
 * @author Antonio de los Mozos Alonso
 *
 */
public class GameServerImpl extends UnicastRemoteObject implements GameServer {

	/**
	 * Serial.
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * Lista de jugadores.
	 */
	private HashMap<Integer, GameClient> playerList = new HashMap<Integer, GameClient>();
	
	/**
	 * Lista de salas junto con los jugadores que juegan en ella.
	 */
	private HashMap<Integer, List<GameClient>> roomList = new HashMap<Integer, List<GameClient>>();
	
	/**
	 * Secuencia para crear ID de sala.
	 */
	private int seqRoom = 0;
	
	/**
	 * Secuencia para crear ID de jugador.
	 */
	private int seqClient = 0;
	
	/**
	 * Exporta servidor remoto.
	 * 
	 * @throws RemoteException if remote communication has problems
	 */
	public GameServerImpl() throws RemoteException {
		super();
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#checkIn(es.ubu.lsi.client.GameClient)
	 */
	public int checkIn(GameClient client) throws RemoteException {
		//Comprobar nickname
		for (GameClient player : this.playerList.values()) {
			if (player.getNickName().equals(client.getNickName())) {
				System.err.println("Fallo en check in: ya existe jugador con  con nickname '" + client.getNickName() + "'");
				return -1;
			}
		}
		
		//Añadir cliente
		System.out.println("Log in de cliente con nickname '" + client.getNickName() + "'...");
		this.playerList.put(++seqClient, client);
		client.setId(seqClient);
		
		//Asignar sala
		if (roomList.get(seqRoom) != null && roomList.get(seqRoom).size() < 2) {//Si hay sala libre
			roomList.get(seqRoom).add(client); //Se añade a esa
		}else{//Si no hay sala libre
			List<GameClient> roomPlayers = new ArrayList<GameClient>();
			roomPlayers.add(client);
			roomList.put(++seqRoom, roomPlayers);//Se añade a una nueva sala
			
		}
		client.setIdRoom(seqRoom);
		
		System.out.println("Log in correcto: '" + client.getNickName() + "' juega en la sala " + seqRoom);
		return seqClient;
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#logout(es.ubu.lsi.client.GameClient)
	 */
	public void logout(GameClient client) throws RemoteException {
		System.out.println("Log out del cliente con id [" + client.getId() + "] y nickname '" + client.getNickName() + "'");
		
		//Eliminar cliente
		this.playerList.remove(client.getId());
		//Quitar la sala
		this.roomList.remove(client.getIdRoom());//No se reutiliza
		
		System.out.println("Log out correcto");
	}

	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#shutdown(es.ubu.lsi.client.GameClient)
	 */
	public void shutdown(GameClient client) throws RemoteException { System.err.println("SHUTDOWN: No implementado");}

	
	/**
	 * Envia los resultados de una confrontacion.
	 * @param jugador1 Jugador1
	 * @param jugador2 Jugador2
	 * @param result1 Resultado de jugador1
	 * @param result2 Resultado de jugador2
	 */
	private void sendResult(GameClient jugador1, GameClient jugador2, GameResult result1, GameResult result2) {
		try {
			jugador1.receive(result1);
			jugador2.receive(result2);
		} catch (RemoteException e) {
			System.err.println("Error al enviar resultados: " + e.getMessage());
		}
	}
	
	
	/**
	 * Resuelve una confrontacion.
	 * 
	 * @param jugador1 Jugador1
	 * @param jugador2 Jugador2
	 * @param jugada1 Jugada de jugador1
	 * @param jugada2 Jugada de jugador2
	 */
	private void getResult(GameClient jugador1, GameClient jugador2, ElementType jugada1, ElementType jugada2) {
		if(jugada1 == ElementType.PAPEL){
			if(jugada2 == ElementType.PIEDRA){
				sendResult(jugador1,jugador2,GameResult.WIN, GameResult.LOSE);
			}else if(jugada2 == ElementType.TIJERA){
				sendResult(jugador1,jugador2,GameResult.LOSE, GameResult.WIN);
			}
		}
		else if(jugada1 == ElementType.PIEDRA){
			if(jugada2 == ElementType.PAPEL){
				sendResult(jugador1,jugador2,GameResult.LOSE, GameResult.WIN);
			}else if(jugada2 == ElementType.TIJERA){
				sendResult(jugador1,jugador2,GameResult.WIN, GameResult.LOSE);
			}
		}
		else if(jugada1 == ElementType.TIJERA){
			if(jugada2 == ElementType.PIEDRA){
				sendResult(jugador1,jugador2,GameResult.LOSE, GameResult.WIN);
			}else if(jugada2 == ElementType.PAPEL){
				sendResult(jugador1,jugador2,GameResult.WIN, GameResult.LOSE);
			}
		}
		if(jugada1 == jugada2){
			sendResult(jugador1,jugador2,GameResult.DRAW, GameResult.DRAW);
		}
			
	}
	
	/* (non-Javadoc)
	 * @see es.ubu.lsi.server.GameServer#broadcastRoom(es.ubu.lsi.common.GameElement)
	 */
	public void broadcastRoom(GameElement element) throws RemoteException {
		System.out.println("Recibiendo jugada de jugador con id [" + element.getId() + "]");
		
		//Obtener jugador1
		GameClient jugador1 = this.playerList.get(element.getId());
		
		GameClient jugador2 = null;
		List<GameClient> room = null;
		
		//Buscar oponente de jugador1: jugador2
		if (jugador1 != null) {
			room = this.roomList.get(jugador1.getIdRoom());
			if (room != null){
				for (GameClient player : room) {
					if (player.getId() != jugador1.getId()) {//Si existe
						jugador2 = player;
						break;
					}
				}
				//¿Ha respondido?
				if (jugador2 != null && jugador2.getLastElement() != null){//Si: Obtener resultado de la partida
					getResult(jugador1, jugador2, element.getElement(), jugador2.getLastElement());
					jugador2.setLastElement(null);
					System.out.println("Se han enviado resultados a la sala " + jugador1.getIdRoom());
				}else {
					//No: Almacenar respuesta y enviar WAITING
					jugador1.setLastElement(element.getElement());
					jugador1.receive(GameResult.WAITING);
					System.out.println("Se puso a '" + jugador1.getNickName() + "' en espera");
				}
			} else{
				jugador1.receive(GameResult.WAITING);
				System.out.println("'" + jugador1.getNickName() + "' en espera por Log Out de oponente");
			}
		}else{
			System.err.println("Jugador con ID [" + element.getId() + "] inexistente");
		}
	}
}