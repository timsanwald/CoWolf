package de.uni_stuttgart.iste.cowolf.core.sirius.editor;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.Association;
import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.ModelAssociation;
import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.ModelVersion;

public class Services {
	public EObject getSuccessor(ModelVersion ePackage) {
        // The service code.
		if (ePackage == null || ePackage.getModel() == null) {
			return null;
		}
		EList<ModelVersion> versions = ePackage.getModel().getVersions();
		int index = versions.indexOf(ePackage);
		if (index < 0 || index+1 >= versions.size()) {
			return null;
		}
        return versions.get(index+1);
    }
	
	public boolean restrictAssociations(Association assoc) {
		ModelAssociation modelAssoc = assoc.getParent();
        return isCurrentAssociation(modelAssoc, assoc);
    }
	
	private boolean isCurrentAssociation(ModelAssociation modelAssoc, Association assoc) {
		List<Association> targetAssocs = assoc.getTarget().get(0).getModel().getTargetAssociations();
		Collections.reverse(targetAssocs);
		for (Association as : targetAssocs) {
			if(as.equals(assoc)) {
				return true;
			}
			if (as.getSource().get(0).getModel().equals(assoc.getSource().get(0).getModel())) {
				return false;
			}
		}		
		return true;
	}
	
	private static boolean oldAssociations = true;
	
	public boolean showOldAssociations() {
		return oldAssociations;
	}
}
