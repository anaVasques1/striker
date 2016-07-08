package com.mygdx.game.sprites.hudSprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by User on 08/07/2016.
 */
public class DirPointer extends Sprite implements Disposable {

    private static final float MAX_DIRECTION = 96;
    private static final float MIN_DIRECTION = -96;
    private boolean risingDir;
    private float xOffset;

    public DirPointer(PlayScreen screen) {

        super(new Texture("dirPointer.png"));

        //initialize default values

        xOffset = 0;
        setSize(32f / Striker.PPM, 96f / Striker.PPM);
        setPosition(((Striker.GAME_WIDTH / 2) - 16f) / Striker.PPM, 200f / Striker.PPM);
    }

    public void update(float dt) {
        this.setX(((Striker.GAME_WIDTH / 2) - 16f + xOffset) / Striker.PPM);
    }

    public void variableDirection() {

        if(risingDir){
            xOffset += 12;
            if(xOffset == MAX_DIRECTION) risingDir = false;

        } else {
            xOffset -= 12;
            if(xOffset == MIN_DIRECTION) risingDir = true;
        }
    }

    public float getxOffset() {
        return xOffset;
    }

    @Override
    public void dispose() {
        super.getTexture().dispose();
    }
}
