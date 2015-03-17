package ca.ulaval.glo4002.med.interfaces;

import java.util.Arrays;

import ca.ulaval.glo4002.med.context.DemoContext;
import ca.ulaval.glo4002.med.interfaces.rest.resources.filters.EntityManagerContextFilter;
import ca.ulaval.glo4002.med.interfaces.rest.server.MedServer;

public class RestMain {

    private final static int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        new DemoContext().apply();
        MedServer server = new MedServer();
        server.start(DEFAULT_PORT, Arrays.asList(EntityManagerContextFilter.class));
        server.join();
    }

    private RestMain() {
    }
}
