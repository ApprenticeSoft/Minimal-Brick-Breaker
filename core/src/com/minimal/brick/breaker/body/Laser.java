package com.minimal.brick.breaker.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.Variables;

public class Laser extends PolygonShape{

	public Body body;
	private BodyDef bodyDef;
	private FixtureDef fixtureDef;
	private static World world;
	private static Camera camera;
	private float  width, height;
	public boolean visible;
	
	public Laser(World world, Camera camera, float posX, float posY){
		this.world = world;
		this.camera = camera;
		
		bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody; 
        
        width = camera.viewportWidth/150;
    	height = camera.viewportWidth/40;
    	 	
    	this.setAsBox(width, height);
        
		fixtureDef = new FixtureDef();
		fixtureDef.shape = this;
        fixtureDef.density = 1.0f;  
        fixtureDef.friction = 0.0f;  
        fixtureDef.restitution = 1f; 
        
        bodyDef.position.set(posX, posY);
        body = world.createBody(bodyDef);
        body.setUserData("Laser");
        body.createFixture(fixtureDef);
		body.setLinearVelocity(0, 3*camera.viewportHeight/2);
        body.setFixedRotation(true);

		visible = true;
		
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void drawOmbre(SpriteBatch batch, TextureRegion textureRegion){
		if(this.visible)
			batch.setColor(0,0,0,0.2f);
		else
			batch.setColor(0,0,0,0);
		batch.draw(textureRegion, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().x - this.getWidth()) + Gdx.graphics.getWidth()/80, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().y - this.getHeight()) - Gdx.graphics.getWidth()/68, 
					Variables.BOX_TO_WORLD * 2 * this.getWidth(), 
					Variables.BOX_TO_WORLD * 2 * this.getHeight());
	}
	
	public void draw(SpriteBatch batch, TextureRegion textureRegion){
		if(this.visible)
			batch.setColor(1,1,1,1);
		else
			batch.setColor(1,1,1,0);
		batch.draw(textureRegion, 
					Variables.BOX_TO_WORLD * (this.body.getPosition().x - this.getWidth()), 
					Variables.BOX_TO_WORLD * (this.body.getPosition().y - this.getHeight()), 
					Variables.BOX_TO_WORLD * 2 * this.getWidth(), 
					Variables.BOX_TO_WORLD * 2 * this.getHeight());
	}
	
	public static void detruire(Array<Laser> array){
		for(int i = 0; i < array.size; i++){
        	if(!array.get(i).visible){
        		array.get(i).body.setActive(false);
        		world.destroyBody(array.get(i).body);
        		array.removeIndex(i);
        	}
        	else if(array.get(i).body.getPosition().y > camera.viewportHeight){
        		array.get(i).body.setActive(false);
        		world.destroyBody(array.get(i).body);
        		array.removeIndex(i);
        	}
        }
	}
}
