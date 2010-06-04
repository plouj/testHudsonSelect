package com.example;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;

public class HelloWorldBuilder extends Recorder {

    public final ArrayList<Configurable> configurables;

    @DataBoundConstructor
    public HelloWorldBuilder(ArrayList<Configurable> configurables) {
        this.configurables = configurables;
    }

    @Override
    public boolean perform(AbstractBuild build, Launcher launcher, BuildListener listener) {
        return true;
    }

    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }

    @Extension // this marker indicates Hudson that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
    	
        public boolean isApplicable(Class<? extends AbstractProject> aClass) { 
            return true;
        }

	@Override
	    public HelloWorldBuilder newInstance(StaplerRequest req, JSONObject formData) {
	    HelloWorldBuilder instance = req.bindJSON(HelloWorldBuilder.class, formData);
	    save();
	    return instance;
	}

        /**
         * This human readable name is used in the configuration screen.
         */
        public String getDisplayName() {
            return "Hello World Builder";
        }
    }

	public static final class Configurable {
		public String optionA;
		public String optionB;

		@DataBoundConstructor
		    public Configurable(String optA, String optB) {
			this.optionA = optA;
			this.optionB = optB;
		}
	}

	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}
}

