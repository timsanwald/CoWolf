package de.uni_stuttgart.iste.cowolf.core.ModelAssociation;

import de.uni_stuttgart.iste.cowolf.model.AbstractArchitectureModelManager;

public class ModelAssociationModelManager extends AbstractArchitectureModelManager {
	
	@Override
	public Class<?> getManagedClass() {
		return ModelAssociation.class;
	}

	@Override
	public String getModelName() {
		return "Model Association";
	}

	@Override
	public String getModelNamespace() {
		return ModelAssociationPackage.eNS_URI;
	}

	@Override
	public String getFileExtension() {
		return "modelassociation";
	}

}
