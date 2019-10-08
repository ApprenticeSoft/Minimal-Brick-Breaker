package com.minimal.brick.breaker.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.minimal.brick.breaker.Donnees;
import com.minimal.brick.breaker.Variables;

public class Balle extends CircleShape{

	public Body body;
	public BodyDef bodyDef;
	public float posX, posY, rayon;
	public static World world;
	Camera camera;
	public boolean balleActive, startImpulse, balleLaserContact;
    private Vector2 vectorSpeed;
    public float maxSpeed, vitesseJeu;
    public int initY, rebond;
    public long balleLaserTime;
	
	public Balle(World world, Camera camera, float posX, float posY){
		super();
		this.world = world;
		this.camera = camera;
		this.posX = posX;
		this.posY = posY;
		rebond = 0;
		balleActive = false;
		startImpulse = false;
		balleLaserContact = false;
		rayon = camera.viewportWidth/50;
		
		switch (Donnees.getVitesse()){
			case 1:	vitesseJeu = Variables.vitesseJeuMin;
					break;
			case 2:	vitesseJeu = Variables.vitesseJeuNormale;
					break;
			case 3:	vitesseJeu = Variables.vitesseJeuMax;
					break;
			default:vitesseJeu = Variables.vitesseJeuNormale;
					break;
		}
		
		maxSpeed = Variables.vitesseBalle * vitesseJeu * camera.viewportHeight;
		
		bodyDef = new BodyDef();
        this.setRadius(rayon);	
        
		bodyDef.position.set(new Vector2(posX, posY));
        bodyDef.type = BodyType.DynamicBody; 
		body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();  
        fixtureDef.shape = this;  
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1;  
        body.createFixture(fixtureDef); 
        body.setUserData("Balle");
        
	}
	
	public void active(){
		//Limitation de la vitesse de la balle	
		maxSpeed = Variables.vitesseBalle * vitesseJeu * camera.viewportHeight;	
		vectorSpeed = body.getLinearVelocity();		
		body.setLinearVelocity(vectorSpeed.clamp(maxSpeed, maxSpeed));
		
		//Déplacement de la balle	
		if(startImpulse && !balleActive && !Gdx.input.isTouched()){
			balleActive = true;
			initY = MathUtils.random(-3,3);
			body.applyLinearImpulse(initY, 10, body.getPosition().x, body.getPosition().y, true);
		}

		//La balle ne sort pas de l'écran
		if(body.getPosition().x - rayon < 0)
			body.setTransform(rayon, body.getPosition().y, 0);
		if(body.getPosition().x + rayon > camera.viewportWidth)
			body.setTransform(camera.viewportWidth - rayon, body.getPosition().y, 0);
		if(body.getPosition().y + rayon > camera.viewportHeight)
			body.setTransform(body.getPosition().x, camera.viewportHeight - rayon, 0);
		
		if (Gdx.input.isTouched()) {
			startImpulse = true;
			
			if(!balleActive){
				if(body.getPosition().x != Variables.WORLD_TO_BOX * Gdx.input.getX())
					body.setLinearVelocity(-(body.getPosition().x - Variables.WORLD_TO_BOX * Gdx.input.getX()) * 50,0);
				else body.setLinearVelocity(0,0);
			}
		} 
		else {
			if(!balleActive)
				body.setLinearVelocity(0,0);
		}
		
		//La balle laser ne dure que 3,6 secondes
		if (balleLaserContact && TimeUtils.millis() - balleLaserTime > 3600)
			this.body.setUserData("Balle");
	}
	
	public void drawOmbre(SpriteBatch batch, TextureRegion textureRegion){
		batch.setColor(0, 0, 0, 0.2f);
		batch.draw(textureRegion, 
				Variables.BOX_TO_WORLD*(this.body.getPosition().x - this.rayon) + Gdx.graphics.getWidth()/80, 
				Variables.BOX_TO_WORLD*(this.body.getPosition().y - this.rayon) - Gdx.graphics.getWidth()/68, 
				Variables.BOX_TO_WORLD * 2 * this.rayon, 
				Variables.BOX_TO_WORLD * 2 * this.rayon);
		
	}
	
	public void draw(SpriteBatch batch, TextureRegion textureRegion){
		if(this.body.getUserData() == "BalleLaser")
			batch.setColor(1, 0, 0, 1);
		else
			batch.setColor(1, 1, 1, 1);
		batch.draw(textureRegion, 
				Variables.BOX_TO_WORLD*(this.body.getPosition().x - this.rayon), 
				Variables.BOX_TO_WORLD*(this.body.getPosition().y - this.rayon), 
				Variables.BOX_TO_WORLD * 2 * this.rayon, 
				Variables.BOX_TO_WORLD * 2 * this.rayon);
		
	}
	
	public void setVitesse(float vitesseBalles){
		maxSpeed = vitesseBalles * vitesseJeu * camera.viewportHeight;;
	}
	
	public static void detruire(Array<Balle> array){
		for(int i = 0; i < array.size; i++){
        	if(array.get(i).body.getPosition().y < -2*array.get(i).getRadius()){
        		array.get(i).body.setActive(false);
        		world.destroyBody(array.get(i).body);
        		array.removeIndex(i);
        	}
        }
	}
}
