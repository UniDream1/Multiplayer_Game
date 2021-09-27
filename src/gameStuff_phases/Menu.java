package gameStuff_phases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import gameobjects_states.GameState;
import gameobjects_states.MenuState;

public class Menu {

	private Rectangle playButton;
	private Rectangle characterButton;
	private Rectangle settingsButton;
	private Rectangle quitButton;
	private Rectangle cancelButton;
	private Rectangle helpButton;

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

	private Game game;

	public Menu(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		if (menuState.equals(MenuState.NONE)) {

			g.setFont(new Font("Arial", Font.PLAIN + Font.BOLD, 70));
			g.setColor(setColor(0, 0, 205));
			g.drawString(title, game.getWidth() / 2 - 220, 100 + 100);

			// playButton text
			g.setColor(getPlayButtonColor());
			g.setFont(new Font("Arial", Font.PLAIN, 35));
			g.drawString("Play", game.getWidth() / 2 - 30, 200 + 100);

			// CharacterButton text
			g.setColor(getCharaterButtonColor());
			g.setFont(new Font("Arial", Font.PLAIN, 35));
			g.drawString("Operators", game.getWidth() / 2 - 70, 350 + 100);

			// SettingsButton text
			g.setColor(getSettingsButtonColor());
			g.setFont(new Font("Arial", Font.PLAIN, 35));
			g.drawString("Settings", game.getWidth() / 2 - 55, 500 + 100);

			// quitButton text
			g.setColor(getQuitButtonColor());
			g.setFont(new Font("Arial", Font.PLAIN, 35));
			g.drawString("Quit", game.getWidth() / 2 - 25, 650 + 100);

			// playButton
			g2d.setColor(getPlayButtonColor());
			playButton = new Rectangle(game.getWidth() / 2 - 130, 150 + 100, 270, 80);
			g2d.draw(playButton);

			// characterButton
			g2d.setColor(getCharaterButtonColor());
			characterButton = new Rectangle(game.getWidth() / 2 - 130, 300 + 100, 270, 80);
			g2d.draw(characterButton);

			// settingsButton
			g2d.setColor(getSettingsButtonColor());
			settingsButton = new Rectangle(game.getWidth() / 2 - 130, 450 + 100, 270, 80);
			g2d.draw(settingsButton);

			// quitButton
			g2d.setColor(getQuitButtonColor());
			quitButton = new Rectangle(game.getWidth() / 2 - 130, 600 + 100, 270, 80);
			g2d.draw(quitButton);

		} else { // CancelButton text
			g2d.setColor(getCancelButtonColor());
			g.setFont(new Font("Arial", Font.PLAIN, 40));
			g.drawString("X", game.getWidth() - 100, 50);

			// cancelButton
			cancelButton = new Rectangle(game.getWidth() - 100, 19, 25, 33);
			// g2d.draw(cancelButton);

			// Characterpreview state
			if (menuState.equals(MenuState.CHARACTERBUTTON)) {
				g.setColor(Color.white);
				g.drawString("operators preview", game.getWidth() / 2 - 150, 500);
			} // settings
			else if (menuState.equals(MenuState.SETTINGSBUTTON)) {
				g.setColor(Color.white);
				g.drawString("Settings", game.getWidth() / 2 - 100, game.getHeight() / 2);
			}
		}

	}

	public void onMousepressedEvent(MouseEvent e) {
		if (game.getGameState().equals(GameState.Menu)) {
			if (getMenuState().equals(MenuState.NONE)) {
				if (getPlayButtonBounds().contains(e.getPoint())) {
					// play button pressed
					game.setGameState(GameState.InQueue);

					try {
						Thread.sleep(50);
						game.getQueue().startQueuing();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				} else if (getCharaterButtonBounds().contains(e.getPoint())) {
					// character preview
					setMenuState(MenuState.CHARACTERBUTTON);
				} else if (getSettingsButtonBounds().contains(e.getPoint())) {
					// setting menu
					setMenuState(MenuState.SETTINGSBUTTON);
				} else if (getQuitButtonBounds().contains(e.getPoint())) {
					// au revoir
					System.exit(0);
				}
			} else if (getMenuState().equals(MenuState.CHARACTERBUTTON)) {
				if (getCancelButtonBounds().contains(e.getPoint())) {
					System.out.println("cancel button pressed");
					setMenuState(MenuState.NONE);
					setCancelButtonColor(Color.white);
				}
			} else if (getMenuState().equals(MenuState.SETTINGSBUTTON)) {
				if (getCancelButtonBounds().contains(e.getPoint())) {
					System.out.println("cancel button pressed");
					setMenuState(MenuState.NONE);
					setCancelButtonColor(Color.white);
				}
			}
		}
	}

	public void onMouseMovedEvent(MouseEvent e) {
	//@formatter:off
		
		if (game.getGameState().equals(GameState.Menu)) {
			if(getMenuState().equals(MenuState.NONE)) {
			if (getPlayButtonBounds().contains(e.getPoint())) {
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
			
			}
			else if(getMenuState().equals(MenuState.CHARACTERBUTTON)) {
				if(getCancelButtonBounds().contains(e.getPoint())) {
					setCancelButtonColor(Color.red);
				} else setCancelButtonColor(Color.white);
			}
			else if(getMenuState().equals(MenuState.SETTINGSBUTTON)) {
				if(getCancelButtonBounds().contains(e.getPoint())) {
					setCancelButtonColor(Color.red);
				} else setCancelButtonColor(Color.white);
			}
			
		}
		// @formatter:on
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

	public Rectangle getPlayButtonBounds() {
		return playButton;
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
