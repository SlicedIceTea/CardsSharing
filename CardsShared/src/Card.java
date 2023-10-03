/**
 * @author Joseph Murray
*/

import java.util.Arrays;

public class Card implements Comparable<Card> {
	
	private final static String[] RANK_NAMES = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
	private final static String[] SUIT_NAMES = {"Clubs", "Diamonds", "Hearts", "Spades"};
	
	private int rank;
	private String suit;
	
	/**
	 * @apiNote: Default Constructor
	*/
	Card() {
		rank = 1;
		suit = "Clubs";
	}
	
	/** 
	 * @param rank
	 * @param suit
	*/
	Card(int rank, int suit) {
		this(rank, getSuitString(suit));
	}
	
	/**
	 * @param rank
	 * @param suit
	*/
	Card(String rank, String suit) {
		this(getRankInt(rank), suit);
	}
	
	/**
	 * @param rank
	 * @param suit
	*/
	Card(String rank, int suit) {
		this(getRankInt(rank), getSuitString(suit));
	}
	
	/**
	 * @param rank
	 * @param suit
	*/
	Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	/**
	 * @apiNote: Copy Constructor
	 * @param card
	*/
	Card(Card card) {
		this.rank = card.getRank();
		this.suit = card.getSuit();
	}
	
	public int getRank() {
		return rank;
	}
	
	public String getSuit() {
		return suit;
	}
	
	/**
	 * @param rank in int form
	 * @return rank in String form
	*/
	public static String getRankString(int rank) {
		return RANK_NAMES[rank - 1];
	}
	
	/**
	 * @param rank in String form
	 * @return rank in int form
	*/
	public static int getRankInt(String rank) {
		return Arrays.binarySearch(RANK_NAMES, rank);
	}
	
	/**
	 * @param suit in int form
	 * @return suit in String form
	*/
	public static String getSuitString(int suit) {
		return SUIT_NAMES[suit];
	}
	
	/**
	 * @param suit in String form
	 * @return suit in int form
	*/
	public static int getSuitInt(String suit) {
		return Arrays.binarySearch(SUIT_NAMES, suit);
	}
	
	public String toString() {
		return getRankString(rank) + "of" + suit;
	}
	
	@Override
	public int compareTo(Card other) {
		double difference = (rank + ((getSuitInt(suit) + 1) / 10.0)) - (other.rank + ((getSuitInt(other.suit) + 1) / 10.0));
		
		if(difference > 0) {
			return 1;
		}
		else if(difference < 0) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Card) {
				Card otherCard = (Card) other;
				return (suit == otherCard.suit && rank == otherCard.rank);
		}
		else {
			return false;
		}
	}
}