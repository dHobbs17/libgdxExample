package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.Hero;
import com.mygdx.game.Shooter;
import com.mygdx.game.assets.AssetDescriptors;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.bullet;
import com.mygdx.game.scenes.Hud;

public class GameScreen implements Screen {

    private final Shooter game;
    private Stage stage;

    private Image bg1;
    private Image bg2;
    private Hero myHero;

    private Hud hud;

    public GameScreen(final Shooter game) {
        System.out.println("WIDTH:"+Shooter.SCREEN_WIDTH);
        System.out.println("HEIGHT:"+Shooter.SCREEN_HEIGHT);
        this.game = game;
        this.hud = new Hud(game.batch);
        loadAssets();
        createActors();
        setStage();
    }
    private void setStage() {
        StretchViewport viewpoint = new StretchViewport(Shooter.SCREEN_WIDTH, Shooter.SCREEN_HEIGHT);
        stage = new Stage(viewpoint);
        Gdx.input.setInputProcessor(stage);

        stage.addActor(bg1);
        stage.addActor(bg2);
        stage.addActor(myHero);
        stage.setKeyboardFocus(myHero);

        stage.addListener(new InputListener() {
            public boolean keyDown(InputEvent event, int keycode) {
                switch(keycode) {
                    case 62:bullet myBullet = new bullet((Texture)game.assets.get("bullet.png"),myHero.getRotation());
                            myBullet.setPosition(myHero.getX()+myHero.getWidth()/2,myHero.getY()+myHero.getHeight()/2);
                            stage.addActor(myBullet);
                }
                return false;
            }
        });
    }
    private void createActors(){
        Texture myTexture = game.assets.get("actor1.png");
        Texture bg1Texture = game.assets.get("floor.png");
        Texture bg2Texture = game.assets.get("grid.png");

        //create backgrounds
        bg1 = new Image(bg1Texture);
        bg2 = new Image(bg2Texture);

        // creates the actors
        myHero = new Hero(Shooter.SCREEN_WIDTH /2,Shooter.SCREEN_HEIGHT/2,myTexture,"Hellios");
    }
    private void loadAssets(){
        game.assets.load(AssetDescriptors.hero);
        game.assets.load(AssetDescriptors.floor);
        game.assets.load(AssetDescriptors.grid);
        game.assets.load(AssetDescriptors.bullet);

        while(!game.assets.update()) {
            float progress = game.assets.getProgress();
            System.out.println("Loading ... " + progress * 100 + "%");
        }
        System.out.println("Loading complete!");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to glClearColor are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        hud.stage.draw();

        //stage.getCamera().position.x += myHero.xSpeed * 1.5;
        //stage.getCamera().position.y += myHero.ySpeed * 1.5;
        stage.getCamera().translate(1,1,0);
        /*
        if(Gdx.input.isTouched()){
            System.out.println("Touched");
            stage.getCamera().position.x += 100 * delta;
            //game.camera.position.x += 100 * delta;
        }
        */
    }
    @Override
    public void dispose() {
        stage.dispose();
        game.assets.dispose();
    }
    @Override
    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}