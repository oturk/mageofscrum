package hu.ideaimpl.mageofscrum.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ProjectPlace extends Place {
	
	public ProjectPlace() {
	}

	@Prefix("!n")
	public static class Tokenizer implements PlaceTokenizer<ProjectPlace>{

		@Override
		public ProjectPlace getPlace(String token) {
			return new ProjectPlace();
		}

		@Override
		public String getToken(ProjectPlace place) {
			return "projects";
		}


	}

}
