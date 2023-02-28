# A simple demo for distributed tracing
In this example we have two Java/SpringBoot micro services that use OpenTracing and Jaeger to provide useful insight into the behavior of the system. The micro service do not use any special code and just a few basic configurations in the `application.properties` files.
Additionally a single component needs to be added in the `pom.xml` file:
```
<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-spring-jaeger-cloud-starter</artifactId>
    <version>3.3.1</version>
</dependency> 
````
With these minimal additions to a standard Java project, we already have a good starting point and can see the traces across multiple micro service.

# Running the demo
1. Run Jaeger with docker compose:
    ```
    docker compose up --detach
    ```
2. Navigate to the `inventory` folder and run the `inventory` micro service, e.g. with:
    ```
    ./mvnw spring-boot:run
    ````
3. Navigate to the `api` folder and run the `api` micro service, e.g. with:
    ```
    ./mvnw spring-boot:run
    ````

4. Open a new browser and navigate to port http://localhost:16686 to find yourself on the Jaeger dashboard