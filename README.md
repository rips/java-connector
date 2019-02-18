[![Maven Central](https://img.shields.io/maven-central/v/com.ripstech.api/connector.svg?style=flat-square)](https://search.maven.org/search?q=g:%22com.ripstech.api%22%20AND%20(a:%22connector%22%20OR%20a:%22utils%22))
# RIPS Java Connector

A Java library to provide easy access to RIPS and all of its features.

## Installation

The `utils` dependency is optional.

### Gradle

```kotlin
implementation("com.ripstech.api:connector:3.1.0")
implementation("com.ripstech.api:utils:3.1.0")
```

### Maven

```xml
<dependencies>
  ...
  <dependency>
    <groupId>com.ripstech.api</groupId>
    <artifactId>connector</artifactId>
    <version>3.1.0</version>
  </dependency>
    <dependency>
      <groupId>com.ripstech.api</groupId>
      <artifactId>utils</artifactId>
      <version>3.1.0</version>
    </dependency>
  ...
</dependencies>
```

## Use locally
```bash
./gradlew clean entity-gen:run api:publishToMavenLocal utils:publishToMavenLocal
```

## Usage

### Create API-Connector

```java
String baseUrl = "https://api-3.ripstech.com";
String email = "";
String password = "";

Api api = new Api.Builder(baseUrl)
                 .withOAuthv2(username, password)
                 .build();
```

### Upload and start scan

```java
long appId = 0;
String scanName = "";
File zipFile = ...;

ScanHandler scanHandler = new ScanHandler(api, appId);
scanHandler.uploadFile(zipFile);
long scanId = scanHandler.startScan(scanName).getId();
```

### Getting issues

```java
long appId = 0;
long scanId = 0;
List<Issue> issues = new IssueHandler(api, appId, scanId)
                          .setLogger(System.out::println)
                          .getAllIssues();
```

### Error handling

#### Exceptions

```java
String version = api.status().get().orThrow(ApiException::new).getVersion();

```
```kotlin
val version = when(val result = api.status().get().result()) {
                is Failure -> throw result.exception()
                is Success -> result.value.getVersion()
              }
```

#### Functional Style

```java
Optional<String> version = api.status().get().map(Status::getVersion);

api.status().get().ifOk(status -> System.out.println(status.getVersion()));
```