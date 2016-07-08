package com.mygdx.game.desktop;

import com.mygdx.game.desktop.server.Server;

/**
 * Created by codecadet on 08/07/16.
 */
public class ServerLauncher {

    static void main(String[] args) {
        Server server = new Server();
        server.init();

    }
}
