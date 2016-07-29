package org.arvesen.oauth.circlekid.guice;

import com.google.inject.AbstractModule;
import org.arvesen.oauth.circlekid.cucumber.features.RegisterUserStepDefinitions;

/**
 * Created by kjella on 29/07/16.
 */
public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RegisterUserStepDefinitions.class).asEagerSingleton();
    }
}
