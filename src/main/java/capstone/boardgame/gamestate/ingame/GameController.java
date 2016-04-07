package capstone.boardgame.gamestate.ingame;

import capstone.boardgame.GUI.Component.BGComponent;
import capstone.boardgame.GUI.Component.Label;
import capstone.boardgame.GUI.GameGUIContainer;
import capstone.boardgame.HTTP.WebSocket.SocketEndpoint;
import capstone.boardgame.HTTP.WebSocket.SocketListener;
import capstone.boardgame.gamestate.GameState;
import capstone.boardgame.gamestate.GameStateManager;
import capstone.boardgame.main.Log;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

/**
 * Created by Kyle on 3/1/2016.
 */
public class GameController extends GameState implements SocketListener {
    private static final String tag = "GameController";
    GameGUIContainer gui = new GameGUIContainer();
    GamePacketHandler handler = new GamePacketHandler();

    public GameController(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        BGComponent.setDefaultColor(Color.cyan);

        gui.addAll(LevelLoader.loadLevel(""));

        SocketEndpoint.setPacketHandler(handler);

        SocketEndpoint.setListener(this);
    }

    int players = 0;
    @Override
    public void onOpen(Session session) throws IOException {
        Log.d(tag, "TODO: Add player for session");
        ((Label)gui.getViewByID("NumPlayers")).setText("" + ++players);
    }

    @Override
    public void onClose(Session session, CloseReason reason) {
        Log.d(tag, "TODO: disable player from session");
        ((Label)gui.getViewByID("NumPlayers")).setText("" + --players);
    }

    @Override
    public void onMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void tick(double deltaTime) {

    }

    @Override
    public void render(Graphics2D g) {
        gui.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gui.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        gui.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gui.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        gui.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        gui.mouseExited(e);
    }
}
