package org.arvesen.oauth.circlekid;

import com.google.inject.Guice;
import com.google.inject.Injector;
import cucumber.api.CucumberOptions;
import cucumber.runtime.Runtime;
import cucumber.runtime.RuntimeOptions;
import cucumber.runtime.RuntimeOptionsFactory;
import cucumber.runtime.io.MultiLoader;
import cucumber.runtime.io.ResourceLoader;
import cucumber.runtime.io.ResourceLoaderClassFinder;
import org.arvesen.oauth.circlekid.guice.GuiceModule;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

/**
 * Created by kjella on 28/07/16.
 */
//@CucumberOptions(features = {"user_login.feature", "create_user.feature"})
@CucumberOptions(features = {"create_user.feature"})
public class CucumberRunner {
    private Runtime runtime = null;

    public static void main(String[] vars) throws Throwable {

        //Injector injector = Guice.createInjector(new GuiceModule());
        //CucumberRunner app = injector.getInstance(CucumberRunner.class);
        CucumberRunner app = new CucumberRunner();
        app.start();
    }

    private void start() throws Exception {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        final RuntimeOptionsFactory runtimeOptionsFactory = new RuntimeOptionsFactory(this.getClass());
        final RuntimeOptions runtimeOptions = runtimeOptionsFactory.create();
        final MultiLoader resourceLoader = new MultiLoader(classLoader);
        this.runtime = this.createRuntime(resourceLoader, classLoader, runtimeOptions);
        runtime.run();
    }

    protected Runtime createRuntime(ResourceLoader resourceLoader, ClassLoader classLoader, RuntimeOptions runtimeOptions) throws InitializationError, IOException {
        ResourceLoaderClassFinder classFinder = new ResourceLoaderClassFinder(resourceLoader, classLoader);
        return new Runtime(resourceLoader, classFinder, classLoader, runtimeOptions);
    }
}
