package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Hero extends Actor {

    private final float SCREEN_WIDTH = Gdx.graphics.getWidth();
    private final float SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private final float yACCELLERATION = 1;
    private final float xACCELLERATION = 1;
    private final float yMAXSPEED = 4;
    private final float xMAXSPEED = 4;
    private final float xyFRICTION = .95f;
    private final float xScale = .5f;
    private final float yScale = .5f;

    private TextureRegion region;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;

    public float xSpeed;
    public float ySpeed;

    public Hero(float xPos, float yPos, Texture texture, String actorName ){
        //init variables
        movingRight = false;
        movingLeft = false;
        movingUp = false;
        movingDown = false;

        xSpeed = 0;
        ySpeed = 0;

        setOriginX(texture.getWidth()/2f);
        setOriginY(texture.getHeight()/2f);

        System.out.println(actorName+" Created");

        region = new TextureRegion(texture,0,0,texture.getWidth(),texture.getHeight());

        setBounds(region.getRegionX(), region.getRegionY(),
                region.getRegionWidth(), region.getRegionHeight());

        //reduce scale of model
        this.setScale(xScale,yScale);

        //Set initial Position
        this.setX(xPos);
        this.setY(yPos);

        System.out.println("WIDTH:"+SCREEN_WIDTH);
        System.out.println("HEIGHT:"+SCREEN_HEIGHT);
        System.out.println("ScaleX: "+getScaleX());
        System.out.println("ScaleY: "+getScaleY());
        System.out.println("X: "+getX());
        System.out.println("Y: "+getY());
        System.out.println("Width: "+getWidth());
        System.out.println("Height: "+getHeight());
        System.out.println("OriginX: "+getOriginX());
        System.out.println("OriginY: "+getOriginY());
        System.out.println("Rotation: "+getRotation());
        /*
                List Of Key codes:
                a - z-> 29 - 54
                "0" - "9"-> 7 - 16
                BACK BUTTON - 4, MENU BUTTON - 82
                UP-19, DOWN-20, LEFT-21, RIGHT-22
                SELECT (MIDDLE) BUTTON - 23
                SPACE - 62, SHIFT - 59, ENTER - 66, BACKSPACE - 67
         */
        addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                //System.out.println(keycode);
                switch(keycode){
                    case 19:movingUp = true;
                        break;
                    case 20:movingDown = true;
                        break;
                    case 21:movingLeft = true;
                             break;
                    case 22:movingRight = true;
                            break;
                }
                /*
                System.out.println("moving Right:"+movingRight);
                System.out.println("moving Left:"+movingLeft);
                System.out.println("moving Up:"+movingUp);
                System.out.println("moving Down:"+movingDown);
                */
                return false;
            }
            @Override
            public boolean keyUp (InputEvent event, int keycode){
                switch(keycode){
                    case 19: movingUp = false;
                        break;
                    case 20: movingDown = false;
                        break;
                    case 21: movingLeft = false;
                        break;
                    case 22: movingRight = false;
                        break;
                }
                /*
                System.out.println("moving Right:"+movingRight);
                System.out.println("moving Left:"+movingLeft);
                System.out.println("moving Up:"+movingUp);
                System.out.println("moving Down:"+movingDown);
                */
                return false;
            }

        });
    }
    public void draw (Batch batch, float parentAlpha) {
        //moveBy(1,0);
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
       // batch.draw(region,getX(),getY());
        //batch.draw(region, getX(), getY(),getWidth()*getScaleX(),getHeight()*getScaleY());
        batch.draw(region, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
    @Override
    public void act(float delta) {
        super.act(delta);
        moveCharacter();
    }
    public void moveCharacter(){

        //  Y-AXIS movement
        if(movingUp){
            setRotation(180);
            if(ySpeed + yACCELLERATION <= yMAXSPEED)
                ySpeed += yACCELLERATION;
            else
                ySpeed = yMAXSPEED;
        }
        else if(movingDown){
            setRotation(0);
            if(ySpeed - yACCELLERATION >= yMAXSPEED * -1)
                ySpeed -= yACCELLERATION;
            else
                ySpeed = yMAXSPEED * -1;
        }
        else
            ySpeed *= xyFRICTION;

        //  X-AXIS movement
        if(movingLeft){
            setRotation(270);
            if(xSpeed - xACCELLERATION >= xMAXSPEED * -1)
                xSpeed -= xACCELLERATION;
            else
                xSpeed = xMAXSPEED * -1;
        }
        else if(movingRight){
            setRotation(90);
            if(xSpeed + xACCELLERATION <= xMAXSPEED)
                xSpeed += xACCELLERATION;
            else
                xSpeed = xMAXSPEED;
        }
        else
            xSpeed *= xyFRICTION;

        moveBy(xSpeed,ySpeed);
        /*
        //right limit
        if(getX()+getOriginX() > SCREEN_WIDTH){
            setX(SCREEN_WIDTH-getOriginX());
            System.out.println("RIGHT LIMIT");
        }
        //left limit
        if(getX()+getOriginX() < 0) {
            setX(0 - getOriginX());
            System.out.println("LEFT LIMIT");
        }
        //top limit
        if(getY()+getOriginY() > SCREEN_HEIGHT)
        {
            setY(SCREEN_HEIGHT-getOriginY());
            System.out.println("TOP LIMIT");
        }
        //bot limit
        if(getY()+getOriginY() < 0) {
            setY(0 - getOriginY());
            System.out.println("BOT LIMIT");
        }
        */
    }
}
