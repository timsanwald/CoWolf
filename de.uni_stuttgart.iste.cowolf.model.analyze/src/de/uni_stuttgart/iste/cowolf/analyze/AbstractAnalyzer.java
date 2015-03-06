package de.uni_stuttgart.iste.cowolf.analyze;


import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;
import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;

public abstract class AbstractAnalyzer {
	
	public abstract String analyze(Resource model, Map<String, Object> parameters, IAnalysisListener listener);

	public boolean isManaged(AbstractQoSModelManager qosModel) {
		if (qosModel == null) {
			return false;
		}

		if (this.getManagedClass().isAssignableFrom(
				qosModel.getClass())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Return the manager class of the supported model.
	 * 
	 * @return manager class of the supported model.
	 */
	public abstract Class<?> getManagedClass();
	
	/**
	 * Returns a representative name.
	 */
	public abstract String getName();
	
	/**
	 * Returns a representative description of the analyzer.
	 */
	public abstract String getDescription();

	@Override
	public String toString() {
		return this.getName() + " - " + getDescription();
	}
}
