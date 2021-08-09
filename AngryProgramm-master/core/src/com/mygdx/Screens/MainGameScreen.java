package com.mygdx.Screens;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Actors.ActorEnemy;
import com.mygdx.Actors.ActorHero;

import javax.xml.soap.Text;


public class MainGameScreen extends BaseScreen implements ApplicationListener
{
    public MainGameScreen(MainGame game)
    {
        super(game);
        batch = new SpriteBatch();

        textureHero = new Texture("characters/standingLeft.png");
        spriteHero = new Sprite(textureHero);
        isUp=false;
        isDown=false;
        isRight = false;
        isLeft = false;
        isRightUp = false;
        isRightDown = false;
        isLeftUp = false;
        isLeftDown = false;
//        spriteHero.setPosition(w/2 - spriteHero.getWidth()/2, h/2 - spriteHero.getHeight()/2);

    }

    private ImageButton createBtn(float x, float y, String name){
        Texture arrow_texture = new Texture("buttons/" + name + ".png");
        TextureRegion arrow_TextureRegion = new TextureRegion(arrow_texture);
        TextureRegionDrawable arrow_TexRegionDrawable = new TextureRegionDrawable(arrow_TextureRegion);
        ImageButton arrow_btn = new ImageButton(arrow_TexRegionDrawable);
        arrow_btn.setPosition(x,y);
        return arrow_btn;
    }

    float W = Gdx.graphics.getWidth();
    float H = Gdx.graphics.getHeight();
    private SpriteBatch batch;
    private Stage stage;
    private ActorHero actorHero;
    private Texture textureHero;
    private Sprite spriteHero;
    private MoveToAction move;
    private ActorEnemy actorEnemy;


    private Boolean isUp;
    private Boolean isDown;
    private Boolean isRight;
    private Boolean isLeft;
    private Boolean isLeftUp;
    private Boolean isLeftDown;
    private Boolean isRightUp;
    private Boolean isRightDown;

    @Override
    public void show() {
        stage = new Stage();


        actorHero = new ActorHero(textureHero);
        actorHero.setPosition(W / 2, H / 2);
        actorHero.setSpriteHeroPosition(W / 2, H / 2);

//        actorEnemy = new ActorEnemy(actorHero);
//        actorEnemy.setPosition(100,100);
//        stage.addActor(actorEnemy);

//        move = new MoveToAction();
//        move.setActor(actorHero);

        // Arrows

        ImageButton arrow_u_btn = createBtn(W / 6, H - 350, "arrow_u");
        ImageButton arrow_d_btn = createBtn(W / 6, H - 460, "arrow_d");
        ImageButton arrow_l_btn = createBtn(W / 6 - 55, H - 405, "arrow_l");
        ImageButton arrow_r_btn = createBtn(W / 6 + 55, H - 405, "arrow_r");
        ImageButton arrow_ru_btn = createBtn(W / 6 + 55, H - 350, "arrow_ru");
        ImageButton arrow_rd_btn = createBtn(W / 6 + 55, H - 450, "arrow_rd");
        ImageButton arrow_lu_btn = createBtn(W / 6 - 45, H - 350, "arrow_lu");
        ImageButton arrow_ld_btn = createBtn(W / 6 - 45, H - 450, "arrow_ld");
        ImageButton jump_btn = createBtn(W - 145, H - 405, "jump");
        ImageButton attack_btn = createBtn(W - 85, H - 405, "attack");

        // stage.addActor(arrows)
        stage.addActor(arrow_u_btn);
        stage.addActor(arrow_l_btn);
        stage.addActor(arrow_r_btn);
        stage.addActor(arrow_d_btn);
        stage.addActor(arrow_ru_btn);
        stage.addActor(arrow_lu_btn);
        stage.addActor(arrow_rd_btn);
        stage.addActor(arrow_ld_btn);
        stage.addActor(jump_btn);
        stage.addActor(attack_btn);

        Gdx.input.setInputProcessor(stage);
        stage.addActor(actorHero);
//        actorHero.rotateBy(180);
//        actorHero.act(100);

        jump_btn.addListener(new ClickListener(){
           @Override
           public void clicked(InputEvent e, float x, float y){
               actorHero.isHeroJump(true);
           }
        });

        attack_btn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent e, float x, float y){
                if(actorHero.isFirstPressedAttack() && !actorHero.isNeedAttack2() && !actorHero.isNeedAttack3() && !actorHero.isNeedJump()) {
                    if (actorHero.isHeroLookRight()) {
                        actorHero.setTextureHeroAttack(new Texture("characters/attackingRight.png")); // #9
                    }else{
                        actorHero.setTextureHeroAttack(new Texture("characters/attackingLeft.png"));
                    }
                    actorHero.isHeroAttack(actorHero.getTextureHeroAttack());
                    actorHero.setTimeSeconds(0f);
                    actorHero.setFirstPressedAttack(false);
                    actorHero.setComboCount(2);
                    actorHero.setNeedDelay05(true);
                    actorHero.setDelaySeconds(0f);
                }else{
                    if(actorHero.getTimeSeconds() < 5 && actorHero.getComboCount() == 2) {
                        actorHero.setComboCount(3);
                        actorHero.setNeedAttack2(true);
                    }
                    else if(actorHero.getTimeSeconds() < 5 && actorHero.getComboCount() == 3) {
                        actorHero.setComboCount(1);
                        actorHero.setNeedAttack3(true);
                        actorHero.setNeedDelay05(true);
                        actorHero.setDelaySeconds(0f);
                    }
                    else if(!actorHero.isNeedDelay05() || actorHero.isNeedDelay05() && actorHero.getDelaySeconds() > 0.5){
                        if (actorHero.isHeroLookRight()) {
                            actorHero.setTextureHeroAttack(new Texture("characters/attackingRight.png")); // #9
                        }else{
                            actorHero.setTextureHeroAttack(new Texture("characters/attackingLeft.png"));
                        }
                        actorHero.setComboCount(2);
                        actorHero.isHeroAttack(actorHero.getTextureHeroAttack());
                        actorHero.setNeedDelay05(false);
                        actorHero.setDelay02Seconds(0);
                        actorHero.setNeedDelay02(true);
                    }
                    actorHero.setTimeSeconds(0);
                }
            }
        });

        arrow_u_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
            {
                actorHero.setMainGameScreenStop(true);
                isUp=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isUp=false;
            }
        });
        arrow_d_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isDown=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isDown=false;
            }
        });
        arrow_r_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isRight=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isRight=false;
            }
        });
        arrow_l_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isLeft=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isLeft=false;
            }
        });
        arrow_lu_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isLeftUp=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isLeftUp=false;
            }
        });
        arrow_ru_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isRightUp=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isRightUp=false;
            }
        });
        arrow_ld_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isLeftDown=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isLeftDown=false;
            }
        });
        arrow_rd_btn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(true);
                isRightDown=true;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isRightDown=false;
            }
        });

    }

    @Override
    public void hide() {
        stage.dispose();

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if(isUp)
        {
            if(!actorHero.isNeedJump()) {
                if (actorHero.isFirstPressed()) {
                    Texture walkSheet = new Texture("characters/runningRight.png"); // #9
                    actorHero.isHeroRun(walkSheet);
                    spriteHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
                    actorHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
                    actorHero.setFirstPressed(false);
                    actorHero.setHeroLookLeft(false);
                    actorHero.setHeroLookRight(true);
                } else {
                    actorHero.isHeroRun();
                    spriteHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
                    actorHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
                }
            }else{
                spriteHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX(), actorHero.getY() + actorHero.HERO_STEP);
            }
        }
        if(isDown)
        {
            if(!actorHero.isNeedJump()) {
                if (actorHero.isFirstPressed()) {
                    Texture walkSheet = new Texture("characters/runningRight.png"); // #9
                    actorHero.isHeroRun(walkSheet);
                    spriteHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
                    actorHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
                    actorHero.setFirstPressed(false);
                    actorHero.setHeroLookLeft(false);
                    actorHero.setHeroLookRight(true);
                } else {
                    actorHero.isHeroRun();
                    spriteHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
                    actorHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
                }
            }else{
                spriteHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX(), actorHero.getY() - actorHero.HERO_STEP);
            }
        }
        if(isRight) {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningRight.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 5, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 5, actorHero.getY());

                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
            }else{
                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 5, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 5, actorHero.getY());
                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
                textureHero = new Texture("characters/jumpingRight.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }
        if(isLeft)
        {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningLeft.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 5, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 5, actorHero.getY());

                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
            }else{
                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 5, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 5, actorHero.getY());
                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
                textureHero = new Texture("characters/jumpingLeft.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }
        if(isRightDown)
        {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningRight.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY() - actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY() - actorHero.HERO_STEP);

                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
            }else{
                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY() - actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY() - actorHero.HERO_STEP);
                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
                textureHero = new Texture("characters/jumpingRight.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }
        if(isLeftDown)
        {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningLeft.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY() - actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY() - actorHero.HERO_STEP);

                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
            }else{
                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY() - actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY() - actorHero.HERO_STEP);
                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
                textureHero = new Texture("characters/jumpingLeft.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }
        if(isRightUp)
        {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningRight.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY() + actorHero.HERO_STEP);

                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
            }else{
                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY() + actorHero.HERO_STEP);
                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
                textureHero = new Texture("characters/jumpingRight.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }
        if(isLeftUp)
        {
            if(!actorHero.isNeedJump()) {
                Texture walkSheet = new Texture("characters/runningLeft.png"); // #9

                actorHero.isHeroRun(walkSheet);

                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY() + actorHero.HERO_STEP);

                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
            }else{
                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY() + actorHero.HERO_STEP);
                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
                textureHero = new Texture("characters/jumpingLeft.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }

        stage.act(delta);
        stage.draw();
    }


    @Override
    public void create()
    {}

    @Override
    public void render() {

    }

    @Override
    public void dispose()
    {
        batch.dispose();
    }

}



