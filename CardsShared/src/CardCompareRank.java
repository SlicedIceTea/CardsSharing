/**
 * @author Joseph Murray
*/

import java.util.Comparator;

public class CardCompareRank implements Comparator<Card> {
	@Override
	public int compare(Card other1, Card other2) {
		int difference = other1.getRank() - other2.getRank();
		
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