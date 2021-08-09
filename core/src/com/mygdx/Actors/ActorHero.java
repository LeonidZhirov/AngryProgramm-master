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

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import sun.font.TextLabel;


public class ActorHero extends Actor {

    public SpriteBatch spriteBatch;
    private Texture textureHero;
    private TextureRegion textureRegionHero;
    private HP hpBar;
    private Texture hpBarTexture;
    private int hp = 8;
    private int lives = 2;

    private Texture textureHeroAttack;
    private Sprite spriteHero;
    private ActorEnemy enemy;

    private final float STEP = 50f;
    private static final int FRAME_COLS = 3; // #1
    private static final int FRAME_ROWS = 1; // #2
    private int yCountJump = 0;
    private int xCountAttack2n3 = 0;
    private int comboCount = 1;

    private Animation walkAnimation; // #3
    private Animation attackAnimation;
    private TextureRegion[] attackFrames; // #5

    private Texture walkSheet; // #4
    private TextureRegion[] walkFrames; // #5
    public TextureRegion currentFrame; // #7
    public float stateTime; // #8

    private boolean isHeroLookRight = true;
    private boolean isHeroLookLeft = false;
    private boolean isRight = false;
    private boolean firstPressed = true;
    private boolean firstPressedAttack = true;
    private boolean needJump = false;
    private boolean jumpUp = true;
    private boolean jumpDown = false;
    private boolean alive;
    private boolean MainGameScreenStop = false;
    private boolean needAttack2 = false;
    private boolean needAttack3 = false;


    private boolean needDelay05 = false;
    private boolean needDelay02 = false;

    public final float HERO_STEP = 50;

    private final float JUMP_STEP_1 = 25f;
    private final float JUMP_STEP_2 = 12.5f;
    private final float JUMP_STEP_3 = 6f;
    private final float JUMP_STEP_4 = 3.5f;
    private final float ATTACK2N3_STEP = 100f;

    public void setMainGameScreenStop(boolean mainGameScreenStop) {
        MainGameScreenStop = mainGameScreenStop;
    }

    public int getLives() {
        return lives;
    }

    public int getHP() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Texture getTextureHero() {
        return textureHero;
    }

    public TextureRegion getTextureRegionHero() {
        return textureRegionHero;
    }

    public boolean isMainGameScreenStop() {
        return MainGameScreenStop;
    }

    public void setTextureHeroAttack(Texture textureHeroAttack) {
        this.textureHeroAttack = textureHeroAttack;
    }

    public Texture getTextureHeroAttack() {
        return textureHeroAttack;
    }

    public void setFirstPressedAttack(boolean firstPressedAttack) {
        this.firstPressedAttack = firstPressedAttack;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public boolean isRight() {
        return isRight;
    }

    public boolean isHeroLookRight() {
        return isHeroLookRight;
    }

    public boolean isHeroLookLeft() {
        return isHeroLookLeft;
    }

    public boolean isFirstPressedAttack() {
        return firstPressedAttack;
    }

    public int getXCountAttack2n3() {
        return xCountAttack2n3;
    }

    public void setXCountAttack2n3(int xCountAttack2n3) {
        this.xCountAttack2n3 = xCountAttack2n3;
    }

    public int getComboCount() {
        return comboCount;
    }

    public void setComboCount(int comboCount) {
        this.comboCount = comboCount;
    }

    public boolean isNeedAttack2() {
        return needAttack2;
    }

    public void setNeedAttack2(boolean needAttack2) {
        this.needAttack2 = needAttack2;
    }

    public boolean isNeedAttack3() {
        return needAttack3;
    }

    public void setNeedAttack3(boolean needAttack3) {
        this.needAttack3 = needAttack3;
    }

    public boolean isNeedDelay05() {
        return needDelay05;
    }

    public void setNeedDelay05(boolean needDelay05) {
        this.needDelay05 = needDelay05;
    }

    public boolean isNeedDelay02() {
        return needDelay02;
    }

    public void setNeedDelay02(boolean needDelay02) {
        this.needDelay02 = needDelay02;
    }

    public float getATTACK2N3_STEP() {
        return ATTACK2N3_STEP;
    }

    public float getTimeSeconds() {
        return timeSeconds;
    }

    public void setTimeSeconds(float timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public float getDelaySeconds() {
        return delay05Seconds;
    }

    public void setDelaySeconds(float delaySeconds) {
        this.delay05Seconds = delaySeconds;
    }

    public float getDelay02Seconds() {
        return delay02Seconds;
    }

    public void setDelay02Seconds(float delay02Seconds) {
        this.delay02Seconds = delay02Seconds;
    }

    public float getJUMP_STEP_1() {
        return JUMP_STEP_1;
    }

    public float getJUMP_STEP_2() {
        return JUMP_STEP_2;
    }

    public float getJUMP_STEP_3() {
        return JUMP_STEP_3;
    }

    public float getJUMP_STEP_4() {
        return JUMP_STEP_4;
    }

    public float getSTEP() {
        return STEP;
    }

    public void setTextureHero(Texture textureHero) {
        this.textureHero = textureHero;
    }

    public void setSpriteHero(Sprite spriteHero) {
        this.spriteHero = spriteHero;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setTextureRegionHero(TextureRegion textureRegionHero) {
        this.textureRegionHero = textureRegionHero;
    }

    public boolean isNeedJump() {
        return needJump;
    }

    public boolean isFirstPressed() {
        return firstPressed;
    }

    public void setFirstPressed(boolean firstPressed) {
        this.firstPressed = firstPressed;
    }

    public void setSpriteHeroPosition(float x, float y){ this.spriteHero.setPosition(x, y); }

    public void setHeroLookRight(boolean heroLookRight) {
        isHeroLookRight = heroLookRight;
    }

    public void setHeroLookLeft(boolean heroLookLeft) {
        isHeroLookLeft = heroLookLeft;
    }

    public void isHeroJump(boolean needJump) {
        this.needJump = needJump;
    }

    public void setEnemy(ActorEnemy enemy) {
        this.enemy = enemy;
    }

    public ActorHero(Texture textureHero) {
        this.textureHero = textureHero;
        textureRegionHero = new TextureRegion(this.textureHero);
        this.alive=true;
        spriteHero = new Sprite(textureRegionHero);
        setSize(spriteHero.getRegionWidth(), spriteHero.getRegionHeight());
        hpBarTexture = new Texture("hpbar/hp8.png");
        hpBar = new HP(hpBarTexture);
    }


    public void isHeroRun(Texture walkSheet) {
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

        textureRegionHero = new TextureRegion(currentFrame);
        spriteHero = new Sprite(textureRegionHero);

        spriteBatch.begin();
        spriteBatch.end();
    }

    public void isHeroRun() {
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

        textureRegionHero = new TextureRegion(currentFrame);
        spriteHero = new Sprite(textureRegionHero);

        spriteBatch.begin();
        spriteBatch.end();
    }


    public void isHeroAttack(Texture textureHeroAttack) {
        this.textureHeroAttack = textureHeroAttack;
        TextureRegion[][] tmp = TextureRegion.split(textureHeroAttack, textureHeroAttack.getWidth() / FRAME_COLS, textureHeroAttack.getHeight() / FRAME_ROWS);
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

        textureRegionHero = new TextureRegion(currentFrame);
        spriteHero = new Sprite(textureRegionHero);
        if(Math.abs(enemy.getX() - this.getX()) < 300 && Math.abs(enemy.getY() - this.getY()) <= 50) {
            if (enemy.isAlive()) {
                enemy.setHp(enemy.getHp() - 1);
                enemy.setNeedDelayHp(true);
                enemy.setDelayHpSeconds(0f);
            }
        }
    }



    private float timeSeconds = 0f;
    private float delay05Seconds = 0f;
    private float delay02Seconds = 0f;

    public void act(float delta)
    {
        super.act(delta);


        timeSeconds += Gdx.graphics.getDeltaTime();
        delay05Seconds += Gdx.graphics.getDeltaTime();
        delay02Seconds += Gdx.graphics.getDeltaTime();

        if(delay02Seconds > 0.2f){
            needDelay02 = false;
        }

        if(alive) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R) && !needAttack2 && !needAttack3 && !needJump) {
                if (firstPressedAttack) {
                    if (isHeroLookRight) {
                        textureHeroAttack = new Texture("characters/attackingRight.png"); // #9
                    } else {
                        textureHeroAttack = new Texture("characters/attackingLeft.png");
                    }
                    isHeroAttack(textureHeroAttack);
                    timeSeconds = 0f;
                    firstPressedAttack = false;
                    comboCount = 2;
                    needDelay05 = true;
                    delay05Seconds = 0f;
                    delay02Seconds = 0f;
                    needDelay02 = true;

                } else {
                    if (timeSeconds < 0.7 && comboCount == 2) {
                        comboCount = 3;
                        needAttack2 = true;
                    } else if (timeSeconds < 0.7 && comboCount == 3) {
                        comboCount = 1;
                        needAttack3 = true;
                        needDelay05 = true;
                        delay05Seconds = 0f;
                    } else if (!needDelay05 || needDelay05 && delay05Seconds > 0.5) {
                        if (isHeroLookRight) {
                            textureHeroAttack = new Texture("characters/attackingRight.png"); // #9
                        } else {
                            textureHeroAttack = new Texture("characters/attackingLeft.png");
                        }
                        comboCount = 2;
                        isHeroAttack(textureHeroAttack);
                        needDelay05 = false;
                        delay02Seconds = 0f;
                        needDelay02 = true;
                    }
                    timeSeconds = 0f;
                }


            }

            if (needAttack2) {
                boolean damaged2 = false;
                if (xCountAttack2n3 < 7) {
                    if (isHeroLookRight) {
                        textureHero = new Texture("characters/strangeRight1.png");
                        textureRegionHero = new TextureRegion(textureHero);
                        spriteHero = new Sprite(textureRegionHero);
                        spriteHero.setPosition(spriteHero.getX() + ATTACK2N3_STEP, spriteHero.getY());
                        this.setPosition(this.getX() + ATTACK2N3_STEP, this.getY());
                    } else {
                        textureHero = new Texture("characters/strangeLeft1.png");
                        textureRegionHero = new TextureRegion(textureHero);
                        spriteHero = new Sprite(textureRegionHero);
                        spriteHero.setPosition(spriteHero.getX() - ATTACK2N3_STEP, spriteHero.getY());
                        this.setPosition(this.getX() - ATTACK2N3_STEP, this.getY());
                    }
                    xCountAttack2n3++;
                }
                if (xCountAttack2n3 == 7) {
                    needAttack2 = false;
                    xCountAttack2n3 = 0;
                    delay02Seconds = 0f;
                    needDelay02 = true;
                }

                if (Math.abs(enemy.getX() - this.getX()) <= 205 && Math.abs(enemy.getY() - this.getY()) <= 50) {
                    if (enemy.isAlive()) {
                        enemy.setHp(enemy.getHp() - 1);
                        enemy.setNeedDelayHp(true);
                        enemy.setDelayHpSeconds(0f);
                    }
                }
            }
            if (needAttack3) {
                boolean isLooking = isHeroLookRight;
                if (xCountAttack2n3 < 7) {
                    if (isHeroLookLeft) {
                        textureHero = new Texture("characters/strangeRight1.png");
                        textureRegionHero = new TextureRegion(textureHero);
                        spriteHero = new Sprite(textureRegionHero);
                        spriteHero.setPosition(spriteHero.getX() + ATTACK2N3_STEP, spriteHero.getY());
                        this.setPosition(this.getX() + ATTACK2N3_STEP, this.getY());

                    } else {
                        textureHero = new Texture("characters/strangeLeft1.png");
                        textureRegionHero = new TextureRegion(textureHero);
                        spriteHero = new Sprite(textureRegionHero);
                        spriteHero.setPosition(spriteHero.getX() - ATTACK2N3_STEP, spriteHero.getY());
                        this.setPosition(this.getX() - ATTACK2N3_STEP, this.getY());
                    }
                    xCountAttack2n3++;
                }
                if (xCountAttack2n3 == 7) {
                    needAttack3 = false;
                    xCountAttack2n3 = 0;
                    isHeroLookRight = isLooking;
                    isHeroLookLeft = !isLooking;
                    delay02Seconds = 0f;
                    needDelay02 = true;
                }
                if (Math.abs(enemy.getX() - this.getX()) <= 105 && Math.abs(enemy.getY() - this.getY()) <= 50) {
                    if (enemy.isAlive()) {
                        enemy.setHp(enemy.getHp() - 1);
                        enemy.setNeedDelayHp(true);
                        enemy.setDelayHpSeconds(0f);
                    }
                }
            }


            if (needJump) {
                if (yCountJump == 0 && jumpUp) {
                    if (isHeroLookRight) {
                        textureHero = new Texture("characters/jumpingRight.png");
                    } else {
                        textureHero = new Texture("characters/jumpingLeft.png");
                    }
                    textureRegionHero = new TextureRegion(textureHero);
                    spriteHero = new Sprite(textureRegionHero);
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() + getJUMP_STEP_1());
                    this.setPosition(this.getX(), this.getY() + getJUMP_STEP_1());
                    yCountJump++;
                }


                if (yCountJump > 0 && yCountJump < 25 && jumpUp) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() + getJUMP_STEP_1());
                    this.setPosition(this.getX(), this.getY() + getJUMP_STEP_1());
                    yCountJump++;
                }
                if (yCountJump >= 25 && yCountJump < 40 && jumpUp) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() + getJUMP_STEP_2());
                    this.setPosition(this.getX(), this.getY() + getJUMP_STEP_2());
                    yCountJump++;
                }
                if (yCountJump >= 40 && yCountJump < 53 && jumpUp) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() + getJUMP_STEP_3());
                    this.setPosition(this.getX(), this.getY() + getJUMP_STEP_3());
                    yCountJump++;
                }
                if (yCountJump >= 53 && yCountJump < 60 && jumpUp) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() + getJUMP_STEP_4());
                    this.setPosition(this.getX(), this.getY() + getJUMP_STEP_4());
                    yCountJump++;
                }


                if (yCountJump == 60 && jumpUp) {
                    jumpUp = false;
                    jumpDown = true;
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - getJUMP_STEP_4());
                    this.setPosition(this.getX(), this.getY() - getJUMP_STEP_4());
                    yCountJump--;
                }


                if (yCountJump >= 53 && yCountJump < 61 && jumpDown) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - getJUMP_STEP_4());
                    this.setPosition(this.getX(), this.getY() - getJUMP_STEP_4());
                    yCountJump--;
                }
                if (yCountJump >= 40 && yCountJump < 53 && jumpDown) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - getJUMP_STEP_3());
                    this.setPosition(this.getX(), this.getY() - getJUMP_STEP_3());
                    yCountJump--;
                }
                if (yCountJump >= 25 && yCountJump < 40 && jumpDown) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - getJUMP_STEP_2());
                    this.setPosition(this.getX(), this.getY() - getJUMP_STEP_2());
                    yCountJump--;
                }
                if (yCountJump > 0 && yCountJump < 25 && jumpDown) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - getJUMP_STEP_1());
                    this.setPosition(this.getX(), this.getY() - getJUMP_STEP_1());
                    yCountJump--;
                }


                if (yCountJump == 0 && jumpDown) {
                    spriteHero.setPosition(spriteHero.getX(), spriteHero.getY() - 4.3f);
                    this.setPosition(this.getX(), this.getY() - 4.3f);
                    needJump = false;
                    jumpUp = true;
                    jumpDown = false;
                }
            }

            if (!Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.UP) && !needDelay02 && !Gdx.input.isKeyPressed(Input.Keys.DOWN) && !needJump && !isMainGameScreenStop() && !needAttack3 && !needAttack2) {
                if (isHeroLookLeft) {
                    textureHero = new Texture("characters/standingLeft.png");
                } else {
                    textureHero = new Texture("characters/standingRight.png");
                }
                textureRegionHero = new TextureRegion(textureHero);
                spriteHero = new Sprite(textureRegionHero);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                if (!needJump) {
                    walkSheet = new Texture("characters/runningLeft.png"); // #9

                    isHeroRun(walkSheet);

                    spriteHero.setPosition(getX() - HERO_STEP - 15, getY());
                    this.setPosition(getX() - HERO_STEP - 15, getY());

                    this.isHeroLookRight = false;
                    this.isHeroLookLeft = true;
                } else {
                    spriteHero.setPosition(getX() - HERO_STEP - 15, getY());
                    this.setPosition(getX() - HERO_STEP - 15, getY());
                    this.isHeroLookRight = false;
                    this.isHeroLookLeft = true;
                    textureHero = new Texture("characters/jumpingLeft.png");
                    textureRegionHero = new TextureRegion(textureHero);
                    spriteHero = new Sprite(textureRegionHero);
                }

                comboCount = 1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                if (!needJump) {
                    walkSheet = new Texture("characters/runningRight.png"); // #9

                    isHeroRun(walkSheet);

                    spriteHero.setPosition(getX() + HERO_STEP + 15, getY());
                    this.setPosition(getX() + HERO_STEP + 15, getY());

                    this.isHeroLookRight = true;
                    this.isHeroLookLeft = false;
                } else {
                    spriteHero.setPosition(getX() + HERO_STEP + 15, getY());
                    this.setPosition(getX() + HERO_STEP + 15, getY());

                    this.isHeroLookRight = true;
                    this.isHeroLookLeft = false;

                    textureHero = new Texture("characters/jumpingRight.png");
                    textureRegionHero = new TextureRegion(textureHero);
                    spriteHero = new Sprite(textureRegionHero);
                }
                isRight = true;
                comboCount = 1;
            }

            if (!Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                isRight = false;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                if (!needJump) {
                    if (firstPressed) {
                        walkSheet = new Texture("characters/runningRight.png"); // #9

                        isHeroRun(walkSheet);

                        spriteHero.setPosition(getX(), getY() - HERO_STEP);
                        this.setPosition(getX(), getY() - HERO_STEP);
                        this.firstPressed = false;
                        this.isHeroLookLeft = false;
                        this.isHeroLookRight = true;
                    } else {
                        isHeroRun();

                        spriteHero.setPosition(getX(), getY() - HERO_STEP);
                        this.setPosition(getX(), getY() - HERO_STEP);
                    }
                } else {
                    spriteHero.setPosition(getX(), getY() - HERO_STEP);
                    this.setPosition(getX(), getY() - HERO_STEP);
                }

                comboCount = 1;
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                if (!needJump) {
                    if (firstPressed) {
                        walkSheet = new Texture("characters/runningRight.png"); // #9

                        isHeroRun(walkSheet);

                        spriteHero.setPosition(getX(), getY() + HERO_STEP);
                        this.setPosition(getX(), getY() + HERO_STEP);

                        this.firstPressed = false;
                        this.isHeroLookLeft = false;
                        this.isHeroLookRight = true;

                    } else {
                        isHeroRun();

                        spriteHero.setPosition(getX(), getY() + HERO_STEP);
                        this.setPosition(getX(), getY() + HERO_STEP);
                    }
                } else {
                    spriteHero.setPosition(getX(), getY() + HERO_STEP);
                    this.setPosition(getX(), getY() + HERO_STEP);
                }

                comboCount = 1;
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                needJump = true;
                comboCount = 1;
            }


            if (hp <= 0 && lives == 2) {
                hp = 8;
                lives = 1;
            }

            float W = Gdx.graphics.getWidth();
            float H = Gdx.graphics.getHeight();


            if (this.getY() <= -50) {
                this.setPosition(this.getX(), -50);
                spriteHero.setPosition(spriteHero.getX(), -50);
            }
            if (!needJump) {
                if (this.getY() >= H / 3) {
                    this.setPosition(this.getX(), H / 3);
                    spriteHero.setPosition(spriteHero.getX(), H / 3);
                }
            }
            if (this.getX() >= W) {
                this.setPosition(W , this.getY());
                spriteHero.setPosition(W , this.getY());
            }
            if (this.getX() <= -50) {
                this.setPosition(-50, this.getY());
                spriteHero.setPosition(-50, this.getY());
            }
        }


    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(textureRegionHero, getX(), getY());
    }
}