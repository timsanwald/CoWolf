package de.uni_stuttgart.iste.cowolf.ui.evolution.wizard;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.wizard.Wizard;

import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.Model;

/**
 * This wizard allows the user to select the two models to use for evolution.
 * 
 * @author Michael Müller
 * @author Rene Trefft
 */
public class ComponentSelectionWizard extends Wizard {

    /**
     * First model to use for evolution.
     */
    private IFile source;

    /**
     * Wizard page displaying contents of wizard.
     */
    private ComponentSelectionWizardPage page;

	private Model sourceModel;
	private Resource baseVersion;
	private Resource targetVersion;
	private File patchFile;
    private boolean patchDefault = false;
	
    /**
     * Constructor setting both models.
     *
     * @param source
     * @param modelB
     */
    public ComponentSelectionWizard(IFile source, Model sourceModel) {
        this.setWindowTitle("Model Difference Wizard");
        this.source = source;
        this.sourceModel = sourceModel;
    }

    @Override
    public void addPages() {
        this.page = new ComponentSelectionWizardPage(this, source, sourceModel, patchDefault);
        this.addPage(this.page);
    }

    @Override
    public boolean canFinish() {
        return super.canFinish();
    }

    /**
     * @return the source
     */
    public IFile getSource() {
        return source;
    }

	@Override
	public boolean performFinish() {
		this.baseVersion = page.getBaseVersion();
		this.targetVersion = page.getTargetVersion();
		this.patchFile = page.getPatchFile();
		return true;
	}

	public Resource getBaseVersion() {
		return this.baseVersion;
	}

	public Resource getTargetVersion() {
		return this.targetVersion;
	}
	
	public File getPatchFile() {
		return this.patchFile;
	}
	
	public boolean isPatchDefault() {
		return patchDefault;
	}

	public void setPatchDefault(boolean patchDefault) {
		this.patchDefault = patchDefault;
	}

}
