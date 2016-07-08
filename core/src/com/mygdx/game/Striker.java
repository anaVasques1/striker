package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.PlayScreen;

public class Striker extends Game {

	public static final int GAME_WIDTH = 480;
    public static final int GAME_HEIGHT = 800;
	public static final int TILE_SIZE = 32;
    public static final float PPM = 100;

    public static final short EDGE_BIT = 1;
    public static final short BALL_BIT = 2;
	public static final short PIN_BIT = 3;

    private SpriteBatch batch;
	private AssetManager manager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
        manager.finishLoading();

        setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
	    super.render();
	}
	
	@Override
	public void dispose () {
        super.dispose();
        manager.dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public void setBatch(SpriteBatch batch) {
		this.batch = batch;
	}

	public AssetManager getManager() {
		return manager;
	}

	public void setManager(AssetManager manager) {
		this.manager = manager;
	}
}
