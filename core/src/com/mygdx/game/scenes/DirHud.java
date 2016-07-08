package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.hudSprites.DirSlider;

/**
 * Created by User on 07/07/2016.
 */
public class DirHud implements Disposable{
    private static final float MAX_STRENGHT = 50;
    private static final float MIN_STRENGHT = 1;
    private static final float MAX_DIRECTION = 50;
    private static final float MIN_DIRECTION = -50;

    private boolean risingStr;
    private boolean risingDir;
    private float strength;
    private float direction;

    private Stage stage;
    private Viewport viewport;

    private DirSlider dirSlider;

    public DirHud(SpriteBatch batch, PlayScreen screen) {
        risingStr = true;
        strength = 0;
        direction = 0;

        viewport = new FitViewport(Striker.GAME_WIDTH, Striker.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        dirSlider = new DirSlider(screen);
    }

    public float variableStrength() {

        if (risingStr) {
            strength++;
            if (strength == MAX_STRENGHT) risingStr = false;

        } else {
            strength--;
            if (strength == MIN_STRENGHT) risingStr = true;
        }

        return strength;
    }

    public float variableDirection() {

        if(risingDir){
            direction++;
            if(direction == MAX_DIRECTION) risingDir = false;

        } else {
            direction--;
            if(direction == MIN_DIRECTION) risingDir = true;
        }

        return direction;
    }

    public void update(float dt) {
        dirSlider.update(dt);
    }

    public void render(float dt) {
        dirSlider.draw(stage.getBatch());
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
