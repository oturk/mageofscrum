package hu.ideaimpl.mageofscrum.client.mvp;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.activity.ErrorActivity;
import hu.ideaimpl.mageofscrum.client.activity.RoleActivity;
import hu.ideaimpl.mageofscrum.client.activity.TeamActivity;
import hu.ideaimpl.mageofscrum.client.activity.UsersActivity;
import hu.ideaimpl.mageofscrum.client.activity.WelcomeActivity;
import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;
import hu.ideaimpl.mageofscrum.client.place.UsersPlace;
import hu.ideaimpl.mageofscrum.client.place.WelcomePlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {
	private final ClientFactory clientFactory;
	
	public AppActivityMapper(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	@Override
	public Activity getActivity(Place place) {
		if(place instanceof WelcomePlace){
			return new WelcomeActivity(clientFactory);
		}else if(place instanceof ErrorPlace){
			return new ErrorActivity(((ErrorPlace) place).getError(), clientFactory);
		}else if(place instanceof TeamPlace){
			return new TeamActivity(clientFactory);
		}else if(place instanceof RolePlace){
			return new RoleActivity(clientFactory);
		}else if(place instanceof UsersPlace){
			return new UsersActivity(clientFactory);
		}
		return null;
	}

}
