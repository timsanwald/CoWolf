package de.uni_stuttgart.iste.cowolf.ui.transformation.wizard;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.Wizard;

public class TransformationWizard extends Wizard {

	// First model for evolution.
	private IFile sourceModelA;

	// Second model for evolution.
	private IFile sourceModelB;

	// Target model of transformation.
	private IFile targetModel;
	/**
	 * Wizard page displaying contents of wizard.
	 */
	private TransformationWizardPage page;
	/**
	 * Class variable to determine whether first element is selected.
	 */
	private boolean isFirstElementSelected = true;

	/**
	 * Constructor setting both models.
	 *
	 * @param sourceModelA
	 * @param sourceModelB
	 * @param targetModel
	 */
	public TransformationWizard(IFile sourceModelA, IFile sourceModelB,
			IFile targetModel) {
		this.setWindowTitle("Co-Evolution Wizard");
		this.sourceModelA = sourceModelA;
		this.sourceModelB = sourceModelB;
		this.targetModel = targetModel;

	}

	@Override
	public void addPages() {
		this.page = new TransformationWizardPage(this);
		this.addPage(this.page);
	}

	@Override
	public boolean performFinish() {
		this.isFirstElementSelected = this.page.isFirstModelSelected();
		return true;
	}

	@Override
	public boolean canFinish() {
		return super.canFinish();
	}

	/**
	 *
	 * @return true if first element is selected.
	 */
	public boolean isFirstModelSelected() {
		return this.isFirstElementSelected;
	}

	/**
	 * @return the modelA
	 */
	public IFile getSourceModelA() {
		return sourceModelA;
	}

	/**
	 * @param modelA
	 *            the modelA to set
	 */
	public void setSourceModelA(IFile sourceModelA) {
		this.sourceModelA = sourceModelA;
	}

	/**
	 * @return the modelB
	 */
	public IFile getSourceModelB() {
		return sourceModelB;
	}

	/**
	 * @param modelB
	 *            the modelB to set
	 */
	public void setSourceModelB(IFile sourceModelB) {
		this.sourceModelB = sourceModelB;
	}

	/**
	 * @return the modelB
	 */
	public IFile getTargetModel() {
		return targetModel;
	}

	/**
	 * @param targetModel
	 *            the targetModel to set
	 */
	public void setTargetModel(IFile targetModel) {
		this.targetModel = targetModel;
	}

}
