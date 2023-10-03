/**
 * @author Joseph Murray
*/

import java.util.Comparator;

public class CardCompareRankAndSuit implements Comparator<Card> {
	@Override
	public int compare(Card other1, Card other2) {
		double difference = (other1.getRank() + (Card.getSuitInt(other1.getSuit() + 1) / 10.0)) - (other2.getRank() + (Card.getSuitInt(other2.getSuit() + 1) / 10.0));
		
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
}