package de.uni_stuttgart.iste.cowolf.model.ctmc;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;

/**
 * 
 * @author Rene Trefft
 *
 */
public class CTMCModelManager extends AbstractQoSModelManager{

	@Override
	public Class<?> getManagedClass() {
		return CTMC.class;
	}

	@Override
	public String getModelName() {
		return "CTMC";
	}

	@Override
	public String getModelNamespace() {
		return CtmcPackage.eNS_URI;
	}
	
	@Override
	public String getFileExtension() {
		return "ctmc";
	}
}
