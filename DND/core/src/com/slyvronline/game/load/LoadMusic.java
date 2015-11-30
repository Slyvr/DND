package com.slyvronline.game.load;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.slyvronline.game.Game;
import com.slyvronline.game.objects.AudioTrack;

public class LoadMusic {

	public static void load(){
		ArrayList<AudioTrack> tracks = new ArrayList<AudioTrack>();
		
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Canyon Battle.mp3")), "music1", "Canyon Battle", "Oblivion Soundtrack", ""));
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Crater Lake.mp3")), "music2", "Crater Lake", "Oblivion Soundtrack", ""));
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Fearful Odds.mp3")), "music3", "Fearful Odds", "Oblivion Soundtrack", ""));
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Radiation Zone.mp3")), "music4", "Radiation Zone", "Oblivion Soundtrack", ""));
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/StarWaves.mp3")), "music5", "StarWaves", "Oblivion Soundtrack", ""));
		tracks.add(new AudioTrack(Gdx.audio.newMusic(Gdx.files.internal("data/music/Supercell.mp3")), "music6", "Supercell", "Oblivion Soundtrack", ""));
		
		Game.getGlobal().setTracks(tracks);
		Game.getGlobal().setCurrentTrack(tracks.get(new Random().nextInt(tracks.size()-1)));
	}
}
