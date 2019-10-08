package com.minimal.brick.breaker.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader.FreeTypeFontLoaderParameter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.minimal.brick.breaker.MyGdxGame;

public class LoadingScreen implements Screen{
	final MyGdxGame game;
	OrthographicCamera camera;
	private Texture textureLogo;
	private Image imageLogo;
	private Stage stage;
	
	public LoadingScreen(final MyGdxGame gam) {
		game = gam;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		textureLogo = new Texture(Gdx.files.internal("Images/Logo.png"), true);
		textureLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		imageLogo = new Image(textureLogo);
		imageLogo.setWidth(9 * Gdx.graphics.getWidth()/10);
		imageLogo.setHeight(imageLogo.getWidth()*textureLogo.getHeight()/textureLogo.getWidth());
		imageLogo.setX(Gdx.graphics.getWidth()/2 - imageLogo.getWidth()/2);
		imageLogo.setY(Gdx.graphics.getHeight()/2 - imageLogo.getHeight()/2);
		stage = new Stage();
		
		//Chargement du son
		game.assets.load("Sons/Collision 1.ogg", Sound.class);
		game.assets.load("Sons/Collision 2.ogg", Sound.class);
		game.assets.load("Sons/Collision 3.ogg", Sound.class);
		game.assets.load("Sons/Collision 4.ogg", Sound.class);
		game.assets.load("Sons/Collision 5.ogg", Sound.class);
		game.assets.load("Sons/Collision 6.ogg", Sound.class);
		game.assets.load("Sons/Collision 7.ogg", Sound.class);
		game.assets.load("Sons/Collision 8.ogg", Sound.class);
		game.assets.load("Sons/Collision 9.ogg", Sound.class);
		game.assets.load("Sons/Collision 10.ogg", Sound.class);
		game.assets.load("Sons/Collision 11.ogg", Sound.class);
		game.assets.load("Sons/Collision 12.ogg", Sound.class);
		game.assets.load("Sons/Collision 13.ogg", Sound.class);
		game.assets.load("Sons/Collision 14.ogg", Sound.class);
		game.assets.load("Sons/Collision 15.ogg", Sound.class);
		game.assets.load("Sons/Collision 16.ogg", Sound.class);
		game.assets.load("Sons/Laser.ogg", Sound.class);
		game.assets.load("Sons/NiveauFini.ogg", Sound.class);
		game.assets.load("Sons/Perdu.ogg", Sound.class);
		
		//Chargement du TextureAtlas
		game.assets.load("Images.pack", TextureAtlas.class);
		
		FileHandleResolver resolver = new InternalFileHandleResolver();
		game.assets.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		game.assets.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
		
		FreeTypeFontLoaderParameter size1Params = new FreeTypeFontLoaderParameter();
		size1Params.fontFileName = "Fonts/calibri.ttf";			
		size1Params.fontParameters.genMipMaps = true;					
		size1Params.fontParameters.minFilter = TextureFilter.Linear;
		size1Params.fontParameters.magFilter = TextureFilter.Linear;						
		size1Params.fontParameters.size = Gdx.graphics.getWidth()/18;
		game.assets.load("font1.ttf", BitmapFont.class, size1Params);
		
		FreeTypeFontLoaderParameter size2Params = new FreeTypeFontLoaderParameter();
		size2Params.fontFileName = "Fonts/calibri.ttf";			
		size2Params.fontParameters.genMipMaps = true;					
		size2Params.fontParameters.minFilter = TextureFilter.Linear;
		size2Params.fontParameters.magFilter = TextureFilter.Linear;						
		size2Params.fontParameters.size = Gdx.graphics.getWidth()/22;
		game.assets.load("fontOption.ttf", BitmapFont.class, size2Params);
		
		FreeTypeFontLoaderParameter size3Params = new FreeTypeFontLoaderParameter();
		size3Params.fontFileName = "Fonts/New_Cicle_Gordita.ttf";			
		size3Params.fontParameters.genMipMaps = true;					
		size3Params.fontParameters.minFilter = TextureFilter.Linear;
		size3Params.fontParameters.magFilter = TextureFilter.Linear;						
		size3Params.fontParameters.size = Gdx.graphics.getWidth()/11;
		game.assets.load("fontTitre.ttf", BitmapFont.class, size3Params);
		
		FreeTypeFontLoaderParameter size4Params = new FreeTypeFontLoaderParameter();
		size4Params.fontFileName = "Fonts/calibri.ttf";			
		size4Params.fontParameters.genMipMaps = true;					
		size4Params.fontParameters.minFilter = TextureFilter.Linear;
		size4Params.fontParameters.magFilter = TextureFilter.Linear;						
		size4Params.fontParameters.size = Gdx.graphics.getWidth()/23;
		game.assets.load("fontBoutonNotation.ttf", BitmapFont.class, size4Params);
		
		FreeTypeFontLoaderParameter size5Params = new FreeTypeFontLoaderParameter();
		size5Params.fontFileName = "Fonts/calibrib.ttf";			
		size5Params.fontParameters.genMipMaps = true;					
		size5Params.fontParameters.minFilter = TextureFilter.Linear;
		size5Params.fontParameters.magFilter = TextureFilter.Linear;						
		size5Params.fontParameters.size = Gdx.graphics.getWidth()/18;
		game.assets.load("fontTextNotation.ttf", BitmapFont.class, size5Params);
	
		FreeTypeFontLoaderParameter size7Params = new FreeTypeFontLoaderParameter();
		size7Params.fontFileName = "Fonts/calibrib.ttf";			
		size7Params.fontParameters.genMipMaps = true;					
		size7Params.fontParameters.minFilter = TextureFilter.Linear;
		size7Params.fontParameters.magFilter = TextureFilter.Linear;						
		size7Params.fontParameters.size = Gdx.graphics.getWidth()/11;
		game.assets.load("fontTextTableJeu.ttf", BitmapFont.class, size7Params);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
		game.batch.setProjectionMatrix(camera.combined);

        stage.act();
        stage.draw();
        
		if(game.assets.update()){
			dispose();
			((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenuScreen(game));	  
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		stage.addActor(imageLogo);
		
		imageLogo.addAction(Actions.sequence(Actions.alpha(0)
                ,Actions.fadeIn(0.75f),Actions.delay(1.5f)));
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		textureLogo.dispose();
		stage.dispose();
	}
}

