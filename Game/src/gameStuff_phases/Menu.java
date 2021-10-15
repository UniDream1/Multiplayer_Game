package gameStuff_phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gameobjects_states.GameState;
import gameobjects_states.MenuState;

public class Menu {

	private Rectangle playButton;
	private Rectangle characterButton;
	private Rectangle settingsButton;
	private Rectangle quitButton;
	private Rectangle cancelButton;
	private Rectangle helpButton;
	private Rectangle volumeButton;

	private Color playButtonColor = Color.white;
	private Color helpButtonColor = Color.white;
	private Color CharaterButtonColor = Color.white;
	private Color settingsButtonColor = Color.white;
	private Color quitButtonColor = Color.white;
	private Color cancelButtonColor = Color.white;

	private MenuState menuState = MenuState.NONE;

	private String title = "Chase & Kill";

	private final int Width_Buttons = 100;
	private final int Height_Buttons = 80;
	private final int offsetX;
	private final int offsetY;
	private final int offsetYY;
	private final int XSIZED_SCALED;
	private final int YSIZED_SCALED;
	private int muteClickcounter;

	private BufferedImage mute;
	private BufferedImage unmute;

	private Game game;

	private boolean cancelButtoncreated = false;
	private boolean muted = false;

	public Menu(Game game) {
		this.game = game;

		this.offsetX = (int) ((double) game.getWidth() * 13.0 / 192.0);
		this.offsetY = (int) ((double) game.getHeight() * 15.0 / 108.0);

		this.XSIZED_SCALED = (int) ((double) this.game.getWidth() * 27.0 / 192.0);
		this.YSIZED_SCALED = (int) ((double) this.game.getHeight() * 8.0 / 108.0);

		this.offsetYY = (int) ((double) game.getHeight() * 10.0 / 108.0);

		playButton = new Rectangle(game.getWidth() / 2 - this.offsetX, this.offsetY + this.offsetYY, XSIZED_SCALED,
				YSIZED_SCALED);

		characterButton = new Rectangle(game.getWidth() / 2 - this.offsetX, 2 * this.offsetY + this.offsetYY,
				XSIZED_SCALED, YSIZED_SCALED);

		settingsButton = new Rectangle(game.getWidth() / 2 - this.offsetX, 3 * this.offsetY + this.offsetYY,
				XSIZED_SCALED, YSIZED_SCALED);

		quitButton = new Rectangle(game.getWidth() / 2 - this.offsetX, 4 * this.offsetY + this.offsetYY, XSIZED_SCALED,
				YSIZED_SCALED);

		cancelButton = new Rectangle(game.getWidth() - (int) ((double) this.game.getWidth() * 10.0 / 192.0),
				(int) ((double) game.getHeight() * 19.0 / 1080), (int) ((double) this.game.getWidth() * 25.0 / 1920.0),
				(int) ((double) this.game.getHeight() * 33.0 / 1080.0));

		this.volumeButton = new Rectangle(this.game.getWidth() - 120, this.game.getHeight() - 120, 80, 80);

		this.mute = this.game.getImageLoader().loadImg("/mute.png").getBufferedImage();
		this.unmute = this.game.getImageLoader().loadImg("/unmute.png").getBufferedImage();

	}

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		if (menuState.equals(MenuState.NONE)) {

			g.setFont(new Font("Arial", Font.PLAIN + Font.BOLD, 70));
			g.setColor(setColor(0, 0, 205));
			int textWidth = g.getFontMetrics().stringWidth(title);
			// g.drawString(title, , this.stringOff + this.titOFF);
			g.drawString(title, (int) (this.playButton.getCenterX() - textWidth / 2),
					this.playButton.y / 2 - this.playButton.y / 16);

			// playButton text
			g.setColor(getPlayButtonColor());
			drawText(g2d, "Play", this.playButton);

			// CharacterButton text
			g.setColor(getCharaterButtonColor());
			drawText(g2d, "Operators", characterButton);

			// SettingsButton text
			g.setColor(getSettingsButtonColor());
			drawText(g2d, "Settings", settingsButton);

			// quitButton text
			g.setColor(getQuitButtonColor());
			drawText(g2d, "Quit", quitButton);

			// playButton
			g2d.setColor(getPlayButtonColor());
			g2d.draw(playButton);

			// characterButton
			g2d.setColor(getCharaterButtonColor());
			g2d.draw(characterButton);

			// settingsButton
			g2d.setColor(getSettingsButtonColor());
			g2d.draw(settingsButton);

			// quitButton
			g2d.setColor(getQuitButtonColor());
			g2d.draw(quitButton);

			g2d.drawImage(muted ? mute : unmute, volumeButton.x, volumeButton.y, null);

		} else { // CancelButton text
			g2d.setColor(getCancelButtonColor());
			g2d.setFont(new Font("Arial", Font.PLAIN, 40));
			createCancelButton(g2d);
			// drawText(g2d, "X", cancelButton);
			g2d.drawString("X", (int) (this.cancelButton.getCenterX() - this.cancelButton.getWidth() / 2),
					(int) (cancelButton.y + cancelButton.getCenterY() / 2));
			// cancelButton
			// g2d.draw(cancelButton);

			// Characterpreview state
			if (menuState.equals(MenuState.CHARACTERBUTTON)) {
				g.setColor(Color.white);
				g.drawString("operators preview", game.getWidth() / 2 - 150, 500);
			} // settings
			else if (menuState.equals(MenuState.SETTINGSBUTTON)) {
				this.game.getSettingsMenu().render(g2d);
			}
		}

	}

	public void createCancelButton(Graphics2D g) {
		if (!cancelButtoncreated) {
			int width = g.getFontMetrics().charWidth('X');
			int height = g.getFontMetrics().getHeight();
			cancelButton = new Rectangle(game.getWidth() - 100, height, width, height - 15);
			cancelButtoncreated = true;
		}
	}

	private void drawText(Graphics g, String text, Rectangle button) {
		g.setFont(new Font("Arial", Font.PLAIN, 35));
		int wo = g.getFontMetrics().stringWidth(text);
		int ho = g.getFontMetrics().getHeight();
		g.drawString(text, (int) (button.getCenterX() - wo / 2), (int) (button.getCenterY() + ho / 4));
	}

	public boolean isMuted() {
		return this.muted;
	}

	public void onMouseClickedEvent(MouseEvent e) {

		if (e.getButton() == MouseEvent.BUTTON1) {
			if (game.getGameState().equals(GameState.Menu)) {
				switch (getMenuState()) {
				case NONE:
					if (playButton.contains(e.getPoint())) {
						// play button pressed
						game.setGameState(GameState.Pickphase);
						return;
					} else if (getCharaterButtonBounds().contains(e.getPoint())) {
						// character preview
						setMenuState(MenuState.CHARACTERBUTTON);
						return;

					} else if (getSettingsButtonBounds().contains(e.getPoint())) {
						// setting menu
						setMenuState(MenuState.SETTINGSBUTTON);
						return;
						// quitButton
					} else if (this.volumeButton.contains(e.getPoint())) {
						this.muteClickcounter++;
						if (this.muteClickcounter == 1) {
							this.muted = true;
							this.game.muteMainMenuMusic(muted);
						} else if (this.muteClickcounter == 2) {
							this.muted = false;
							this.game.muteMainMenuMusic(muted);
							this.muteClickcounter = 0;
						}

					}

					else if (getQuitButtonBounds().contains(e.getPoint())) {
						this.game.setRunning(false);
						try {
							Thread.sleep(500);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						System.exit(0);// exits the game

					}
					break;

				case CHARACTERBUTTON:
					if (getCancelButtonBounds().contains(e.getPoint())) {
						setMenuState(MenuState.NONE);
						setCancelButtonColor(Color.white);
					}
					break;
				case SETTINGSBUTTON:
					this.game.getSettingsMenu().onMouseClickedEvent(e);
					if (getCancelButtonBounds().contains(e.getPoint())) {
						setMenuState(MenuState.NONE);
						setCancelButtonColor(Color.white);
					}
					break;
				default:
					break;
				}
			}
		}
	}

	//@formatter:off
	
	public void onMouseDraggedEvent(MouseEvent e) {
		switch(menuState) {
		case SETTINGSBUTTON:
			this.game.getSettingsMenu().onMouseDraggedEvent(e);
		default:
			break;
		}
	}
	
	public void onMouseMovedEvent(MouseEvent e) {	

		if (game.getGameState().equals(GameState.Menu)) {
			switch(getMenuState()) {
			case NONE:
				if (playButton.getBounds().contains(e.getPoint())) {
					setPlayButtonColor(Color.blue);
				} else setPlayButtonColor(Color.white);
				if (getSettingsButtonBounds().contains(e.getPoint())) {
					setSettingsButtonColor(Color.green);
				} else setSettingsButtonColor(Color.white);
					
				if (getQuitButtonBounds().contains(e.getPoint())) {
					setQuitButtonColor(Color.red);
				} else setQuitButtonColor(Color.white);
					
				if (getCharaterButtonBounds().contains(e.getPoint())) {
					setCharaterButtonColor(Color.yellow);
				} else setCharaterButtonColor(Color.white);
				break;
				
			case CHARACTERBUTTON:
				if(getCancelButtonBounds().contains(e.getPoint())) {
							setCancelButtonColor(Color.red);
				} else setCancelButtonColor(Color.white);
					
			break;
			
			case SETTINGSBUTTON:
				this.game.getSettingsMenu().onMouseMovedEvent(e);

				if(getCancelButtonBounds().contains(e.getPoint())) {
					setCancelButtonColor(Color.red);
				} else setCancelButtonColor(Color.white);
				break;
			
			default:
				break;
			}
	
		}
		// @formatter:on
	}

	public void onkeyPressedEvent(KeyEvent e) {
		switch (this.menuState) {
		case SETTINGSBUTTON:
			this.game.getSettingsMenu().onKeyPressedEvent(e);
			break;

		default:
			break;
		}
	}

	public Color setColor(int r, int g, int b) {
		return new Color(r, g, b);
	}

	public MenuState getMenuState() {
		return menuState;
	}

	public void setMenuState(MenuState menuState) {
		this.menuState = menuState;
	}

	public Color getPlayButtonColor() {
		return playButtonColor;
	}

	public void setPlayButtonColor(Color playButtonColor) {
		this.playButtonColor = playButtonColor;
	}

	public Color getHelpButtonColor() {
		return helpButtonColor;
	}

	public void setHelpButtonColor(Color helpButtonColor) {
		this.helpButtonColor = helpButtonColor;
	}

	public Color getCharaterButtonColor() {
		return CharaterButtonColor;
	}

	public void setCharaterButtonColor(Color charaterButtonColor) {
		CharaterButtonColor = charaterButtonColor;
	}

	public Color getSettingsButtonColor() {
		return settingsButtonColor;
	}

	public void setSettingsButtonColor(Color settingsButtonColor) {
		this.settingsButtonColor = settingsButtonColor;
	}

	public Color getQuitButtonColor() {
		return quitButtonColor;
	}

	public void setQuitButtonColor(Color quitButtonColor) {
		this.quitButtonColor = quitButtonColor;
	}

	public Color getCancelButtonColor() {
		return cancelButtonColor;
	}

	public void setCancelButtonColor(Color cancelButtonColor) {
		this.cancelButtonColor = cancelButtonColor;
	}

	public Rectangle getHelpButtonBounds() {
		return helpButton;
	}

	public Rectangle getCharaterButtonBounds() {
		return characterButton;
	}

	public Rectangle getSettingsButtonBounds() {
		return settingsButton;
	}

	public Rectangle getQuitButtonBounds() {
		return quitButton;
	}

	public Rectangle getCancelButtonBounds() {
		return cancelButton;
	}

	public int getWidth_Buttons() {
		return Width_Buttons;
	}

	public int getHeight_Buttons() {
		return Height_Buttons;
	}
}
