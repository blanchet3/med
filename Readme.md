Med
===

This is a sample project to show the full stack integration of jbehave -> rest -> DDD -> hibernate.

The hibernate persistence is missing for now, it's only InMemory.

To run the tests : mvn test
To run jbehave tests too : mvn integration-test (NOT working for now, for some reason)
To execute the projet: mvn exec:java (this will start the server on port 8080)

By default (and because I haven't configured anything else yet), the application will run in the Demo context. This means you get a couple of patients pre-filled, see the DemoContext file.

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

This is not yet completed.
