Crypto wallet App
======================
<p float="left">
  <img src="/screenshots/launcher.jpeg" width="150" /> 
   <img src="/screenshots/current_account.jpeg" width="150" />
  <img src="/screenshots/no_transactions.jpeg" width="150" /> 
</p>


## Features

The application presents a list of cryptocurrency transactions using Blockchain multiaddress API, together with their basic details.

Assumptions: 

* this app is single user only.

## Development Environment

The app is written entirely in Kotlin and uses the Gradle build system.
Latest Android Studio 3.5.3, Gradle 5.4.1, Gradle plugin 3.5.3 were used for development.


## Architecture

MVVM architecture, LiveData and Data Binding used to bind UI components in layouts to the app's data sources.
Repository later handles data operations: 
online fetching the transactions via a Retrofit client with coroutines and Moshi Json parser, 
and offline fetching from a local cache using Room with RxJava.
Ideally the fallback between remote and local data source is done in the repository layer.

##  Running the App

* Clone the repository and import `wallet` project.

* The project already makes use of the supplied xPub address.
  You can use your own xPub, copy-paste it in `gradle.properties`:  `xPubAddress` =

* To build the app, use the `gradlew clean build` command.

* Now you can deploy the application on your device.

 Run monkey runner with run_monkey.sh

##  QA Plan

* Break down the test scenarios by us use cases and functionality.
 For example: As a user I want to see a list of all my transactions.

* Run static code analysis in order to: catch code smells & vulnerabilities, but also to promote certain coding standards within the team.

* Run load tests to check how application performs under a heavy load.

* Perform regular security tests :
 - perform input validation to avoid SQL injection.
 - insecure files I/O access avoiding MODE_WORLD_READABLE/MODE_WORLD_WRITABLE, SharedPreferences that are not encrypted.
 - avoid JavaScript injection in WebViews.
 - use signature protection level on permissions for IPC communication between apps.
 - use authorization tokens rather than storing credentials on the device.

* Setup mock local service for integration tests, to avoid hitting Production during development phase.

* Performance tests & benchmarking: establish tresholds for app launch, operations (independent of network quality) to ensure a good user experience.

* If the app depended on more user input, Data-Driven tests would come in handy, as input data sets are external to the test codebase.

* Setup device farm to target more devices.


## Ideas of improvements !

### In terms of implementation:

* API response is not paginated yet. 
 Ideally, for large amount of transactions we will request the data paginated. We should implement a PagedAdapter with RecyclerView to load pages based on user interaction. 
 At this moment we only request latest 10 transactions.

Blockchain API for Multi Address does support pagination:
- Optional limit parameter to show n transactions 
- Optional offset parameter to skip the first n transactions


* In order to be mindfull of user data, we should request the list of transactions from the API with minimum information given for each transaction. In this case the API would provide another endpoint to fetch transactions individually based on 'hash'.

* Exchange rate is hardcoded, should be updated from the API.

* To gain insight on app usage and user engamenent integrate Analytics using Firebase.

* Configure Sonarqube to monitor code vulnerabilities.

### In terms of features:

* most certainly the transaction list is not immutable, so we could refresh the list in the background, at a certain interval that makes sense for the product.

* add filtering capabilities, such as transaction date or sorting.



### In terms of QA:

* More code coverage, more integration tests and tests that mock the API.

