package com.mygdx.Actors;

import com.badlogic.gdx.Gdx;
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
    private final float STEP = 5f;

    private Texture walkSheet;
    private TextureRegion[] walkFrames; // #5
    public TextureRegion currentFrame; // #7
    private float stateTime; // #8
    private static final int FRAME_COLS = 3; // #1
    private static final int FRAME_ROWS = 1; // #2
    private Animation walkAnimation; // #3
    private SpriteBatch spriteBatch;
    private boolean firstPressed = true;

    private boolean isEnemyLookRight = true;
    private boolean isEnemyLookLeft = false;

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

        spriteBatch.begin();
        spriteBatch.end();
    }

    public ActorEnemy(ActorHero actorHero) {
        textureEnemy = new Texture("characters/standingRight.png");
        textureRegionEnemy = new TextureRegion(textureEnemy);
        this.alive=true;
        this.actorHero = actorHero;
        spriteEnemy = new Sprite(textureRegionEnemy);
        setSize(spriteEnemy.getRegionWidth(), spriteEnemy.getRegionHeight());
    }

    public void act(float delta) {
        super.act(delta);
        if (this.getX() != actorHero.getX()) {
            if (this.getX() < actorHero.getX()) {
                walkSheet = new Texture("characters/runningRight.png"); // #9

                isEnemyRun(walkSheet);
                this.setPosition(this.getX() + 5f, this.getY());
                spriteEnemy.setPosition(this.getX() + 5f, this.getY());

                this.isEnemyLookRight = true;
                this.isEnemyLookLeft = false;
            }

            if (this.getX() > actorHero.getX()) {
                walkSheet = new Texture("characters/runningLeft.png"); // #9

                isEnemyRun(walkSheet);

                this.setPosition(this.getX() - 5f, this.getY());
                spriteEnemy.setPosition(this.getX() - 5f, this.getY());

                this.isEnemyLookRight = false;
                this.isEnemyLookLeft = true;
            }
        }

        if (this.getY() != actorHero.getY()) {
            if (this.getY() < actorHero.getY()) {
                walkSheet = new Texture("characters/runningRight.png"); // #9

                isEnemyRun(walkSheet);

                this.setPosition(this.getX(), this.getY() + 5f);
                spriteEnemy.setPosition(this.getX(), this.getY() + 5f);

                this.firstPressed = false;
                this.isEnemyLookLeft = false;
                this.isEnemyLookRight = true;

            } else {
                isEnemyRun();

                this.setPosition(this.getX(), this.getY() + 5f);
                spriteEnemy.setPosition(this.getX(), this.getY() + 5f);
            }


            if (this.getY() > actorHero.getY()) {
                if (firstPressed) {
                    walkSheet = new Texture("characters/runningRight.png"); // #9

                    isEnemyRun(walkSheet);

                    this.setPosition(this.getX(), this.getY() - 5f);
                    spriteEnemy.setPosition(this.getX(), this.getY() - 5f);
                    this.firstPressed = false;
                    this.isEnemyLookLeft = false;
                    this.isEnemyLookRight = true;
                } else {
                    isEnemyRun();
//                    textureEnemy = new Texture("characters/sittingRight.png");
//                    textureRegionEnemy = new TextureRegion(textureEnemy);
//                    spriteEnemy = new Sprite(textureRegionEnemy);
                    this.setPosition(this.getX(), this.getY() - 6f);
                    spriteEnemy.setPosition(this.getX(), this.getY() - 6f);
                }

            }
        }

//        if (this.getY() == actorHero.getY()) {
//            if (isEnemyLookLeft) {
//                textureEnemy = new Texture("characters/standingLeft.png");
//                textureRegionEnemy = new TextureRegion(textureEnemy);
//                spriteEnemy = new Sprite(textureRegionEnemy);
//            } else {
//                textureEnemy = new Texture("characters/standingRight.png");
//                textureRegionEnemy = new TextureRegion(textureEnemy);
//                spriteEnemy = new Sprite(textureRegionEnemy);
//            }
//        }

//        if (this.getX() == actorHero.getX()) {
//            if (isEnemyLookLeft) {
//                textureEnemy = new Texture("characters/standingLeft.png");
//                textureRegionEnemy = new TextureRegion(textureEnemy);
//                spriteEnemy = new Sprite(textureRegionEnemy);
//            } else {
//                textureEnemy = new Texture("characters/standingRight.png");
//                textureRegionEnemy = new TextureRegion(textureEnemy);
//                spriteEnemy = new Sprite(textureRegionEnemy);
//            }
//        }
    }

    public void draw(Batch batch, float parentAlpha) { batch.draw(textureRegionEnemy, getX(), getY()); }
}
