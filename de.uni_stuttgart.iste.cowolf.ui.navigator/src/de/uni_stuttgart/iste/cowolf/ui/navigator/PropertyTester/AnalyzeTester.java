package de.uni_stuttgart.iste.cowolf.ui.navigator.PropertyTester;

import java.io.File;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.uni_stuttgart.iste.cowolf.core.extensions.ExtensionHandler;
import de.uni_stuttgart.iste.cowolf.model.IModelManager;

public class AnalyzeTester extends PropertyTester {

	public AnalyzeTester() {
	}

	@Override
	public boolean test(final Object receiver, final String property, final Object[] args,
			final Object expectedValue) {

		ExtensionHandler extensionHandler = new ExtensionHandler();

		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		if (window == null) {
			return false;
		}
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();
		if (selection == null) {
			return false;
		}
		Object selectedElement = selection.getFirstElement();
		if (selectedElement == null) {
			return false;
		}
		// catch exceptions from wrong parsing as we can only recognize IFiles
		try {
			// file then try to parse
			if (selectedElement instanceof IFile) {
				IFile iFile = (IFile) selectedElement;
				File file = iFile.getLocation().toFile();

				ResourceSet resourceSet = new ResourceSetImpl();
				Resource resource;

				URI uri = URI.createPlatformResourceURI(iFile.getFullPath()
						.toString(), true);

				if (!file.exists()) {
					return false;
				}

				resource = resourceSet.getResource(uri, true);

				IModelManager modelManager = extensionHandler
						.getModelManager(resource);

				// must find model manager
				if (modelManager != null) {
					return true;
				} else {
					return false;
				}
			} else {
				// no file -> cannot open
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

}
