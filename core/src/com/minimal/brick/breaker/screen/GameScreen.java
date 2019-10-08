package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.minimal.brick.breaker.Couleurs;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.LevelHandler;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Sons;
import com.minimal.brick.breaker.Variables;
import com.minimal.brick.breaker.body.Balle;
import com.minimal.brick.breaker.body.Barre;
import com.minimal.brick.breaker.body.Bouclier;
import com.minimal.brick.breaker.body.Brique;
import com.minimal.brick.breaker.body.Laser;
import com.minimal.brick.breaker.body.Objet;

public class GameScreen extends InputAdapter implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	World world; 
    static int initY, son, spawnObjet;    
    private PolygonShape boxBordure;
    private BodyDef bordureBodyDef;
    private Body bodyHaut, bodyGauche, bodyDroite;
	private Skin skin;
	private TextureAtlas textureAtlas;
	boolean pause, fin, spawn;
	
	Stage stage;
	Table tableFin, tablePause, tablePerdu;
	private Image imagePause;
	TextButtonStyle textButtonStyle, textButtonStyleFini;
	TextButton nextBouton, replayBouton, 
				resumeBouton, restartBouton, restartBouton2, menuBouton, menuBouton2, 
				groupeFini, groupeDebloque, jeuFini,
				quitBouton,
				micrograviteDebloque, epileptiqueDebloque;
	LabelStyle labelStyle;
	Label labelComplete, labelPerdu;
	
	private Array<Brique> briques;
	private Array<Balle> balles;
	private Array<Objet> objets;
	private Array<Laser> lasers;
	private Balle balle;
	private Barre barre;
	private Bouclier bouclier;
	
	private LevelHandler level;
	private Couleurs couleurs;
	private Sons sons;
	private Sound sonLaser, sonNiveauFini, sonPerdu;

	long lastDropTime;
	private int couleurEdit, couleurMin, couleurMax;
	float spawnX, spawnY;
	
	FPSLogger fps;
	Box2DDebugRenderer debugRenderer; 
	
	public GameScreen(final MyGdxGame gam){
		game = gam;
		
		fps = new FPSLogger();
		couleurEdit = Variables.groupeSelectione;
		if(Variables.epileptique){
			couleurMin = 1;
			couleurMax = 19;
		}
		else{
			couleurMin = Variables.groupeSelectione;
			couleurMax = Variables.groupeSelectione;
		}
		couleurs = new Couleurs(couleurEdit);
		sons = new Sons(game, Variables.groupeSelectione);
		Variables.vitesseBalle = Variables.vitesseBalleNormale;
		Variables.ecart = Variables.barreNormale;
		Variables.bouclierActif = false;
		Variables.laserActif = false;
		Variables.tirs = 0;
		Variables.viesPerdues = 0;
		Variables.BOX_STEP = 1/60f;
		pause = false;
		fin = false;
		
      	world = new World(new Vector2(0, 0), true); 
		World.setVelocityThreshold(0);
      	
		Variables.ecart = Variables.barreNormale;
		sonLaser = game.assets.get("Sons/Laser.ogg", Sound.class);
		sonNiveauFini = game.assets.get("Sons/NiveauFini.ogg", Sound.class);
		sonPerdu = game.assets.get("Sons/Perdu.ogg", Sound.class);
		spawn = false;
		
		camera = new OrthographicCamera();
        camera.viewportHeight = Gdx.graphics.getHeight() * Variables.WORLD_TO_BOX;  
        camera.viewportWidth = Gdx.graphics.getWidth() * Variables.WORLD_TO_BOX;  
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);  
        camera.update();  
	
		skin = new Skin();
		textureAtlas = game.assets.get("Images.pack", TextureAtlas.class);
		skin.addRegions(textureAtlas);
		
        //Bordure de l'écran de jeu  
		bordureBodyDef = new BodyDef();    
        boxBordure = new PolygonShape();  
        boxBordure.setAsBox(camera.viewportWidth/2, camera.viewportHeight/100);     
        bordureBodyDef.position.set(new Vector2(camera.viewportWidth/2, 101*camera.viewportHeight/100)); 
        bodyHaut = world.createBody(bordureBodyDef);
        bodyHaut.setUserData("Haut");
        bodyHaut.createFixture(boxBordure, 0.0f); 
        
        bordureBodyDef.position.set(new Vector2(-camera.viewportWidth/100, camera.viewportHeight/2)); 
        boxBordure.setAsBox(camera.viewportWidth/100, camera.viewportHeight/2);
        bodyGauche = world.createBody(bordureBodyDef);
        bodyGauche.setUserData("Rebord");
        bodyGauche.createFixture(boxBordure, 0.0f);
        
        bordureBodyDef.position.set(new Vector2(101*camera.viewportWidth/100, camera.viewportHeight/2)); 
        bodyDroite = world.createBody(bordureBodyDef);
        bodyDroite.setUserData("Rebord");
        bodyDroite.createFixture(boxBordure, 0.0f);

        //Pad
        barre = new Barre(world, camera);
        //Bouclier
        bouclier = new Bouclier(world, camera);
        
        briques = new Array<Brique>();
        objets = new Array<Objet>();
        lasers = new Array<Laser>();
        
        level = new LevelHandler(world, camera, briques);
        level.loadLevel(Variables.groupeSelectione, Variables.niveauSelectione);
        
        balles = new Array<Balle>();
		balle = new Balle(world, camera, barre.body.getPosition().x, (barre.body.getPosition().y + camera.viewportWidth/50 + camera.viewportHeight/100));
		balles.add(balle);
		
		//Table fin de niveau
		stage = new Stage();

		textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("FondTable2");
		textButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyle.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyle.fontColor = Color.WHITE;
		textButtonStyle.downFontColor = new Color(0.27f, 0.695f, 0.613f, 1);
		
		nextBouton = new TextButton(gam.langue.suivant, textButtonStyle);	
		replayBouton = new TextButton(gam.langue.rejouer, textButtonStyle);
		
		labelStyle = new LabelStyle(game.assets.get("font1.ttf", BitmapFont.class), Color.WHITE);
		labelComplete = new Label(gam.langue.niveauComplete, labelStyle);
		
		tableFin = new Table();
		tableFin.row().colspan(2);
		tableFin.add(labelComplete).spaceBottom(Gdx.graphics.getHeight()/30);
		tableFin.row().height(Gdx.graphics.getHeight()/12).width(Gdx.graphics.getWidth()/3);
		tableFin.add(nextBouton);
		tableFin.add(replayBouton);
		tableFin.setX(-Gdx.graphics.getWidth()/2);
		tableFin.setY(Gdx.graphics.getHeight()/2 + tableFin.getPrefHeight()/4);
		
		//Table pause
		resumeBouton = new TextButton(gam.langue.reprendre, textButtonStyle);
		restartBouton = new TextButton(gam.langue.recommencer, textButtonStyle);	
		menuBouton = new TextButton(gam.langue.menu, textButtonStyle);
		quitBouton = new TextButton(gam.langue.quitter, textButtonStyle);
		
		tablePause = new Table();
		if(Donnees.getLangue() == 2)
			tablePause.defaults().height(Gdx.graphics.getHeight()/12).width(1.2f*game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.recommencer).width).space(Gdx.graphics.getHeight()/100);
		else
			tablePause.defaults().height(Gdx.graphics.getHeight()/12).width(2*game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.recommencer).width).space(Gdx.graphics.getHeight()/100);
		tablePause.add(resumeBouton).row();
		tablePause.add(restartBouton).row();
		tablePause.add(menuBouton).row();
		tablePause.add(quitBouton).row();
		tablePause.setSize(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		tablePause.setX(2 * Gdx.graphics.getWidth());
		tablePause.setY(Gdx.graphics.getHeight()/2 - tablePause.getHeight()/2);
		//tablePause.setBackground(skin.getDrawable("FondTable5"));

		//Table perdu
		tablePerdu = new Table();
		labelPerdu = new Label(gam.langue.perdu, labelStyle);
		restartBouton2 = new TextButton(gam.langue.rejouer, textButtonStyle);	
		menuBouton2 = new TextButton(gam.langue.menu, textButtonStyle);
		
		tablePerdu.row().colspan(2);
		tablePerdu.add(labelPerdu).spaceBottom(Gdx.graphics.getHeight()/30);
		tablePerdu.row().height(Gdx.graphics.getHeight()/12).width(Gdx.graphics.getWidth()/3);
		tablePerdu.add(restartBouton2);
		tablePerdu.add(menuBouton2);
		tablePerdu.setX(-Gdx.graphics.getWidth()/2);
		tablePerdu.setY(Gdx.graphics.getHeight()/2 + tablePerdu.getPrefHeight()/4);
		
		//Bouton goupe debloqué
		textButtonStyleFini = new TextButtonStyle();
		textButtonStyleFini.up = skin.getDrawable("FondTable2");
		textButtonStyleFini.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyleFini.fontColor = Color.WHITE;
		
		groupeFini = new TextButton(gam.langue.groupe + " " + Donnees.getGroupe() + " " + gam.langue.fini + " !", textButtonStyleFini);
		groupeFini.setWidth(56*Gdx.graphics.getWidth()/100);
		groupeFini.setHeight(groupeFini.getWidth()/3);
		groupeFini.setX(-Gdx.graphics.getWidth());
		groupeFini.setY(0);
		groupeFini.addAction(Actions.alpha(0));
	
		groupeDebloque = new TextButton(gam.langue.groupe + " " + (Donnees.getGroupe()+1) + " " + gam.langue.debloque + " !", textButtonStyleFini);
		groupeDebloque.setWidth(56*Gdx.graphics.getWidth()/100);
		groupeDebloque.setHeight(groupeDebloque.getWidth()/3);
		groupeDebloque.setX(-Gdx.graphics.getWidth());
		groupeDebloque.setY(0);
		groupeDebloque.addAction(Actions.alpha(0));
		
		jeuFini = new TextButton(gam.langue.jeuComplete, textButtonStyleFini);
		jeuFini.setWidth(56*Gdx.graphics.getWidth()/100);
		jeuFini.setHeight(jeuFini.getWidth()/3);
		jeuFini.setX(-Gdx.graphics.getWidth());
		jeuFini.setY(0);
		jeuFini.addAction(Actions.alpha(0));
		
		micrograviteDebloque = new TextButton(gam.langue.micrograviteDebloque, textButtonStyleFini);
		//micrograviteDebloque.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.micrograviteDebloque).width + Gdx.graphics.getWidth()/50);
		//micrograviteDebloque.setHeight(game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.micrograviteDebloque).height + Gdx.graphics.getHeight()/100);
		micrograviteDebloque.setX(-Gdx.graphics.getWidth());
		micrograviteDebloque.setY(0);
		micrograviteDebloque.addAction(Actions.alpha(0));
		
		System.out.println("Gdx.graphics.getHeight() = " + Gdx.graphics.getHeight());
		System.out.println("Hauteur du texte = " + game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.micrograviteDebloque).height);
		System.out.println("Gdx.graphics.getWidth() = " + Gdx.graphics.getWidth());
		System.out.println("Largeur du texte = " + game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.micrograviteDebloque).width);
		
		epileptiqueDebloque = new TextButton(gam.langue.epileptiqueDebloque, textButtonStyleFini);
		//epileptiqueDebloque.setWidth(game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.epileptiqueDebloque).width + Gdx.graphics.getWidth()/50);
		//epileptiqueDebloque.setHeight(game.assets.get("font1.ttf", BitmapFont.class).getBounds(gam.langue.epileptiqueDebloque).height + Gdx.graphics.getHeight()/100);
		epileptiqueDebloque.setX(-Gdx.graphics.getWidth());
		epileptiqueDebloque.setY(0);
		epileptiqueDebloque.addAction(Actions.alpha(0));
		
		imagePause = new Image(skin.getDrawable("Fond"));
		imagePause.setWidth(Gdx.graphics.getWidth());
		imagePause.setHeight(Gdx.graphics.getHeight());
		imagePause.setX(0);
		imagePause.setY(0);
		imagePause.addAction(Actions.alpha(0));
		
		stage.addActor(imagePause);
		stage.addActor(tableFin);
		stage.addActor(tablePause);
		stage.addActor(tablePerdu);
		stage.addActor(groupeDebloque);
		stage.addActor(groupeFini);
		stage.addActor(jeuFini);
		stage.addActor(micrograviteDebloque);
		stage.addActor(epileptiqueDebloque);
		tableFin.setVisible(false);

        debugRenderer = new Box2DDebugRenderer();
        
        Variables.briquesInitiales = briques.size;
        System.out.println("Variables.briquesInitiales = " + Variables.briquesInitiales);
        Variables.briquesDetruitesAuLaser = 0;
        System.out.println(" Variables.briquesDetruitesAuLaser = " +  Variables.briquesDetruitesAuLaser);
        /*
        for(BriqueBox2D brique: briques){
        	brique.body.setAwake(true);
        }
        */
	}
	
	@Override
	public void render(float delta) {  
		couleurs.setGroupe(couleurEdit);
		for(Brique brique : briques){
			brique.setCouleur(couleurEdit);
		}
		
		Gdx.gl.glClearColor(couleurs.getCouleur5().r, couleurs.getCouleur5().g, couleurs.getCouleur5().b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//fps.log();
		
		for(Balle balle : balles){
			balle.active();
		}

		//Déplacement de la barre
		barre.deplacement();
		bouclier.actif();
        
		//Fin du niveau
		if(briques.size == 0 && !fin){
			sonNiveauFini.play();
			fin = true;
			Variables.BOX_STEP = 0;
			imagePause.addAction(Actions.alpha(0.75f, 0.25f));
			

			if(Variables.niveauSelectione == 25 && Variables.groupeSelectione == Variables.NombreDeGroupes){
				if(Donnees.getGroupe() == Variables.NombreDeGroupes)
					Donnees.setGroupe(Donnees.getGroupe() + 1);
				
				Donnees.setNiveau(1);
				jeuFini.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - jeuFini.getWidth()/2, Gdx.graphics.getHeight()/2 - jeuFini.getHeight()/2));
				jeuFini.addAction(Actions.alpha(1, 0.5f));
			}
			else if(Variables.niveauSelectione == Donnees.getNiveau() && Variables.groupeSelectione == Donnees.getGroupe()){
				if(Donnees.getNiveau() < 25){
					Donnees.setNiveau(Donnees.getNiveau() + 1);
					tableFin.setVisible(true);
					tableFin.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, tableFin.getY(), 0.2f));
				}
				else if(Donnees.getNiveau() == 25 && Variables.groupeSelectione == Donnees.getGroupe() && Donnees.getGroupe() < Variables.NombreDeGroupes){
					Donnees.setNiveau(1);
					Donnees.setGroupe(Donnees.getGroupe() + 1);
					groupeFini.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - groupeFini.getWidth()/2, Gdx.graphics.getHeight()/2 - groupeFini.getHeight()/2));
					groupeFini.addAction(Actions.alpha(1, 0.5f));
				}
			}
			else{
				tableFin.setVisible(true);
				tableFin.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, tableFin.getY(), 0.2f));
			}
		}
		
		//Niveau perdu
		if(Variables.viesPerdues == 3){
			if(!fin)
				sonPerdu.play();
			fin = true;
			Variables.BOX_STEP = 0;
			imagePause.addAction(Actions.alpha(0.75f, 0.25f));
			tablePerdu.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, tablePerdu.getY(), 0.2f));			
		}
		 
        world.step(Variables.BOX_STEP, Variables.BOX_VELOCITY_ITERATIONS, Variables.BOX_POSITION_ITERATIONS);
        
        //Mettre le jeu en pause
        if (Gdx.input.isKeyJustPressed(Keys.BACK) && /*!tableFin.isVisible()*/ Variables.viesPerdues < 3 && briques.size != 0){
			if(pause){
				pause = false;
				
				if(briques.size != 0){
			       	Variables.BOX_STEP = 1/60f;
			       	imagePause.addAction(Actions.alpha(0, 0.25f));
			       	tablePause.addAction(Actions.moveTo(2 * Gdx.graphics.getWidth(),
								tablePause.getY(), 
								0.25f));
				}
			}
       	  	else{
       	  		pause = true;
       	  		
       	  		if(briques.size != 0){
       		       	Variables.BOX_STEP = 0;
       		       	imagePause.addAction(Actions.alpha(0.75f, 0.25f));
       		       	tablePause.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - tablePause.getWidth()/2,
       							tablePause.getY(), 
       							0.25f));
       			}
       	  	}
		}
        
        //Apparition des objets
        if(spawn){
        	//Jamais plus de 4 objets en même temps
        	if(objets.size < 4){													
        	Objet objet = new Objet(world, camera, spawnX, spawnY, barre, balles, lasers);
        	objets.add(objet);
        	}
        	spawn = false;
        }
        
        //Apparition du laser
        if(Variables.laserActif){
			if(TimeUtils.millis() - Variables.laserTime > 200){
				if(Donnees.getSon())
					sonLaser.play();
				if(Variables.tirs%2 == 0){
					Laser laser = new Laser(world, camera, barre.body.getPosition().x - 0.7f*barre.getWidth(), barre.body.getPosition().y + barre.getHeight());
					lasers.add(laser);
				}
				else{
					Laser laser = new Laser(world, camera, barre.body2.getPosition().x + 0.7f*barre.getWidth(), barre.body.getPosition().y + barre.getHeight());
					lasers.add(laser);
				}
				Variables.laserTime = TimeUtils.millis();
				Variables.tirs++;
			}
			else if(Variables.tirs == 14){
				Variables.laserActif = false;
				Variables.tirs = 0;
			}
		}
        //Vie perdue - Apparition d'une balle & réinitialisation des powerups
        if(balles.size == 0){
    		balle = new Balle(world, camera, barre.body.getPosition().x, (barre.body.getPosition().y + camera.viewportWidth/50 + camera.viewportHeight/100));
    		balles.add(balle);
    		Variables.vitesseBalle = Variables.vitesseBalleNormale;
    		Variables.ecart = Variables.barreNormale;
    		Variables.laserActif = false;
    		Variables.tirs = 0;  	
    		Variables.viesPerdues++;
    		System.out.println("Vies perdues = " +Variables.viesPerdues);
        }
        
        //Destruction des briques, balles, lasers et objets
		Brique.detruire(briques);
        Laser.detruire(lasers);
        Balle.detruire(balles);
        Objet.detruire(objets);
               
        //Gestion de la vitesse de la balle
        if(Variables.vitesseBalle == Variables.vitesseBalleMin && (TimeUtils.millis() - Variables.vitesseBalleTime) > 5100)
        	Variables.vitesseBalle = Variables.vitesseBalleNormale;
        else if(Variables.vitesseBalle == Variables.vitesseBalleMax && (TimeUtils.millis() - Variables.vitesseBalleTime) > 5000)
        	Variables.vitesseBalle = Variables.vitesseBalleNormale;
        
          
		//debugRenderer.render(world, camera.combined);
		
		/**********************************Affichage des graphismes************************************/
		game.batch.begin();
		//Affichage des ombres
		for(int i = 0; i < briques.size; i++){
			briques.get(i).drawOmbre(game.batch, textureAtlas.findRegion("Barre"));
		}
		for(int i = 0; i < lasers.size; i++){
			lasers.get(i).drawOmbre(game.batch, textureAtlas.findRegion("Barre"));
		}
		for(Balle balle : balles){
			balle.drawOmbre(game.batch, textureAtlas.findRegion("Balle"));
		}
		
		//Affichage des briques
		for(int i = 0; i < briques.size; i++){
			briques.get(i).draw(game.batch, textureAtlas.findRegion("Barre"));
		}
		//Affichage de la barre
		barre.draw(game.batch, textureAtlas.findRegion("Barre"));
		//Affichage des objets
		for(int i = 0; i < objets.size; i++){
			objets.get(i).draw(game.batch, textureAtlas);
		}
		//Affichage des Lasers
		for(int i = 0; i < lasers.size; i++){
			lasers.get(i).draw(game.batch, textureAtlas.findRegion("Barre"));
		}	
		//Affichage des balles
		game.batch.setColor(1,1,1,1);
		for(Balle balle : balles){
			balle.draw(game.batch, textureAtlas.findRegion("Balle"));
		}
		bouclier.draw(game.batch, textureAtlas.findRegion("Barre"));
		game.batch.end();
		
        stage.act();
        stage.draw();
		/**********************************************************************************************/
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {	
		//Important pour pouvoir utiliser la touche BACK
		Gdx.input.setCatchBackKey(true);
		//Permet d'interagir avec les Actors du Stage
		Gdx.input.setInputProcessor(stage);
		
		world.setContactListener(new ContactListener(){
			@Override
			public void beginContact(Contact contact) {
				Body a = contact.getFixtureA().getBody();
			    Body b = contact.getFixtureB().getBody();
			    if(a.getUserData() != null && b.getUserData() != null){
				    if(a.getUserData().equals("Brique") && b.getUserData().equals("Balle")){
				    	couleurEdit = MathUtils.random(couleurMin,couleurMax);
				    	
				    	for(Balle balle : balles){
				    		if(balle.body == b)
				    			balle.rebond = 0;
				    	}
				    	
				    	for(Brique brique : briques){
				    		if(brique.body == a){
				    			brique.Collision();
				    			if(Donnees.getSon()){
									son = MathUtils.random(0,15);
									sons.getSons().get(son).play();
				    			}

						    	//Apparition des objets
						    	spawnObjet = MathUtils.random(1,100);
						    	if(spawnObjet > 38 && spawnObjet < 65 && brique.opacite == 0 && Variables.briquesDetruites > 2){
						    		Variables.briquesDetruites = 0;
						    		spawn = true;
						    		spawnX = a.getPosition().x;
						    		spawnY = a.getPosition().y;
						    	}
				    		}
				    	}  	
				    }
				    else if(a.getUserData().equals("Barre") && (b.getUserData().equals("Balle") || b.getUserData().equals("BalleLaser"))){
				    	if(b.getPosition().x > (barre.getPosition() - barre.getLargeur()) && b.getPosition().x < (barre.getPosition() + barre.getLargeur())){
				    		
				    		for(Balle balle : balles){
					    		if(balle.body == b)
					    			balle.rebond = 0;
					    	}
				    		
				    		b.setLinearVelocity(b.getLinearVelocity().rotate((30*(b.getPosition().x - barre.getPosition())/(barre.getLargeur()))));
				    	}
				    }
				    else if(a.getUserData().equals("Rebord") && (b.getUserData().equals("Balle") || b.getUserData().equals("BalleLaser"))){
				    	for(Balle balle : balles){
				    		if(balle.body == b){
				    			balle.rebond++;
						    	if(balle.rebond > 3 && b.getLinearVelocity().y >= 0 ){
						    		b.applyLinearImpulse(0, 0.1f, b.getPosition().x, b.getPosition().y, true);
						    	}
						    	else if(balle.rebond > 3){
						    		b.applyLinearImpulse(0, -0.1f, b.getPosition().x, b.getPosition().y, true);
						    	}	
				    		}
				    	}
				    }	
				    else if((a.getUserData().equals("Bouclier") && b.getUserData().equals("Balle")) || 
				    		(a.getUserData().equals("Balle") && b.getUserData().equals("Bouclier"))){
				    	Variables.bouclierActif = false;
				    }			    
			    }
			}

			@Override
			public void endContact(Contact contact) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
				//Ignore les collisions entre la balle et les objets
				Body a = contact.getFixtureA().getBody();
			    Body b = contact.getFixtureB().getBody();
			    if(a.getUserData() != null && b.getUserData() != null){	
			    	if(a.getUserData().equals("Brique") && b.getUserData().equals("BalleLaser")){
				    	couleurEdit = MathUtils.random(couleurMin,couleurMax);			//Choix de la couleur pour le mode Epileptique
				        Variables.briquesDetruitesAuLaser++;
				        System.out.println(" Variables.briquesDetruitesAuLaser = " +  Variables.briquesDetruitesAuLaser);
				    	contact.setEnabled(false);
				    	
				    	for(Balle balle : balles){
				    		if(balle.body == b){
				    			balle.rebond = 0;
				    			
				    			if(!balle.balleLaserContact){
				    				balle.balleLaserContact = true;
				    				balle.balleLaserTime = TimeUtils.millis();
				    			}
				    		}
				    	}
				    	
				    	for(Brique brique : briques){
				    		if(brique.body == a){
				    			brique.Destruction();
				    			if(Donnees.getSon()){
				    				son = MathUtils.random(0,15);
				    				sons.getSons().get(son).play();
				    			}

						    	//Apparition des objets
						    	spawnObjet = MathUtils.random(1,100);
						    	if(spawnObjet > 42 && spawnObjet < 60 && Variables.briquesDetruites > 2){
						    		Variables.briquesDetruites = 0;
						    		spawn = true;
						    		spawnX = a.getPosition().x;
						    		spawnY = a.getPosition().y;
						    	}
				    		}
				    	}
				    }
				    else if((a.getUserData().equals("Objet") && b.getUserData() != "Barre") ||
				    		(b.getUserData().equals("Objet") && a.getUserData() != "Barre")){
				    	contact.setEnabled(false);
				    }
				    else if((a.getUserData().equals("Laser") && b.getUserData() != "Brique") ||
				    		(b.getUserData().equals("Laser") && a.getUserData() != "Brique")){
				    	contact.setEnabled(false);
				    }
				    else if((a.getUserData().equals("Balle") || a.getUserData().equals("BalleLaser")) &&
				    		(b.getUserData().equals("Balle") || b.getUserData().equals("BalleLaser"))){
				    	contact.setEnabled(false);
				    }
			    }
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
				//Permet d'activer la destruction de l'objet touché par la barre, dans le renderer, sans faire planter l'osti de programme
				Body a = contact.getFixtureA().getBody();
			    Body b = contact.getFixtureB().getBody();
			    if(a.getUserData() != null && b.getUserData() != null){		
			    	if(a.getUserData().equals("Objet") && b.getUserData().equals("Barre") || a.getUserData().equals("Barre") && b.getUserData().equals("Objet")){
			    		for(int i = 0; i < objets.size; i++){
				    		if(objets.get(i).body == a || objets.get(i).body == b){
								objets.get(i).body.setAwake(false);
				    		}	    			
			    		}
			    	}
			    	//Destruction du laser et des briques par le laser
			    	else if(a.getUserData().equals("Laser") && b.getUserData().equals("Brique") || a.getUserData().equals("Brique") && b.getUserData().equals("Laser")){
				    	couleurEdit = MathUtils.random(couleurMin,couleurMax);
				    	
			    		for(int i = 0; i < lasers.size; i++){
				    		if(lasers.get(i).body == a || lasers.get(i).body == b){
				    			lasers.get(i).visible = false;
				    		}	    			
			    		}
			    		for(Brique brique : briques){
				    		if(brique.body == a || brique.body == b){
				    			brique.Collision();
				    			if(Donnees.getSon()){
				    				son = MathUtils.random(0,15);
				    				sons.getSons().get(son).play();
				    			}

						    	//Apparition des objets
						    	spawnObjet = MathUtils.random(1,100);
						    	if(spawnObjet > 45 && spawnObjet < 70 && brique.opacite == 0 && Variables.briquesDetruites > 2){
						    		Variables.briquesDetruites = 0;
						    		spawn = true;
						    		spawnX = brique.body.getPosition().x;
						    		spawnY = brique.body.getPosition().y;
						    	}
				    		}
				    	} 
			    	}
			    }
				
			}
			
		});
		
		nextBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				//Effacement du niveau actuel
				for(Brique brique : briques){
					world.destroyBody(brique.body);
				}
				if(briques.size > 0)
					briques.removeRange(0, briques.size-1);
				
				//Sortie de la pause
				Variables.BOX_STEP = 1/60f;
				
				//Transfert vers le niveau suivant s'il y en a encore un
				if(Variables.niveauSelectione < 25)
					Variables.niveauSelectione +=1;
				else if(Variables.niveauSelectione == 25 && Variables.groupeSelectione < Variables.NombreDeGroupes){
					Variables.niveauSelectione = 1;
					Variables.groupeSelectione += 1;
				}
				try{
					dispose();
					game.setScreen(new GameScreen(game));
				}
				catch(Exception e){
					dispose();
					game.setScreen(new MainMenuScreen(game));
				}
			}
		});
		
		replayBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques){
				world.destroyBody(brique.body);
				}
				if(briques.size > 0)
					briques.removeRange(0, briques.size-1);
				Variables.BOX_STEP = 1/60f;
				dispose();
				game.setScreen(new GameScreen(game));
			}
		});
		
		restartBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques){
				world.destroyBody(brique.body);
				}
				briques.removeRange(0, briques.size-1);
				Variables.BOX_STEP = 1/60f;
				dispose();
				game.setScreen(new GameScreen(game));
			}
		});
		
		restartBouton2.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques){
				world.destroyBody(brique.body);
				}
				briques.removeRange(0, briques.size-1);
				Variables.BOX_STEP = 1/60f;
				dispose();
				game.setScreen(new GameScreen(game));
			}
		});
		
		menuBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
		
		menuBouton2.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
		
		resumeBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				pause = false;
		       	Variables.BOX_STEP = 1/60f;
		       	imagePause.addAction(Actions.alpha(0, 0.25f));
		       	tablePause.addAction(Actions.moveTo(2 * Gdx.graphics.getWidth(),
							tablePause.getY(), 
							0.25f));
			}
		});
		
		quitBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				Gdx.app.exit();
			}
		});
		
		groupeFini.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				groupeFini.addAction(Actions.moveTo(-Gdx.graphics.getWidth(), 0));
				
				if(Donnees.getGroupe() == 4 && !Donnees.getMicrogravite()){
					Donnees.setMicrogravite(true);
					micrograviteDebloque.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - micrograviteDebloque.getWidth()/2, Gdx.graphics.getHeight()/2 - micrograviteDebloque.getHeight()/2));
					micrograviteDebloque.addAction(Actions.alpha(1, 0.5f));
				}
				else{
					groupeDebloque.addAction(Actions.sequence(Actions.moveTo(Gdx.graphics.getWidth()/2 - groupeDebloque.getWidth()/2, Gdx.graphics.getHeight()/2 - groupeDebloque.getHeight()/2)));
					groupeDebloque.addAction(Actions.sequence(Actions.alpha(1, 0.35f)));
				}
				
			}
		});
		
		groupeDebloque.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				tableFin.setVisible(true);
				groupeDebloque.addAction(Actions.sequence(Actions.alpha(0, 0.25f), Actions.moveTo(-Gdx.graphics.getWidth(), 0), Actions.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0.2f), tableFin)));
			}
		});
		
		jeuFini.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				if(Donnees.getEpileptique()){
					dispose();
					game.setScreen(new MainMenuScreen(game));
				}
				else{
					Donnees.setEpileptique(true);
					jeuFini.addAction(Actions.moveTo(-Gdx.graphics.getWidth(), 0));
					epileptiqueDebloque.addAction(Actions.moveTo(Gdx.graphics.getWidth()/2 - epileptiqueDebloque.getWidth()/2, Gdx.graphics.getHeight()/2 - epileptiqueDebloque.getHeight()/2));
					epileptiqueDebloque.addAction(Actions.alpha(1, 0.5f));
				}
			}
		});
		
		micrograviteDebloque.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				micrograviteDebloque.addAction(Actions.moveTo(-Gdx.graphics.getWidth(), 0));
				groupeDebloque.addAction(Actions.sequence(Actions.moveTo(Gdx.graphics.getWidth()/2 - groupeDebloque.getWidth()/2, Gdx.graphics.getHeight()/2 - groupeDebloque.getHeight()/2)));
				groupeDebloque.addAction(Actions.sequence(Actions.alpha(1, 0.35f)));
			}
		});
		
		epileptiqueDebloque.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				dispose();
				game.setScreen(new MainMenuScreen(game));
			}
		});
	}
	
	public void spawnBalle(float x, float y, Vector2 speed){
		Balle balle = new Balle(world, camera, x, y);
		balle.balleActive = true;
		balle.startImpulse = true;
		balles.add(balle);
		balle.body.applyLinearImpulse(speed, new Vector2(balle.body.getPosition().x, balle.body.getPosition().y), true);
		lastDropTime = TimeUtils.nanoTime();
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
		System.out.println("------------------------Game screen disposed");
		skin.dispose();
		stage.dispose();
		world.dispose();
	}
}
