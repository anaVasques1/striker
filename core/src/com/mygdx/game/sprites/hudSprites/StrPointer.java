package com.mygdx.game.sprites.hudSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by User on 08/07/2016.
 */
public class StrPointer extends Sprite implements Disposable {

    private static final float MAX_STRENGHT = 192;
    private static final float MIN_STRENGHT = 0;
    private boolean risingStr;
    private float yOffset;

    public StrPointer(PlayScreen screen) {

        super(new Texture("strPointer.png"));

        //initialize default values

        yOffset = 12;
        setSize(96f / Striker.PPM, 32f / Striker.PPM);
        setPosition(((Striker.GAME_WIDTH / 2) - 48f) / Striker.PPM, 212f / Striker.PPM);
    }

    public void update(float dt) {
        this.setY((200f + yOffset) / Striker.PPM);
    }

    public void variableStrength() {

        if (risingStr) {
            yOffset += 12;
            if (yOffset == MAX_STRENGHT) risingStr = false;

        } else {
            yOffset -= 12;
            if (yOffset == MIN_STRENGHT) risingStr = true;
        }
    }

    public float getyOffset() {
        return yOffset;
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
