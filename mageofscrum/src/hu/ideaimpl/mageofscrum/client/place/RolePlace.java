package hu.ideaimpl.mageofscrum.client.place;

import hu.ideaimpl.mageofscrum.client.MageOfScrum;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class RolePlace extends Place {

	public RolePlace() {
		
	}
	
	@Prefix("ROP")
	public static class Tokenizer implements PlaceTokenizer<RolePlace>{

		@Override
		public RolePlace getPlace(String token) {
			return new RolePlace();
		}

		@Override
		public String getToken(RolePlace place) {
			return MageOfScrum.DEFAULT_LINK;
		}
		
	}

}
