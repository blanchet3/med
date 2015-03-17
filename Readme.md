Med
===

This is a sample project to show the full stack integration of jbehave -> rest -> DDD -> hibernate.

To run the tests : mvn test
To run jbehave and integration tests too : mvn integration-test
To run the server : For now exec:java does NOT work, you need to open it in eclipse and run the RestMain class.

By default (and because I haven't made it configurable yet), the application will run in the Demo context. This means you get a couple of patients pre-filled, see the DemoContext file.

Here is a sample request you can try :

_POST http://localhost:8080/patients/1234/prescriptions_
```
{
  "din": "234",
  "physician": "890u3",
  "date": "2012-04-07T17:00:00.000+0000",
  "renewals": 3
}
```

Things to fix
=============

  * The exec:java to start the server
  * I'm not sure the EntityManagerProvider is in the right project.
  * Create An architecture guide
