package com.minimal.brick.breaker;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Array;

public class Sons {

	final MyGdxGame game;
	int groupe;
	private Array<Sound> sons;
	
	public Sons(final MyGdxGame game, int groupe){
		this.game = game;
		this.groupe = groupe;
		
		sons = new Array<Sound>();

		for(int i = 1; i < 17; i++){
			Sound son = game.assets.get("Sons/Collision " + i + ".ogg", Sound.class);
			sons.add(son);
		}
	}
	
	public Array<Sound> getSons(){
		return sons;
	}
}
