package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.menus.BoutonsReseauxSociaux;
import com.minimal.brick.breaker.menus.MenuPrincipale;
import com.minimal.brick.breaker.menus.NewGameAd;
import com.minimal.brick.breaker.menus.TableNotation;

public class MainMenuScreen implements Screen {

	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private Skin skin;
	private TextureAtlas textureAtlas;
	//private TextButton editeurBouton;
	private TextButtonStyle textButtonStyle;
	private BitmapFont fontTitre;
	private TableNotation tableNotation;
	private MenuPrincipale menuPrincipale;
	private BoutonsReseauxSociaux boutonsReseauxSociaux;
	private NewGameAd cosmonautAd;
	
	public MainMenuScreen(final MyGdxGame gam){
		game = gam;
	
		if(!game.music.isPlaying())
			game.music.play();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		skin = new Skin();
		textureAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		skin.addRegions(textureAtlas);
		
		fontTitre = game.assets.get("fontTitre.ttf", BitmapFont.class);

		boutonsReseauxSociaux = new BoutonsReseauxSociaux(gam, skin);
		boutonsReseauxSociaux.draw(stage);
		
		menuPrincipale = new MenuPrincipale(gam, skin, stage, new Color(0.27f, 0.695f, 0.613f, 1));
		
		if(!Donnees.getRate() && Donnees.getRateCount() < 1){
			tableNotation = new TableNotation(game, skin);
			tableNotation.draw(stage);
		}
		
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("BoutonPatch");
		textButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyle.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
			
		/*
		 * TEST PUBLICITÉ NOUVEAU JEU
		 */
		if(!Donnees.getPromoteCosmonaut())
			if(Donnees.getRateCount() < 2 || Donnees.getNiveau() > 3){
				cosmonautAd = new NewGameAd(skin, "https://play.google.com/store/apps/details?id=com.cosmonaut.android");
				LabelStyle labelStyle = new LabelStyle(game.assets.get("fontTextTableJeu.ttf", BitmapFont.class), Color.WHITE);
				cosmonautAd.setLabelStyle(labelStyle);
				cosmonautAd.setTextButtonStyle(textButtonStyle);
				cosmonautAd.create(	skin.getDrawable("imageTable"),
									"Images/CosmonautImage.png",
									0.85f*Gdx.graphics.getWidth(), 
									0.65f*Gdx.graphics.getHeight(), 
									game.langue.nouveauJeu, 
									game.langue.jouer);
				cosmonautAd.setBackgroundColor(51/256f,77/256f,92/256f, 1); 
				cosmonautAd.addToStage(stage);
			}
		
		
		
		
		/*
		editeurBouton = new TextButton("Editeur", textButtonStyle);
		editeurBouton.setWidth(Gdx.graphics.getWidth()/3);
		editeurBouton.setHeight(Gdx.graphics.getHeight()/10);
		editeurBouton.setX(0);
		editeurBouton.setY(0);

		stage.addActor(editeurBouton);
		*/
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.27f, 0.695f, 0.613f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		fontTitre.draw(game.batch, "MINIMAL BRICK BREAKER", 
						Gdx.graphics.getWidth()/2 - game.assets.get("fontTitre.ttf", BitmapFont.class).getBounds("MINIMAL BRICK BREAKER").width/2, 
						85*Gdx.graphics.getHeight()/100);
		game.batch.end();
		
		stage.act();
		stage.draw();
		
		if (Gdx.input.isKeyJustPressed(Keys.BACK))	
			Gdx.app.exit();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		
		if(!Donnees.getPromoteCosmonaut())
			if(Donnees.getRateCount() < 2 || Donnees.getNiveau() > 3)
				cosmonautAd.action();
		
		if(!Donnees.getRate() && Donnees.getRateCount() < 1){
			tableNotation.action();
		}

		boutonsReseauxSociaux.action();
		menuPrincipale.boutonListener();
		
		/*
		editeurBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				game.setScreen(new EditeurScreen(game));
			}
		});
		*/
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		System.out.println("------------------------Main menu screen disposed");
		stage.dispose();
		skin.dispose();
	}

}
