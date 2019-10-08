package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.ActionBouton;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Variables;

public class NiveauxScreen implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	private Stage stage;
	private Skin skin;
	private TextureAtlas textureAtlas;
	
	private TextButtonStyle textButtonStyle, textButtonStyleInactif, textButtonStyleSpecial;
	private Array<TextButton> groupes, niveaux;
	private TextButton retourBouton, graviteBouton, epileptiqueBouton, optionBloqueeBouton;
	private Image transitionImage, imageNoire;
	//private TextButton titre;
	private Table tableGroupes, tableNiveaux;
	private ScrollPane scrollPaneGroupe;
	private ActionBouton action;
	private float scrollPaneGroupeActif, scrollPaneGroupeInactif, tableNiveauxActif, tableNiveauxInactif;
	
	

	public NiveauxScreen(final MyGdxGame gam){
		game = gam;
		Variables.choixNiveau = false;
		
		action = new ActionBouton();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		stage = new Stage();
		skin = new Skin();
		textureAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		skin.addRegions(textureAtlas);		
		
		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("BoutonPatch");
		textButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyle.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		
		//Style des boutons des groupes et niveaux non débloqués
		textButtonStyleInactif = new TextButtonStyle();
		textButtonStyleInactif.up = skin.getDrawable("BoutonInactifPatch");
		textButtonStyleInactif.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyleInactif.fontColor = new Color().set(0.5f, 0.5f, 0.5f, 60/256f);
		
		//Style des boutons Microgravite et Epileptique
		textButtonStyleSpecial = new TextButtonStyle();
		textButtonStyleSpecial.up = skin.getDrawable("BoutonPatch");
		textButtonStyleSpecial.down = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyleSpecial.checked = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyleSpecial.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyleSpecial.fontColor = Color.WHITE;
		textButtonStyleSpecial.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		textButtonStyleSpecial.checkedFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		
		//Titre de l'écran
		/*
		titre = new TextButton(gam.langue.choisirGroupe.toUpperCase(), textButtonStyle);
		titre.setTouchable(Touchable.disabled);
		titre.setTransform(true);
		titre.setText(game.langue.choisirGroupe.toUpperCase());
		titre.setWidth(1.3f*game.assets.get("font1.ttf", BitmapFont.class).getBounds(titre.getText()).width);
		titre.setX(Gdx.graphics.getWidth()/2 - titre.getWidth()/2);
		titre.setY(87*Gdx.graphics.getHeight()/100);
		*/
		
		//Affichage des groupes
		tableGroupes = new Table();
		tableGroupes.defaults().width(Gdx.graphics.getWidth()/3).height(Gdx.graphics.getHeight()/13).space(Gdx.graphics.getHeight()/200);
		
		scrollPaneGroupe = new ScrollPane(tableGroupes);
		scrollPaneGroupe.setHeight(2*Gdx.graphics.getHeight()/3);
		scrollPaneGroupe.setWidth(Gdx.graphics.getWidth()/2);
		
		scrollPaneGroupeActif = Gdx.graphics.getWidth()/2 - scrollPaneGroupe.getWidth()/2;
		scrollPaneGroupeInactif = -2 * scrollPaneGroupe.getWidth();
		
		scrollPaneGroupe.setX(scrollPaneGroupeActif);
		scrollPaneGroupe.setY(Gdx.graphics.getHeight()/2 - scrollPaneGroupe.getHeight()/2);
		
		groupes = new Array<TextButton>();
		
		for(int i = 0; i < Donnees.getGroupe(); i++){
			if(i + 1 <= Variables.NombreDeGroupes){
				TextButton textButton = new TextButton(gam.langue.groupe.toUpperCase() + " " + (i + 1), textButtonStyle);
				groupes.add(textButton);
				tableGroupes.add(textButton).row();
			}
		}
		
		//Affichage des niveaux
		tableNiveaux = new Table();
		tableNiveaux.defaults().width(Gdx.graphics.getWidth()/7).height(Gdx.graphics.getWidth()/7).space(Gdx.graphics.getWidth()/50);
		
		niveaux = new Array<TextButton>();
		
		for(int i = 0; i < Variables.niveauxParGroupe; i++){
			TextButton textButton = new TextButton("" + (i + 1), textButtonStyle);
			niveaux.add(textButton);
			if(i + 1 == 5 || i + 1 == 10 || i + 1 == 15 || i + 1 == 20) 
				tableNiveaux.add(textButton).row();
			else 
				tableNiveaux.add(textButton);
		}
		
		tableNiveauxActif = Gdx.graphics.getWidth()/2;
		tableNiveauxInactif = 2 * Gdx.graphics.getWidth();
		
		tableNiveaux.setX(tableNiveauxInactif);
		tableNiveaux.setY(45*Gdx.graphics.getHeight()/100);
		
		stage.addActor(scrollPaneGroupe);
		stage.addActor(tableNiveaux);
		stage.act();
		stage.draw();
		
		//Bouton retour
		retourBouton = new TextButton("<", textButtonStyle);
		retourBouton.setWidth(Gdx.graphics.getWidth()/7);
		retourBouton.setHeight(Gdx.graphics.getWidth()/7);
		retourBouton.setX(niveaux.get(0).localToStageCoordinates(new Vector2(0,0)).x - tableNiveauxInactif + tableNiveauxActif);
		//retourBouton.setY(niveaux.get(20).localToStageCoordinates(new Vector2(0,0)).y - Gdx.graphics.getWidth()/50 - retourBouton.getHeight());
		retourBouton.setY(Gdx.graphics.getWidth()/50);
		
		//Bouton Microgravite
		if(Donnees.getMicrogravite()){
			graviteBouton = new TextButton(game.langue.microgravite, textButtonStyleSpecial);
		}
		else graviteBouton = new TextButton(game.langue.microgravite, textButtonStyleInactif);
			if(Variables.microgravite) graviteBouton.setChecked(true);
		graviteBouton.setWidth(2*Gdx.graphics.getWidth()/7 + Gdx.graphics.getWidth()/30);
		graviteBouton.setHeight(Gdx.graphics.getWidth()/7);
		graviteBouton.setX(retourBouton.getX() + retourBouton.getWidth() + Gdx.graphics.getWidth()/30);
		graviteBouton.setY(retourBouton.getY());
		stage.addActor(graviteBouton);

		//Bouton Epileptique
		if(Donnees.getEpileptique()){
			epileptiqueBouton = new TextButton(game.langue.epileptique, textButtonStyleSpecial);
		}
		else epileptiqueBouton = new TextButton(game.langue.epileptique, textButtonStyleInactif);
			if(Variables.epileptique) epileptiqueBouton.setChecked(true);
		epileptiqueBouton.setWidth(2*Gdx.graphics.getWidth()/7 + Gdx.graphics.getWidth()/30);
		epileptiqueBouton.setHeight(Gdx.graphics.getWidth()/7);
		epileptiqueBouton.setX(graviteBouton.getX() + graviteBouton.getWidth() + Gdx.graphics.getWidth()/30);
		epileptiqueBouton.setY(retourBouton.getY());
		stage.addActor(epileptiqueBouton);
		
		transitionImage = new Image(skin.getDrawable("Barre"));
		transitionImage.setWidth(Gdx.graphics.getWidth());
		transitionImage.setHeight(Gdx.graphics.getHeight());
		transitionImage.setColor(0.27f, 0.695f, 0.613f, 1);
		transitionImage.setX(-Gdx.graphics.getWidth());
		transitionImage.setY(0);
		transitionImage.addAction(Actions.alpha(0));
		
		imageNoire = new Image(skin.getDrawable("Barre"));
		imageNoire.setWidth(Gdx.graphics.getWidth());
		imageNoire.setHeight(Gdx.graphics.getHeight());
		imageNoire.setColor(0, 0, 0, 1);
		imageNoire.setX(-Gdx.graphics.getWidth());
		imageNoire.setY(0);
		imageNoire.addAction(Actions.alpha(0));
		
		optionBloqueeBouton = new TextButton(game.langue.microGraviteBloquee, textButtonStyleSpecial);
		optionBloqueeBouton.setX(-Gdx.graphics.getWidth());
		optionBloqueeBouton.setY(0);
		optionBloqueeBouton.addAction(Actions.alpha(0));

		stage.addActor(retourBouton);
		stage.addActor(transitionImage);
		stage.addActor(imageNoire);
		stage.addActor(optionBloqueeBouton);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.27f, 0.695f, 0.613f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		if(!Variables.choixNiveau)
			game.assets.get("fontTitre.ttf", BitmapFont.class).draw(game.batch, game.langue.choisirGroupe.toUpperCase(), 
																	Gdx.graphics.getWidth()/2 - game.assets.get("fontTitre.ttf", BitmapFont.class).getBounds(game.langue.choisirGroupe.toUpperCase()).width/2, 
																	90*Gdx.graphics.getHeight()/100);
		else 
			game.assets.get("fontTitre.ttf", BitmapFont.class).draw(game.batch, game.langue.groupe.toUpperCase() + " " + Variables.groupeSelectione, 
																	Gdx.graphics.getWidth()/2 - game.assets.get("fontTitre.ttf", BitmapFont.class).getBounds(game.langue.groupe.toUpperCase() + " " + Variables.groupeSelectione).width/2, 
																	90*Gdx.graphics.getHeight()/100);
		game.batch.end();
		
		//Déplacement des affichages des groupes et niveaux
		if(!Variables.choixNiveau){
			scrollPaneGroupe.addAction(Actions.moveTo(scrollPaneGroupeActif,
										scrollPaneGroupe.getY(), 
										0.25f));	
			tableNiveaux.addAction(Actions.moveTo(tableNiveauxInactif,
					tableNiveaux.getY(), 
					0.25f));	
		}
		else{
			scrollPaneGroupe.addAction(Actions.moveTo(scrollPaneGroupeInactif,
										scrollPaneGroupe.getY(), 
										0.25f));	
			tableNiveaux.addAction(Actions.moveTo(tableNiveauxActif,
					tableNiveaux.getY(), 
					0.25f));	
		}
		
		//Activation et désactivation des boutons en fonctions des groupes et niveaux débloqués
		if(Variables.groupeSelectione < Donnees.getGroupe()){
			for(TextButton textButton : niveaux){
				textButton.setStyle(textButtonStyle);
				textButton.setTouchable(Touchable.enabled);
			}
		}
		else if(Variables.groupeSelectione == Donnees.getGroupe()){
			for(int i = 0; i < niveaux.size; i++){
				if((i + 1) <= Donnees.getNiveau()){
					niveaux.get(i).setStyle(textButtonStyle);
					niveaux.get(i).setTouchable(Touchable.enabled);
				}
				else {
					niveaux.get(i).setStyle(textButtonStyleInactif);
					niveaux.get(i).setTouchable(Touchable.disabled);
				}
			}
		}
		retourBouton.setStyle(textButtonStyle);
		retourBouton.setTouchable(Touchable.enabled);
		
		if(Donnees.getMicrogravite()){
			graviteBouton.setStyle(textButtonStyleSpecial);
			graviteBouton.setTouchable(Touchable.enabled);
		}
		if(Donnees.getEpileptique()){
			epileptiqueBouton.setStyle(textButtonStyleSpecial);
			epileptiqueBouton.setTouchable(Touchable.enabled);
		}
		
		stage.act();
		stage.draw();	
		
		 //Utilisation du bouton BACK
        if (Gdx.input.isKeyJustPressed(Keys.BACK)){
        	  if(Variables.choixNiveau) Variables.choixNiveau = false;
        	  else game.setScreen(new MainMenuScreen(game));
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
		
		if(Donnees.getGroupe() <= Variables.NombreDeGroupes){
			for(int i = 0; i < Donnees.getGroupe(); i++){
				action.groupeListener(groupes.get(i), (i+1));
			}
		}
		else{
			for(int i = 0; i < Variables.NombreDeGroupes; i++){
				action.groupeListener(groupes.get(i), (i+1));
			}
		}
		
		for(int i = 0; i < niveaux.size; i++){
			if(niveaux.get(i).getStyle() == textButtonStyle)
				action.niveauListener(game, niveaux.get(i), (i+1));
		}
		
		//action.retourListener(game, retourBouton);
		retourBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(Variables.choixNiveau) 
					Variables.choixNiveau = false;
				else{
					transitionImage.addAction(Actions.moveTo(0, 0));
					transitionImage.addAction(Actions.sequence(Actions.alpha(1, 0.2f),	 
																Actions.run(new Runnable() {
														            @Override
														            public void run() {
														            	dispose();
																		game.setScreen(new MainMenuScreen(game));
														            }})));
				}
			}
		});
			
		graviteBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(Donnees.getMicrogravite()){
					if(Variables.microgravite)
						Variables.microgravite = false;
					else Variables.microgravite = true;
				}
				else{
					imageNoire.addAction(Actions.moveTo(0, 0));
					optionBloqueeBouton.setText(game.langue.microGraviteBloquee);
					optionBloqueeBouton.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - optionBloqueeBouton.getWidth()/2, Gdx.graphics.getHeight()/2 - optionBloqueeBouton.getHeight()/2));
					imageNoire.addAction(Actions.alpha(0.75f, 0.25f));
					optionBloqueeBouton.addAction(Actions.alpha(1, 0.25f));
				}
			}
		});
		
		epileptiqueBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(Donnees.getEpileptique()){
					if(Variables.epileptique)
						Variables.epileptique = false;
					else Variables.epileptique = true;
				}
				else{
					imageNoire.addAction(Actions.moveTo(0, 0));
					optionBloqueeBouton.setText(game.langue.optionBloquee);
					optionBloqueeBouton.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - optionBloqueeBouton.getWidth()/2, Gdx.graphics.getHeight()/2 - optionBloqueeBouton.getHeight()/2));
					imageNoire.addAction(Actions.alpha(0.75f, 0.25f));
					optionBloqueeBouton.addAction(Actions.alpha(1, 0.25f));
				}
			}
		});
		
		optionBloqueeBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				imageNoire.addAction(Actions.alpha(0, 0.25f));
				optionBloqueeBouton.addAction(Actions.alpha(0, 0.25f));
				imageNoire.addAction(Actions.moveTo(-Gdx.graphics.getWidth(), 0));
				optionBloqueeBouton.addAction(Actions.moveTo(-Gdx.graphics.getWidth(), 0));
				optionBloqueeBouton.setChecked(false);
			}
		});
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
		System.out.println("------------------------Niveaux screen disposed");
		stage.dispose();
		skin.dispose();
	}
}
