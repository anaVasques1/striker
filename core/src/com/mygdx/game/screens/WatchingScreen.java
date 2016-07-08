package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.Striker;
import com.mygdx.game.sprites.Pin;

/**
 * Created by User on 08/07/2016.
 */
public class WatchingScreen extends PlayScreen implements Screen {

    public WatchingScreen(Striker game) {
        super(game);
    }

    public void handleInput(float dt) {
        if (getGame().getNextScreen().equals("ShowPlay")) {
            getGame().setNextScreen("CreateScreen");
            getBall().getB2Body().applyForce(new Vector2(getGame().getDir() * 5, getGame().getStr() * 5), getBall().getB2Body().getWorldCenter(), true);
            getGame().getManager().get("bowl.wav", Sound.class).play();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    if(!getGame().isGameOver()) {
                        getGame().createScreen();
                    }
                }
            }, 5);
        }
        if (getGame().getNextScreen().equals("PlayScreen"))
            getGame().createScreen();
    }

    public void update(float dt) {
        handleInput(dt);
        getWorld().step(1 / 60f, 6, 2);
        getBall().update(dt);
        for(int i = 0; i < 10; i++){
            getPins()[i].update(dt);
        }

        //update our gameCam with correct coordinates after changes
        getGameCam().update();
        //tell our renderer to draw only what our camera can see in our game world.
        getRenderer().setView(getGameCam());

    }
}
