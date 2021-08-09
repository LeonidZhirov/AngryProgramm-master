package com.mygdx.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HP extends Actor
{
    private Texture hpBarTexture;
    private TextureRegion hpBarTextureRegion;
    private Sprite hpBarSprite;

    public Texture getHpBarTexture() {
        return hpBarTexture;
    }

    public void setHpBarTexture(Texture hpBarTexture) {
        this.hpBarTexture = hpBarTexture;
    }

    public TextureRegion getHpBarTextureRegion() {
        return hpBarTextureRegion;
    }

    public void setHpBarTextureRegion(TextureRegion hpBarRegionTexture) {
        this.hpBarTextureRegion = hpBarRegionTexture;
    }

    public Sprite getHpBarSprite() {
        return hpBarSprite;
    }

    public void setHpBarSprite(Sprite hpBarSprite) {
        this.hpBarSprite = hpBarSprite;
    }

    public HP(Texture hpBarTexture)
    {
        this.hpBarTexture = hpBarTexture;
        hpBarTextureRegion = new TextureRegion(hpBarTexture);
        hpBarSprite = new Sprite(hpBarTextureRegion);
        hpBarSprite.setSize(hpBarSprite.getRegionWidth(), hpBarSprite.getRegionHeight());
    }

    public void draw(Batch batch, float parentAlpha) { batch.draw(hpBarTextureRegion, getX(), getY()); }
}