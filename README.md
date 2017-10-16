# hystrix-request-caching


### Run and build
Step-1: 
./gradlew clean build

Step-2:
java -jar build/libs/sample-0.0.1-SNAPSHOT.jar

### Test
Run `curl http://localhost:{server_port}/api/hystrix`
The endpoint will return HTTP 503 error response.