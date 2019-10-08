package com.minimal.brick.breaker.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.minimal.brick.breaker.Donnees;

public class NewGameAd {

	public Image backgroundImage, gameImage;
	public LabelStyle labelStyle;
	public Label newGameLabel;
	public TextButtonStyle textButtonStyle;
	public TextButton playButton;
	public Button closeButton;
	private Skin skin;
	private String playStoreLink;
	
	public NewGameAd(Skin skin, String playStoreLink){
		this.skin = skin;
		this.playStoreLink = playStoreLink;
	}
	
	public void create(Drawable backgroundDrawable, String gameImagePath, float width, float height, String newGame, String play){
		backgroundImage = new Image(backgroundDrawable);
		backgroundImage.setWidth(width);
		backgroundImage.setHeight(height);
		backgroundImage.setX(Gdx.graphics.getWidth()/2 - backgroundImage.getWidth()/2);
		backgroundImage.setY(Gdx.graphics.getHeight()/2 - backgroundImage.getHeight()/2);

		newGameLabel = new Label(newGame, labelStyle);
		newGameLabel.setWidth(0.95f * width);
		newGameLabel.setWrap(true);
		newGameLabel.setAlignment(0);
		newGameLabel.setX(backgroundImage.getX() + 0.5f*backgroundImage.getWidth() - 0.5f * newGameLabel.getWidth());
		newGameLabel.setY(backgroundImage.getY() + 0.9f*backgroundImage.getHeight() - 0.5f * newGameLabel.getPrefHeight());

		playButton = new TextButton(play, textButtonStyle);
		playButton.setWidth(1.5f * playButton.getPrefWidth());
		playButton.setX(backgroundImage.getX() + 0.5f*backgroundImage.getWidth() - 0.5f * playButton.getWidth());
		playButton.setY(backgroundImage.getY() + 0.2f*backgroundImage.getHeight() - 0.5f * playButton.getHeight());
		playButton.getLabel().scaleBy(5.5f);
		
		Texture textureLogo = new Texture(Gdx.files.internal(gameImagePath), false);
		textureLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		gameImage = new Image(textureLogo);
		gameImage.setWidth(0.92f * backgroundImage.getWidth());
		gameImage.setHeight(gameImage.getWidth() * textureLogo.getHeight() / textureLogo.getWidth());
		gameImage.setX(backgroundImage.getX() + 0.04f*backgroundImage.getWidth());
		gameImage.setY(newGameLabel.getY() - 0.5f*(newGameLabel.getY() - 0.5f*newGameLabel.getHeight() - (playButton.getY() + 0.5f*playButton.getHeight())) - 0.5f * gameImage.getHeight());

		closeButton = new Button(skin.getDrawable("Close"));
		closeButton.setWidth(0.1f * backgroundImage.getWidth());
		closeButton.setHeight(closeButton.getWidth());
		closeButton.setX(backgroundImage.getX() + backgroundImage.getWidth() - closeButton.getWidth()/2);
		closeButton.setY(backgroundImage.getY() - closeButton.getWidth()/2);
		
	}
	
	public void setLabelStyle(LabelStyle style){
		labelStyle = style;
	}
	
	public void setTextButtonStyle(TextButtonStyle style){
		textButtonStyle = style;
	}
	
	public void setBackgroundColor(float r, float g, float b, float a){
		backgroundImage.setColor(r,g,b,a);
	}
	
	public void setBackgroundColor(Color color){
		backgroundImage.setColor(color);
	}
	
	public void addToStage(Stage stage){
		stage.addActor(backgroundImage);
		//stage.addActor(table);
		stage.addActor(newGameLabel);
		stage.addActor(gameImage);
		stage.addActor(playButton);
		stage.addActor(closeButton);

		//stage.addActor(gameButton);
	}
	
	public void action(){
		closeButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				backgroundImage.addAction(Actions.alpha(0, 0.5f));
		       	newGameLabel.addAction(Actions.alpha(0, 0.5f));
		       	gameImage.addAction(Actions.alpha(0, 0.5f));
		       	playButton.addAction(Actions.alpha(0, 0.5f)); 	
		       	closeButton.addAction(Actions.alpha(0, 0.5f));

		       	backgroundImage.setTouchable(Touchable.disabled);
		       	newGameLabel.setTouchable(Touchable.disabled);
		       	gameImage.setTouchable(Touchable.disabled);
		       	playButton.setTouchable(Touchable.disabled);
		       	closeButton.setTouchable(Touchable.disabled);
		       	
		       	Donnees.setPromoteCosmonaut(true);
			}
		});
			
		playButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(playStoreLink);
				
				backgroundImage.addAction(Actions.alpha(0, 0.5f));
		       	newGameLabel.addAction(Actions.alpha(0, 0.5f));
		       	gameImage.addAction(Actions.alpha(0, 0.5f));
		       	playButton.addAction(Actions.alpha(0, 0.5f)); 	
		       	closeButton.addAction(Actions.alpha(0, 0.5f));

		       	backgroundImage.setTouchable(Touchable.disabled);
		       	newGameLabel.setTouchable(Touchable.disabled);
		       	gameImage.setTouchable(Touchable.disabled);
		       	playButton.setTouchable(Touchable.disabled);
		       	closeButton.setTouchable(Touchable.disabled);

		       	Donnees.setPromoteCosmonaut(true);
			}
		});
		
		gameImage.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(playStoreLink);
				
				backgroundImage.addAction(Actions.alpha(0, 0.5f));
		       	newGameLabel.addAction(Actions.alpha(0, 0.5f));
		       	gameImage.addAction(Actions.alpha(0, 0.5f));
		       	playButton.addAction(Actions.alpha(0, 0.5f)); 	
		       	closeButton.addAction(Actions.alpha(0, 0.5f));

		       	backgroundImage.setTouchable(Touchable.disabled);
		       	newGameLabel.setTouchable(Touchable.disabled);
		       	gameImage.setTouchable(Touchable.disabled);
		       	playButton.setTouchable(Touchable.disabled);
		       	closeButton.setTouchable(Touchable.disabled);
		       	
		       	Donnees.setPromoteCosmonaut(true);
			}
		});
	}
}
