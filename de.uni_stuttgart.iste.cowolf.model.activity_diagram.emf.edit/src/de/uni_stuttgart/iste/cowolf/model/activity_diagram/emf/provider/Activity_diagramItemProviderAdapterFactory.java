/**
 */
package de.uni_stuttgart.iste.cowolf.model.activity_diagram.emf.provider;

import de.uni_stuttgart.iste.cowolf.model.activity_diagram.util.Activity_diagramAdapterFactory;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Activity_diagramItemProviderAdapterFactory extends Activity_diagramAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Activity_diagramItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ActivityDiagram} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivityDiagramItemProvider activityDiagramItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ActivityDiagram}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActivityDiagramAdapter() {
		if (activityDiagramItemProvider == null) {
			activityDiagramItemProvider = new ActivityDiagramItemProvider(this);
		}

		return activityDiagramItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Start} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StartItemProvider startItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Start}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createStartAdapter() {
		if (startItemProvider == null) {
			startItemProvider = new StartItemProvider(this);
		}

		return startItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Action} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionItemProvider actionItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createActionAdapter() {
		if (actionItemProvider == null) {
			actionItemProvider = new ActionItemProvider(this);
		}

		return actionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Decision} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionItemProvider decisionItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Decision}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDecisionAdapter() {
		if (decisionItemProvider == null) {
			decisionItemProvider = new DecisionItemProvider(this);
		}

		return decisionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Split} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SplitItemProvider splitItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Split}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSplitAdapter() {
		if (splitItemProvider == null) {
			splitItemProvider = new SplitItemProvider(this);
		}

		return splitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Join} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinItemProvider joinItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.Join}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createJoinAdapter() {
		if (joinItemProvider == null) {
			joinItemProvider = new JoinItemProvider(this);
		}

		return joinItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.End} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EndItemProvider endItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.End}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEndAdapter() {
		if (endItemProvider == null) {
			endItemProvider = new EndItemProvider(this);
		}

		return endItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ArrowAfterStart} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrowAfterStartItemProvider arrowAfterStartItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ArrowAfterStart}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createArrowAfterStartAdapter() {
		if (arrowAfterStartItemProvider == null) {
			arrowAfterStartItemProvider = new ArrowAfterStartItemProvider(this);
		}

		return arrowAfterStartItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.DecisionArrow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionArrowItemProvider decisionArrowItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.DecisionArrow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createDecisionArrowAdapter() {
		if (decisionArrowItemProvider == null) {
			decisionArrowItemProvider = new DecisionArrowItemProvider(this);
		}

		return decisionArrowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.AfterActionArrow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AfterActionArrowItemProvider afterActionArrowItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.AfterActionArrow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAfterActionArrowAdapter() {
		if (afterActionArrowItemProvider == null) {
			afterActionArrowItemProvider = new AfterActionArrowItemProvider(this);
		}

		return afterActionArrowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.SplitArrow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SplitArrowItemProvider splitArrowItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.SplitArrow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSplitArrowAdapter() {
		if (splitArrowItemProvider == null) {
			splitArrowItemProvider = new SplitArrowItemProvider(this);
		}

		return splitArrowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.JoinArrow} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JoinArrowItemProvider joinArrowItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.JoinArrow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createJoinArrowAdapter() {
		if (joinArrowItemProvider == null) {
			joinArrowItemProvider = new JoinArrowItemProvider(this);
		}

		return joinArrowItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ArrowAfterJoin} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrowAfterJoinItemProvider arrowAfterJoinItemProvider;

	/**
	 * This creates an adapter for a {@link de.uni_stuttgart.iste.cowolf.model.activity_diagram.ArrowAfterJoin}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createArrowAfterJoinAdapter() {
		if (arrowAfterJoinItemProvider == null) {
			arrowAfterJoinItemProvider = new ArrowAfterJoinItemProvider(this);
		}

		return arrowAfterJoinItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void dispose() {
		if (activityDiagramItemProvider != null) activityDiagramItemProvider.dispose();
		if (startItemProvider != null) startItemProvider.dispose();
		if (actionItemProvider != null) actionItemProvider.dispose();
		if (decisionItemProvider != null) decisionItemProvider.dispose();
		if (splitItemProvider != null) splitItemProvider.dispose();
		if (joinItemProvider != null) joinItemProvider.dispose();
		if (endItemProvider != null) endItemProvider.dispose();
		if (arrowAfterStartItemProvider != null) arrowAfterStartItemProvider.dispose();
		if (decisionArrowItemProvider != null) decisionArrowItemProvider.dispose();
		if (afterActionArrowItemProvider != null) afterActionArrowItemProvider.dispose();
		if (splitArrowItemProvider != null) splitArrowItemProvider.dispose();
		if (joinArrowItemProvider != null) joinArrowItemProvider.dispose();
		if (arrowAfterJoinItemProvider != null) arrowAfterJoinItemProvider.dispose();
	}

}