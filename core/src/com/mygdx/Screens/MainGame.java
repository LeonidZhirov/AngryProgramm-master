package com.mygdx.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MainGame extends Game {

    public MainGameScreen gameScreen;
    public GameOverScreen gameOverScreen;
    public MenuScreen menuScreen;

    @Override
    public void create ()
    {
        menuScreen = new MenuScreen(this);
        gameScreen = new MainGameScreen(this);
        gameOverScreen = new GameOverScreen(this);
        setScreen(menuScreen);
        Music music = Gdx.audio.newMusic(Gdx.files.internal("torero.mp3"));
        music.setVolume(0.1f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render() {
        super.render();
        if(gameScreen.isGameover()){
            setScreen(gameOverScreen);
            gameScreen.setGameover(false);
        }
    }
}
