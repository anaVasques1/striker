package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.hudSprites.DirPointer;

/**
 * Created by User on 07/07/2016.
 */
public class DirHud implements Disposable {
    private boolean disposed;

    private Stage stage;
    private Viewport viewport;

    private Texture dirSlider;
    private DirPointer dirPointer;

    public DirHud(SpriteBatch batch, PlayScreen screen) {

        viewport = new FitViewport(Striker.GAME_WIDTH, Striker.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        dirSlider = new Texture("dirSlider.png");
        dirPointer = new DirPointer(screen);
    }

    public void render(float dt) {
        stage.getBatch().draw(
                dirSlider,
                ((Striker.GAME_WIDTH / 2) - 112f) / Striker.PPM,
                200f / Striker.PPM,
                224f / Striker.PPM,
                64f / Striker.PPM);
        dirPointer.draw(stage.getBatch());
    }

    public void update(float dt) {
        dirPointer.variableDirection();
        dirPointer.update(dt);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        dirSlider.dispose();
        dirPointer.dispose();
        stage.dispose();
        disposed = true;
    }

    public DirPointer getDirPointer() {
        return dirPointer;
    }

    public boolean isDisposed() {
        return disposed;
    }
}
