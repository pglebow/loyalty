# Loyalty Service Specification

## Overview
This service consumes orderShipped events and credits the user's loyalty account with a number of points proportional
to the amount paid.  When an orderReturn event is consumed, the user's loyalty account is debited with a number points
proportional to the amount paid.

## Implementation Details

### Events

#### orderShipped Event
This event contains these data elements
* Event type
* Sequence number
* order ID
* order amount
* order currency
* customer ID
* timestamp in UTC

#### orderReturn Event
This event contains these data elements
* Event type
* Sequence number
* order ID
* order amount
* order currency
* customer ID
* timestamp in UTC

### Languages, frameworks
The system is implemented in the most recent version of Java, and uses the latest version of the Spring framework.
Apache Kafka is the event stream in use.
Postgres is the database in use.
Apache maven is used for building and dependency management.

### Notes
Extensive unit and integration tests are important.