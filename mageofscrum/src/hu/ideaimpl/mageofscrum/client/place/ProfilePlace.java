package hu.ideaimpl.mageofscrum.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ProfilePlace extends Place {

	public ProfilePlace() {
	}
	
	@Prefix("place")
	public static class Tokenizer implements PlaceTokenizer<ProfilePlace>{

		@Override
		public ProfilePlace getPlace(String token) {
			return new ProfilePlace();
		}

		@Override
		public String getToken(ProfilePlace place) {
			return "userprofile";
		}
		
	}

}
