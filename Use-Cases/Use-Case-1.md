# USE CASE: 1 Produce a Report on the Population of Countries of a Given Role

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *User* I want *goal* so that *reason.*

### Scope

Company.

### Level

Primary task.

### Preconditions

We know the role.  Database contains current employee data.

### Success End Condition

A report is available to provide.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

A request for report information is sent to User.

## MAIN SUCCESS SCENARIO

1. Report request population information for a given country.
2. User captures name of the role to get Population information.
3. User extracts current poplation information of all countries of the given role.
4. User provides report to organisation.

## EXTENSIONS

3. **Country does not exist**:
    1. User informs organisation no country exists.

## SUB-VARIATIONS

None.

## SCHEDULE

**DUE DATE**: Release 1.0