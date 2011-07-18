package game;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Function.ReadFile;

public class PlayerTank extends Tank {

	/*
	 * TODO add lives function
	 */

	int keyleft;
	int keyright;
	int keyup;
	int keydown;
	int keypause;
	int keyfire;
	int lives;
	int score;

	public boolean[] keyPressedState = { false, false, false, false };

	public PlayerTank(BufferedImage image, int frameHeight, int frameWidth) {
		super(image, frameHeight, frameWidth);
		// TODO Auto-generated constructor stub
		this.setTotalHealth(100);
		this.currentHealth = this.totalHealth;
		this.lives = 3;
		this.score = 0;
		getKey();
	}

	public void keyPressedReact(KeyEvent e) {
		// System.out.println("Key pressed = " + e.getKeyCode());
		if (e.getKeyCode() == keyup) {
			keyPressedState[Sprite.UP] = true;
			setCurrentDirection(Sprite.UP);
			this.setRunning(true);
		} else if (e.getKeyCode() == keydown) {
			keyPressedState[Sprite.DOWN] = true;
			this.setCurrentDirection(Sprite.DOWN);
			this.setRunning(true);
		} else if (e.getKeyCode() == keyleft) {
			keyPressedState[Sprite.LEFT] = true;
			this.setCurrentDirection(Sprite.LEFT);
			this.setRunning(true);
		} else if (e.getKeyCode() == keyright) {
			keyPressedState[Sprite.RIGHT] = true;
			this.setCurrentDirection(Sprite.RIGHT);
			this.setRunning(true);
		} else if (e.getKeyCode() == keyfire) {
			this.fire();
		}
	}

	public void update() {
		super.update();
		if (this.isDestroyed()) {
			this.lives--;
			this.respawnd();
		} else {
			int count = 0;
			for (int i = 0; i < this.keyPressedState.length; i++) {
				if (this.keyPressedState[i]) {
					count++;
				}
			}

			for (int i = 0; i < this.keyPressedState.length; i++) {
				if (this.keyPressedState[i]) {
					if (count >= 2) {
						if (this.getCurrentDirection() != i) {
							this.setRunning(true);
						}
					}

					if (count == 1) {
						this.setCurrentDirection(i);
						this.setRunning(true);
					}
				}
			}
		}
		checkCollisonWithItem();
	}

	private void respawnd() {
		// TODO Auto-generated method stub
		this.setPositionAndBound(10 * 32, 16 * 32);
		this.currentHealth = this.totalHealth;
		this.setDestroyed(false);
	}

	public void keyReleasedReact(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == keyup) {
			this.keyPressedState[Sprite.UP] = false;
			if (this.getCurrentDirection() == Sprite.UP)
				this.setRunning(false);
		}
		if (key == keydown) {
			this.keyPressedState[Sprite.DOWN] = false;
			if (this.getCurrentDirection() == Sprite.DOWN)
				this.setRunning(false);
		}
		if (key == keyleft) {
			this.keyPressedState[Sprite.LEFT] = false;
			if (this.getCurrentDirection() == Sprite.LEFT)
				this.setRunning(false);
		}
		if (key == keyright) {
			this.keyPressedState[Sprite.RIGHT] = false;
			if (this.getCurrentDirection() == Sprite.RIGHT)
				this.setRunning(false);
		}
		if (key == keyfire) {
		}
	}

	private void getKey() {
		ReadFile rf = new ReadFile("Resources/Key.bcr");
		keyleft = Integer.parseInt(rf.ReadOneLine());
		keyright = Integer.parseInt(rf.ReadOneLine());
		keydown = Integer.parseInt(rf.ReadOneLine());
		keyup = Integer.parseInt(rf.ReadOneLine());
		keyfire = Integer.parseInt(rf.ReadOneLine());
		// keypause=Integer.parseInt(rf.ReadOneLine());
		rf.Close();
	}

	public void setLives(int value){
		this.lives = value;
	}
	
	public int getLives() {
		// TODO Auto-generated method stub
		return this.lives;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int i) {
		this.score += i;
	}
}
