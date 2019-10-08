package com.minimal.brick.breaker.body;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.Couleurs;
import com.minimal.brick.breaker.Variables;

public class BriqueRonde extends CircleShape{

	public float opacite;
	public boolean visible;
	public int durete;
	public Body body;
	public BodyDef bodyDef;
	public float posX, posY, rayon;
	static World world;
	Camera camera;
	Couleurs couleurs = new Couleurs(Variables.groupeSelectione);
	
	public BriqueRonde(World world, Camera camera, float posX, float posY){
		super();
		this.world = world;
		this.camera = camera;
		this.posX = posX;
		this.posY = posY;

		rayon = camera.viewportWidth/24;
		
		bodyDef = new BodyDef();
        this.setRadius(rayon);	
		
		opacite = 1;
		visible = true;
		durete = 1;	
	}
	
	public float getWidth(){
		return rayon;
	}
	
	public float getHeight(){
		return rayon;
	}
	
	public float getX(){
		return posX;
	}
	
	public float getY(){
		return posY;
	}

	public void setX( float X){
		posX = X;
	}

	public void setY( float Y){
		posY = Y;
	}
	
	public void setPosition(float X, float Y){
		posX = X;
		posY = Y;
		bodyDef.position.set(new Vector2(posX, posY));
		body = world.createBody(bodyDef);
		body.createFixture(this, 0.0f);
	}
	
	public void Collision(){
    	if(durete > 1)
			durete--;
		else {
			durete--;
			opacite = 0;
			Variables.briquesDetruites++;
			visible = false;
		}
	}
	
	public void Destruction(){
		durete = 0;
		opacite = 0;
		Variables.briquesDetruites++;
		visible = false;
	}
	
	public void Edition(){
    	if(durete > 1){
			durete--;
			opacite = 1;
    	}
		else if(durete == 1){
			durete = 0;	
			opacite = 0;
		}
		else {
			durete = 4;	
			opacite = 1;
		}
	}
	
	public void draw(SpriteBatch batch, TextureRegion textureRegion){
		if(durete == 0) 
			batch.setColor(couleurs.getCouleur0());
		else if(durete == 1) 
			batch.setColor(couleurs.getCouleur1());
		else if(durete == 2) 
			batch.setColor(couleurs.getCouleur2());
		else if(durete == 3)
			batch.setColor(couleurs.getCouleur3());
		else if(durete == 4)
			batch.setColor(couleurs.getCouleur4());
		batch.draw(textureRegion, 
						Variables.BOX_TO_WORLD * (this.getX() - this.getWidth()), 
						Variables.BOX_TO_WORLD * (this.getY() - this.getHeight()), 
						Variables.BOX_TO_WORLD * 2 * this.getWidth(), 
						Variables.BOX_TO_WORLD * 2 * this.getHeight());
	}
	
	public static void detruire(Array<BriqueRonde> array){
		for(int i = 0; i < array.size; i++){
        	if(!array.get(i).visible){
        		array.get(i).body.setActive(false);
        		world.destroyBody(array.get(i).body);
        		array.removeIndex(i);
        	}
        }
	}
}
