package ca.ulaval.glo4002.med.uat.steps.state;

import static com.jayway.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.jbehave.core.annotations.BeforeScenario;

import ca.ulaval.glo4002.med.uat.context.MedServerRunner;

public class StatefulStep<T extends StepState> {

    private static ThreadLocal<Map<Class<?>, Object>> perThreadState = new ThreadLocal<>();

    static {
        perThreadState.set(new HashMap<>());
    }

    // Unfortunatly this cannot be abstract, JBehave needs to create
    // the class to run the @BeforeScenario
    protected T getInitialState() {
        return null;
    };

    @BeforeScenario
    public void createState() {
        T initialState = getInitialState();
        if (initialState != null) {
            initialState.request = given().port(MedServerRunner.DEFAULT_UAT_PORT);
            perThreadState.get().put(getClass(), initialState);
        }
    }

    @SuppressWarnings("unchecked")
    protected T state() {
        return (T) perThreadState.get().get(getClass());
    }
}
