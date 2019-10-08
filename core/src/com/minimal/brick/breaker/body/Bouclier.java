package com.minimal.brick.breaker.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.minimal.brick.breaker.Variables;

public class Bouclier extends PolygonShape{

	World world;
	Camera camera;
	private BodyDef bodyDef;
	public Body body;
	public FixtureDef fixtureDef;
	public boolean actif;
	
	public Bouclier(World world, Camera camera){
		super();
		this.world = world;
		this.camera = camera;
		
		actif = Variables.bouclierActif;
		
		this.setAsBox(camera.viewportWidth/2, camera.viewportHeight/100);
		
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(camera.viewportWidth/2, 7*camera.viewportHeight/100));
		body = world.createBody(bodyDef);
		body.setUserData("Bouclier");
		body.createFixture(this, 0.0f);	
		
	}
	
	public void draw(SpriteBatch batch, TextureRegion textureRegion){
		if(Variables.bouclierActif){
			batch.setColor(0,0,0,0.2f);
			batch.draw(textureRegion, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().x - camera.viewportWidth/2) + Gdx.graphics.getWidth()/80, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().y - camera.viewportHeight/100) - Gdx.graphics.getWidth()/68, 
					Variables.BOX_TO_WORLD * 2 * camera.viewportWidth/2, 
					Variables.BOX_TO_WORLD * 2 * camera.viewportHeight/100);
			batch.setColor(1,1,1,1);
			batch.draw(textureRegion, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().x - camera.viewportWidth/2), 
					Variables.BOX_TO_WORLD * (this.body.getPosition().y - camera.viewportHeight/100), 
					Variables.BOX_TO_WORLD * 2 * camera.viewportWidth/2, 
					Variables.BOX_TO_WORLD * 2 * camera.viewportHeight/100);
		}
		/*
		else if(!Variables.bouclierActif)
			batch.setColor(1,1,1,0);
		batch.draw(textureRegion, 
						Variables.BOX_TO_WORLD * (this.body.getPosition().x - camera.viewportWidth/2), 
						Variables.BOX_TO_WORLD * (this.body.getPosition().y - camera.viewportHeight/100), 
						Variables.BOX_TO_WORLD * 2 * camera.viewportWidth/2, 
						Variables.BOX_TO_WORLD * 2 * camera.viewportHeight/100);
		*/
	}
	
	public void actif(){
		if(Variables.bouclierActif)
			this.body.setActive(true);
		else if(!Variables.bouclierActif)
			this.body.setActive(false);
	}
}
