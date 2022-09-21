# Grid Java

#### Grid Lib ####

The Grid Library was developed at the [National Geospatial-Intelligence Agency (NGA)](http://www.nga.mil/) in collaboration with [BIT Systems](https://www.caci.com/bit-systems/). The government has "unlimited rights" and is releasing this software to increase the impact of government investments by providing developers with the opportunity to take things in new directions. The software use, modification, and distribution rights are stipulated within the [MIT license](http://choosealicense.com/licenses/mit/).

### Pull Requests ###
If you'd like to contribute to this project, please make a pull request. We'll review the pull request and discuss the changes. All pull request contributions to this project will be released under the MIT license.

Software source code previously released under an open source license and then modified by NGA staff is considered a "joint work" (see 17 USC § 101); it is partially copyrighted, partially public domain, and as a whole is protected by the copyrights of the non-government authors and must be released according to the terms of the original open source license.

### About ###

[Grid](http://ngageoint.github.io/grid-java/) is a Java library providing common geospatial reference system grid functionality.

### Usage ###

View the latest [Javadoc](http://ngageoint.github.io/grid-java/docs/api/)

#### Military Grid Reference System ####

[MGRS](https://github.com/ngageoint/mgrs-java) is a Java library providing Military Grid Reference System functionality, a geocoordinate standard used by NATO militaries for locating points on Earth.

#### Global Area Reference System ####

[GARS](https://github.com/ngageoint/gars-java) is a Java library providing Global Area Reference System functionality, a standardized geospatial reference system for areas.

### Installation ###

Pull from the [Maven Central Repository](http://search.maven.org/#artifactdetails|mil.nga|grid|1.1.0|jar) (JAR, POM, Source, Javadoc)

    <dependency>
        <groupId>mil.nga</groupId>
        <artifactId>grid</artifactId>
        <version>1.1.0</version>
    </dependency>

### Build ###

[![Build & Test](https://github.com/ngageoint/grid-java/workflows/Build%20&%20Test/badge.svg)](https://github.com/ngageoint/grid-java/actions/workflows/build-test.yml)

Build this repository using Eclipse and/or Maven:

    mvn clean install

### Remote Dependencies ###

* [Simple Features](https://github.com/ngageoint/simple-features-java) (The MIT License (MIT)) - Simple Features Lib
* [Color](https://github.com/ngageoint/color-java) (The MIT License (MIT)) - Color Lib
