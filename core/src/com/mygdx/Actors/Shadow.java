package com.mygdx.Actors;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Screens.MainGameScreen;

public class Shadow extends Actor {
    private Texture shadowTexture;
    private TextureRegion shadowTextureRegion;
    private Sprite shadowSprite;
    private ActorHero target;

    private int yCountJump = 0;
    private boolean jumpUp = true;
    private boolean jumpDown = false;

    public Shadow(ActorHero target, String path) {
        this.shadowTexture = new Texture(path);
        this.shadowTextureRegion = new TextureRegion(shadowTexture);
        this.shadowSprite = new Sprite(shadowTextureRegion);
        this.target = target;
    }

    public void setShadowTexture(Texture shadowTexture) {
        this.shadowTexture = shadowTexture;
    }

    public void setShadowTextureRegion(TextureRegion shadowTextureRegion) {
        this.shadowTextureRegion = shadowTextureRegion;
    }

    public void setShadowSprite(Sprite shadowSprite) {
        this.shadowSprite = shadowSprite;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if(!target.isNeedJump()){
            if(!target.isNeedAttack2() && !target.isNeedAttack3()) {
                this.shadowTexture = new Texture("shadow.png");
                this.shadowTextureRegion = new TextureRegion(shadowTexture);
                if (target.isHeroLookRight()) {
                    if(target.isRight()) {
                        this.setPosition(target.getX() + 22, target.getY() + 5);
                    }else{
                        this.setPosition(target.getX() + 16, target.getY() + 5);
                    }
                }
                if (target.isHeroLookLeft()) {
                    this.setPosition(target.getX() + 20, target.getY() + 5);
                }
            }
            else {
                this.shadowTexture = new Texture("shadow_attack.png");
                this.shadowTextureRegion = new TextureRegion(shadowTexture);
                if (target.isHeroLookRight()) {
                    this.setPosition(target.getX() + 26, target.getY() + 5);
                }
                if (target.isHeroLookLeft()) {
                    this.setPosition(target.getX() + 26, target.getY() + 5);
                }
            }
        }
        else {
            if (yCountJump == 0 && jumpUp) {
                this.setPosition(target.getX(), target.getY() - target.getJUMP_STEP_1());
                yCountJump++;
            }


            if (yCountJump > 0 && yCountJump < 25 && jumpUp) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_1() * yCountJump);
                yCountJump++;
            }
            if (yCountJump >= 25 && yCountJump < 40 && jumpUp) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_2() * (yCountJump - 25) - target.getJUMP_STEP_1() * 25);
                yCountJump++;
            }
            if (yCountJump >= 40 && yCountJump < 53 && jumpUp) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_3() * (yCountJump - 40) - target.getJUMP_STEP_2() * 15 - target.getJUMP_STEP_1() * 25);
                yCountJump++;
            }
            if (yCountJump >= 53 && yCountJump < 60 && jumpUp) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_4() * (yCountJump - 53) - target.getJUMP_STEP_3() * 13 - target.getJUMP_STEP_2() * 15 - target.getJUMP_STEP_1() * 25);
                yCountJump++;
            }


            if (yCountJump == 60 && jumpUp) {
                jumpUp = false;
                jumpDown = true;
                this.setPosition(target.getX() + 15, target.getY() + target.getJUMP_STEP_4() * (yCountJump - 53) + target.getJUMP_STEP_3() * 13 + target.getJUMP_STEP_2() * 15 + target.getJUMP_STEP_1() * 25);
                yCountJump--;
            }


            if (yCountJump >= 53 && yCountJump < 61 && jumpDown) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_4() * (yCountJump - 53) - target.getJUMP_STEP_3() * 13 - target.getJUMP_STEP_2() * 15 - target.getJUMP_STEP_1() * 25);
                yCountJump--;
            }
            if (yCountJump >= 40 && yCountJump < 53 && jumpDown) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_3() * (yCountJump - 40) - target.getJUMP_STEP_2() * 15 - target.getJUMP_STEP_1() * 25);
                yCountJump--;
            }
            if (yCountJump >= 25 && yCountJump < 40 && jumpDown) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_2() * (yCountJump - 25) - target.getJUMP_STEP_1() * 25);
                yCountJump--;
            }
            if (yCountJump > 0 && yCountJump < 25 && jumpDown) {
                this.setPosition(target.getX() + 15, target.getY() - target.getJUMP_STEP_1() * yCountJump);
                yCountJump--;
            }


            if (yCountJump == 0 && jumpDown) {
                this.setPosition(target.getX() + 15, target.getY() + 4.3f);
                jumpUp = true;
                jumpDown = false;
            }
        }


    }

    public void draw (Batch batch, float parentAlpha) {
        batch.draw(shadowTextureRegion, getX(), getY());
    }
}
