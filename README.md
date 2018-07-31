# RIPS Java Connector

A Java library to provide easy access to RIPS and all of its features.

## Installation

### Gradle

```groovy
implementation 'com.ripstech:api-connector-java2:2.5.0'
```

### Maven

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.ripstech</groupId>
    <artifactId>api-connector-java2</artifactId>
    <version>2.5.0</version>
  </dependency>
  ...
</dependencies>
```

## Use locally
```bash
gradle publishToMavenLocal
```

## Usage

### Create API-Connector

```java
String baseUrl = "https://api-2.ripstech.com";
String username = "";
String password = "";

Api api = new Api.Builder(baseUrl)
                 .withOAuthv2(username, password)
                 .build();
```

### Upload and start scan

```java
int appId = 0;
String scanName = "";
File zipFile = ...;


int uploadId = api.application(appId)
                  .uploads()
                  .post(zipFile)
                  .orThrow(ApiException::new)
                  .getId();

int scanId = api.application(appId)
                .scans()
                .post(ScanPost.createPost(
                  scan(scanName,
                       upload(uploadId)))
                .orThrow(ApiException::new)
                .getId();
```

### Getting issues

```java
int appId = 0;
int scanId = 0;
List<Issue> issues = ApiUtils.getScanIssues(api.application(appId).scans(),
                                            api.application(appId).scan(scanId).issues(),
                                            scanId,
                                            System.out::println)
                             .get(5, TimeUnit.HOURS);
```

### Error handling

#### Exceptions

```java
try {
    String version = api.status().get().orThrow(ApiException::new).getVersion();
} catch(ApiException e) {
    e.printStackTrace();
}
```

#### Functional Style

```java
Optional<String> version = api.status().get().map(Status::getVersion);

api.status().get().ifOk(status -> System.out.println(status.getVersion()));
```