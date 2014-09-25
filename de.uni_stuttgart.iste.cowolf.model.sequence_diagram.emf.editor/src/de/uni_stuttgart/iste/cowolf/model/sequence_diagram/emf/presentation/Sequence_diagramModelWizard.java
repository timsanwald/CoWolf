/**
 */
package de.uni_stuttgart.iste.cowolf.model.sequence_diagram.emf.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Set;
import java.util.StringTokenizer;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tools.api.command.semantic.AddSemanticResourceCommand;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelectionCallbackWithConfimation;
import org.eclipse.sirius.ui.business.internal.commands.ChangeViewpointSelectionCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.ISetSelectionTarget;
import de.uni_stuttgart.iste.cowolf.model.sequence_diagram.Interaction;
import de.uni_stuttgart.iste.cowolf.model.sequence_diagram.Sequence_diagramFactory;
import de.uni_stuttgart.iste.cowolf.model.sequence_diagram.Sequence_diagramPackage;
import de.uni_stuttgart.iste.cowolf.model.sequence_diagram.emf.provider.Sequence_diagramEditPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.uml2.uml.PackageableElement;
import org.eclipse.uml2.uml.internal.impl.PackageImpl;

/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc
 * --> <!-- end-user-doc -->
 * @generated
 */
public class Sequence_diagramModelWizard extends Wizard implements INewWizard {

	private EObject root;
	/**
	 * The supported extensions for created files.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static final List<String> FILE_EXTENSIONS = Collections.unmodifiableList(Arrays.asList(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramEditorFilenameExtensions").split("\\s*,\\s*")));
	/**
	 * A formatted list of supported file extensions, suitable for display. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static final String FORMATTED_FILE_EXTENSIONS = Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");

	private static final String FILE_EXTENSION = "sequence_diagram";
	/**
	 * This caches an instance of the model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Sequence_diagramPackage sequence_diagramPackage = Sequence_diagramPackage.eINSTANCE;
	/**
	 * This caches an instance of the model factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Sequence_diagramFactory sequence_diagramFactory = sequence_diagramPackage.getSequence_diagramFactory();
	/**
	 * This is the file creation page. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected Sequence_diagramModelWizardNewFileCreationPage newFileCreationPage;
	/**
	 * This is the initial object creation page.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	protected Sequence_diagramModelWizardInitialObjectCreationPage initialObjectCreationPage;
	/**
	 * Remember the selection during initialization for populating the default container.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IStructuredSelection selection;
	/**
	 * Remember the workbench during initialization.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IWorkbench workbench;
	/**
	 * Caches the names of the types that can be created as the root object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected List<String> initialObjectNames;

	/**
	 * This just records the information.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;
		this.selection = selection;
		setWindowTitle(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
		setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(Sequence_diagramEditorPlugin.INSTANCE.getImage("full/wizban/NewSequence_diagram")));
	}

	/**
	 * Returns the names of the types that can be created as the root object.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected Collection<String> getInitialObjectNames() {
		if (initialObjectNames == null) {
			initialObjectNames = new ArrayList<String>();
			for (EClassifier eClassifier : sequence_diagramPackage
					.getEClassifiers()) {
				if (eClassifier instanceof EClass) {
					EClass eClass = (EClass) eClassifier;
					if (!eClass.isAbstract()
							&& eClass.getName().equalsIgnoreCase("Package")) {
						initialObjectNames.add(eClass.getName());
					}
				}
			}
			Collections.sort(initialObjectNames,
					CommonPlugin.INSTANCE.getComparator());
		}
		return initialObjectNames;
	}

	/**
	 * Create a new model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected EObject createInitialModel() {
		EClass eClass = (EClass) sequence_diagramPackage
				.getEClassifier(initialObjectCreationPage
						.getInitialObjectName());
		EObject rootObject = sequence_diagramFactory.create(eClass);
		Interaction interaction = sequence_diagramFactory.createInteraction();

		((org.eclipse.uml2.uml.internal.impl.PackageImpl) rootObject)
				.getPackagedElements().add(interaction);
		return rootObject;
	}

	/**
	 * Do the work after everything is specified. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean performFinish() {
		try {
			// Remember the file.
			//
			final IFile modelFile = getModelFile();

			// Do the work within an operation.
			//
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				@Override
				protected void execute(IProgressMonitor progressMonitor) {
					try {
						// Create a resource set
						//
						ResourceSet resourceSet = new ResourceSetImpl();

						// Get the URI of the model file.
						//
						URI fileURI = URI.createPlatformResourceURI(modelFile
								.getFullPath().toString(), true);

						// Create a resource for this file.
						//
						Resource resource = resourceSet.createResource(fileURI);

						// Add the initial model object to the contents.
						//
						EObject rootObject = createInitialModel();
						setRoot(rootObject);
						if (rootObject != null) {
							resource.getContents().add(rootObject);
						}

						// Save the contents of the resource to the file system.
						//
						Map<Object, Object> options = new HashMap<Object, Object>();
						options.put(XMLResource.OPTION_ENCODING,
								initialObjectCreationPage.getEncoding());
						resource.save(options);
					} catch (Exception exception) {
						Sequence_diagramEditorPlugin.INSTANCE.log(exception);
					} finally {
						progressMonitor.done();
					}
				}
			};

			getContainer().run(false, false, operation);

			// Select the new file resource in the current view.
			//
			IWorkbenchWindow workbenchWindow = workbench
					.getActiveWorkbenchWindow();
			IWorkbenchPage page = workbenchWindow.getActivePage();
			final IWorkbenchPart activePart = page.getActivePart();
			if (activePart instanceof ISetSelectionTarget) {
				final ISelection targetSelection = new StructuredSelection(
						modelFile);
				getShell().getDisplay().asyncExec(new Runnable() {
					public void run() {
						((ISetSelectionTarget) activePart)
								.selectReveal(targetSelection);
					}
				});
			}

			// create viewpoint
			IFile airdFile = modelFile.getProject().getFile(
					"representations.aird");
			if (!airdFile.exists())
				throw new Exception("could not find file:"
						+ airdFile.getLocationURI());
			URI airdFileURI = URI.createPlatformResourceURI(airdFile
					.getFullPath().toOSString(), true);
			Session session = SessionManager.INSTANCE.getSession(airdFileURI,
					new NullProgressMonitor());

			URI fileURI = URI.createPlatformResourceURI(
					modelFile.getFullPath().toString() , true);

			// adding the resource also to Sirius session
			AddSemanticResourceCommand addCommandToSession = new AddSemanticResourceCommand(
					session, fileURI, new NullProgressMonitor());
			session.getTransactionalEditingDomain().getCommandStack()
					.execute(addCommandToSession);
			session.save(new NullProgressMonitor());

			;

			// find and add viewpoint
			Set<Viewpoint> availableViewpoints = ViewpointSelection
					.getViewpoints(FILE_EXTENSION);
			if (availableViewpoints.isEmpty())
				throw new Exception(
						"Could not find viewport for fileextension "
								+ FILE_EXTENSION);

			Set<Viewpoint> viewpoints = new HashSet<Viewpoint>();
			for (Viewpoint p : availableViewpoints)
				viewpoints.add(SiriusResourceHelper.getCorrespondingViewpoint(
						session, p));

			ViewpointSelection.Callback callback = new ViewpointSelectionCallbackWithConfimation();

			RecordingCommand command = new ChangeViewpointSelectionCommand(
					session, callback, viewpoints, new HashSet<Viewpoint>(),
					true, new NullProgressMonitor());
			TransactionalEditingDomain domain = session
					.getTransactionalEditingDomain();
			domain.getCommandStack().execute(command);
			

			// create representation
			Interaction interaction = null;
			Object[] elements1 = session
					.getSemanticResources().toArray();
			Resource resource = (Resource) elements1[elements1.length-1];
					
			EList<PackageableElement> pack =((PackageImpl) resource.getContents()
					.get(0)).getPackagedElements();
			for (PackageableElement element : pack) {
				if (element instanceof Interaction) {
					interaction = (Interaction) element;
				}
			}

			EObject rootObject = interaction;
			Collection<RepresentationDescription> descriptions = DialectManager.INSTANCE
					.getAvailableRepresentationDescriptions(
							session.getSelectedViewpoints(false), rootObject);
			if (descriptions.isEmpty())
				throw new Exception(
						"Could not find representation description for object: "
								+ rootObject);
			RepresentationDescription description = descriptions.iterator()
					.next();


			DialectManager viewpointDialectManager = DialectManager.INSTANCE;
			Command createViewCommand = new CreateRepresentationCommand(
					session, description, rootObject, modelFile.getName(),
					new NullProgressMonitor());

			session.getTransactionalEditingDomain().getCommandStack()
					.execute(createViewCommand);

			SessionManager.INSTANCE.notifyRepresentationCreated(session);

			// open editor for last representation
			Collection<DRepresentation> representations = viewpointDialectManager
					.getRepresentations(description, session);
			Object[] arrayRep =  representations
					.toArray();
			DRepresentation myDiagramRepresentation = (DRepresentation) arrayRep[arrayRep.length - 1];
			DialectUIManager dialectUIManager = DialectUIManager.INSTANCE;
			dialectUIManager.openEditor(session, myDiagramRepresentation,
					new NullProgressMonitor());

			// save session and refresh workspace
			session.save(new NullProgressMonitor());
			modelFile.getProject().refreshLocal(IResource.DEPTH_INFINITE,
					new NullProgressMonitor());

			return true;
		} catch (Exception exception) {
			Sequence_diagramEditorPlugin.INSTANCE.log(exception);
			return false;
		}
	}

	/**
	 * This is the one page of the wizard.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public class Sequence_diagramModelWizardNewFileCreationPage extends
			WizardNewFileCreationPage {
		/**
		 * Pass in the selection.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public Sequence_diagramModelWizardNewFileCreationPage(String pageId,
				IStructuredSelection selection) {
			super(pageId, selection);
		}

		/**
		 * The framework calls this to see if the file is correct. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		@Override
		protected boolean validatePage() {
			if (super.validatePage()) {
				String extension = new Path(getFileName()).getFileExtension();
				if (extension == null || !FILE_EXTENSIONS.contains(extension)) {
					String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
					setErrorMessage(Sequence_diagramEditorPlugin.INSTANCE.getString(key, new Object [] { FORMATTED_FILE_EXTENSIONS }));
					return false;
				}
				return true;
			}
			return false;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public IFile getModelFile() {
			return ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName()));
		}
	}

	/**
	 * This is the page where the type of object to create is selected. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public class Sequence_diagramModelWizardInitialObjectCreationPage extends
			WizardPage {
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo initialObjectField;
		/**
		 * @generated <!-- begin-user-doc --> <!-- end-user-doc -->
		 */
		protected List<String> encodings;
		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Combo encodingField;

		/**
		 * Pass in the selection.
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public Sequence_diagramModelWizardInitialObjectCreationPage(
				String pageId) {
			super(pageId);
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.NONE); {
				GridLayout layout = new GridLayout();
				layout.numColumns = 1;
				layout.verticalSpacing = 12;
				composite.setLayout(layout);

				GridData data = new GridData();
				data.verticalAlignment = GridData.FILL;
				data.grabExcessVerticalSpace = true;
				data.horizontalAlignment = GridData.FILL;
				composite.setLayoutData(data);
			}

			Label containerLabel = new Label(composite, SWT.LEFT);
			{
				containerLabel.setText(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				containerLabel.setLayoutData(data);
			}

			initialObjectField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				initialObjectField.setLayoutData(data);
			}

			for (String objectName : getInitialObjectNames()) {
				initialObjectField.add(getLabel(objectName));
			}

			if (initialObjectField.getItemCount() == 1) {
				initialObjectField.select(0);
			}
			initialObjectField.addModifyListener(validator);

			Label encodingLabel = new Label(composite, SWT.LEFT);
			{
				encodingLabel.setText(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				encodingLabel.setLayoutData(data);
			}
			encodingField = new Combo(composite, SWT.BORDER);
			{
				GridData data = new GridData();
				data.horizontalAlignment = GridData.FILL;
				data.grabExcessHorizontalSpace = true;
				encodingField.setLayoutData(data);
			}

			for (String encoding : getEncodings()) {
				encodingField.add(encoding);
			}

			encodingField.select(0);
			encodingField.addModifyListener(validator);

			setPageComplete(validatePage());
			setControl(composite);
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected ModifyListener validator = new ModifyListener() {
				public void modifyText(ModifyEvent e) {
					setPageComplete(validatePage());
				}
			};

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected boolean validatePage() {
			return getInitialObjectName() != null && getEncodings().contains(encodingField.getText());
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		@Override
		public void setVisible(boolean visible) {
			super.setVisible(visible);
			if (visible) {
				if (initialObjectField.getItemCount() == 1) {
					initialObjectField.clearSelection();
					encodingField.setFocus();
				}
				else {
					encodingField.clearSelection();
					initialObjectField.setFocus();
				}
			}
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public String getInitialObjectName() {
			String label = initialObjectField.getText();

			for (String name : getInitialObjectNames()) {
				if (getLabel(name).equals(label)) {
					return name;
				}
			}
			return null;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		public String getEncoding() {
			return encodingField.getText();
		}

		/**
		 * Returns the label for the specified type name.
		 * <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * @generated
		 */
		protected String getLabel(String typeName) {
			try {
				return Sequence_diagramEditPlugin.INSTANCE.getString("_UI_" + typeName + "_type");
			}
			catch(MissingResourceException mre) {
				Sequence_diagramEditorPlugin.INSTANCE.log(mre);
			}
			return typeName;
		}

		/**
		 * <!-- begin-user-doc --> <!-- end-user-doc -->
		 * @generated
		 */
		protected Collection<String> getEncodings() {
			if (encodings == null) {
				encodings = new ArrayList<String>();
				for (StringTokenizer stringTokenizer = new StringTokenizer(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); ) {
					encodings.add(stringTokenizer.nextToken());
				}
			}
			return encodings;
		}
	}

	/**
	 * The framework calls this to create the contents of the wizard. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void addPages() {
		// Create a page, set the title, and the initial model file name.
		//
		newFileCreationPage = new Sequence_diagramModelWizardNewFileCreationPage("Whatever", selection);
		newFileCreationPage.setTitle(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramModelWizard_label"));
		newFileCreationPage.setDescription(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramModelWizard_description"));
		newFileCreationPage.setFileName(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
		addPage(newFileCreationPage);

		// Try and get the resource selection to determine a current directory for the file dialog.
		//
		if (selection != null && !selection.isEmpty()) {
			// Get the resource...
			//
			Object selectedElement = selection.iterator().next();
			if (selectedElement instanceof IResource) {
				// Get the resource parent, if its a file.
				//
				IResource selectedResource = (IResource)selectedElement;
				if (selectedResource.getType() == IResource.FILE) {
					selectedResource = selectedResource.getParent();
				}

				// This gives us a directory...
				//
				if (selectedResource instanceof IFolder || selectedResource instanceof IProject) {
					// Set this for the container.
					//
					newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

					// Make up a unique new name here.
					//
					String defaultModelBaseFilename = Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramEditorFilenameDefaultBase");
					String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
					String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
					for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i) {
						modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
					}
					newFileCreationPage.setFileName(modelFilename);
				}
			}
		}
		initialObjectCreationPage = new Sequence_diagramModelWizardInitialObjectCreationPage("Whatever2");
		initialObjectCreationPage.setTitle(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Sequence_diagramModelWizard_label"));
		initialObjectCreationPage.setDescription(Sequence_diagramEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
		addPage(initialObjectCreationPage);
	}

	/**
	 * Get the file from the page.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IFile getModelFile() {
		return newFileCreationPage.getModelFile();
	}

	public EObject getRoot() {
		return root;
	}

	public void setRoot(EObject rootObject) {
		this.root = rootObject;
	}
}
