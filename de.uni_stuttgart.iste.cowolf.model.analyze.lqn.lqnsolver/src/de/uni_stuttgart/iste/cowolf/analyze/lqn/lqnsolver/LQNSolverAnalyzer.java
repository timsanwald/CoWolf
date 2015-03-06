package de.uni_stuttgart.iste.cowolf.analyze.lqn.lqnsolver;

import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.analyze.AbstractAnalyzer;
import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;
import de.uni_stuttgart.iste.cowolf.model.lqn.LQNModelManager;

public class LQNSolverAnalyzer extends AbstractAnalyzer {

	@Override
	public String analyze(Resource model, Map<String, Object> parameters,
			IAnalysisListener listener) {
		final LQNAnalyzeJob job = new LQNAnalyzeJob(model,
				parameters, listener);
		job.addJobChangeListener(new LQNAnalyzeJobListener());
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();

		return null;
	}

	@Override
	public Class<?> getManagedClass() {
		return LQNModelManager.class;
	}

	@Override
	public String getName() {
		return "LQNSolver";
	}

	@Override
	public String getDescription() {
		return "Analyzes LQN's with LQNSolver";
	}

}
