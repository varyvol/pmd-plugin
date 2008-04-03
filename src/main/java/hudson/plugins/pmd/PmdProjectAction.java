package hudson.plugins.pmd;

import hudson.model.AbstractProject;
import hudson.plugins.pmd.util.AbstractProjectAction;

/**
 * Entry point to visualize the PMD trend graph in the project screen.
 * Drawing of the graph is delegated to the associated
 * {@link PmdResultAction}.
 *
 * @author Ulli Hafner
 */
public class PmdProjectAction extends AbstractProjectAction<PmdResultAction> {
    /** Unique identifier of this class. */
    private static final long serialVersionUID = -654316141132780561L;

    /**
     * Instantiates a new find bugs project action.
     *
     * @param project
     *            the project that owns this action
     */
    public PmdProjectAction(final AbstractProject<?, ?> project) {
        super(project, PmdResultAction.class, PmdDescriptor.PMD_ACTION_LOGO, PmdDescriptor.PLUGIN_NAME);
    }

    /** {@inheritDoc} */
    public String getDisplayName() {
        return Messages.PMD_ProjectAction_Name();
    }

    /** {@inheritDoc} */
    public String getUrlName() {
        return PmdDescriptor.PLUGIN_NAME;
    }

    /** {@inheritDoc} */
    @Override
    protected String getCookieName() {
        return "PMD_displayMode";
    }
}

