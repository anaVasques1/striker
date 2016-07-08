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
import com.mygdx.game.sprites.hudSprites.StrPointer;

/**
 * Created by User on 08/07/2016.
 */
public class StrHud implements Disposable {
    private boolean disposed;

    private Stage stage;
    private Viewport viewport;

    private Texture strSlider;
    private StrPointer strPointer;

    public StrHud(SpriteBatch batch, PlayScreen screen) {

        viewport = new FitViewport(Striker.GAME_WIDTH, Striker.GAME_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, batch);

        strSlider = new Texture("strSlider.png");
        strPointer = new StrPointer(screen);
    }

    public void render(float dt) {
        stage.getBatch().draw(
                strSlider,
                ((Striker.GAME_WIDTH / 2) - 32f) / Striker.PPM,
                200f / Striker.PPM,
                64f / Striker.PPM,
                224f / Striker.PPM);
        strPointer.draw(stage.getBatch());
    }

    public void update(float dt) {
        strPointer.variableStrength();
        strPointer.update(dt);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void dispose() {
        strSlider.dispose();
        strPointer.dispose();
        stage.dispose();
        disposed = true;
    }

    public StrPointer getStrPointer() {
        return strPointer;
    }

    public boolean isDisposed() {
        return disposed;
    }
}
