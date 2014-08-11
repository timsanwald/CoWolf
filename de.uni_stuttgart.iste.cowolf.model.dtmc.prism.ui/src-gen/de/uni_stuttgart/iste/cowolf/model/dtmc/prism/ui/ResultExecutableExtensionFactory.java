/*
 * generated by Xtext
 */
package de.uni_stuttgart.iste.cowolf.model.dtmc.prism.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import de.uni_stuttgart.iste.cowolf.model.dtmc.prism.ui.internal.ResultActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ResultExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return ResultActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return ResultActivator.getInstance().getInjector(ResultActivator.DE_UNI_STUTTGART_ISTE_COWOLF_MODEL_DTMC_PRISM_RESULT);
	}
	
}
