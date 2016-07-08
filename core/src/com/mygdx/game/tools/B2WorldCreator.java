package com.mygdx.game.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Striker;
import com.mygdx.game.screens.PlayScreen;
import jdk.nashorn.internal.ir.Block;

public class B2WorldCreator {

    //private static Array<Block> blocks;

    public B2WorldCreator(PlayScreen screen) {

        World world = screen.getWorld();
        TiledMap map = screen.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for (MapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Striker.PPM, (rect.getY() + rect.getHeight() / 2) / Striker.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2 / Striker.PPM, rect.getHeight() / 2 / Striker.PPM);
            fdef.shape = shape;
            fdef.filter.categoryBits = Striker.EDGE_BIT;
            body.createFixture(fdef);
        }

    }
}
