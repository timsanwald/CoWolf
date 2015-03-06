package de.uni_stuttgart.iste.cowolf.model.dtmc;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;

public class DTMCModelManager extends AbstractQoSModelManager {

	@Override
	public Class<?> getManagedClass() {
		return DTMC.class;
	}

	@Override
	public String getModelName() {
		return "dtmc";
	}
	
	@Override
	public String getModelNamespace() {
		return DTMCPackage.eNS_URI;
	}
	
	@Override
	public String getFileExtension() {
		return "dtmc";
	}
}
