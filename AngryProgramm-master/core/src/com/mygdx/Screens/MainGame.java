package com.mygdx.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MainGame extends Game {

    @Override
    public void create ()
    {
        setScreen(new MainGameScreen(this));
        Music music = Gdx.audio.newMusic(Gdx.files.internal("torero.mp3"));
        music.setVolume(0.3f);
        music.setLooping(true);
        //music.play();
    }

}
//ABOBA