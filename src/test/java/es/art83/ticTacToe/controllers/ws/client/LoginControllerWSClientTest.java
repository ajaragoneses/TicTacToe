package es.art83.ticTacToe.controllers.ws.client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import es.art83.ticTacToe.controllers.ws.client.ControllerFactoryWSClient;
import es.art83.ticTacToe.controllers.ws.client.LoginControllerWSClient;
import es.art83.ticTacToe.controllers.ws.client.utils.TicTacToeResource;
import es.art83.ticTacToe.controllers.ws.client.utils.WebServiceClient;
import es.art83.ticTacToe.models.entities.PlayerEntity;

public class LoginControllerWSClientTest {

    private LoginControllerWSClient login;

    private PlayerEntity playerEntity;

    @Before
    public void before() {
        this.login = (LoginControllerWSClient) new ControllerFactoryWSClient().getLoginController();
        this.playerEntity = new PlayerEntity("u", "pass");
    }

    @Test
    public void testRegisterNotExits() {
        assertTrue(this.login.register(playerEntity));
    }

    @Test
    public void testRegisterExits() {
        this.login.register(playerEntity);
        assertFalse(this.login.register(this.playerEntity));
    }

    @Test
    public void testNotLogged() {
        assertFalse(this.login.logged());
    }

    @Test
    public void testLogged() {
        this.login.register(playerEntity);
        assertTrue(this.login.logged());
    }

    @After
    public void after() {
        new WebServiceClient<>(TicTacToeResource.PATH_SESSIONS, this.login.getSessionId()).delete();
        new WebServiceClient<>(TicTacToeResource.PATH_PLAYERS, this.playerEntity.getUser()).delete();
    }
}