package com.minimal.brick.breaker.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.minimal.brick.breaker.Variables;

public class Barre extends PolygonShape{

	public float opacite, centre, ecart;
	public boolean visible;
	public int durete;
	public Body body, body2;
	public BodyDef bodyDef;
	public FixtureDef fixtureDef;
	public float width, height;
	World world;
	Camera camera;
	
	public Barre(World world, Camera camera){
		super();
		this.world = world;
		this.camera = camera;
		
		width = 1.01f*camera.viewportWidth/15;
		height = camera.viewportHeight/100;
		centre = camera.viewportWidth/2;
		ecart = width * Variables.barreNormale;
    	this.setAsBox(width , height);
        
    	bodyDef = new BodyDef();
    	bodyDef.type = BodyType.KinematicBody;
    	
		fixtureDef = new FixtureDef();
		fixtureDef.shape = this;
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1f; 
        
        bodyDef.position.set(new Vector2(centre - ecart, camera.viewportHeight/9));
        body = world.createBody(bodyDef);
        body.setUserData("Barre");
        body.createFixture(fixtureDef);
        body.setFixedRotation(true);

        bodyDef.position.set(new Vector2(centre + ecart, camera.viewportHeight/9));
        body2 = world.createBody(bodyDef);
        body2.setUserData("Barre");
        body2.createFixture(fixtureDef);
        body2.setFixedRotation(true);
        
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void draw(SpriteBatch batch, TextureRegion textureRegion){
		batch.setColor(0,0,0,0.2f);
		batch.draw(textureRegion, 
						Variables.BOX_TO_WORLD * (this.body.getPosition().x - this.getWidth()) + Gdx.graphics.getWidth()/80, 
						Variables.BOX_TO_WORLD * (this.body.getPosition().y - this.getHeight()) - Gdx.graphics.getWidth()/68, 
						Variables.BOX_TO_WORLD * (2 * this.getWidth() + this.body2.getPosition().x - this.body.getPosition().x), 
						Variables.BOX_TO_WORLD * 2 * this.getHeight());
		batch.setColor(1,1,1,1);
		batch.draw(textureRegion, 
						Variables.BOX_TO_WORLD * (this.body.getPosition().x - this.getWidth()), 
						Variables.BOX_TO_WORLD * (this.body.getPosition().y - this.getHeight()), 
						Variables.BOX_TO_WORLD * (2 * this.getWidth() + this.body2.getPosition().x - this.body.getPosition().x), 
						Variables.BOX_TO_WORLD * 2 * this.getHeight());
	}
	
	public void deplacement(){
		//Déplacement de la barre
		if (Gdx.input.isTouched()) {
			centre = Variables.WORLD_TO_BOX * Gdx.input.getX();

			//La barre ne sort pas de l'écran
			if(centre < width + ecart)
				centre = width + ecart;
			if(centre > camera.viewportWidth - width - ecart)
				centre = camera.viewportWidth - width - ecart;
		} 
		else {
			this.body.setLinearVelocity(0,0);
		}
		
		//Modification de la taille de la barre
		setEcart(Variables.ecart);
		
		//Les deux parties de la barre suivent la position X
		if(this.body.getPosition().x != centre - ecart)
			this.body.setLinearVelocity(-(this.body.getPosition().x - (centre - ecart)) * 45,0);
		else this.body.setLinearVelocity(0,0);
		
		if(this.body2.getPosition().x != centre + ecart)
			this.body2.setLinearVelocity(-(this.body2.getPosition().x - (centre + ecart)) * 45,0);
		else this.body2.setLinearVelocity(0,0);
	}
	
	public void setEcart(float nEcart){
		this.ecart = width * nEcart;
	}
	
	public float getPosition(){
		return centre;
	}
	
	public float getLargeur(){
		return width + ecart;
	}
}
