package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class bullet extends Actor {

    public static  final int SPEED = 25;

    private TextureRegion region;
    public boolean remove = false;

    public bullet(Texture texture,float rotation) {
      //  this.x = x;
        //this.y = y;
        this.setRotation(rotation);
        setOriginX(texture.getWidth()/2f);
        setOriginY(texture.getHeight()/2f);
        region = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());

        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    @Override
    public void act(float delta) {
        switch((int)getRotation()){
            case 0:moveBy(0,-SPEED);
                break;
            case 90:moveBy(SPEED,0);
                break;
            case 180:moveBy(0,SPEED);
                break;
            case 270:moveBy(-SPEED,0);
                break;
        }
    }
}
