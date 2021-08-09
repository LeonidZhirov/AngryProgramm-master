package com.mygdx.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ActorEnemy extends Actor
{
    private Texture textureEnemy;
    private TextureRegion textureRegionEnemy;
    private Sprite spriteEnemy;
    private boolean alive;
    private ActorHero actorHero;
    private final float STEP = 50f;
    private int damage = 1;

    private Shadow shadow;

    private int hp = 7;

    private Texture walkSheet;
    private TextureRegion[] walkFrames; // #5
    public TextureRegion currentFrame; // #7
    private float stateTime; // #8
    private static final int FRAME_COLS = 3; // #1
    private static final int FRAME_ROWS = 1; // #2
    private Animation walkAnimation; // #3
    private SpriteBatch spriteBatch;
    private boolean firstPressed = true;
    private float heroPositionX;
    private float heroPositionY;

    private boolean needDelay4 = false;
    private boolean needDelay02 = false;
    private boolean needDelayHp = false;
    private boolean needAttack2or3 = false;
    private boolean needSitDelay = false;
    float delaySeconds4 = 0f;

    private boolean isEnemyLookRight = true;
    private boolean isEnemyLookLeft = false;
    private boolean isRunning = false;
    private boolean sitted = false;
    private Texture textureEnemyAttack;
    private TextureRegion[] attackFrames;
    private Animation attackAnimation;
    private boolean needAttack2 = false;
    private boolean needAttack3 = false;
    private boolean canAttackX = false;
    private boolean canAttackY = false;
    private boolean firstPressedAttack = true;
    private float timeSeconds = 0f;
    private float delay02Seconds = 0f;
    private int comboCount = 1;
    private int xCountAttack2n3 = 0;
    private float ATTACK2N3_STEP = 100f;

    public void setShadow(Shadow shadow) {
        this.shadow = shadow;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setNeedDelayHp(boolean needDelayHp) {
        this.needDelayHp = needDelayHp;
    }

    public void setDelayHpSeconds(float delayHpSeconds) {
        this.delayHpSeconds = delayHpSeconds;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setTimeSeconds(float timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public float getTimeSeconds() {
        return timeSeconds;
    }

    public void setFirstPressedAttack(boolean firstPressedAttack) {
        this.firstPressedAttack = firstPressedAttack;
    }

    public boolean getFirstPressedAttack() {
        return firstPressedAttack;
    }

    private void isEnemyRun(Texture walkSheet) {
        this.walkSheet = walkSheet;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;

        for (int i = 0; i < FRAME_ROWS; i++)
        {
            for (int j = 0; j < FRAME_COLS; j++)
            {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation(0.005f, walkFrames); // #11
        spriteBatch = new SpriteBatch(); // #12
        stateTime = 0f; // #13

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // #14
        stateTime += Gdx.graphics.getDeltaTime() * 4; // #15
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true); // #16

        textureRegionEnemy = new TextureRegion(currentFrame);
        spriteEnemy = new Sprite(textureRegionEnemy);

        spriteBatch.begin();
        spriteBatch.end();
    }

    private void isEnemyRun() {
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / FRAME_COLS, walkSheet.getHeight() / FRAME_ROWS);
        walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;

        for (int i = 0; i < FRAME_ROWS; i++)
        {
            for (int j = 0; j < FRAME_COLS; j++)
            {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation(0.005f, walkFrames); // #11
        spriteBatch = new SpriteBatch(); // #12
        stateTime = 0f; // #13

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // #14
        stateTime += Gdx.graphics.getDeltaTime() * 4; // #15
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true); // #16

        textureRegionEnemy = new TextureRegion(currentFrame);
        spriteEnemy = new Sprite(textureRegionEnemy);
        comboCount = 1;
        spriteBatch.begin();
        spriteBatch.end();
    }

    private void isEnemyAttack(Texture textureEnemyAttack) {
        this.textureEnemyAttack = textureEnemyAttack;
        TextureRegion[][] tmp = TextureRegion.split(textureEnemyAttack, textureEnemyAttack.getWidth() / FRAME_COLS, textureEnemyAttack.getHeight() / FRAME_ROWS);
        attackFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;

        for (int i = 0; i < FRAME_ROWS; i++)
        {
            for (int j = 0; j < FRAME_COLS; j++)
            {
                attackFrames[index++] = tmp[i][j];
            }
        }

        attackAnimation = new Animation(0.05f, attackFrames); // #11
        spriteBatch = new SpriteBatch(); // #12
        stateTime = 0f; // #13

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT); // #14
        stateTime += Gdx.graphics.getDeltaTime() * 5; // #15
        currentFrame = (TextureRegion) attackAnimation.getKeyFrame(stateTime, false); // #16

        textureRegionEnemy = new TextureRegion(currentFrame);
        spriteEnemy = new Sprite(textureRegionEnemy);
    }

    public ActorEnemy(ActorHero actorHero) {
        textureEnemy = new Texture("characters/standingRight.png");
        textureRegionEnemy = new TextureRegion(textureEnemy);
        this.alive=true;
        this.actorHero = actorHero;
        spriteEnemy = new Sprite(textureRegionEnemy);
        setSize(spriteEnemy.getRegionWidth(), spriteEnemy.getRegionHeight());
    }

    float sittingTimer = 0f;
    float delayHpSeconds = 0f;

    public void act(float delta) {
        super.act(delta);

        delaySeconds4 += Gdx.graphics.getDeltaTime();
        delayHpSeconds += Gdx.graphics.getDeltaTime();
        if(needDelayHp && delayHpSeconds > 0.6f){
            needDelayHp = false;
        }


        if(alive && actorHero.isAlive() && !needDelayHp) {
            if (Math.abs(this.getX() - actorHero.getX()) > 300) {
                canAttackX = false;
                if (this.getX() < actorHero.getX()) {
                    isRunning = true;
                    walkSheet = new Texture("characters/runningRight.png"); // #9

                    isEnemyRun(walkSheet);

                    this.setPosition(this.getX() + STEP, this.getY());
                    spriteEnemy.setPosition(this.getX() + STEP, this.getY());

                    this.isEnemyLookRight = true;
                    this.isEnemyLookLeft = false;
                } else if (this.getX() > actorHero.getX()) {
                    isRunning = true;
                    walkSheet = new Texture("characters/runningLeft.png"); // #9

                    isEnemyRun(walkSheet);

                    this.setPosition(this.getX() - STEP, this.getY());
                    spriteEnemy.setPosition(this.getX() - STEP, this.getY());

                    this.isEnemyLookRight = false;
                    this.isEnemyLookLeft = true;
                }

            } else {
                canAttackX = true;
            }
            if (Math.abs(this.getY() - shadow.getY()) > 50) {
                canAttackY = false;
                if (this.getY() < actorHero.getY()) {
                    if (firstPressed) {
                        walkSheet = new Texture("characters/runningRight.png"); // #9

                        isEnemyRun(walkSheet);

                        this.setPosition(this.getX(), this.getY() + STEP / 2);
                        spriteEnemy.setPosition(this.getX(), this.getY() + STEP / 2);

                        this.firstPressed = false;
                        this.isEnemyLookLeft = false;
                        this.isEnemyLookRight = true;
                    } else {

                        isEnemyRun();

//                    if (this.getY() <= actorHero.getY() - 20f)
//                    {
                        this.setPosition(this.getX(), this.getY() + STEP / 2);
                        spriteEnemy.setPosition(this.getX(), this.getY() + STEP / 2);
                    }
                }
                if (this.getY() > shadow.getY()) {
                    if (firstPressed) {
                        walkSheet = new Texture("characters/runningRight.png"); // #9

                        isEnemyRun(walkSheet);

                        this.setPosition(this.getX(), this.getY() - STEP - 5f);
                        spriteEnemy.setPosition(this.getX(), this.getY() - STEP - 5f);

                        this.firstPressed = false;
                        this.isEnemyLookLeft = false;
                        this.isEnemyLookRight = true;
                    } else {
                        isEnemyRun();

                        this.setPosition(this.getX(), this.getY() - STEP - 5f);
                        spriteEnemy.setPosition(this.getX(), this.getY() - STEP - 5f);
                    }
                }
            } else {
                canAttackY = true;
            }

            delay02Seconds += Gdx.graphics.getDeltaTime();

            if (delay02Seconds > 0.2f) {
                needDelay02 = false;
            }

            if (canAttackX && canAttackY && !actorHero.isNeedJump()) {
                isRunning = false;
                if (needAttack2or3 && comboCount != 1) {
                    if (delaySeconds4 >= 2 && comboCount == 2) {
                        needAttack2 = true;
                        comboCount = 3;
                        needDelay02 = true;
                        delay02Seconds = 0f;

                    } else if (delaySeconds4 >= 2 && comboCount == 3) {
                        needAttack3 = true;
                        comboCount = 1;
                        needAttack2or3 = false;
                        needDelay02 = true;
                        delay02Seconds = 0f;

                    }
                } else if (delaySeconds4 >= 2) {
                    if (isEnemyLookRight) {
                        textureEnemyAttack = new Texture("characters/attackingRight.png"); // #9
                    } else {
                        textureEnemyAttack = new Texture("characters/attackingLeft.png");
                    }
                    isEnemyAttack(textureEnemyAttack);

                    comboCount = 2;
                    delaySeconds4 = 0f;
                    needAttack2or3 = true;
                    needDelay02 = true;
                    delay02Seconds = 0f;


                    if (actorHero.isHeroLookRight()) {
                        actorHero.setTextureHero(new Texture("characters/scaryRight.png"));
                    } else {
                        actorHero.setTextureHero(new Texture("characters/scaryLeft.png"));
                    }
                    actorHero.setNeedDelay02(true);
                    actorHero.setDelay02Seconds(0f);
                    actorHero.setTextureRegionHero(new TextureRegion(actorHero.getTextureHero()));
                    actorHero.setSpriteHero(new Sprite(actorHero.getTextureRegionHero()));
                }
                timeSeconds = 0f;
            }

            if (delaySeconds4 == 0) {
                if (actorHero.getHP() <= 3 && actorHero.getLives() == 1) {
                    actorHero.setAlive(false);
                    actorHero.setHp(actorHero.getHP() - damage);
                    if(actorHero.isHeroLookRight()) {
                        actorHero.setTextureHero(new Texture("characters/lyingRight.png"));
                    }else{
                        actorHero.setTextureHero(new Texture("characters/lyingLeft.png"));
                    }
                    actorHero.setTextureRegionHero(new TextureRegion(actorHero.getTextureHero()));
                    actorHero.setSpriteHero(new Sprite(actorHero.getTextureRegionHero()));
                } else {
                    actorHero.setHp(actorHero.getHP() - damage);
                }
            }


            if (this.getX() < actorHero.getX()) {
                isEnemyLookRight = true;
                isEnemyLookLeft = false;
            } else if (this.getX() > actorHero.getX()) {
                isEnemyLookLeft = true;
                isEnemyLookRight = false;
            }


            if (!isRunning && !needDelay02) {
                if (isEnemyLookRight) {
                    this.textureEnemy = new Texture("characters/defaultRight.png");
                } else {
                    this.textureEnemy = new Texture("characters/defaultLeft.png");
                }
                this.textureRegionEnemy = new TextureRegion(this.textureEnemy);
                this.spriteEnemy = new Sprite(this.textureRegionEnemy);
            }

            if (needAttack2) {
                if (xCountAttack2n3 < 7) {
                    if (isEnemyLookRight) {
                        textureEnemy = new Texture("characters/strangeRight1.png");
                        textureRegionEnemy = new TextureRegion(textureEnemy);
                        spriteEnemy = new Sprite(textureRegionEnemy);
                        spriteEnemy.setPosition(spriteEnemy.getX() + ATTACK2N3_STEP, spriteEnemy.getY());
                        this.setPosition(this.getX() + ATTACK2N3_STEP, this.getY());
                    } else {
                        textureEnemy = new Texture("characters/strangeLeft1.png");
                        textureRegionEnemy = new TextureRegion(textureEnemy);
                        spriteEnemy = new Sprite(textureRegionEnemy);
                        spriteEnemy.setPosition(spriteEnemy.getX() - ATTACK2N3_STEP, spriteEnemy.getY());
                        this.setPosition(this.getX() - ATTACK2N3_STEP, this.getY());
                    }
                    xCountAttack2n3 += 7;
                }
                if (xCountAttack2n3 == 7) {
                    needAttack2 = false;
                    xCountAttack2n3 = 0;
                }
            }

            if (needAttack3) {
                boolean isLooking = isEnemyLookRight;
                if (xCountAttack2n3 < 7) {
                    if (isEnemyLookLeft) {
                        textureEnemy = new Texture("characters/strangeRight1.png");
                        textureRegionEnemy = new TextureRegion(textureEnemy);
                        spriteEnemy = new Sprite(textureRegionEnemy);
                        spriteEnemy.setPosition(spriteEnemy.getX() + ATTACK2N3_STEP, spriteEnemy.getY());
                        this.setPosition(this.getX() + ATTACK2N3_STEP, this.getY());

                    } else {
                        textureEnemy = new Texture("characters/strangeLeft1.png");
                        textureRegionEnemy = new TextureRegion(textureEnemy);
                        spriteEnemy = new Sprite(textureRegionEnemy);
                        spriteEnemy.setPosition(spriteEnemy.getX() - ATTACK2N3_STEP, spriteEnemy.getY());
                        this.setPosition(this.getX() - ATTACK2N3_STEP, this.getY());
                    }
                    xCountAttack2n3 += 7;
                }
                if (xCountAttack2n3 == 7) {
                    needAttack3 = false;
                    xCountAttack2n3 = 0;
                    isEnemyLookRight = isLooking;
                    isEnemyLookLeft = !isLooking;
                }
            }
        }else if(needDelayHp) {
            if (isEnemyLookRight) {
                this.textureEnemy = new Texture("characters/scaryRight.png");
            } else {
                this.textureEnemy = new Texture("characters/scaryLeft.png");
            }
            this.textureRegionEnemy = new TextureRegion(this.textureEnemy);
            this.spriteEnemy = new Sprite(this.textureRegionEnemy);
        }else{
            if (isEnemyLookRight) {
                this.textureEnemy = new Texture("characters/defaultRight.png");
            } else {
                this.textureEnemy = new Texture("characters/defaultLeft.png");
            }
            this.textureRegionEnemy = new TextureRegion(this.textureEnemy);
            this.spriteEnemy = new Sprite(this.textureRegionEnemy);
        }
        sittingTimer += Gdx.graphics.getDeltaTime();


        if(sittingTimer > 0.3f && needSitDelay){
            needSitDelay = false;
            sitted = true;
        }

        if(hp == 0){
            alive = false;
            if(!sitted){
                if(isEnemyLookRight) {
                    textureEnemy = new Texture("characters/sittingRight.png");
                }else{
                    textureEnemy = new Texture("characters/sittingLeft.png");
                }
                if(!needSitDelay) {
                    sittingTimer = 0f;
                }
                needSitDelay = true;
            }else{
                if(isEnemyLookRight) {
                    textureEnemy = new Texture("characters/lyingRight.png");
                }else{
                    textureEnemy = new Texture("characters/lyingLeft.png");
                }
            }
            textureRegionEnemy = new TextureRegion(textureEnemy);
            spriteEnemy = new Sprite(textureRegionEnemy);
        }

    }

    public void draw(Batch batch, float parentAlpha) { batch.draw(textureRegionEnemy, getX(), getY()); }

}
