package ca.ulaval.glo4002.med.interfaces;

import ca.ulaval.glo4002.med.context.DemoContext;
import ca.ulaval.glo4002.med.interfaces.rest.server.MedServer;

public class RestMain {

    private final static int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        new DemoContext().apply();
        MedServer server = new MedServer();
        server.start(DEFAULT_PORT);
        server.join();
    }

    private RestMain() {
    }
}
