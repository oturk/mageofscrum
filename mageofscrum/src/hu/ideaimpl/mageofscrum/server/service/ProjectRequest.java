package hu.ideaimpl.mageofscrum.server.service;

import hu.ideaimpl.mageofscrum.client.proxies.ProjectProxy;
import hu.ideaimpl.mageofscrum.server.entity.Project;

import java.util.ArrayList;

import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

@Service(Project.class)
public interface ProjectRequest extends RequestContext {
	public ArrayList<ProjectProxy> findAllProjects();
	public ProjectProxy findProject(Long id);
}
