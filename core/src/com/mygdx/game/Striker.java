package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Client.Reader;
import com.mygdx.game.Client.Writer;
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
	private Reader reader;
	private Writer writer;

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
        manager.finishLoading();
		reader = new Reader(this);
		reader.init();

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

	public void createScreen() {
		setScreen(new PlayScreen(this));
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

	public void sendResult(String result) {
		writer.sendMessage(result);
	}
}
