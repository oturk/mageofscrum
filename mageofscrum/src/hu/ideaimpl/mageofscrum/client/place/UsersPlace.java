package hu.ideaimpl.mageofscrum.client.place;

import hu.ideaimpl.mageofscrum.client.MageOfScrum;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class UsersPlace extends Place {

	public UsersPlace() {
	}
	
	@Prefix("USP")
	public static class Tokenizer implements PlaceTokenizer<UsersPlace>{

		@Override
		public UsersPlace getPlace(String token) {
			return new UsersPlace();
		}

		@Override
		public String getToken(UsersPlace place) {
			return MageOfScrum.DEFAULT_LINK;
		}
		
	}

}
