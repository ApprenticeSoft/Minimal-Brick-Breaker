package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Variables;

public class OptionScreen implements Screen{
	
	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private Skin skin;
	private TextureAtlas textureAtlas;
	private TextButton langueBouton, vitesseBouton, rateBouton, moreAppsBouton, retourBouton, sonBouton, 
						lentBouton, normalBouton, rapideBouton,
						englishBouton, francaisBouton, espanolBouton,
						onBouton, offBouton;
	private TextButtonStyle menuButtonStyle, optionButtonStyle;
	private Image transitionImage;
	private Table optionTable;
	private ButtonGroup langueGroupe, vitesseGroupe, sonGroupe;
	private float dimensionsBouton, vitesseInactifX, vitesseY, langueY, sonY;
	private boolean vitesseActif, langueActif, sonActif;
	
	public OptionScreen(final MyGdxGame gam){
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		skin = new Skin();
		textureAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		skin.addRegions(textureAtlas);	
		
		menuButtonStyle = new TextButtonStyle();
		menuButtonStyle.up = skin.getDrawable("BoutonPatch");
		menuButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		menuButtonStyle.font = game.assets.get("fontOption.ttf", BitmapFont.class);
		menuButtonStyle.fontColor = Color.WHITE;
		menuButtonStyle.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		
		optionButtonStyle = new TextButtonStyle();
		optionButtonStyle.up = skin.getDrawable("BoutonPatch");
		optionButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		optionButtonStyle.checked = skin.getDrawable("BoutonCheckedPatch");
		optionButtonStyle.font = game.assets.get("fontOption.ttf", BitmapFont.class);
		optionButtonStyle.fontColor = Color.WHITE;
		optionButtonStyle.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		optionButtonStyle.checkedFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		
		optionTable = new Table();
		
		//Menu général
		langueBouton = new TextButton(gam.langue.langage, menuButtonStyle);
		vitesseBouton = new TextButton(gam.langue.vitesse, menuButtonStyle);
		rateBouton = new TextButton(gam.langue.noter, menuButtonStyle);
		moreAppsBouton = new TextButton(gam.langue.plusDApp, menuButtonStyle);
		sonBouton = new TextButton(gam.langue.sons, menuButtonStyle);
		retourBouton = new TextButton("<", menuButtonStyle);
		//Menu Vitesse
		lentBouton = new TextButton(gam.langue.lent, optionButtonStyle);
		normalBouton = new TextButton(gam.langue.normal, optionButtonStyle);
		rapideBouton = new TextButton(gam.langue.rapide, optionButtonStyle);
		vitesseGroupe = new ButtonGroup();
		vitesseGroupe.add(lentBouton);
		vitesseGroupe.add(rapideBouton);
		vitesseGroupe.add(normalBouton);
		vitesseGroupe.setMinCheckCount(1);
		vitesseGroupe.setMaxCheckCount(1);
		//Menu langue
		englishBouton = new TextButton("English", optionButtonStyle);
		francaisBouton = new TextButton("Français", optionButtonStyle);
		espanolBouton = new TextButton("Español", optionButtonStyle);	
		langueGroupe = new ButtonGroup();
		langueGroupe.add(englishBouton);
		langueGroupe.add(francaisBouton);
		langueGroupe.add(espanolBouton);
		langueGroupe.setMinCheckCount(1);
		langueGroupe.setMaxCheckCount(1);
		//Menu son
		onBouton = new TextButton(gam.langue.activé, optionButtonStyle);
		offBouton = new TextButton(gam.langue.désactivé, optionButtonStyle);	
		sonGroupe = new ButtonGroup();
		sonGroupe.add(onBouton);
		sonGroupe.add(offBouton);
		sonGroupe.setMinCheckCount(1);
		sonGroupe.setMaxCheckCount(1);	

		langueBouton.setVisible(false);
		vitesseBouton.setVisible(false);
		sonBouton.setVisible(false);
		rateBouton.setVisible(false);
		moreAppsBouton.setVisible(false);
		lentBouton.setVisible(false);
		normalBouton.setVisible(false);
		rapideBouton.setVisible(false);
		onBouton.setVisible(false);
		offBouton.setVisible(false);
		francaisBouton.setVisible(false);
		englishBouton.setVisible(false);
		espanolBouton.setVisible(false);
		
		optionTable.defaults().width(23*Gdx.graphics.getWidth()/100).height(23*Gdx.graphics.getWidth()/100).space(Gdx.graphics.getWidth()/100);
		optionTable.add(langueBouton).row();
		optionTable.add(vitesseBouton).row();
		optionTable.add(sonBouton).row();
		optionTable.add(rateBouton).row();
		optionTable.add(moreAppsBouton).row();
		optionTable.add(retourBouton).height(Gdx.graphics.getWidth()/7).width(Gdx.graphics.getWidth()/7).left().spaceTop(5*Gdx.graphics.getHeight()/100);
		optionTable.setX(14*Gdx.graphics.getWidth()/100);
		optionTable.setY(50*Gdx.graphics.getHeight()/100);
		
		//Transition entre les écrans
		transitionImage = new Image(skin.getDrawable("Barre"));
		transitionImage.setWidth(Gdx.graphics.getWidth());
		transitionImage.setHeight(Gdx.graphics.getHeight());
		transitionImage.setColor(0.27f, 0.695f, 0.613f, 1);
		transitionImage.setX(-Gdx.graphics.getWidth());
		transitionImage.setY(0);
		transitionImage.addAction(Actions.alpha(0));

		stage.addActor(rapideBouton);
		stage.addActor(normalBouton);
		stage.addActor(lentBouton);
		stage.addActor(espanolBouton);
		stage.addActor(francaisBouton);
		stage.addActor(englishBouton);
		stage.addActor(offBouton);
		stage.addActor(onBouton);
		stage.addActor(optionTable);
		stage.addActor(transitionImage);
		
		stage.draw();	
		
		vitesseActif = false;
		langueActif = false;
		sonActif= false;
		vitesseInactifX = vitesseBouton.localToStageCoordinates(new Vector2(0,0)).x;
		vitesseY = vitesseBouton.localToStageCoordinates(new Vector2(0,0)).y;
		langueY = langueBouton.localToStageCoordinates(new Vector2(0,0)).y;
		sonY = sonBouton.localToStageCoordinates(new Vector2(0,0)).y;
		
		dimensionsBouton = 23*Gdx.graphics.getWidth()/100;
		
		//Vitesse
		lentBouton.setWidth(dimensionsBouton);
		lentBouton.setHeight(dimensionsBouton);
		lentBouton.setX(vitesseInactifX);
		lentBouton.setY(vitesseY);
		normalBouton.setWidth(dimensionsBouton);
		normalBouton.setHeight(dimensionsBouton);
		normalBouton.setX(vitesseInactifX);
		normalBouton.setY(vitesseY);
		rapideBouton.setWidth(dimensionsBouton);
		rapideBouton.setHeight(dimensionsBouton);
		rapideBouton.setX(vitesseInactifX);
		rapideBouton.setY(vitesseY);
		if(Donnees.getVitesse() == 1) lentBouton.setChecked(true);
		else if(Donnees.getVitesse() == 2) normalBouton.setChecked(true);
		else if(Donnees.getVitesse() == 3) rapideBouton.setChecked(true);
		
		//Langue
		englishBouton.setWidth(dimensionsBouton);
		englishBouton.setHeight(dimensionsBouton);
		englishBouton.setX(vitesseInactifX);
		englishBouton.setY(langueY);
		francaisBouton.setWidth(dimensionsBouton);
		francaisBouton.setHeight(dimensionsBouton);
		francaisBouton.setX(vitesseInactifX);
		francaisBouton.setY(langueY);
		espanolBouton.setWidth(dimensionsBouton);
		espanolBouton.setHeight(dimensionsBouton);
		espanolBouton.setX(vitesseInactifX);
		espanolBouton.setY(langueY);
		if(Donnees.getLangue() == 1) englishBouton.setChecked(true);
		else if(Donnees.getLangue() == 2) francaisBouton.setChecked(true);
		else if(Donnees.getLangue() == 3) espanolBouton.setChecked(true);
		
		//Son
		onBouton.setWidth(dimensionsBouton);
		onBouton.setHeight(dimensionsBouton);
		onBouton.setX(vitesseInactifX);
		onBouton.setY(sonY);
		offBouton.setWidth(dimensionsBouton);
		offBouton.setHeight(dimensionsBouton);
		offBouton.setX(vitesseInactifX);
		offBouton.setY(sonY);
		if(Donnees.getSon()) onBouton.setChecked(true);
		else offBouton.setChecked(true);		
		
		langueBouton.addAction(Actions.parallel(Actions.alpha(0), 
												Actions.addAction(Actions.alpha(0), vitesseBouton), 
												Actions.addAction(Actions.alpha(0), sonBouton),
												Actions.addAction(Actions.alpha(0), rateBouton),
												Actions.addAction(Actions.alpha(0), moreAppsBouton),
												Actions.run(new Runnable() {
										            @Override
										            public void run() {
										            	langueBouton.setVisible(true);
											    		vitesseBouton.setVisible(true);
											    		sonBouton.setVisible(true);
											    		rateBouton.setVisible(true);
											    		moreAppsBouton.setVisible(true);	
										            }})));
		
		langueBouton.addAction(Actions.sequence(Actions.delay(0.1f),
												Actions.alpha(1, 0.1f), 
												Actions.addAction(Actions.alpha(1, 0.1f), vitesseBouton),
												Actions.alpha(1, 0.1f),  
												Actions.addAction(Actions.alpha(1, 0.1f), sonBouton),
												Actions.delay(0.1f),
												Actions.addAction(Actions.alpha(1, 0.1f), rateBouton),
												Actions.delay(0.1f),
												Actions.addAction(Actions.alpha(1, 0.1f), moreAppsBouton),
												Actions.run(new Runnable() {
										            @Override
										            public void run() {
										            	lentBouton.setVisible(true);
										            	normalBouton.setVisible(true);
										        		rapideBouton.setVisible(true);
										        		onBouton.setVisible(true);
										        		offBouton.setVisible(true);
										        		francaisBouton.setVisible(true);
										        		englishBouton.setVisible(true);
										        		espanolBouton.setVisible(true);	
										            }})));										
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.27f, 0.695f, 0.613f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		
		stage.act();
		stage.draw();	
		
		 //Utilisation du bouton BACK
        if (Gdx.input.isKeyJustPressed(Keys.BACK)){
        	game.setScreen(new MainMenuScreen(game));
        }
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
		Gdx.input.setCatchBackKey(true);
		
		//Utilisation du menu
		langueBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(langueActif){
					langueActif = false;
					englishBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));	
					francaisBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
					espanolBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
				}
				else{
					langueActif = true;
					englishBouton.addAction(Actions.moveTo(vitesseInactifX + 24*Gdx.graphics.getWidth()/100, langueY, 0.7f, Interpolation.exp5Out));	
					francaisBouton.addAction(Actions.moveTo(vitesseInactifX + 48*Gdx.graphics.getWidth()/100, langueY, 0.7f, Interpolation.exp5Out));
					espanolBouton.addAction(Actions.moveTo(vitesseInactifX + 72*Gdx.graphics.getWidth()/100, langueY, 0.7f, Interpolation.exp5Out));
				}
				//Fermeture des menus non utilisés
				if(vitesseActif){
					vitesseActif = false;
					lentBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));	
					normalBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
					rapideBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
				}				
				if(sonActif){
					sonActif = false;
					onBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));	
					offBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));
				}
			}
		});
		
		vitesseBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(vitesseActif){
					vitesseActif = false;
					lentBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));	
					normalBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
					rapideBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
				}
				else{
					vitesseActif = true;
					lentBouton.addAction(Actions.moveTo(vitesseInactifX + 24*Gdx.graphics.getWidth()/100, vitesseY, 0.7f, Interpolation.exp5Out));	
					normalBouton.addAction(Actions.moveTo(vitesseInactifX + 48*Gdx.graphics.getWidth()/100, vitesseY, 0.7f, Interpolation.exp5Out));
					rapideBouton.addAction(Actions.moveTo(vitesseInactifX + 72*Gdx.graphics.getWidth()/100, vitesseY, 0.7f, Interpolation.exp5Out));
				}
				//Fermeture des menus non utilisés
				if(langueActif){
					langueActif = false;
					englishBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));	
					francaisBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
					espanolBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
				}			
				if(sonActif){
					sonActif = false;
					onBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));	
					offBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));
				}
			}
		});
		
		sonBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(sonActif){
					sonActif = false;
					onBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));	
					offBouton.addAction(Actions.moveTo(vitesseInactifX, sonY, 0.7f, Interpolation.exp5Out));
				}
				else{
					sonActif = true;
					onBouton.addAction(Actions.moveTo(vitesseInactifX + 24*Gdx.graphics.getWidth()/100, sonY, 0.7f, Interpolation.exp5Out));	
					offBouton.addAction(Actions.moveTo(vitesseInactifX + 48*Gdx.graphics.getWidth()/100, sonY, 0.7f, Interpolation.exp5Out));
				}
				//Fermeture des menus non utilisés
				if(langueActif){
					langueActif = false;
					englishBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));	
					francaisBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
					espanolBouton.addAction(Actions.moveTo(vitesseInactifX, langueY, 0.7f, Interpolation.exp5Out));
				}				
				if(vitesseActif){
					vitesseActif = false;
					lentBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));	
					normalBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
					rapideBouton.addAction(Actions.moveTo(vitesseInactifX, vitesseY, 0.7f, Interpolation.exp5Out));
				}
			}
		});
		
		moreAppsBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(Variables.GOOGLE_PLAY_STORE_URL);
				//Gdx.net.openURI(Variables.AMAZON_STORE_URL);
			}
		});
		
		rateBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.net.openURI(Variables.GOOGLE_PLAY_GAME_URL);
				//Gdx.net.openURI(Variables.AMAZON_GAME_URL);
			}
		});
		
		retourBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				transitionImage.addAction(Actions.moveTo(0, 0));
				transitionImage.addAction(Actions.sequence(Actions.alpha(1, 0.2f),	 
															Actions.run(new Runnable() {
													            @Override
													            public void run() {
													            	dispose();
																	game.setScreen(new MainMenuScreen(game));
													            }})));
			}
		});
		
		//Utilisation des options
		//Son
		onBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setSon(true);
			}
		});
		
		offBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setSon(false);
			}
		});
		//Vitesse
		lentBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setVitesse(1);
			}
		});
		normalBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setVitesse(2);
			}
		});
		rapideBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setVitesse(3);
			}
		});
		//Langue
		englishBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangue(1);
				game.langue.setLangue(1);
				setLangage();
			}
		});
		francaisBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangue(2);
				game.langue.setLangue(2);
				setLangage();
			}
		});
		espanolBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Donnees.setLangue(3);
				game.langue.setLangue(3);
				setLangage();
			}
		});	
	}

	public void setLangage(){
		langueBouton.setText(game.langue.langage); 
		vitesseBouton.setText(game.langue.vitesse); 
		rateBouton.setText(game.langue.noter);
		moreAppsBouton.setText(game.langue.plusDApp);
		sonBouton.setText(game.langue.sons); 
		lentBouton.setText(game.langue.lent);
		normalBouton.setText(game.langue.normal);
		rapideBouton.setText(game.langue.rapide);
		onBouton.setText(game.langue.activé);
		offBouton.setText(game.langue.désactivé);
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
		System.out.println("------------------------Option screen disposed");
		stage.dispose();
		skin.dispose();
	}
}
