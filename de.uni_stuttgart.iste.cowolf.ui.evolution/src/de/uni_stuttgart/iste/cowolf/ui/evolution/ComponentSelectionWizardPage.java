package de.uni_stuttgart.iste.cowolf.ui.evolution;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This class provides the main wizard content for selecting model and evolution
 * direction.
 *
 * @author Michael Müller
 *
 */
public class ComponentSelectionWizardPage extends WizardPage {

    /**
     * Wizard for Model Selection.
     */
    private ComponentSelectionWizard wizard;
    /**
     * Radio button for first model.
     */
    private Button modelAButton;
    /**
     * Radio button for second model.
     */
    private Button modelBButton;

    /**
     * Label for arrow icon.
     */
    private Label arrowLabel;

    /**
     * Page providing main content for wizard.
     *
     * @param wizard
     */
    protected ComponentSelectionWizardPage(ComponentSelectionWizard wizard) {
        super("Model Selection");
        this.wizard = wizard;
    }

    @Override
    public void createControl(final Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout(3, false);
        container.setLayout(layout);

        // row for first model
        this.modelAButton = new Button(container, SWT.RADIO);
        this.modelAButton.setSelection(true);
        Label labelA = new Label(container, SWT.NONE);
        labelA.setText(this.modelToString(this.wizard.modelA));
        Button modelAChooser = new Button(container, 0);
        modelAChooser.setText("Browse ...");
        modelAChooser.addSelectionListener(this.browseWorkspace(
                this.wizard.modelA, labelA, parent.getShell()));

        // "arrow" row
        new Label(container, SWT.NONE);
        this.arrowLabel = new Label(container, SWT.NONE);
        this.arrowLabel.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING,
                true, false));
        this.arrowLabel.setFont(new Font(null, "Arial", 35, SWT.BOLD));
        this.arrowLabel.setAlignment(SWT.CENTER);
        this.arrowLabel.setText("\u2193");
        new Label(container, SWT.NONE);

        // row for second model
        this.modelBButton = new Button(container, SWT.RADIO);
        this.modelAButton.addSelectionListener(this.radioButtonChanged());
        this.modelBButton.addSelectionListener(this.radioButtonChanged());
        final Label labelB = new Label(container, SWT.NONE);
        labelB.setText(this.modelToString(this.wizard.modelB));
        Button modelBChooser = new Button(container, 0);
        modelBChooser.setText("Browse ...");
        modelBChooser.addSelectionListener(this.browseWorkspace(
                this.wizard.modelB, labelB, parent.getShell()));

        // complete wizard page
        this.setControl(container);
        this.setPageComplete(true);
    }

    /**
     * Returns which of the two models is selected.
     * 
     * @return true if first model is selected.
     */
    public boolean isFirstModelSelected() {
        return this.modelAButton.getSelection();
    }

    /**
     * Returns the string representation of a model file.
     * 
     * @param model
     *            model to get string of.
     * @return
     */
    protected String modelToString(Resource model) {
        if (model != null) {
            IPath path = new Path(model.getURI().toString());
            return path.toString();
        }
        return "";
    }

    /**
     * Returns a Selection listener for browsing the workspace.
     * 
     * @param originalModel
     * @param label
     * @param shell
     * @return returns a Selection listener
     */
    protected SelectionListener browseWorkspace(final Resource originalModel,
            final Label label, final Shell shell) {
        SelectionListener listener = new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                IFile[] files = WorkspaceResourceDialog.openFileSelection(
                        shell, "Choose model file", "Choose model file", true,
                        null, null);
                if (files.length >= 1) {
                    URI uri = URI.createPlatformResourceURI(files[0]
                            .getFullPath().toString(), true);
                    Resource model = new ResourceSetImpl().createResource(uri);
                    if (originalModel != null
                            && originalModel.equals(wizard.modelA)) {
                        wizard.modelA = model;
                    } else {
                        wizard.modelB = model;
                    }
                    System.out.println(modelToString(model));
                    System.out.println(wizard.modelB);
                    label.setText(modelToString(model));
                    label.pack();
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
                // nothing to do
            }
        };
        return listener;
    }

    /**
     * Provides SelectionListener if the selection of a radio button (group)
     * changed.
     *
     * @return
     */
    protected SelectionListener radioButtonChanged() {
        SelectionListener listener = new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                if (modelAButton.getSelection()) {
                    arrowLabel.setText("\u2193");
                } else if (modelBButton.getSelection()) {
                    arrowLabel.setText("\u2191");
                }

            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            }
        };
        return listener;
    }

}
