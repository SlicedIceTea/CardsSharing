/**
 * @author Joseph Murray
*/

public class Deck implements Comparable<Deck> {
	
	private Card[] deck;
	int topCard;
	
	/**
	 * @apiNote Default Constructor, returns a sorted deck of 52 cards
	*/
	public Deck() {
		this(true);
	}
	
	/**
	 * @param isSorted: returns a sorted deck of 52 cards if true, unsorted if false
	*/
	public Deck(boolean isSorted) {
		
		deck = new Card[52];
		
		int k = 0;
		
		for(int i = 0; i < 4; i++) {
			for(int j = 1; j <= 13; j++) {
				deck[k] = new Card(i, j);
				k++;
			}
		}
		
		if(!isSorted) {
			shuffle();
		}
	}
	
	/**
	 * @apiNote: Copy constructor
	 * @param deck
	*/
	public Deck(Deck deck) {
		this();
		this.deck = deck.getDeck();
	}
	
	/**
	 * @apiNote Creates a Deck with the deck of given Card[]
	 * @param cards
	*/
	public Deck(Card[] cards) {
		this();
		deck = cards;
	}
	
	public Card[] getDeck() {
		return deck;
	}
	
	public int getTopCard() {
		return topCard;
	}
	
	/**
	 * @apiNote returns a string of 4 columns if deck is 52 cards, single column otherwise 
	*/
	public String toString() {
		
		String s = "";
		
		if(topCard == 51) {
			
			int[] suitIndices = {51, 51, 51, 51};
			
			for(int i = 1; i <= 13; i++) {
				for(int j = 0; j < 4; j++) {
					for(int k = suitIndices[j]; k >= 0; k--) {
						if(Card.getSuitInt(deck[k].getSuit()) == j) {
							suitIndices[j] = k;
							s += deck[k].toString() + "\t";
						}
					}
				}
				
				s += "\n";
			}
		}
		else {
			for(int i = 0; i <= topCard; i++) {
				s += i + ". " + deck[i] + "\n";
			}
		}
		
		return s;
	}
	
	/**
	 * @apiNote Shuffles the current deck, moving each card to a different place
	*/
	public void shuffle() {
		Card[] shuffledDeck = new Card[topCard + 1];
		
		for(int i = 0; i < topCard; i++) {
			Card currentCard = pick();
			shuffledDeck[i] = currentCard;
		}
		
		deck = shuffledDeck;
	}	
	
	@Override
	public boolean equals(Object other) {
		
		double deck1Value = 0;
		double deck2Value = 0;
		
		if(other instanceof Deck) {
			
			Deck otherDeck = (Deck) other;
			
			for(Card card : deck) {
				deck1Value += card.getRank() + ((Card.getSuitInt(card.getSuit()) + 1) / 10.0);
			}
			for(Card card : otherDeck.getDeck()) {
				deck2Value += card.getRank() + ((Card.getSuitInt(card.getSuit()) + 1) / 10.0);
			}
			
			return Math.max(deck1Value, deck2Value) - Math.min(deck1Value, deck2Value) < 0.0001;
		}
		
		return false;
	}
	
	@Override
	public int compareTo(Deck other) {
		
		double deck1Value = 0;
		double deck2Value = 0;
		
		for(Card card : deck) {
			deck1Value += card.getRank() + ((Card.getSuitInt(card.getSuit()) + 1) / 10.0);
		}
		for(Card card : other.getDeck()) {
			deck2Value += card.getRank() + ((Card.getSuitInt(card.getSuit()) + 1) / 10.0);
		}
		
		if(Math.max(deck1Value, deck2Value) - Math.min(deck1Value, deck2Value) > 0.0001) {
			return 1;
		}
		else if(Math.max(deck1Value, deck2Value) - Math.min(deck1Value, deck2Value) < -0.0001) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
	/**
	 * @apiNote Deals cards out into hands
	 * @param hands: number of hands dealed to
	 * @param cardsPerHand: number of cards dealed to each hand 
	 * @return an array of hands, each one its own Deck
	*/
	public Deck[] deal(int hands, int cardsPerHand) {
		if(hands * cardsPerHand <= topCard + 1) {
			Deck[] dealedHands = new Deck[hands];
			
			for(int i = 0; i < hands; i++) {
				
				Card[] dealedCards = new Card[cardsPerHand];
				
				for(int j = 0; j < cardsPerHand; j++) {
					dealedCards[j] = deck[topCard];
					removeCard(topCard);
				}
				
				dealedHands[i] = new Deck(dealedCards);
			}
			
			return dealedHands;
		}
		else {
			return null;
		}
	}
	
	/**
	 * @return a random card from the deck
	*/
	public Card pick() {
		
		int pickedCardIndex = (int) (Math.random() * (topCard + 1));
		Card pickedCard = deck[pickedCardIndex];
		removeCard(pickedCardIndex);
		
		return pickedCard;
	}
	
	public void selectionSort() {
		int n = topCard;
		
		while(n > 0) {
			Card largestCard = deck[n];
			
			for(int i = n; i >= 0; i--) {
				if(largestCard.compareTo(deck[i]) < 0) {
					largestCard = deck[i];
				}
			}
			
			n--;
		}
	}
	
	public void mergeSort() {
		if(topCard > 1) {
			Card[] firstHalf = new Card[topCard / 2];
			Card[] secondHalf = new Card[(int) Math.ceil(topCard / 2)];
			
			for(int i = 0; i < firstHalf.length; i++) {
				firstHalf[i] = deck[i];
			}
			
			for(int i = 0; i < secondHalf.length; i++) {
				secondHalf[i] = deck[(topCard / 2) + 1 + i];
			}
			
			Deck firstHalfDeck = new Deck(firstHalf);
			Deck secondHalfDeck = new Deck(secondHalf);
			
			firstHalfDeck.mergeSort();
			secondHalfDeck.mergeSort();
			
			if(firstHalfDeck.compareTo(secondHalfDeck) > -1) {
				firstHalf = secondHalfDeck.getDeck();
				secondHalf = firstHalfDeck.getDeck();
			}
			else {
				firstHalf = firstHalfDeck.getDeck();
				secondHalf = secondHalfDeck.getDeck();
			}
			
			for(int i = 0; i < firstHalf.length; i++) {
				deck[i] = firstHalf[1];
			}
			
			for(int i = 0; i < secondHalf.length; i++) {
				deck[(topCard / 2) + 1 + i] = secondHalf[i]; 
			}
		}
		else if(topCard == 1) {
			if(deck[0].compareTo(deck[1]) > -1) {
				Card tempCard = deck[0];
				deck[0] = deck[1];
				deck[1] = tempCard;
			}
		}
	}
	
	/**
	 * @apiNote removes a card from the deck
	 * @param cardIndex: index of the card that will be removed
	*/
	private void removeCard(int cardIndex) {
		Card[] cards = new Card[topCard];
		
		for(int i = 0; i < cardIndex; i++) {
			cards[i] = cards[i];
		}
		
		for(int i = cardIndex + 1; i <= topCard; i++) {
			cards[i - 1] = cards[i];
		}
		
		deck = cards;		
		topCard--;
	}
}