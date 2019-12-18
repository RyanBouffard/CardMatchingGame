package assignment4;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * A class to model the board for a card matching game.
 * 
 * @author RyanBouffard
 *
 */
public class Board extends JFrame {
	private JMenuBar menuBar;
	private JMenu fileMenu;
	ArrayList<Card> cardList = new ArrayList<Card>();

	/**
	 * Board constructor, creates 6 pairs of different colors, then for each pair
	 * afterward creates a pair of a random color.
	 */
	public Board() {
		setTitle("Concentration Game v1.0");
		setSize(400, 500);

		Judge judy = new Judge();
		System.out.println("Enter amount of pairs in matching game. More than 6 pairs will allow for duplicate pairs");
		Scanner s = new Scanner(System.in);
		int pairs = s.nextInt();

		Container c = getContentPane();
		c.setLayout(new GridLayout((4), (6)));

		Random randomNumber = new Random();
		ArrayList<Color> colorList = new ArrayList<Color>();
		colorList.add(Color.red);
		colorList.add(Color.orange);
		colorList.add(Color.yellow);
		colorList.add(Color.green);
		colorList.add(Color.blue);
		colorList.add(Color.magenta);

		for (int i = 0; i < pairs; i++) {
			if (i == 0) {
				Card b1 = new Card(Color.red);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.red);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i < 2) {
				Card b1 = new Card(Color.yellow);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.yellow);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i < 3) {
				Card b1 = new Card(Color.green);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.green);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i < 4) {
				Card b1 = new Card(Color.blue);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.blue);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i < 5) {
				Card b1 = new Card(Color.magenta);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.magenta);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i < 6) {
				Card b1 = new Card(Color.orange);
				b1.addActionListener(judy);
				Card b2 = new Card(Color.orange);
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			} else if (i >= 6) {
				int a = randomNumber.nextInt(6);
				Card b1 = new Card(colorList.get(a));
				b1.addActionListener(judy);
				Card b2 = new Card(colorList.get(a));
				b2.addActionListener(judy);
				cardList.add(b1);
				cardList.add(b2);
			}
		}
		Collections.shuffle(cardList);
		for (Card card : cardList) {
			c.add(card);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		s.close();
		buildMenu();
	}

	/**
	 * Builds the menu on the program, allowing the user to exit from File, Exit
	 * rather than closing out.
	 */
	private void buildMenu() {
		Judge judy = new Judge();
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(judy);
		fileMenu.add(menuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);
	}

	/**
	 * Creates the board.
	 * 
	 * @param args Array of arguments
	 */
	public static void main(String[] args) {
		new Board();
	}

	/**
	 * A class that models a judge, knows rules of the game and checks for win
	 * condition.
	 * 
	 * @author RyanBouffard
	 *
	 */
	private class Judge implements ActionListener {
		private int clickCounter;
		private Card cardOne = null;
		private Card cardTwo = null;

		@Override
		/**
		 * Knows when action is performed, grabs the object associated with the action.
		 */
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getActionCommand().equals("Exit")) {
				System.out.println("Exiting program");
				System.exit(0);
			}

			clickCounter++;
			Object clickedObject = arg0.getSource();
			Card clickedCard = ((Card) clickedObject);
			if (cardOne == null && cardTwo == null) {
				cardOne = clickedCard;
				clickedCard.faceUp();
			} else if (cardOne != null && cardOne != clickedCard && cardTwo == null) {
				cardTwo = clickedCard;
				clickedCard.faceUp();
				doCardsMatch();

			}
		}

		/**
		 * Asks the cards if they match.
		 */
		public void doCardsMatch() {
			if (cardOne.getFaceColor().equals(cardTwo.getFaceColor())) {
				cardOne.isMatching(true);
				cardTwo.isMatching(true);
				if (this.isGameOver() == true) {
					System.out.println("Game Over");
					System.out.println("Number of cards clicked : " + clickCounter);
					try {
						TimeUnit.SECONDS.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					System.exit(0);
				}
			} else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				cardOne.faceDown();
				cardTwo.faceDown();

			}
			cardOne = null;
			cardTwo = null;
		}

		/**
		 * Allows for game to end, game state is checked each time a card is clicked.
		 * 
		 * @return Returns whether the game should be over, or if it is still in
		 *         progress.
		 */
		public boolean isGameOver() {
			int gameOverNumber = 0;
			int trueNumber = 0;

			for (Card cards : cardList) {
				gameOverNumber++;
			}
			for (Card cards : cardList) {
				if (cards.getFaceState() == true) {
					trueNumber++;
				}
			}
			if (gameOverNumber == trueNumber) {
				return true;
			}
			return false;
		}
	}
}