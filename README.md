# jobrunr-db-loss

A small minimalist project to demonstrate a shortcoming of JobRunr, namely its lack of robustness when there's a
temporary database outage.

## The test

The test consists in:

- Launch a fully working instance of Postgres.
- Launch this Spring Boot Server.
- The `JobService` defines a recurring job that should be executed every 10 seconds.
- Wait a little bit to check that the job is properly executed a few times.
- Shut down the database for a few seconds until JobRunr has a problem because of the temporary unavailability of the DB
    - => We get the error message "JobRunr encountered a problematic exception. Please create a bug report (if possible,
      provide the code to reproduce this and the stacktrace) - Processing will continue."
    - => We have a stack trace that looks like "org.jobrunr.storage.StorageException:
      java.sql.SQLTransientConnectionException: HikariPool-1 - Connection is not available, request timed out after
      30003ms."
    - => And finally we have those 2 log messages from JobRunr: "JobRunr BackgroundJobServer shutdown requested -
      waiting for jobs to finish (at most 10 seconds)" and "BackgroundJobServer and BackgroundJobPerformers stopped"
- Re-launch the DB

* What we see: JobRunr has definitely stopped working and the recurring jobs are never executed again unless the
  application is fully re-started.
* What we would have expected: Of course JobRunr would not function during the DB outage but should automatically come
  back alive when the DB is up again.

## Comparison

For comparison purposes, this minimalist project includes another service (`OtherService`) that schedules a periodic
task with the default mechanisms of Spring Boot (the `@Schedule` annotation). For the purpose of the test, this service
makes a call to the DB. In this case, the scheduling dos not work while the DB is temporarily unavailable (as expected).
However, when the DB is up again, the "business logic" restarts to work again as soon as the connection to the DB is up
again.

## Versions

This behavior has been tested with:

- Postgres 14
- JobRunr 6.0.0
- Spring Boot 2.7.x
- Java 11 Temurin