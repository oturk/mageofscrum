package hu.ideaimpl.mageofscrum.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class BacklogPlace extends Place {

	public BacklogPlace() {
	}
	
	@Prefix("!blp")
	public static class Tokenizer implements PlaceTokenizer<BacklogPlace>{

		@Override
		public BacklogPlace getPlace(String token) {
			return new BacklogPlace();
		}

		@Override
		public String getToken(BacklogPlace place) {
			return "backlog";
		}
		
	}
	
}
