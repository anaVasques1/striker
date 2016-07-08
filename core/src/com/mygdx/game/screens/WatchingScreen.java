package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.Striker;

/**
 * Created by User on 08/07/2016.
 */
public class WatchingScreen extends PlayScreen implements Screen {

    public WatchingScreen(Striker game) {
        super(game);
    }

    public void handleInput(float dt) {}

    public void update(float dt) {
        handleInput(dt);
        super.getWorld().step(1 / 60f, 6, 2);
        super.getBall().update(dt);
        for(int i = 0; i < 10; i++){
            super.getPins()[i].update(dt);
        }

        //update our gameCam with correct coordinates after changes
        super.getGameCam().update();
        //tell our renderer to draw only what our camera can see in our game world.
        super.getRenderer().setView(getGameCam());
        if (!super.getGame().getNextScreen().equals("WatchingScreen")) {
            super.getGame().createScreen();
        }

    }
}
