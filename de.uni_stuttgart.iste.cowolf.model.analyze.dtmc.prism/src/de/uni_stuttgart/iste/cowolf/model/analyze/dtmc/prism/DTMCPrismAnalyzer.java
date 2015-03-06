package de.uni_stuttgart.iste.cowolf.model.analyze.dtmc.prism;

import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;
import de.uni_stuttgart.iste.cowolf.model.analyze.AbstractAnalyzer;
import de.uni_stuttgart.iste.cowolf.model.dtmc.DTMCModelManager;

public class DTMCPrismAnalyzer extends AbstractAnalyzer {
	
	@Override
	public String analyze(final Resource model,
			final Map<String, Object> parameters, final IAnalysisListener listener) {

		// TODO Add return values
		final DTMCAnalyzeJob job = new DTMCAnalyzeJob(model, parameters);
		job.addJobChangeListener(new DTMCAnalyzeJobListener(listener));
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();
		return null;
	}

	@Override
	public Class<?> getManagedClass() {
		return DTMCModelManager.class;
	}

	@Override
	public String getName() {
		return "PRISM";
	}

	@Override
	public String getDescription() {
		return "Analyzes DTMC's with PRISM model checker";
	}
}
