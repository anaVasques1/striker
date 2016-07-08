package com.mygdx.game.sprites.hudSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by User on 08/07/2016.
 */
public class DirSlider extends Sprite {
    private static final int SLIDER_X = 224;
    private static final int SLIDER_Y = 64;

    private World world;
    private Body b2Body;
    private Fixture fixture;

    public DirSlider(PlayScreen screen) {

        super(new Texture("DirSlider.png"));

        //initialize default values
        this.world = screen.getWorld();

        setSize(SLIDER_X * 2 / Striker.PPM, SLIDER_Y * 2 / Striker.PPM);

    }

    public void update(float dt) {
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }
}
