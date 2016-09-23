package ch.isitar.colorPickerSwing;

import java.util.Observable;

public class CurrentColor extends Observable {
	private int red;
	private int green;
	private int blue;

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		if (red < 0)
			red = 0;

		if (red > 255)
			red = 255;
		this.red = red;
		setChanged();
		notifyObservers();

	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		if (green < 0)
			green = 0;

		if (green > 255)
			green = 255;
		this.green = green;
		setChanged();
		notifyObservers();
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		if (blue < 0)
			blue = 0;
		if (blue > 255)
			blue = 255;
		this.blue = blue;
		setChanged();
		notifyObservers();
	}

	private final int deltaDarkBright = 10;

	public void darker() {
		setRed(getRed() - deltaDarkBright);
		setGreen(getGreen() - deltaDarkBright);
		setBlue(getBlue() - deltaDarkBright);
		setChanged();
		notifyObservers();
	}

	public void brighter() {
		setRed(getRed() + deltaDarkBright);
		setGreen(getGreen() + deltaDarkBright);
		setBlue(getBlue() + deltaDarkBright);

		setChanged();
		notifyObservers();
	}

}
