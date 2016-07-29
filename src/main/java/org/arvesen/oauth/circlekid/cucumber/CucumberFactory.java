package org.arvesen.oauth.circlekid.cucumber;

import cucumber.api.java.ObjectFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kjella on 27/07/16.
 */
public class CucumberFactory implements ObjectFactory {
    private Map<String,Class<?>> classes = new HashMap<>();

    @Override
    public void start() {
        System.out.println("Starting objectFactory...");
    }

    @Override
    public void stop() {
        System.out.println("Stopping objectFactory...");
    }

    @Override
    public boolean addClass(Class<?> aClass) {
        classes.put(aClass.getName(), aClass);
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> aClass) {
        System.out.println("Getting instance of class: " + aClass.getName());
        if (classes.containsKey(aClass.getName())) {
            return (T) classes.get(aClass.getName());
        }
        return null;
    }
}
