package assignment4;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

/**
 * A class to model a card in a card matching game.
 * 
 * @author RyanBouffard
 *
 */
public class Card extends JButton {

	private Color faceColor;
	private static Color backColor;
	private boolean isFaceUp = false;
	private static int width = 150, height = 200;
	private boolean isMatched = false;

	/**
	 * Card constructor from a facecolor.
	 * 
	 * @param fc The facecolor that the card will have.
	 */
	public Card(Color fc) {
		this.faceColor = fc;
		setPreferredSize(new Dimension(width, height));
	}

	/**
	 * Sets the card face up.
	 */
	public void faceUp() {
		setBackground(faceColor);
		paintImmediately(0, 0, width, height);
		this.isFaceUp = true;
	}

	/**
	 * Sets the card face down.
	 */
	public void faceDown() {
		setBackground(backColor);
		this.isFaceUp = false;
	}

	/**
	 * Gets the card's facecolor.
	 * 
	 * @return Returns the card's facecolor.
	 */
	public Color getFaceColor() {
		return this.faceColor;
	}

	/**
	 * Gets the card's face state, either face up or face down.
	 * 
	 * @return Returns whether the card is face up or face down.
	 */
	public boolean getFaceState() {
		return this.isFaceUp;
	}

	/**
	 * Finds if the card is matched.
	 * 
	 * @return Returns whether the card is matched or not.
	 */
	public boolean getIsMatched() {
		return this.isMatched;
	}

	/**
	 * Sets the state to matching another card.
	 * 
	 * @param isMatched Sets value indicating whether it is matched or not.
	 */
	public void isMatching(boolean isMatched) {
		this.isMatched = isMatched;
	}
}
