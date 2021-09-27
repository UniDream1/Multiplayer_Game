package gameStuff_phases;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.UUID;

import Characters.Box;
import gameobjects_states.GameState;
import gameobjects_states.ID;
import imageloader.BufferedImageLoader;
import inputlisteners.KeyInputs;
import inputlisteners.MouseAdapter;
import musicmanagment.Music;
import rendering.Render;
import window.Window;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 8358263811084238555L;

	private boolean isRunning = false;

	private Thread gameThread;

	private Handler handler;

	private GameState gameState = GameState.Menu;

	private Menu menu;

	private QueuePhase queue;

	private BufferedImageLoader imageLoader;

	private KeyInputs keyAdapter;

	private MouseAdapter mouseAdapter;

	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	@SuppressWarnings("unused")
	private Music music; // do not initialize, because of missing file paths

	
	public Game() {
		@SuppressWarnings("unused")
		Window window = new Window((int) screenSize.getWidth(), (int) screenSize.getHeight(), this);

		start();

		handler = new Handler();

		handler.addGameObject(new Box(32, 32, ID.Player, UUID.randomUUID(), handler));

		imageLoader = new BufferedImageLoader();

		menu = new Menu(this);

		mouseAdapter = new MouseAdapter(this);
		keyAdapter = new KeyInputs(this);

		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);

		addKeyListener(keyAdapter);

		queue = new QueuePhase(this);

	}

	private void start() {
		if (isRunning) {
			return;
		}
		gameThread = new Thread(this);
		gameThread.start();
		isRunning = true;
	}

	private void stop() {
		if (isRunning) {
			try {
				gameThread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			isRunning = false;
		}
	}

	public void tick() {
		handler.tick();

	}

	public Menu getMenu() {
		return this.menu;
	}

	public QueuePhase getQueue() {
		return this.queue;
	}

	public BufferedImageLoader getImageLoader() {
		return this.imageLoader;
	}

	public void render() {
		Render renderer = new Render(this, handler);
		renderer.commenceRenderingSequence();
	}

	@Override
	public void run() {

		requestFocus();

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}

		stop();
	}

	public GameState getGameState() {
		return this.gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Handler getHandler() {
		return this.handler;
	}

}
