# USE CASE: 4 Produce a Report on the Top 5 populated cities in the world, continent, region, country and district

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the Top 5 populated cities in the world, continent, region, country and district organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the cities.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for the Top 5 populated cities information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request Top 5 populated cities information organised by largest to smallest.
2. User searches for the Top 5 populated cities in the world, continent, region, country and district.
3. User extracts current Top 5 populated cities information in of the world, continent, region, country and district.
4. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0