package ca.ulaval.glo4002.med.applicationServices.shared;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceLocator {

    private static ServiceLocator instance;
    private static final ReentrantLock lock = new ReentrantLock();

    private HashMap<Class<?>, Object> services;

    public static ServiceLocator getInstance() {
        // Théoriquement la seule façon 100% safe de faire ça. C'est volontairement laid
        // et overkill, ce n'est pas simple la concurrence même si ici c'est plus ou moins nécessaire =)
        if (instance == null) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ServiceLocator();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public static void reset() {
        // C'est un lock "pessimiste", mais on préfère ça vu que reset ne sera pas vraiment utilisé...
        lock.lock();
        try {
            instance = null;
        } finally {
            lock.unlock();
        }
    }

    private ServiceLocator() {
        services = new HashMap<Class<?>, Object>();
    }

    // Pour les 2 méthodes suivantes on laisse faire les lock pour simplifier.

    @SuppressWarnings("unchecked")
    public <T> T resolve(Class<T> service) {
        if (!services.containsKey(service)) {
            throw new UnregisteredServiceException(service);
        }

        return (T) services.get(service);

    }

    public <T> void register(Class<T> service, T implementation) {
        if (services.containsKey(service)) {
            throw new DoubleServiceRegistrationException(service);
        }
        services.put(service, implementation);
    }
}
