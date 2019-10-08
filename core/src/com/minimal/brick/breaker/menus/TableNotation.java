package com.minimal.brick.breaker.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minimal.brick.breaker.Couleurs;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Variables;

public class TableNotation{
	
	final MyGdxGame game;
	private TextButtonStyle textButtonStyle, titreStyle;
	private TextButton ouiBouton, plusTardBouton, jamaisBouton, Titre;
	private Image image;
	private Table table;
	
	
	public TableNotation(final MyGdxGame gam, Skin skin){
		game = gam;
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("BoutonNotation");
		textButtonStyle.down = skin.getDrawable("BoutonNotationChecked");
		textButtonStyle.font = game.assets.get("fontBoutonNotation.ttf", BitmapFont.class);
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.downFontColor = new Couleurs(1).getCouleur2();
		
		titreStyle = new TextButtonStyle();
		titreStyle.up = skin.getDrawable("BoutonNotation");
		titreStyle.font = game.assets.get("fontTextNotation.ttf", BitmapFont.class);
		titreStyle.fontColor = Color.WHITE;
		
		Titre = new TextButton(game.langue.textNotation, titreStyle);
		
		ouiBouton = new TextButton(game.langue.oui, textButtonStyle);
		plusTardBouton = new TextButton(game.langue.plusTard, textButtonStyle);
		jamaisBouton = new TextButton(game.langue.jamais, textButtonStyle);

		
		table = new Table();
		table.add(Titre).colspan(3).height(Gdx.graphics.getHeight()/6).fill();
		table.row().height(Gdx.graphics.getHeight()/15).width(Gdx.graphics.getWidth()/4.5f);
		table.add(ouiBouton);
		table.add(plusTardBouton);
		table.add(jamaisBouton);

		table.setX(Gdx.graphics.getWidth()/2);
		table.setY(Gdx.graphics.getHeight()/2);
		
		image = new Image(skin.getDrawable("Fond"));
		image.setWidth(Gdx.graphics.getWidth());
		image.setHeight(Gdx.graphics.getHeight());
		image.setX(0);
		image.setY(0);
		image.addAction(Actions.alpha(0.9f));
	}
	
	public void draw(Stage stage){
		stage.addActor(image);
		stage.addActor(table);
	}
	
	public void action(){
		ouiBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		       	image.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
		       										Actions.moveTo(-Gdx.graphics.getWidth(),
		       														image.getY(), 
		       														0.25f)));

		       	table.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
		       										Actions.moveTo(-Gdx.graphics.getWidth(),
		       														table.getY(), 
		       														0.25f)));
				Donnees.setRate(true);
		       	Gdx.net.openURI(Variables.GOOGLE_PLAY_GAME_URL);
		       	//Gdx.net.openURI(Variables.AMAZON_GAME_URL);
			}
		});

		plusTardBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		       	image.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
							Actions.moveTo(-Gdx.graphics.getWidth(),
											image.getY(), 
											0.25f)));

				table.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
											Actions.moveTo(-Gdx.graphics.getWidth(),
															table.getY(), 
															0.25f)));	
				Donnees.setRateCount(3);
			}
		});

		jamaisBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
		       	image.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
							Actions.moveTo(-Gdx.graphics.getWidth(),
											image.getY(), 
											0.25f)));
				
				table.addAction(Actions.sequence(Actions.alpha(0, 0.1f),	 
											Actions.moveTo(-Gdx.graphics.getWidth(),
															table.getY(), 
															0.25f)));	
				Donnees.setRate(true);
			}
		});
	}	
}
