# API Gateway

Spring Cloud Gateway for the SOA DevOps Incident Management System.

## Port

`8080`

## Current test route

Requests to:

`/api/test/**`

are routed through Eureka to the `test-service`.

Example:

`http://localhost:8080/api/test/hello`

This route is only for validating Gateway + Eureka + service discovery. It will be replaced/expanded with the real microservice routes later.
