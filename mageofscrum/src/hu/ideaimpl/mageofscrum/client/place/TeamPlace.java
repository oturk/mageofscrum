package hu.ideaimpl.mageofscrum.client.place;

import hu.ideaimpl.mageofscrum.client.MageOfScrum;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class TeamPlace extends Place {

	public TeamPlace() {
	}
	
	@Prefix("TEP")
	public static class Tokenizer implements PlaceTokenizer<TeamPlace>{

		@Override
		public TeamPlace getPlace(String token) {
			return new TeamPlace();
		}

		@Override
		public String getToken(TeamPlace place) {
			return MageOfScrum.DEFAULT_LINK;
		}
		
	}

}
