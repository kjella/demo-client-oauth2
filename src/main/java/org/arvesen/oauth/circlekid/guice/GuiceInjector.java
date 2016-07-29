package org.arvesen.oauth.circlekid.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;

/**
 * Created by kjella on 29/07/16.
 */
public class GuiceInjector implements InjectorSource {


        @Override
        public Injector getInjector() {
            return Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new GuiceModule());
        }

}
