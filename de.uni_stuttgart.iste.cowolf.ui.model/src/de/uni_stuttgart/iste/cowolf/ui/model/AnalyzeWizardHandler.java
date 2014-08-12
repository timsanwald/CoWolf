package de.uni_stuttgart.iste.cowolf.ui.model;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import de.uni_stuttgart.iste.cowolf.model.IQoSModelManager;

public class AnalyzeWizardHandler {

	/**
	 * ID for the QoS analyze wizard extension.
	 */
	public static final String QOS_ANALYZE_WIZARD_EXTENSION_ID = "de.uni_stuttgart.iste.cowolf.ui.model.qosAnalyzeWizardExtension";

	private List<AbstractQoSAnalyzeWizard> qosAnalyzeWizards;

	public AnalyzeWizardHandler() {
		this.createQoSAnalyzeWizardExtensions();
	}

	/**
	 * @return the qosAnalyzeWizards
	 */
	public AbstractQoSAnalyzeWizard getQosAnalyzeWizard(final IQoSModelManager modelManager) {
		for (AbstractQoSAnalyzeWizard wizard : this.qosAnalyzeWizards) {
			if (wizard.isManaged(modelManager)) {
				return wizard;
			}
		}

		return null;
	}

	/**
	 * Create the QoS analyze wizard extensions, which are registered.
	 */
	private void createQoSAnalyzeWizardExtensions() {
		this.qosAnalyzeWizards = this.createExecuteableExtensions(QOS_ANALYZE_WIZARD_EXTENSION_ID, "class", AbstractQoSAnalyzeWizard.class);
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
				e1.printStackTrace();
			}
		}
		return extensions;
	}

	private static AnalyzeWizardHandler instance = null;

	public static AnalyzeWizardHandler getInstance() {
		if (instance == null) {
			return new AnalyzeWizardHandler();
		}
		return instance;
	}
}