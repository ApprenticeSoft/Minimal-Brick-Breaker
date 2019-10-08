package com.minimal.brick.breaker.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Variables;

public class BoutonsReseauxSociaux {

	final MyGdxGame game;
	private ImageButton facebookBouton, twitterBouton, logoBouton;
	
	public BoutonsReseauxSociaux(final MyGdxGame gam, Skin skin){
		game = gam;
		
		facebookBouton = new ImageButton(skin.getDrawable("Facebook"), skin.getDrawable("FacebookDown"));
		facebookBouton.setWidth(0.071f * Gdx.graphics.getHeight());
		facebookBouton.setHeight(facebookBouton.getWidth());
		facebookBouton.setX(Gdx.graphics.getWidth()/30);
		facebookBouton.setY(facebookBouton.getX());
		
		twitterBouton = new ImageButton(skin.getDrawable("Twitter"), skin.getDrawable("TwitterDown"));
		twitterBouton.setWidth(facebookBouton.getWidth());
		twitterBouton.setHeight(facebookBouton.getWidth());
		twitterBouton.setX(facebookBouton.getX() + 1.4f * facebookBouton.getWidth());
		twitterBouton.setY(facebookBouton.getY());
		
		//logoBouton = new ImageButton(skin.getDrawable("Logo"), skin.getDrawable("LogoDown"));
		logoBouton = new ImageButton(skin.getDrawable("MoreGames" + Donnees.getLangue()), skin.getDrawable("MoreGames" + Donnees.getLangue() + "Down"));	
		logoBouton.setWidth(facebookBouton.getWidth());
		logoBouton.setHeight(facebookBouton.getWidth());
		logoBouton.setX(twitterBouton.getX() + 1.4f * twitterBouton.getWidth());
		logoBouton.setY(facebookBouton.getY());
	}
	
	public void draw(Stage stage){
		stage.addActor(facebookBouton);
		stage.addActor(twitterBouton);
		stage.addActor(logoBouton);
	}
	
	public void action(){	
		facebookBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//try{
				//	Gdx.net.openURI("fb://page/157533514581396");
				//}catch(Exception e){
					Gdx.net.openURI("https://m.facebook.com/profile.php?id=157533514581396");
				//}
			}
		});
		
		twitterBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		       	Gdx.net.openURI("https://twitter.com/ApprenticeSoft");
			}
		});
		
		logoBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		       	Gdx.net.openURI(Variables.GOOGLE_PLAY_STORE_URL);
			}
		});
	}
}
