# USE CASE: 1 Produce a Report on the Population of Countries in the world, continent and region

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the population of countries in the world, continent and region organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for the population information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request population information organised by largest to smallest.
2. User searches for the population of the countries in the world, continent and region.
3. User extracts current population information of all countries of the world, continent and region.
4. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0