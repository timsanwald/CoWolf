package de.uni_stuttgart.iste.cowolf.analyze.ctmc.prism;

import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.analyze.AbstractAnalyzer;
import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;
import de.uni_stuttgart.iste.cowolf.model.ctmc.CTMCModelManager;

public class CTMCPrismAnalyzer extends AbstractAnalyzer {

	@Override
	public String analyze(final Resource model,
			final Map<String, Object> parameters, final IAnalysisListener listener) {

		// TODO Add return values
		final CTMCPrismAnalyzeJob job = new CTMCPrismAnalyzeJob(model, parameters);
		job.addJobChangeListener(new CTMCAnalyzeJobListener(listener));
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();
		return null;
	}

	@Override
	public Class<?> getManagedClass() {
		return CTMCModelManager.class;
	}

	@Override
	public String getName() {
		return "PRISM";
	}

	@Override
	public String getDescription() {
		return "Analyzes CTMC's with PRISM model checker";
	}

}
