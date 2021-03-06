package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Striker;
import com.mygdx.game.desktop.server.Server;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Striker.GAME_WIDTH;
		config.height = Striker.GAME_HEIGHT;
		config.title = "Striker!";
		new LwjglApplication(new Striker(arg[0]), config);

	}
}
