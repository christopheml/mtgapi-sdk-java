# mtgapi-sdk-java

Unofficial Java SDK for using the magicthegathering.io APIs

[![Build Status](https://travis-ci.org/christopheml/mtgapi-sdk-java.svg?branch=master)](https://travis-ci.org/christopheml/mtgapi-sdk-java) [![Sonar Quality Gate](https://sonarcloud.io/api/badges/gate?key=com.github.christopheml%3Amtgapi-sdk-java)](https://sonarcloud.io/dashboard?id=com.github.christopheml%3Amtgapi-sdk-java)

This SDK aims to provide more advanced filters than those available with the API itself, by adding a filtering pass on the results returned by the API endpoint.

## Installation

Publication to Maven Central pending.

## Usage

### Querying cards

If you want all cards whose name start with "Champion", you'll write (with the help of a few static imports):
```java
CardApi cardApi = new CardApi();
List<Cards> result = cardApi.find(query()
    .name(startsWith("Champion")) 
);
``` 

### Available filters

* Card Name (case insensitive)
    * Exact match
    * Contains text
    * Starting with a prefix
