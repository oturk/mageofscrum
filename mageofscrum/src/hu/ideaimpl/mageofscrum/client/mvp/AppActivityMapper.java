package hu.ideaimpl.mageofscrum.client.mvp;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.MageOfScrum;
import hu.ideaimpl.mageofscrum.client.activity.BacklogActivity;
import hu.ideaimpl.mageofscrum.client.activity.DiagnoseActivity;
import hu.ideaimpl.mageofscrum.client.activity.ErrorActivity;
import hu.ideaimpl.mageofscrum.client.activity.ProfileActivity;
import hu.ideaimpl.mageofscrum.client.activity.ProjectActivity;
import hu.ideaimpl.mageofscrum.client.activity.RoleActivity;
import hu.ideaimpl.mageofscrum.client.activity.SprintActivity;
import hu.ideaimpl.mageofscrum.client.activity.TeamActivity;
import hu.ideaimpl.mageofscrum.client.place.BacklogPlace;
import hu.ideaimpl.mageofscrum.client.place.DiagnosePlace;
import hu.ideaimpl.mageofscrum.client.place.ErrorPlace;
import hu.ideaimpl.mageofscrum.client.place.ProfilePlace;
import hu.ideaimpl.mageofscrum.client.place.ProjectPlace;
import hu.ideaimpl.mageofscrum.client.place.RolePlace;
import hu.ideaimpl.mageofscrum.client.place.SprintPlace;
import hu.ideaimpl.mageofscrum.client.place.TeamPlace;
import hu.ideaimpl.mageofscrum.shared.Roles;

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
		if(place instanceof ErrorPlace){
			return new ErrorActivity(((ErrorPlace) place).getError(), clientFactory);
		}
		if(place instanceof TeamPlace){
			if (hasAdminsRoles()) {
				return new TeamActivity(clientFactory);
			}else{
				return new SprintActivity();
			}
		}
		if(place instanceof RolePlace){
			if (hasAdminsRoles()) {
				return new RoleActivity(clientFactory);
			}else{
				//TODO error placere kellene irányítani, ahol közölni kell vele, hogy nincs megfelelõ permissionje
				return new SprintActivity();
			}
		}
		if(place instanceof ProfilePlace){
			return new ProfileActivity();
		}
		if(place instanceof ProjectPlace){
			if (hasAdminsRoles()) {
				return new ProjectActivity();
			}else{
				return new SprintActivity();
			}
		}
		if(place instanceof BacklogPlace){
			return new BacklogActivity();
		}
		if(place instanceof SprintPlace){
			return new SprintActivity();
		}
		if(place instanceof DiagnosePlace){
			return new DiagnoseActivity();
		}
		return null;
	}
	
	private boolean hasAdminsRoles(){
		if(MageOfScrum.role == Roles.ADMIN || MageOfScrum.role == Roles.MASTER || MageOfScrum.role == Roles.OWNER){
			return true;
		}
		return false;
	}

}
