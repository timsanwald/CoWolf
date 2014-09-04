package de.uni_stuttgart.iste.cowolf.ui.evolution.wizard;

import java.text.DateFormat;
import java.util.Date;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Image;

import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.Model;
import de.uni_stuttgart.iste.cowolf.core.ModelAssociation.ModelVersion;

import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * This class provides the main wizard content for selecting model and evolution
 * direction.
 *
 */
public class ComponentSelectionWizardPage extends WizardPage {

	private final IFile source;
	private final Model model;
	private Text text;
	private final ModelVersionProvider baseSelection;
	private final ModelVersionProvider targetSelection;
	private TableViewer tableViewer1;
	private TableViewer tableViewer2;
	private Table table2;
	private Table table1;
	private Label lblSelectBaseVersion;
	private Label lblSelectTargetVersion;

	/**
	 * Page providing main content for wizard.
	 *
	 * @param wizard
	 */
	protected ComponentSelectionWizardPage(ComponentSelectionWizard wizard,
			IFile sourceFile, Model model) {
		super("Model Evolution");
		this.setDescription("Compare two versions of a model with SiLift.");
		this.setTitle("Compare models with each other.");
		baseSelection = new ModelVersionProvider();
		targetSelection = new ModelVersionProvider();
		this.source = sourceFile;
		this.model = model;
	}

	@Override
	public void createControl(final Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		GridLayout gl_container = new GridLayout(4, false);
		gl_container.marginWidth = 10;
		gl_container.horizontalSpacing = 10;
		gl_container.marginHeight = 10;
		container.setLayout(gl_container);

		Label label = new Label(container, SWT.NONE);
		GridData gd_label = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1,
				1);
		gd_label.horizontalIndent = 10;
		label.setLayoutData(gd_label);
		label.setText("Source model:");
		
				text = new Text(container, SWT.BORDER);
				text.setEnabled(false);
				text.setText((String) source.getFullPath().toOSString());
				text.setEditable(false);
				text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		
		lblSelectBaseVersion = new Label(container, SWT.NONE);
		lblSelectBaseVersion.setText("Select base version:");
		new Label(container, SWT.NONE);
		
		lblSelectTargetVersion = new Label(container, SWT.NONE);
		lblSelectTargetVersion.setText("Select target version");
		new Label(container, SWT.NONE);

		tableViewer1 = new TableViewer (container, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		table1 = tableViewer1.getTable();
		table1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tableViewer1.setContentProvider(baseSelection);
		tableViewer1.setLabelProvider(baseSelection);
		tableViewer1.setInput(model);
		
		tableViewer2 = new TableViewer (container, SWT.BORDER | SWT.V_SCROLL | SWT.SINGLE);
		table2 = tableViewer2.getTable();
		table2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
		tableViewer2.setContentProvider(targetSelection);
		tableViewer2.setLabelProvider(targetSelection);
		tableViewer2.setInput(model);

		if (targetSelection.size() > 0) {
			tableViewer2.setSelection(new StructuredSelection(tableViewer2.getElementAt(0)),true);
		}

		if (baseSelection.size() > 0) {
			if (baseSelection.size() == 1) {
				tableViewer1.setSelection(new StructuredSelection(tableViewer1.getElementAt(0)),true);
			} else {
				tableViewer1.setSelection(new StructuredSelection(tableViewer1.getElementAt(1)),true);
			}
		}

		setPageComplete();
	}

	public ModelVersion getBaseVersion() {
		return (ModelVersion) this.table1.getSelection()[0].getData();
	}

	public ModelVersion getTargetVersion() {
		return (ModelVersion) this.table2.getSelection()[0].getData();
	}

	public void setPageComplete() {
		this.setErrorMessage(null);
		this.setMessage(null);
		// ERRORS
		if (this.getBaseVersion() == null) {
			this.setErrorMessage("No base version is selected!");
			this.setPageComplete(false);
			return;
		}

		if (this.getTargetVersion() == null) {
			this.setErrorMessage("No target version is selected!");
			this.setPageComplete(false);
			return;
		}

		// WARNINGS
		if (this.getBaseVersion().equals(this.getTargetVersion())) {
			this.setMessage("Base version and target version are the same!",
					DialogPage.WARNING);
		}
		// INFO
		this.setPageComplete(true);
	}

	private class ModelVersionProvider extends LabelProvider implements
			IStructuredContentProvider {

		@Override
		public void dispose() {
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return model.getVersions().toArray();
		}

		@Override
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		@Override
		public String getText(Object element) {
			if (element instanceof ModelVersion) {
				ModelVersion version = (ModelVersion) element;
				Date date = new Date(version.getTimestamp());
				DateFormat df = DateFormat.getDateTimeInstance(
						DateFormat.SHORT, DateFormat.SHORT);
				return df.format(date);
			}
			return super.getText(element);
		}

		public int size() {
			return this.getElements(null).length;
		}
	}
}
