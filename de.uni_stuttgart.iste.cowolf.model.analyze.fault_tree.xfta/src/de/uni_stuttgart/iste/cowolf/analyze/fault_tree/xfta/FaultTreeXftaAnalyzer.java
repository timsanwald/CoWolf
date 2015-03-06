package de.uni_stuttgart.iste.cowolf.analyze.fault_tree.xfta;

import java.util.Map;

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.analyze.AbstractAnalyzer;
import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;
import de.uni_stuttgart.iste.cowolf.model.fault_tree.FaultTreeModelManager;

public class FaultTreeXftaAnalyzer extends AbstractAnalyzer {
	
	public static final String PARAM_PATH_TO_XFTA = "pathToXFTA";

	@Override
	public String analyze(Resource model, Map<String, Object> parameters,
			IAnalysisListener listener) {
		final FaultTreeAnalyzeJob job = new FaultTreeAnalyzeJob(model,
				parameters, listener);
		job.addJobChangeListener(new FaultTreeAnalyzeJobListener());
		job.setPriority(Job.LONG);
		job.setUser(true);
		job.schedule();

		return null;
	}

	@Override
	public Class<?> getManagedClass() {
		return FaultTreeModelManager.class;
	}

	@Override
	public String getName() {
		return "Xfta";
	}

	@Override
	public String getDescription() {
		return "Analyzes Fault Trees with Xfta";
	}

}
