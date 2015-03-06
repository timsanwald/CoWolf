package de.uni_stuttgart.iste.cowolf.model.fault_tree;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;

public class FaultTreeModelManager extends AbstractQoSModelManager {

	@Override
	public Class<?> getManagedClass() {
		return FaultTree.class;
	}

	@Override
	public String getModelName() {
		return "FaultTree";
	}

	@Override
	public String getModelNamespace() {
		return FaultTreePackage.eNS_URI;
	}
	
	@Override
	public String getFileExtension() {
		return "faulttree";
	}
}
