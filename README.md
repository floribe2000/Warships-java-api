[![Build Status](https://ci.floribe2000.de/job/Warships-java-api/job/master/badge/icon)](https://ci.floribe2000.de/job/Warships-java-api/job/master/)

# Warships-java-api
A java interface to access the official World of Warships API

**This is a community project and not associated with Wargaming.net!**

For details about the official Wargaming API see their official documentation at https://developers.wargaming.net/documentation/

## Introduction

This project tries to provide a java interface to the official Wargaming web api.
It only acts as an interface to make creating requests easier, all data comes from the api and is not changed by this library except for the deserialization of the received data into java objects.

Currently this is a work in progress. There might be errors and unexpected results, please report them to the issues section.
However, we are not responsible for api errors from the official api and can't fix them.

## How to use

Currently, you have to clone and compile this by yourself.
We are working on providing this as maven repository.

To use this library you have to get an api key for the Wargaming api first. 
To do this, go to https://developers.wargaming.net and follow the instructions.
Keep in mind to read the api conditions! 
The terms and conditions set by Wargaming apply for you, no matter if you use this library to connect to the api or connect to it directly with your own implementation!

If you have an api key, you can start using this library.

To initialize the api builder, use the following code:
`ApiBuilder.Companion.createInstance(apiKey)`
Where you provide your api key as string.
If you use a server api key you should also provide the ClientType argument when creating a new instance to allow the api to use the increased rate limit.

After initializing the ApiBuilder, you can start sending requests, an example might look like this:

`Players response = PlayersRequest.createRequest().region(Region.EU).searchText("<username>").fetch();`

In this case the request is used for the EU server, for other servers just replace the parameter of the region() method.

You can also create and store requests without executing them or to execute them multiple times.
To do this, just don't call the fetch() method and store the request to a variable of your choice.
Afer that you can replace some parameters or execute the request whenever you want.
You can also replace parameters after executing it and execute it again with changed parameters.

## Rate limit

Requests to the Wargaming api are always rate limited.
This library implements basic rate limiting that should avoid running into rate limit errors.
Keep in mind that all requests are executed synchronously and a thread is blocked until the rate limit allows the request to be sent!

You can manually disable the rate limiting by calling `ApiBuilder.getInstanceWithName("InstanceName").getRateLimiter().disable();`.
Replace InstanceName with the custom name of your instance.

If you want to disable the rate limiting right at the creation of the instance, you can do that by using the 
`createInstance(String apiKey, boolean rateLimitEnabled)` method (There are multiple methods with that parameter, use the one that accepts all parameters you need).

Disabling the rate limiter will only work of there are no threads waiting!

**It is not recommended to disable the rate limiter because you might get request limit exceeded errors from the api!**

You cannot circumvent the api rate limiting with this library!



## Dependencies

This project requires Java 8 or higher.

Dependencies are managed by Maven.

The following dependencies are used by this project:

- GSON
  - Version 2.8.6

- Lombok
  - Version 1.18.12

- JUnit
  - Version 4.13
  
- slf4j-api
  - Version 1.7.30
  
- Apache Commons
  - Version 4.4




