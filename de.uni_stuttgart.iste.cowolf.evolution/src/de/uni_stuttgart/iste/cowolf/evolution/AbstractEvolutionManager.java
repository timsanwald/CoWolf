package de.uni_stuttgart.iste.cowolf.evolution;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.sidiff.common.emf.exceptions.InvalidModelException;
import org.sidiff.difference.lifting.facade.LiftingFacade;
import org.sidiff.difference.lifting.settings.LiftingSettings;
import org.sidiff.difference.lifting.settings.LiftingSettings.RecognitionEngineMode;
import org.sidiff.difference.matcher.IMatcher;
import org.sidiff.difference.matcher.namedelement.NamedElementMatcher;
import org.sidiff.difference.symmetric.SymmetricDifference;
import org.silift.common.util.access.EMFModelAccessEx;
import org.silift.common.util.emf.Scope;
import org.silift.common.util.exceptions.NoCorrespondencesException;

/**
 * Abstract implementation of the manager that handles evolution of a single
 * model type. Has to be extended by the specific evolution plugin for a model
 * type.
 * 
 * @author Michael Müller
 *
 */
public abstract class AbstractEvolutionManager {

	/**
	 * TODO: set matcher
	 */
	public static final String MATCHER = NamedElementMatcher.KEY;

	/**
	 * Says whether the manager is able to handle a specific model.
	 * 
	 * @param model
	 * @return true if it is able to handle model.
	 */
	public abstract boolean isManaged(EPackage model);

	/**
	 * This method handles the evolution between two models of the same class.
	 * It returns the differences between two models.
	 * 
	 * @param oldModel
	 *            old model for comparison
	 * @param newModel
	 *            new model for comparison
	 * @return differences between two models.
	 */
	public SymmetricDifference evolve(EPackage oldModel, EPackage newModel) {
		if (!this.isManaged(oldModel) || !this.isManaged(newModel)) {
			// TODO: return value?
			return null;
		}
		// do required pre-computing work
		Resource oldResource = oldModel.eResource();
		Resource newResource = newModel.eResource();
		String documentType = EMFModelAccessEx
				.getCharacteristicDocumentType(oldResource);
		LiftingSettings settings = this.getDefaultSettings(documentType,
				oldResource, newResource);
		try {
			return LiftingFacade.liftMeUp(oldResource, newResource, settings);
		} catch (NoCorrespondencesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public abstract EvolutionTypeInfo getEvolutionTypeInfo();

	/**
	 * This method returns default settings for generating differences between
	 * models. Can be overwritten by inheriting managers.
	 * 
	 * @param documentType
	 * @param modelA
	 * @param modelB
	 * @return settings object.
	 */
	protected LiftingSettings getDefaultSettings(String documentType,
			Resource modelA, Resource modelB) {
		LiftingSettings settings = new LiftingSettings(documentType);
		settings.setRecognitionEngineMode(RecognitionEngineMode.LIFTING_AND_POST_PROCESSING);
		settings.setMatcher(this.getMatcher(MATCHER, modelA, modelB));
		settings.setScope(Scope.RESOURCE);
		return settings;
	}

	/**
	 * Get matcher for two models by key.
	 * 
	 * @param matcherKey
	 *            Key to use.
	 * @param modelA
	 *            Resource for model A.
	 * @param modelB
	 *            Resource for model B.
	 * @return
	 */
	private IMatcher getMatcher(String matcherKey, Resource modelA,
			Resource modelB) {
		Set<IMatcher> matchers = LiftingFacade.getAvailableMatchers(modelA,
				modelB);
		Map<String, IMatcher> key2matcher = new HashMap<String, IMatcher>();
		for (IMatcher matcher : matchers) {
			key2matcher.put(matcher.getKey(), matcher);
		}

		// Assert matcher is available
		assert (key2matcher.containsKey(matcherKey)) : "Matcher with key '"
				+ matcherKey + "' not found!";

		return key2matcher.get(matcherKey);
	}

}