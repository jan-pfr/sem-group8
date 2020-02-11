# USE CASE: 3 Produce a Report of the population of cities in the world, continent, region, country and district

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the population of cities in the world, continent, region, country and district organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the population of cities in the world, continent, region, country and district.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for the population of cities in the world, continent, region, country and district information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request population information of cities in the world, continent, region, country and district organised by largest to smallest.
2. User searches population of cities in the world, continent, region, country and district.
3. User extracts current population of cities in the world, continent, region, country and district.
4. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0