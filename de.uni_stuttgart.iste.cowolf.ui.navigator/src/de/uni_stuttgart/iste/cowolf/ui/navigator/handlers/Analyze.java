package de.uni_stuttgart.iste.cowolf.ui.navigator.handlers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.uni_stuttgart.iste.cowolf.model.AbstractModelManager;
import de.uni_stuttgart.iste.cowolf.model.AbstractQoSModelManager;
import de.uni_stuttgart.iste.cowolf.model.ModelRegistry;
import de.uni_stuttgart.iste.cowolf.model.analyze.AbstractAnalyzer;
import de.uni_stuttgart.iste.cowolf.ui.model.analyze.AbstractQoSAnalyzeWizard;
import de.uni_stuttgart.iste.cowolf.ui.model.analyze.AnalyzeWizardHandler;
import de.uni_stuttgart.iste.cowolf.ui.model.analyze.FileOpenAnalysisListener;

public class Analyze implements IHandler {

	
	private final static Logger LOGGER = LoggerFactory.getLogger(Analyze.class);
	
	@Override
	public void addHandlerListener(final IHandlerListener handlerListener) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {

    	if (!PlatformUI.getWorkbench().saveAllEditors(true)) {
    		return null;
    	}
		
    	LOGGER.debug("Doing Analyze");
		IWorkbenchWindow window = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		IStructuredSelection selection = (IStructuredSelection) window.getSelectionService().getSelection();

		Object selectedElement = selection.getFirstElement();

		if (selectedElement != null && selectedElement instanceof IFile) {
			IFile iFile = (IFile) selectedElement;
			ResourceSet resSet = new ResourceSetImpl();
			Resource resource;
			URI uri = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
			resource = resSet.getResource(uri, true);
			AbstractModelManager modelManager = ModelRegistry.getInstance().getModelManager(resource);
			LOGGER.debug("Found Model Manager " + modelManager.toString());
			if (modelManager != null && modelManager instanceof AbstractQoSModelManager) {
				AbstractQoSModelManager qosModelManager = (AbstractQoSModelManager) modelManager;
				HashMap<String, Object> properties = new HashMap<String, Object>();
				// Get Analyze method
				AbstractAnalyzer analyzer = chooseAnalyzer(qosModelManager); 
				LOGGER.debug("Found Analyzer? " + analyzer.toString());
				AbstractQoSAnalyzeWizard wizard = AnalyzeWizardHandler.getInstance().getQosAnalyzeWizard(analyzer);
	
				if (wizard != null) {
					LOGGER.debug("Found Wizard " + wizard.toString());
					// Call UI to define Properties
					wizard.initialize(qosModelManager, resource, properties);
					if (!wizard.checkConditions()) {
						return null;
					}
					WizardDialog wizardDialog = new WizardDialog(window.getShell(), wizard);
					wizardDialog.setBlockOnOpen(true);
					FileOpenAnalysisListener fileOpenListener = new FileOpenAnalysisListener();
					if (wizardDialog.open() == Window.OK) {
						LOGGER.debug("Ok pressed");
						analyzer.analyze(resource, properties, fileOpenListener);
					} else {
						LOGGER.debug("Cancel pressed");
					}
				}
			}
		}

		return null;
	}
	
	/**
	 * Creates the given executable extensions specified by their extension id, propertyName and class type.
	 *
	 * @param extensionPointID the unique identifier of the extension point
	 * @param propertyName the name of the property have to be a class
	 * @param type class type of the interface
	 * @return All created extensions.
	 */
	@SuppressWarnings("unchecked")
	private <T> List<T> createExecuteableExtensions(final String extensionPointID, final String propertyName, final Class<T> type) {
		final IExtensionRegistry registry = Platform.getExtensionRegistry();
		final IConfigurationElement[] config = registry.getConfigurationElementsFor(extensionPointID);
		final List<T> extensions = new LinkedList<T>();
		for (final IConfigurationElement element : config) {
			Object obj;
			try {
				obj = element.createExecutableExtension(propertyName);
				if (obj != null && type.isAssignableFrom(obj.getClass())) {
					extensions.add((T) obj);
				}
			} catch (final CoreException e1) {
				LOGGER.error("Creating extension failed.", e1);
			}
		}
		return extensions;
	}
	
	public AbstractAnalyzer chooseAnalyzer(AbstractQoSModelManager manager) {
		String id = "de.uni_stuttgart.iste.cowolf.model.qosModelManagerExtension.analyzer";
		List<AbstractAnalyzer> methods = this.createExecuteableExtensions(id, "class", AbstractAnalyzer.class);
		LOGGER.debug(Arrays.toString(methods.toArray()));
		if (methods.isEmpty()) {
			return null;
		}/* else if (methods.size() == 1) {
			return methods.get(0);
		}*/ else {
			ElementListSelectionDialog dialog = new ElementListSelectionDialog(Display.getCurrent().getActiveShell(),new LabelProvider());
			dialog.setMessage("Which analyzer should be used?");
			dialog.setTitle("Analyze Method Chooser");
			dialog.setMultipleSelection(false);
			dialog.setElements(methods.toArray());
			AbstractAnalyzer selected=null;
			  if (dialog.open() == Window.OK) {
			    selected=(AbstractAnalyzer)dialog.getFirstResult();
			  }
			return selected;
		}
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isHandled() {
		return true;
	}

	@Override
	public void removeHandlerListener(final IHandlerListener handlerListener) {
		
	}

}
