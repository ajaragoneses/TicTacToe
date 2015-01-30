package es.art83.ticTacToe.controllers.ws.client;

import java.util.ArrayList;
import java.util.List;

import es.art83.ticTacToe.controllers.StartGameController;
import es.art83.ticTacToe.controllers.ws.client.utils.TicTacToeResource;
import es.art83.ticTacToe.controllers.ws.client.utils.WebServiceClient;
import es.art83.ticTacToe.models.utils.ListStringWrapper;

public class StartControllerWSClient extends ControllerWSClient implements StartGameController {

    private String pathSessionsIdPlayer;

    public StartControllerWSClient(String sessionId) {
        super(sessionId);
        this.pathSessionsIdPlayer = TicTacToeResource.PATH_SESSIONS + "/" + this.getSessionId()
                + TicTacToeResource.PATH_PLAYER;
    }

    @Override
    public List<String> readGameNames() {
        ListStringWrapper listStringWrapper = new WebServiceClient<ListStringWrapper>(
                pathSessionsIdPlayer, TicTacToeResource.PATH_GAME_NAMES)
                .entity(ListStringWrapper.class);
        List<String> list = listStringWrapper.getListString();
        if (list == null) {
            list = new ArrayList<String>();
        }
        return list;
    }

}
