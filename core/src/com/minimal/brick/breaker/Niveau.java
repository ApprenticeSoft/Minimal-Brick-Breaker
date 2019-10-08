package com.minimal.brick.breaker;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.body.Brique;
import com.minimal.brick.breaker.enums.BriqueEnum;

public class Niveau {

	private Array<Brique> briquesBox2D;
	private World world;
	private Camera camera;
	
	public Niveau(World world, Camera camera, Array<Brique> briquesBox2D){
		this.briquesBox2D = briquesBox2D;
		this.world = world;
		this.camera = camera;
	}
	
	public void Creer(int niveau, int ligne, int colonne, BriqueEnum briqueEnum){
		if(niveau == 1){
			for(int i = 0; i < (ligne); i++){
				for(int j = 0; j < colonne; j++){
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
												camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					briquesBox2D.add(brique);
					brique.body.setUserData("Brique");
					brique.dispose();
				}
			}
		}
		
		else if(niveau == 2){
			for(int i = 0; i < (ligne); i++){
				for(int j = 0; j < colonne; j++){
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					if(i%2 == 1)
						brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
								camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					else 
						brique.setPosition((j + 1)*((camera.viewportWidth - (colonne - 1)*2*brique.getWidth())/(colonne)) + (1 + j*2)*brique.getWidth(),
								camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));	
					
					briquesBox2D.add(brique);
					brique.body.setUserData("Brique");
					brique.dispose();
				}
			}
			
			for(int i = 0; i < briquesBox2D.size; i++){
	        	if(briquesBox2D.get(i).body.getPosition().x + briquesBox2D.get(i).getWidth() > camera.viewportWidth){
	        		briquesBox2D.get(i).body.setActive(false);
	        		world.destroyBody(briquesBox2D.get(i).body);
	        		briquesBox2D.removeIndex(i);
	        	}
	        }
		}
		
		else if(niveau == 3){
			for(int i = 0; i < (ligne); i++){
				for(int j = 0; j < colonne; j++){
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					if(i%2 == 1)
						brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
								camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					else 
						brique.setPosition((j + 1.5f)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (j + 1) * 2 * brique.getWidth(),
								camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));	
					
					briquesBox2D.add(brique);
					brique.body.setUserData("Brique");
					brique.dispose();
				}
			}
			
			for(int i = 0; i < briquesBox2D.size; i++){
	        	if(briquesBox2D.get(i).body.getPosition().x + briquesBox2D.get(i).getWidth() > camera.viewportWidth){
	        		briquesBox2D.get(i).body.setActive(false);
	        		world.destroyBody(briquesBox2D.get(i).body);
	        		briquesBox2D.removeIndex(i);
	        	}
	        }
		}
		
		else if(niveau == 4){
			for(int i = 0; i < (ligne); i++){
				for(int j = 0; j < colonne; j++){
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					if(j%2 == 1)
						brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
								camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					else 
						brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
								camera.viewportHeight - 2*(i + 1) * brique.getHeight() - (i + 0.5f) *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					
					briquesBox2D.add(brique);
					brique.body.setUserData("Brique");
					brique.dispose();
				}
			}
		}
		
		else if(niveau == 5){
			for(int i = 0; i < (ligne); i++){
				for(int j = 0; j < colonne; j++){
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					brique.setPosition((j + 1)*((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)) + (1 + j*2)*brique.getWidth(),
												camera.viewportHeight - (2*i + 1) * brique.getHeight() - i *((camera.viewportWidth - colonne*2*brique.getWidth())/(colonne + 1)));
					briquesBox2D.add(brique);
					brique.body.setTransform(brique.body.getPosition().x, brique.body.getPosition().y, 45*MathUtils.degreesToRadians); //Rotation !
					brique.body.setUserData("Brique");
					brique.dispose();
				}
			}
		}	
	}
}
