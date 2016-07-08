package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by codecadet on 08/07/16.
 */
public class Pin extends Sprite{

    public enum State { STOPPED, CHARGING, DIRECTING, LAUNCHED };
    private Pin.State currentState;

    private static final int PIN_RADIUS = 16;
    private static int pin_starting_height;
    private static int pin_starting_width;
    private World world;
    private Body b2Body;
    private Fixture fixture;

    private boolean pinHit;


    public Pin(PlayScreen screen,int width, int height ) {

        super(new Texture("Pin.png"));
        pin_starting_height = height;
        pin_starting_width = width;

        //initialize default values
        this.world = screen.getWorld();

        setSize(PIN_RADIUS*2 / Striker.PPM, PIN_RADIUS * 2 / Striker.PPM);
        currentState = Pin.State.STOPPED;
        definePin();
    }


    public void update(float dt) {
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }


    private void definePin() {

        BodyDef bdef = new BodyDef();
        bdef.position.set(pin_starting_width / Striker.PPM, pin_starting_height / Striker.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(PIN_RADIUS / Striker.PPM);
        //TODO Verificar essa categoryBits
        fdef.filter.categoryBits = Striker.PIN_BIT;
        fdef.filter.maskBits = Striker.EDGE_BIT | Striker.BALL_BIT;

        fdef.shape = shape;
        //TODO verificar essas variaveis
        fdef.restitution = 0f;
        fdef.friction = 15.5f;
        fdef.density = 7f;
        fixture = b2Body.createFixture(fdef);
        fixture.setUserData(this);
    }

    public boolean isPinHit() {
        return pinHit;
    }

    public void setPinHit(boolean pinHit) {
        this.pinHit = pinHit;
    }

    public Pin.State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Pin.State currentState) {
        this.currentState = currentState;
    }

    public Body getB2Body() {
        return b2Body;
    }
}
