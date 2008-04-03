package hudson.plugins.pmd.util;

import hudson.maven.AbstractMavenProject;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.util.FormFieldValidator;

import java.io.IOException;

import javax.servlet.ServletException;

import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.StaplerResponse;

/**
 * Base class for a Hudson plug/in descriptor.
 */
public abstract class PluginDescriptor extends BuildStepDescriptor<Publisher> {
    /**
     * Creates a new instance of <code>PluginDescriptor</code>.
     *
     * @param clazz
     *            the type of the publisher
     */
    public PluginDescriptor(final Class<? extends Publisher> clazz) {
        super(clazz);
    }

    /** {@inheritDoc} */
    @Override
    public final String getHelpFile() {
        return "/plugin/" + getPluginName() + "/help.html";
    }

    /**
     * Returns the name of the plug-in.
     *
     * @return the name of the plug-in
     */
    protected abstract String getPluginName();

    /**
     * Performs on-the-fly validation on the file mask.
     *
     * @param request
     *            Stapler request
     * @param response
     *            Stapler response
     */
    public final void doCheckPattern(final StaplerRequest request, final StaplerResponse response) throws IOException, ServletException {
        new FormFieldValidator.WorkspaceFileMask(request, response).process();
    }

    /**
     * Performs on-the-fly validation on the bugs threshold.
     *
     * @param request
     *            Stapler request
     * @param response
     *            Stapler response
     */
    public final void doCheckThreshold(final StaplerRequest request, final StaplerResponse response) throws IOException, ServletException {
        new ThresholdValidator(request, response).process();
    }

    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public final boolean isApplicable(final Class<? extends AbstractProject> jobType) {
        return !AbstractMavenProject.class.isAssignableFrom(jobType);
    }
}
