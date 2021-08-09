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
import com.mygdx.Actors.HP;
import com.mygdx.Actors.Shadow;

import javax.xml.soap.Text;


public class MainGameScreen extends BaseScreen implements ApplicationListener
{


    public MainGameScreen(MainGame game)
    {
        super(game);
        batch = new SpriteBatch();
        background = new Texture("Background.png");

        textureHero = new Texture("characters/standingLeft.png");
        spriteHero = new Sprite(textureHero);
        hpBarTexture = new Texture("hpbar/hp8.png");
        heart = new Image(new Texture("heart.png"));
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
    private ActorEnemy actorEnemy;
    private Shadow shadowHero;
    private HP hpBar;
    private Texture hpBarTexture;
    private Texture background;

    private Image heart;

    private Boolean isUp;
    private Boolean isDown;
    private Boolean isRight;
    private Boolean isLeft;
    private Boolean isLeftUp;
    private Boolean isLeftDown;
    private Boolean isRightUp;
    private Boolean isRightDown;

    private boolean gameover = false;

    private boolean needDelay5 = false;
    private boolean needDelayGameover = false;

    public boolean isGameover() {
        return gameover;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

    @Override
    public void show() {
        stage = new Stage();


        actorHero = new ActorHero(textureHero);
        actorHero.setPosition(W / 2, H / 4);
        actorHero.setSpriteHeroPosition(W / 2, H / 4);
        shadowHero = new Shadow(actorHero, "shadow.png");
        shadowHero.setPosition(W/2,H/2);

        actorEnemy = new ActorEnemy(actorHero);
        actorEnemy.setPosition(100,100);
        actorEnemy.setShadow(shadowHero);

        actorHero.setEnemy(actorEnemy);


        stage.addActor(actorEnemy);

        hpBar = new HP(hpBarTexture);
        hpBar.setPosition(W - 850, H - 200);

        heart.setPosition(hpBar.getX() - 140, hpBar.getY() - 48);
        stage.addActor(hpBar);

//        move = new MoveToAction();
//        move.setActor(actorHero);

        // Arrows

        ImageButton arrow_u_btn = createBtn(280, 500, "arrow_u");
        ImageButton arrow_d_btn = createBtn(280, 60, "arrow_d");
        ImageButton arrow_l_btn = createBtn(60, 280, "arrow_l");
        ImageButton arrow_r_btn = createBtn(500, 280, "arrow_r");
        ImageButton arrow_ru_btn = createBtn(500, 500, "arrow_ru");
        ImageButton arrow_rd_btn = createBtn(500, 100, "arrow_rd");
        ImageButton arrow_lu_btn = createBtn(100, 500, "arrow_lu");
        ImageButton arrow_ld_btn = createBtn(100, 100, "arrow_ld");
        ImageButton jump_btn = createBtn(W - 400, H / 2  - 200, "jump");
        ImageButton attack_btn = createBtn(W - 700, H / 2 - 200, "attack");


        Gdx.input.setInputProcessor(stage);
        stage.addActor(shadowHero);
        stage.addActor(actorHero);
//        actorHero.rotateBy(180);
//        actorHero.act(100);

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
        stage.addActor(heart);
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
                    actorHero.setDelay02Seconds(0f);
                    actorHero.setNeedDelay02(true);
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
                actorHero.setRight(true);
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                actorHero.setMainGameScreenStop(false);
                isRight=false;
                actorHero.setRight(false);
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

    private float delay5Seconds = 0f;
    private float delayGameoverSeconds = 0f;
    private boolean delay5SecondsNulled = false;
    private boolean delayGameoverSecondsNulled = false;

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        delay5Seconds += Gdx.graphics.getDeltaTime();
        delayGameoverSeconds += Gdx.graphics.getDeltaTime();

        if(!actorHero.isAlive()){
            if(!delayGameoverSecondsNulled){
                delayGameoverSeconds = 0f;
                delayGameoverSecondsNulled = true;
            }
            needDelayGameover = true;
        }
        if(needDelayGameover && delayGameoverSeconds > 2.5f){
            gameover = true;
        }

        if(needDelay5 && delay5Seconds > 5f){
            actorEnemy.remove();
            needDelay5 = false;
            stage.addActor(actorEnemy);
            actorEnemy.setHp(7);
            actorEnemy.setAlive(true);
            actorEnemy.setPosition(W,H/4);
            actorEnemy.setDamage(3);
        }

        if(!actorEnemy.isAlive()){
            if(!delay5SecondsNulled) {
                delay5Seconds = 0f;
                delay5SecondsNulled = true;
            }
            needDelay5 = true;
        }

        if(actorHero.getLives() == 1){
            heart.remove();
        }

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

                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY());

                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
            }else{
                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 3, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 3, actorHero.getY());
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

                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY());

                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
            }else{
                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 3, spriteHero.getY());
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 3, actorHero.getY());
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

                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 15, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 15, actorHero.getY() + actorHero.HERO_STEP);

                actorHero.setHeroLookRight(true);
                actorHero.setHeroLookLeft(false);
            }else{
                spriteHero.setPosition(spriteHero.getX() + actorHero.HERO_STEP + 15, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() + actorHero.HERO_STEP + 15, actorHero.getY() + actorHero.HERO_STEP);
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

                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 15, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 15, actorHero.getY() + actorHero.HERO_STEP);

                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
            }else{
                spriteHero.setPosition(spriteHero.getX() - actorHero.HERO_STEP - 15, spriteHero.getY() + actorHero.HERO_STEP);
                actorHero.setPosition(actorHero.getX() - actorHero.HERO_STEP - 15, actorHero.getY() + actorHero.HERO_STEP);
                actorHero.setHeroLookRight(false);
                actorHero.setHeroLookLeft(true);
                textureHero = new Texture("characters/jumpingLeft.png");
                TextureRegion textureRegionHero = new TextureRegion(textureHero);
                actorHero.setTextureRegionHero(textureRegionHero);
            }
        }

        if(actorHero.getHP() < 0) {
            hpBar.setHpBarTexture(new Texture("hpbar/hp0.png"));
        }else {
            hpBar.setHpBarTexture(new Texture("hpbar/hp" + actorHero.getHP() + ".png"));
        }
        hpBar.setHpBarTextureRegion(new TextureRegion(hpBar.getHpBarTexture()));
        hpBar.setHpBarSprite(new Sprite(hpBar.getHpBarTextureRegion()));

        stage.act(delta);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, W, H);
        stage.getBatch().end();

//        stage.getBatch().begin();
//        stage.getBatch().draw(hpBarTexture, 440, 460, hpBarTexture.getWidth(), hpBarTexture.getHeight());
//        stage.getBatch().end();

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



