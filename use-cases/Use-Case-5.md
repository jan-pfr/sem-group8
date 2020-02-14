# USE CASE: 5 Produce a Report for languages spoken in the world including the percentage of the world population  

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *the spoken language Chinese, English, Hindi, Spanish and Arabic organised by the greatest to smallest number, including the percentage of the world population* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

We know which country speaks which language. We know the overall population of the world.  Database contains current population data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for languages spoken in the world including the percentage of the world population information is sent to User.

## MAIN SUCCESS SCENARIO

1. Organisation request languages spoken in the world including the percentage of the world population organised by the greatest to smallest.
2. User searches for languages spoken in the world.
3. User extracts current spoken languages in the world.
4. User searches for current population percentage of the world.
5. User sorts languages spoken from greatest to smallest and compares it with the population of the world.
6. User provides report to organisation.

## EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0