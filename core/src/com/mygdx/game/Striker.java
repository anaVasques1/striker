package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Client.Reader;
import com.mygdx.game.Client.Writer;
import com.mygdx.game.screens.PlayScreen;
import com.mygdx.game.screens.WatchingScreen;

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
	private String nextScreen;
	private boolean gameOver;
	private String ip;
	private String winner;


	public Striker(String ip){
		this.ip = ip;

	}
	public boolean isGameOver() {
		return gameOver;
	}

	public void gameOver() {
		this.gameOver = true;
	}

	private int score;
	private float dir;
	private float str;

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
        manager.load("bowl.wav", Sound.class);
        manager.load("strike.ogg", Sound.class);
		manager.load("chillsong.ogg", Music.class);
        manager.finishLoading();
		nextScreen = "";
		setScreen(new WatchingScreen(this));
		reader = new Reader(this, ip);
		Thread t = new Thread(reader);
		t.start();
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
		nextScreen = "PlayScreen";
		setScreen(new PlayScreen(this));
		System.out.println("so this happened");
	}

	public void createWatchingScreen(){
		nextScreen = "WatchingScreen";
		setScreen(new WatchingScreen(this));
		System.out.println("so this happened");
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

	public void setNextScreen(String nextScreen) {
		this.nextScreen = nextScreen;
	}

	public String getNextScreen() {
		return nextScreen;
	}

	public void move(int score, float dir, float str){
		this.dir = dir;
		this.str = str;
		this.score = score;
	}

	public void whoWon(String winner) {
		this.winner = winner;
	}

	public int getScore() {
		return score;
	}

	public float getDir() {
		return dir;
	}

	public float getStr() {
		return str;
	}

	public String getIp() {
		return ip;
	}

	public String getWinner() {
		return winner;
	}
}
