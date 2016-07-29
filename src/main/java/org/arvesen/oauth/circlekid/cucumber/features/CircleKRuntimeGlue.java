package org.arvesen.oauth.circlekid.cucumber.features;

import cucumber.api.StepDefinitionReporter;
import cucumber.runtime.DuplicateStepDefinitionException;
import cucumber.runtime.Glue;
import cucumber.runtime.HookDefinition;
import cucumber.runtime.RuntimeGlue;
import cucumber.runtime.StepDefinition;
import cucumber.runtime.StepDefinitionMatch;
import cucumber.runtime.UndefinedStepsTracker;
import cucumber.runtime.xstream.LocalizedXStreams;
import gherkin.I18n;
import gherkin.formatter.model.Step;

import java.util.List;

/**
 * Created by kjella on 27/07/16.
 */
public class CircleKRuntimeGlue implements Glue {

    private UndefinedStepsTracker tracker;
    private LocalizedXStreams localizedXStreams;

    public CircleKRuntimeGlue() {

    }

/*    public CircleKRuntimeGlue(UndefinedStepsTracker tracker, LocalizedXStreams localizedXStreams) {
        this.tracker = tracker;
        this.localizedXStreams = localizedXStreams;
    }
*/
    @Override
    public void addStepDefinition(StepDefinition stepDefinition) throws DuplicateStepDefinitionException {

    }

    @Override
    public void addBeforeHook(HookDefinition hookDefinition) {

    }

    @Override
    public void addAfterHook(HookDefinition hookDefinition) {

    }

    @Override
    public List<HookDefinition> getBeforeHooks() {
        return null;
    }

    @Override
    public List<HookDefinition> getAfterHooks() {
        return null;
    }

    @Override
    public StepDefinitionMatch stepDefinitionMatch(String featurePath, Step step, I18n i18n) {
        return null;
    }

    @Override
    public void reportStepDefinitions(StepDefinitionReporter stepDefinitionReporter) {

    }

    @Override
    public void removeScenarioScopedGlue() {

    }
}
