package ca.ulaval.glo4002.med.uat.steps.state;

import static com.jayway.restassured.RestAssured.*;

import org.jbehave.core.annotations.BeforeScenario;

import ca.ulaval.glo4002.med.uat.context.MedServerRunner;

public class StatefulStep<T extends StepState> {

    private static ThreadLocal<Object> perThreadState = new ThreadLocal<>();

    protected T getInitialState() {
        return null;
    }

    @BeforeScenario
    public void createState() {
        T initialState = getInitialState();
        if (initialState != null) {
            initialState.request = given().port(MedServerRunner.DEFAULT_UAT_PORT);
            perThreadState.set(initialState);
        }
    }

    @SuppressWarnings("unchecked")
    protected T state() {
        return (T) perThreadState.get();
    }
}
