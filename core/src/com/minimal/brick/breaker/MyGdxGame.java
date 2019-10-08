package com.minimal.brick.breaker;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.minimal.brick.breaker.screen.LoadingScreen;
import com.minimal.brick.breaker.Donnees;

public class MyGdxGame extends Game implements ApplicationListener {
	public SpriteBatch batch;
	public AssetManager assets;
	public Langue langue;
	public Music music;
	
	@Override
	public void create () {
		
		Donnees.Load();
		if(Donnees.getGroupe() < 1)
			Donnees.setGroupe(1);
		if(Donnees.getNiveau() < 1)
			Donnees.setNiveau(1);
		
		if(Donnees.getGroupe() > 3 && !Donnees.getMicrogravite())
			Donnees.setMicrogravite(true);

		//Donnees.setGroupe(5);
		//Donnees.setNiveau(25);
		//Donnees.setGroupe(2);
		//Donnees.setNiveau(17);
		//Donnees.setEpileptique(false);
		//Donnees.setMicrogravite(false);
		//Donnees.setRate(false);
		
		if(!Donnees.getRate()){
			Donnees.setRateCount(Donnees.getRateCount() - 1);
		}
		
		batch = new SpriteBatch();
		assets = new AssetManager();
		langue = new Langue();
		langue.setLangue(Donnees.getLangue());
		
		music = Gdx.audio.newMusic(Gdx.files.internal("Sons/Minimal Brick Breaker - Menu.ogg"));
		music.setLooping(true);
		
		this.setScreen(new LoadingScreen(this));
	}

	@Override
	public void render () {
		super.render();
	
		if(!Donnees.getSon())
			music.setVolume(0);
		else
			music.setVolume(1);
		//System.out.println("Gdx.app.getJavaHeap() = " + Math.round((float)Gdx.app.getJavaHeap()/10000)/100);
		//System.out.println("Gdx.app.getNativeHeap() = " + Math.round((float)Gdx.app.getNativeHeap()/10000)/100);
	}
	 
	public void dispose() {
		batch.dispose();
	}	 
}
