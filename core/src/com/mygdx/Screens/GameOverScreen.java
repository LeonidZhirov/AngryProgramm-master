package com.mygdx.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import javax.xml.bind.annotation.W3CDomHandler;

public class GameOverScreen extends BaseScreen
{
    private Stage stage;
    private ImageButton menu_btn;

    private ImageButton createBtn(float x, float y){
        Texture menu_texture = new Texture("buttons/menuButton.png");
        TextureRegion menu_TextureRegion = new TextureRegion(menu_texture);
        TextureRegionDrawable menu_TexRegionDrawable = new TextureRegionDrawable(menu_TextureRegion);
        ImageButton menu_btn = new ImageButton(menu_TexRegionDrawable);
        menu_btn.setPosition(x,y);
        return menu_btn;
    }


    public GameOverScreen(final MainGame game) {
        super(game);
        stage = new Stage();
        menu_btn = createBtn(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        menu_btn.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(game.menuScreen);
            }
        });

        menu_btn.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        stage.addActor(menu_btn);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.3f, 0.4f, 0.7f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}