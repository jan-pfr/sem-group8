# USE CASE: 1 Produce a Report on the population of countries in the world, continent and region

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the population of countries in the world, continent and region organised by largest to smallest* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains current population data for the world, continent and region.

### Success End Condition

A report for population in the world, continent and region has been created.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

Organisation wants a report for the current population in the world, continents and regions.

### MAIN SUCCESS SCENARIO

1. Organisation request a report for current population of countries.
2. System provides requested data
3. User extracts current population information from the system.
4. User provides report to organisation.

### EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

### SUB-VARIATIONS

None.

### SCHEDULE

**DUE DATE**: Release 1.0