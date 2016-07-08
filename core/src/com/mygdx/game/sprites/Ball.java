package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by User on 07/07/2016.
 */
public class Ball extends Sprite {
    public enum State { STOPPED, CHARGING, DIRECTING, LAUNCHED };
    private State currentState;

    private static final int BALL_RADIUS = 30;
    private static final int BALL_STARTING_HEIGHT = 100;
    private World world;
    private Body b2Body;
    private Fixture fixture;


    public Ball(PlayScreen screen) {

        super(new Texture("ball.png"));

        //initialize default values
        this.world = screen.getWorld();

        setSize(BALL_RADIUS*2 / Striker.PPM, BALL_RADIUS * 2 / Striker.PPM);
        currentState = State.STOPPED;
        defineBall();
    }


    public void update(float dt) {
        setPosition(b2Body.getPosition().x - getWidth() / 2, b2Body.getPosition().y - getHeight() / 2);
    }


    private void defineBall() {

        BodyDef bdef = new BodyDef();
        bdef.position.set((Striker.GAME_WIDTH / 2) / Striker.PPM, BALL_STARTING_HEIGHT / Striker.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2Body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(BALL_RADIUS / Striker.PPM);
        fdef.filter.categoryBits = Striker.BALL_BIT;
        fdef.filter.maskBits = Striker.EDGE_BIT;

        fdef.shape = shape;
        fdef.restitution = 1f;
        fdef.friction = 0f;
        //fdef.density = 1000f;
        fixture = b2Body.createFixture(fdef);
        fixture.setUserData(this);
    }


    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public Body getB2Body() {
        return b2Body;
    }
}
