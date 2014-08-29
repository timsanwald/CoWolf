package de.uni_stuttgart.iste.cowolf.model.component_diagram;

import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.model.AbstractArchitectureModelManager;

/**
 * 
 * @author Rene Trefft
 *
 */
public class ComponentDiagramModelManageer extends AbstractArchitectureModelManager {

	@Override
	public boolean isManaged(final Resource model) {
		if (model == null || model.getContents() == null
				|| model.getContents().size() == 0)
			return false;
		else
			return (model.getContents().get(0) instanceof ComponentDiagram);

	}

	@Override
	public Class<?> getManagedClass() {
		return ComponentDiagram.class;
	}

	@Override
	public String getModelName() {
		return "Component Diagram";
	}

	@Override
	public String getModelNamespace() {
		return Component_diagramPackage.eNS_URI;
	}

}