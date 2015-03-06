package de.uni_stuttgart.iste.cowolf.skeleton.model;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;

public class qosModelManager extends AbstractQoSModelManager {

	@Override
	public Class<?> getManagedClass() {
		// TODO Return the main class of the generated ecore model
		// used this class because it is an impossible value
		return this.getClass();
	}

	@Override
	public String getModelName() {
		// TODO name of the model
		return "skeleton";
	}

	@Override
	public String getModelNamespace() {
		// TODO namespace of the ecore model
		return "http://QosSkeleton/1.0";
	}
	
	@Override
	public String getFileExtension() {
		return "qosskeleton";
	}
}
