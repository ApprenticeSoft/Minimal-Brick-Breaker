package com.minimal.brick.breaker;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minimal.brick.breaker.screen.MainMenuScreen;
import com.minimal.brick.breaker.screen.GameScreen;

public class ActionBouton {

	public ActionBouton(){
	}
	
	public void groupeListener(TextButton bouton, final int groupe){
		bouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Variables.groupeSelectione = groupe;
				System.out.println("Numéro du groupe : " + Variables.groupeSelectione);
				Variables.choixNiveau = true;
			}
		});
	}
	
	public void niveauListener(final MyGdxGame game, TextButton bouton, final int niveau){
		bouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Variables.niveauSelectione = niveau;
				try{
					game.music.stop();
					game.getScreen().dispose();
					game.setScreen(new GameScreen(game));
				}
				catch(Exception e){
					System.out.println("Le niveau n'existe pas !");
				}
				System.out.println("Numéro du niveau : " + Variables.niveauSelectione);
			}
		});
	}
	
	public void retourListener(final MyGdxGame game, TextButton bouton){
		bouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(Variables.choixNiveau) 
					Variables.choixNiveau = false;
				else 
					game.setScreen(new MainMenuScreen(game));
			}
		});
	}
}
