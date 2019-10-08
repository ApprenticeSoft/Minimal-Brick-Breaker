package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.QueryCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.MouseJoint;
import com.badlogic.gdx.physics.box2d.joints.MouseJointDef;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.Couleurs;
import com.minimal.brick.breaker.LevelHandler;
import com.minimal.brick.breaker.MyGdxGame;
import com.minimal.brick.breaker.Niveau;
import com.minimal.brick.breaker.Variables;
import com.minimal.brick.breaker.body.Brique;
import com.minimal.brick.breaker.enums.BriqueEnum;

public class EditeurScreen extends InputAdapter implements Screen{

	final MyGdxGame game;
	OrthographicCamera camera;
	World world; 
	Box2DDebugRenderer debugRenderer; 
    private PolygonShape boxBordure;
    private BodyDef bordureBodyDef;
    private Body groundBody, bodyHaut, bodyGauche, bodyDroite;
	private Skin skin;
	private TextureAtlas textureAtlas;
	
	private Array<Brique> briques;
	private Niveau niveau;
	private LevelHandler level;
	private TextButtonStyle textButtonStyle;
	private TextButton saveBouton, nouveauBouton, vPlusBouton, vMoinsBouton, ePlusBouton, eMoinsBouton;
	private Stage stage;
	private InputMultiplexer inputMultiplexer;
	
	//Détection du clic sur un body
	private MouseJointDef jointDef;
	private MouseJoint joint;
	private Vector3 tmp = new Vector3();
	private Vector2 tmp2 = new Vector2();
	private Couleurs couleurs;
	private BriqueEnum briqueEnum;
	private int niveauEdit, couleurEdit;
	
	public EditeurScreen(final MyGdxGame gam){
		game = gam;
		      	
      	world = new World(new Vector2(0, 0), true);
      	
      	inputMultiplexer = new InputMultiplexer();
      	
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
		bordureBodyDef.position.set(new Vector2(camera.viewportWidth/2, -camera.viewportHeight/100));  
        groundBody = world.createBody(bordureBodyDef);  
        boxBordure = new PolygonShape();  
        boxBordure.setAsBox(camera.viewportWidth/2, camera.viewportHeight/100);
        groundBody.createFixture(boxBordure, 0.0f);  
        
        bordureBodyDef.position.set(new Vector2(camera.viewportWidth/2, 101*camera.viewportHeight/100)); 
        bodyHaut = world.createBody(bordureBodyDef);
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
        
        briques = new Array<Brique>();
        niveau = new Niveau(world, camera, briques);
        
        briqueEnum = BriqueEnum.rectangleH;											//CHOIX DU TYPE DE BRIQUE
        niveau.Creer(1, 17, 11, briqueEnum);									//CRÉATION DU NIVEAU
        couleurEdit = 7;
		couleurs = new Couleurs(couleurEdit);									//CHOIX DES COULEURS
		niveauEdit = 25;														//NUMÉRO DU NIVEAU		
		
      	level = new LevelHandler(world, camera, briques);
		//level.loadLevel(1,niveauEdit);
      	
      	//for(int i = 0; i < briques.size; i++){
      		/*if(i%2 == 0){*/
      	//		briques.get(i).durete = 0;
      	//		briques.get(i).opacite = 0;
      		/*}
      		else
      			briques.get(i).durete = 4;*/
      	//}
      	
		/*
		for(int i = 1; i < 26; i++){								//Modification de plusieurs fichiers
		for(BriqueBox2D brique : briques){
					world.destroyBody(brique.body);
				}
			try{
				briques.removeRange(0, briques.size-1);
			}catch(Exception e){System.out.println("Pas de briques");}
			
			level.loadLevel(4,i);
			level.saveLevel("Niveau " + i, BriqueEnum.rectangleV);
			System.out.println("Niveau " + i + " enregistré");
			try{
				briques.removeRange(0, briques.size-1);
			}catch(Exception e){System.out.println("Pas de briques... Encore !");}
		}
		*/
      	System.out.println("briques.size = " + briques.size);
      	
        debugRenderer = new Box2DDebugRenderer();  
        
        stage = new Stage();
        textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.getDrawable("BoutonPatch");
		textButtonStyle.down = skin.getDrawable("BoutonCheckedPatch");
		textButtonStyle.font = game.assets.get("font1.ttf", BitmapFont.class);
		textButtonStyle.pressedOffsetY = -2;
		
		saveBouton = new TextButton("SAVE", textButtonStyle);
		saveBouton.setWidth(Gdx.graphics.getWidth()/6);
		saveBouton.setHeight(Gdx.graphics.getHeight()/18);
		saveBouton.setX(0);
		saveBouton.setY(0);
		
		nouveauBouton = new TextButton("NEW", textButtonStyle);
		nouveauBouton.setWidth(saveBouton.getWidth());
		nouveauBouton.setHeight(saveBouton.getHeight());
		nouveauBouton.setX(saveBouton.getX() + nouveauBouton.getWidth());
		nouveauBouton.setY(0);
		
		vPlusBouton = new TextButton("V +", textButtonStyle);
		vPlusBouton.setWidth(saveBouton.getWidth());
		vPlusBouton.setHeight(saveBouton.getHeight());
		vPlusBouton.setX(nouveauBouton.getX() + vPlusBouton.getWidth());
		vPlusBouton.setY(0);
		
		vMoinsBouton = new TextButton("V -", textButtonStyle);
		vMoinsBouton.setWidth(saveBouton.getWidth());
		vMoinsBouton.setHeight(saveBouton.getHeight());
		vMoinsBouton.setX(vPlusBouton.getX() + vMoinsBouton.getWidth());
		vMoinsBouton.setY(0);
		
		ePlusBouton = new TextButton("E +", textButtonStyle);
		ePlusBouton.setWidth(saveBouton.getWidth());
		ePlusBouton.setHeight(saveBouton.getHeight());
		ePlusBouton.setX(vMoinsBouton.getX() + ePlusBouton.getWidth());
		ePlusBouton.setY(0);
		
		eMoinsBouton = new TextButton("E -", textButtonStyle);
		eMoinsBouton.setWidth(saveBouton.getWidth());
		eMoinsBouton.setHeight(saveBouton.getHeight());
		eMoinsBouton.setX(ePlusBouton.getX() + eMoinsBouton.getWidth());
		eMoinsBouton.setY(0);
		
		stage.addActor(saveBouton);
		stage.addActor(nouveauBouton); 	
		stage.addActor(vPlusBouton);
		stage.addActor(vMoinsBouton); 
		stage.addActor(ePlusBouton);
		stage.addActor(eMoinsBouton); 
	}
	
	@Override
	public void render(float delta) {
		couleurs.setGroupe(couleurEdit);

		Gdx.gl.glClearColor(couleurs.getCouleur5().r, couleurs.getCouleur5().g, couleurs.getCouleur5().b, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Affichage des graphismes
		game.batch.begin();
		for(int i = 0; i < briques.size; i++){
			if(briques.get(i).durete == 0) 
				game.batch.setColor(couleurs.getCouleur0());
			else if(briques.get(i).durete == 1) 
				game.batch.setColor(couleurs.getCouleur1());
			else if(briques.get(i).durete == 2) 
				game.batch.setColor(couleurs.getCouleur2());
			else if(briques.get(i).durete == 3)
				game.batch.setColor(couleurs.getCouleur3());
			else if(briques.get(i).durete == 4)
				game.batch.setColor(couleurs.getCouleur4());
			game.batch.draw(textureAtlas.findRegion("Barre"), 
					Variables.BOX_TO_WORLD * (briques.get(i).body.getPosition().x - briques.get(i).getWidth()), 
					Variables.BOX_TO_WORLD * (briques.get(i).body.getPosition().y - briques.get(i).getHeight()),
					Variables.BOX_TO_WORLD * briques.get(i).getWidth(),
					Variables.BOX_TO_WORLD * briques.get(i).getHeight(),
					Variables.BOX_TO_WORLD * 2 * briques.get(i).getWidth(), 
					Variables.BOX_TO_WORLD * 2 * briques.get(i).getHeight(),
					1,
					1,
					briques.get(i).body.getAngle()*MathUtils.radiansToDegrees);
		}
		game.batch.end();					
		
		stage.act();
		stage.draw();
		
		debugRenderer.render(world, camera.combined);
        world.step(Variables.BOX_STEP, Variables.BOX_VELOCITY_ITERATIONS, Variables.BOX_POSITION_ITERATIONS);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(this);
		Gdx.input.setInputProcessor(inputMultiplexer);
	
		// mouse joint
		jointDef = new MouseJointDef();
		jointDef.bodyA = groundBody;
		jointDef.collideConnected = true;
		jointDef.maxForce = 500;			
		
		//Boutons de l'éditeur
		saveBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				level.saveLevel("Niveau " + niveauEdit, briqueEnum);
			}
		});
		
		nouveauBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				couleurEdit = MathUtils.random(1,18);
				System.out.println("couleurEdit = " + couleurEdit);
				
				for(Brique brique : briques){
					world.destroyBody(brique.body);
				}
				briques.removeRange(0, briques.size-1);
				
				//Niveau Random
				int style = MathUtils.random(1,3);
				BriqueEnum styleEnum = BriqueEnum.rectangleH;
				switch(style){
					case 1:
						styleEnum = BriqueEnum.rectangleH;
						niveau.Creer(MathUtils.random(1,4), MathUtils.random(7,15), MathUtils.random(7,12), styleEnum);	
						break;
					case 2:
						styleEnum = BriqueEnum.rectangleV;
						niveau.Creer(MathUtils.random(1,4), MathUtils.random(5,8), MathUtils.random(11,24), styleEnum);	
						break;
					case 3:
						styleEnum = BriqueEnum.carre;
						niveau.Creer(MathUtils.random(1,4), MathUtils.random(7,10), MathUtils.random(7,12), styleEnum);	
						break;
					default:
						styleEnum = BriqueEnum.rectangleH;
						niveau.Creer(MathUtils.random(1,4), MathUtils.random(6,18), MathUtils.random(7,12), styleEnum);	
						break;
				}
				//Retrait des briques hors écran
				for(Brique brique : briques){
					brique.durete = MathUtils.random(0,4);
					
					if(brique.body.getPosition().x + brique.getWidth() > camera.viewportWidth){
						brique.opacite = 0;
						brique.durete = 0;
					}
					
					if(brique.body.getPosition().y < 45*camera.viewportHeight/100){
						brique.opacite = 0;
						brique.durete = 0;
					}
				}
				//Retrait des briques trop basses
				for(int i = 0; i < briques.size; i++){				
		        	if(briques.get(i).body.getPosition().x + briques.get(i).getWidth() > camera.viewportWidth ||
		        		briques.get(i).body.getPosition().y < 45*camera.viewportHeight/100){
		        		briques.get(i).opacite = 0;
		        		briques.get(i).durete = 0;
		        		briques.get(i).body.setActive(false);
		        		world.destroyBody(briques.get(i).body);
		        		briques.removeIndex(i);
		        	}
		        }
			}
		});
		
		vPlusBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques)
					brique.body.setTransform(brique.body.getPosition().x, brique.body.getPosition().y + 0.1f, brique.body.getAngle());
			}
		});
		
		vMoinsBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques)
					brique.body.setTransform(brique.body.getPosition().x, brique.body.getPosition().y - 0.1f, brique.body.getAngle());
			}
		});
		
		ePlusBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques)
					brique.body.setTransform(brique.body.getPosition().x, 1.05f * brique.body.getPosition().y - 0.8f, brique.body.getAngle());
			}
		});
		
		eMoinsBouton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				for(Brique brique : briques)
					brique.body.setTransform(brique.body.getPosition().x, 0.95f * brique.body.getPosition().y + 0.8f, brique.body.getAngle());
			}
		});
	}
	
	/*********************************************************************************************************************/
	private QueryCallback queryCallback = new QueryCallback() {
		@Override
		public boolean reportFixture(Fixture fixture) {
			if(!fixture.testPoint(tmp.x, tmp.y)){
				System.out.println("AAAAAAAA");
				return true;
			}
			if(fixture.testPoint(tmp.x, tmp.y)){
				System.out.println("DDDDDDD");
				
				for(Brique brique : briques){
		    		if(brique.body == fixture.getBody()){
		    			brique.Edition();
						System.out.println("durete = " + brique.durete);
						System.out.println("opacite = " + brique.opacite);
		    		}
		    	}		
			}

			jointDef.bodyB = fixture.getBody();
			jointDef.target.set(tmp.x, tmp.y);
			joint = (MouseJoint) world.createJoint(jointDef);
			return false;
		}

	};
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		camera.unproject(tmp.set(screenX, screenY, 0));
		world.QueryAABB(queryCallback, tmp.x, tmp.y, tmp.x, tmp.y);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		/*
		if(joint == null)
			return false;

		camera.unproject(tmp.set(screenX, screenY, 0));
		joint.setTarget(tmp2.set(tmp.x, tmp.y));
		return true;
		*/
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(joint == null)
			return false;

		world.destroyJoint(joint);
		joint = null;
		return true;
	}
	/*********************************************************************************************************************/

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
		// TODO Auto-generated method stub
		
	}

}
