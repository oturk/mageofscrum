package hu.ideaimpl.mageofscrum.client.proxies;

import hu.ideaimpl.mageofscrum.server.entity.Project;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(Project.class)
public interface ProjectProxy extends EntityProxy {
	Long getId();

	String getName();

	String getDescription();

	void setName(String name);

	void setDescription(String description);
}
