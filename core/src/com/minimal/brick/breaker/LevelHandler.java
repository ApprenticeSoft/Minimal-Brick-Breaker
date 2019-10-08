package com.minimal.brick.breaker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.minimal.brick.breaker.body.Brique;
import com.minimal.brick.breaker.enums.BriqueEnum;

public class LevelHandler {
	
	private static Array<Brique> briquesBox2D;
	private static World world;
	private static Camera camera;
	
	public LevelHandler(World world, Camera camera, Array<Brique> briques){
		this.briquesBox2D = briques;
		this.world = world;
		this.camera = camera;
	}
	
	public static void saveLevel(String fileName, BriqueEnum briqueEnum) {
		FileHandle file = Gdx.files.local("Niveaux/" + fileName + ".txt");
		file.writeString("", false);
		
		for(Brique brique : briquesBox2D){
			if(brique.opacite == 1)
				file.writeString("" + brique.body.getPosition().x/camera.viewportWidth + "," + brique.body.getPosition().y/camera.viewportHeight + "," + brique.body.getAngle() + "," + brique.durete + "," + briqueEnum + "\n", true);	
    	}
	}
	
	public static void loadLevel(int groupe, int niveau) {
		System.out.println("loading level");
		FileHandle file = Gdx.files.internal("Niveaux/Groupe " + groupe + "/Niveau " + niveau + ".txt");
		String[] fileContent = file.readString().split("\n");
		for(int i = 0; i < fileContent.length; i++) {
			String[] blockData = fileContent[i].split(",");
			if (blockData.length == 5) {
				float x;
				float y;
				float angle;
				int durete;
				BriqueEnum briqueEnum;
				try {
					x = Float.valueOf(blockData[0]);
					y = Float.valueOf(blockData[1]);
					angle = Float.valueOf(blockData[2]);
					durete = Integer.valueOf(blockData[3]);
					briqueEnum = BriqueEnum.valueOf(blockData[4]); 
					
					Brique brique = new Brique(world, camera, 0, 0, briqueEnum);
					brique.setPosition(x*camera.viewportWidth, y*camera.viewportHeight);
					brique.body.setTransform(x*camera.viewportWidth, y*camera.viewportHeight, angle);
					brique.durete = durete;	
					briquesBox2D.add(brique);
					brique.body.setUserData("Brique");
					brique.dispose();
					
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);				// malformed block data. ignore this block
				}
			}
		}
	}
}

