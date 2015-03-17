package ca.ulaval.glo4002.med.uat.context;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStories;

import ca.ulaval.glo4002.med.interfaces.rest.server.MedServer;

public class MedServerRunner {

    public final static int DEFAULT_UAT_PORT = 8181;

    private static MedServer server;
    private static UatContext context;

    @BeforeStories
    public static void startServer() throws Exception {
        context = new UatContext();
        context.apply();
        server = new MedServer();
        server.start(DEFAULT_UAT_PORT);
    }

    @BeforeScenario
    public void reinitializeData() {
        context.reinitialize();
    }

    @AfterStories
    public static void killServer() throws Exception {
        if (server != null) {
            server.stop();
        }
    }
}
