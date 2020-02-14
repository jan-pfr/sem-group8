# USE CASE: 4 Produce a Report of the population of people, people living in cities and people not living in cities for each continent, region and country
## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the population of people, people living in cities and people not living in cities for each continent, region and country organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know the population of the world. We know the population of cities.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for the population of people, people living in cities and people not living in cities information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request population information of population of people, people living in cities and people not living in cities organised by largest to smallest for each continent, region and country.
2. User searches population of population of people, people living in cities and people not living in cities for each continent, region and country.
3. User extracts current population of people, people living in cities and people not living in cities for each continent, region and country.
4. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0