package musicmanagment;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Music class built to play a certain music(preloaded)
 * @author wahab
 * @version 1.0
 * @since 20.09.2021
 */
public class Music {

	@SuppressWarnings("unused")

	private Clip meleeSound;
	private Clip mainMenuMusic;
	private Clip inGameSound;
	private Clip collisionSound;
	private Clip gameOver;

	/*
	 * ---------------------------------- clip duration/length should be considered
	 * as well as file-paths should be corrected at first glance
	 * ----------------------------------------------------------
	 */
	public Music() {
		try {

			// verify all files with correct path files
			meleeSound = AudioSystem.getClip();
			meleeSound.open(AudioSystem.getAudioInputStream(
					new File("dummy path:----------Actual path goes in here------------").getAbsoluteFile()));

			mainMenuMusic = AudioSystem.getClip();
			mainMenuMusic.open(AudioSystem.getAudioInputStream(
					new File("dummy path:----------Actual path goes in here------------").getAbsoluteFile()));

			inGameSound = AudioSystem.getClip();
			inGameSound.open(AudioSystem.getAudioInputStream(
					new File("dummy path:----------Actual path goes in here------------").getAbsoluteFile()));

			collisionSound = AudioSystem.getClip();
			collisionSound.open(AudioSystem.getAudioInputStream(
					new File("dummy path:----------Actual path goes in here------------").getAbsoluteFile()));

			gameOver = AudioSystem.getClip();
			gameOver.open(AudioSystem.getAudioInputStream(
					new File("dummy path:----------Actual path goes in here------------").getAbsoluteFile()));

		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	public void playMeleeSoundEffect() {
		gameOver.setFramePosition(0);
		gameOver.start();
	}

	public void playMainMenuMusic() {
		mainMenuMusic.setFramePosition(0);
		mainMenuMusic.start();
	}

	public void playInGameSoundEffects() {
		inGameSound.setFramePosition(0);
		inGameSound.start();
	}

	public void playCollisionSoundEffect() {
		collisionSound.setFramePosition(0);
		collisionSound.start();
	}

	public void playGameOverSound() {
		gameOver.setFramePosition(0);
		gameOver.start();
	}

}
