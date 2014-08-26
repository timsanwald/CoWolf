/**
 *
 */
package de.uni_stuttgart.iste.cowolf.model.dtmc.analyze;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Map.Entry;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.emf.ecore.resource.Resource;

import de.uni_stuttgart.iste.cowolf.model.IAnalysisListener;
import de.uni_stuttgart.iste.cowolf.model.dtmc.State;

/**
 * @author philipp
 *
 */
public class DTMCAnalyzeJobListener implements IJobChangeListener {

	private final IAnalysisListener listener;

	public DTMCAnalyzeJobListener(final IAnalysisListener listener) {
		this.listener = listener;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#aboutToRun(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void aboutToRun(final IJobChangeEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#awake(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void awake(final IJobChangeEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#done(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void done(final IJobChangeEvent event) {
		if (!(event.getJob() instanceof DTMCAnalyzeJob)) {
			return;
		}
		if (!event.getResult().isOK()) {
			// no error handling yet.
		}
		DTMCAnalyzeJob job = (DTMCAnalyzeJob) event.getJob();

		Resource resource = job.getModel();

		IFile modelfile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resource.getURI().toPlatformString(true)));

		IFile resultfile = ResourcesPlugin.getWorkspace().getRoot().getFile(modelfile.getFullPath().addFileExtension("analysis.csv"));
		
		try {
			PipedInputStream in = new PipedInputStream();
			PipedOutputStream out = new PipedOutputStream(in);
			
			out.write("State,Probability\n".getBytes());

			for(Entry<Object, String> entry : job.getAnalysis().entrySet()) {
				String key = "";
				if (entry.getKey() instanceof State) {
					key = ((State) entry.getKey()).getName();
				} else if (entry.getKey() instanceof String) {
					key = "Label: " + (String) entry.getKey();
				}
				out.write(key.getBytes());
				out.write(',');
				out.write(entry.getValue().toString().getBytes());
				out.write('\n');
			}

			out.close();
			
			if (resultfile.exists()) {
				resultfile.setContents(in, true, false, null);
			} else {
				resultfile.create(in, true, null);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.listener != null) {
			this.listener.finished(resource, resultfile);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#running(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void running(final IJobChangeEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#scheduled(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void scheduled(final IJobChangeEvent event) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.runtime.jobs.IJobChangeListener#sleeping(org.eclipse.core.runtime.jobs.IJobChangeEvent)
	 */
	@Override
	public void sleeping(final IJobChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
