# USE CASE: 5 Produce a Report of the Top 5 populated capital cities in the world, continent and region

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the Top 5 populated capital cities in the world, continent and region organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the capital cities.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for the the Top 5 populated capital cities information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request population information of the Top 5 capital cities in the world, continent and region organised by largest to smallest.
2. User searches population of the Top 5 capital cities in the world, continent and region.
3. User extracts current population of the Top 5 capital cities in the world, continent and region.
4. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0