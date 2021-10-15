package musicmanagment;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioPlayer extends Thread {

	private Clip audio;

	public AudioPlayer(Clip clip) {
		this.audio = clip;
	}

	public void setVolume(int vol) {
		FloatControl volManager = (FloatControl) this.audio.getControl(FloatControl.Type.MASTER_GAIN);
		float volume = (float) (vol) / 100f;
		float range = volManager.getMaximum() - volManager.getMinimum();
		float gain = (range * volume) + volManager.getMinimum();
		volManager.setValue(gain);
	}

	public void stopAndErase() {
		this.audio.stop();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.audio.setFramePosition(0);
		this.audio.loop(Clip.LOOP_CONTINUOUSLY);
		this.audio.start();
	}

}
