package hu.ideaimpl.mageofscrum.client.mvp;

import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.place.ProfilePlace;
import hu.ideaimpl.mageofscrum.client.place.ProjectPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({
	ErrorPlace.Tokenizer.class,
	TeamPlace.Tokenizer.class,
	RolePlace.Tokenizer.class,
	ProfilePlace.Tokenizer.class,
	ProjectPlace.Tokenizer.class
	})
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper{

}
