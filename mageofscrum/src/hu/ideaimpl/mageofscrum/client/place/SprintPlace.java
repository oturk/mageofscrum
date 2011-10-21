package hu.ideaimpl.mageofscrum.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class SprintPlace extends Place {

	public SprintPlace() {
		
	}
	
	@Prefix("!sp")
	public static class Tokenizer implements PlaceTokenizer<SprintPlace>{

		@Override
		public SprintPlace getPlace(String token) {
			return new SprintPlace();
		}

		@Override
		public String getToken(SprintPlace place) {
			return "sprint";
		}
		
	}
	
}
