package de.uni_stuttgart.iste.cowolf.model.lqn;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQN;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LQNPackage;
import de.uni_stuttgart.iste.cowolf.model.LqnCore.LqnModelType;

public class LQNModelManager extends AbstractQoSModelManager {
	
	public static final String PARAM_PATH_TO_LQN_SOLVER = "pathToLqnSolver";

	@Override
	public Class<?> getManagedClass() {
		return LQN.class;
	}

	@Override
	public String getModelName() {
		return "LQN";
	}

	@Override
	public String getModelNamespace() {
		return LQNPackage.eNS_URI;
	}

	@Override
	public String getFileExtension() {
		return "lqn";
	}
	
	@Override
	public EObject getRootObject(Resource resource) {
		
		LqnModelType lqnmodeltype = null;
		TreeIterator<EObject> allObjects = resource.getAllContents();

		while(allObjects.hasNext()){
			EObject object = allObjects.next();
			if (object instanceof LqnModelType) {
				lqnmodeltype = (LqnModelType) object;
			}
		}
		return lqnmodeltype;
	}
}