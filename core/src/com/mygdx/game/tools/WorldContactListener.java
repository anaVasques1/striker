package com.mygdx.game.tools;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.sprites.Pin;

/**
 * Created by User on 07/07/2016.
 */
public class WorldContactListener implements ContactListener {
    private PlayScreen screen;
    private boolean hit;

    public WorldContactListener(PlayScreen screen) {
        this.screen = screen;
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        int pinCheck = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;

        switch (pinCheck) {
            case Striker.PIN_BIT:
                if (fixA.getFilterData().categoryBits == Striker.PIN_BIT) {
                    ((Pin) fixB.getUserData()).setPinHit(true);
                    ((Pin) fixA.getUserData()).setPinHit(true);
                }
                if (fixA.getFilterData().categoryBits == Striker.BALL_BIT) {
                    ((Pin) fixB.getUserData()).setPinHit(true);
                }
                if (!hit) screen.getGame().getManager().get("strike.ogg", Sound.class).play();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
