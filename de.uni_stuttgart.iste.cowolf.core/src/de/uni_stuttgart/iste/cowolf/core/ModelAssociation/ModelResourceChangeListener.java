package de.uni_stuttgart.iste.cowolf.core.ModelAssociation;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import de.uni_stuttgart.iste.cowolf.core.extensions.ExtensionHandler;
import de.uni_stuttgart.iste.cowolf.core.natures.ProjectNature;

public class ModelResourceChangeListener implements IResourceChangeListener {
	
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
			return;
		}
		
		IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
			
			@Override
			public boolean visit(IResourceDelta delta) throws CoreException {
				IResource res = delta.getResource(); 
				
				// Don't go into non-CoWolf projects.
				if (res.getType() == IResource.PROJECT) {
					return res.getProject().hasNature(ProjectNature.NATURE_ID);
				}
				
				// ignore hidden files/directories (starting with ".")
				if (res.getName().startsWith(".")) {
					return false;
				}
				
				// Go into all things other than files
				if (res.getType() != IResource.FILE) {
					return true;
				}
				
				if (res.getProject() == null) {
					return false;
				}
				
				// Now, there are only visible files within a project.
				System.out.println(res.getFullPath().toString() + " - " + delta.getKind());
				ModelAssociation ma = ModelAssociationFactory.eINSTANCE.getModelAssociation(res.getProject());
				
				switch (delta.getKind()) {
					case IResourceDelta.ADDED:
						
						// Register only managed models.
						ResourceSetImpl resSet = new ResourceSetImpl();
						Resource modelRes = resSet.createResource(URI.createURI(res.getLocationURI().toString()));
						
						try {
							modelRes.load(Collections.EMPTY_MAP);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							return false;
						}
						
						// not a model resource.
						if (modelRes == null || !modelRes.isLoaded() || ExtensionHandler.getInstance().getModelManager(modelRes) == null) {
							return false;
						}
						
						// Moved files
						if ((delta.getFlags() & IResourceDelta.MOVED_FROM) == IResourceDelta.MOVED_FROM) {
							IPath from = delta.getMovedFromPath();
							if (res.getProject().getProjectRelativePath().isPrefixOf(from)) {
								// moved from same project
								System.out.println(from.makeRelativeTo(res.getProject().getFullPath()));
								Model model = ma.getModelByPath(from.makeRelativeTo(res.getProject().getFullPath()).toString());
								
								if (model != null) {
									model.rename(res.getProjectRelativePath().toString());
									System.out.println("Renamed model file.");
								}
							} else {
								// FIXME: For now, handle as new.
								Model model = ma.registerModel(modelRes);
								model.createVersion();
								System.out.println("Created model after move.");
							}
						} else { // created files
							Model model = ma.registerModel(modelRes);
							model.createVersion();
							System.out.println("Created model file.");
						}
						break;
					
					case IResourceDelta.CHANGED:
						
						
						if ((delta.getFlags() & IResourceDelta.CONTENT) == IResourceDelta.CONTENT) {
							Model model = ma.getModelByPath(res.getProjectRelativePath().toString());
							if (model != null) {
								model.createVersion();
								System.out.println("Created version.");
							}
						}
						break;
					
					case IResourceDelta.REMOVED:
						// If not moved, but really removed.
						if ((delta.getFlags() & IResourceDelta.MOVED_TO) != IResourceDelta.MOVED_TO) {
							Model model = ma.getModelByPath(res.getProjectRelativePath().toString());
							if (model != null) {
								ma.removeModel(model);
								System.out.println("Removed model.");
							}
						}
						break;
				}
				
				return true;
			}

		};
		
		try {
			event.getDelta().accept(visitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
