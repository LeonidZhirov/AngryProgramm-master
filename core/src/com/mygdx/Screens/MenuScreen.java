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
import com.badlogic.gdx.utils.viewport.FillViewport;

public class MenuScreen extends BaseScreen
{
    private Stage stage;
    private ImageButton start_btn;

    private ImageButton createBtn(float x, float y){
        Texture start_texture = new Texture("buttons/startButton.png");
        TextureRegion start_TextureRegion = new TextureRegion(start_texture);
        TextureRegionDrawable start_TexRegionDrawable = new TextureRegionDrawable(start_TextureRegion);
        ImageButton start_btn = new ImageButton(start_TexRegionDrawable);
        start_btn.setPosition(x, y);
        return start_btn;
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

    public MenuScreen(final MainGame game)
    {
        super(game);
        stage = new Stage();
        start_btn = createBtn(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);

        start_btn.addCaptureListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y){
                game.setScreen(game.gameScreen);
            }
        });

        stage.addActor(start_btn);
    }

}