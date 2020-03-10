# USE CASE: 5 Produce a Report for languages spoken in the world including the percentage of the world population  

## CHARACTERISTIC INFORMATION

### Goal in Context

As an *data scientist* I want *the spoken languages Chinese, English, Hindi, Spanish and Arabic organised by the greatest to smallest number, including the percentage of the world population* so that *I can create a report for my organisation.*

### Scope

Organisation.

### Level

Primary task.

### Preconditions

Database contains spoken languages in the world data.

### Success End Condition

A report for spoken language has been created.

### Failed End Condition

No report is produced.

### Primary Actor

User.

### Trigger

Organisation wants a report for the current spoken languages.

### MAIN SUCCESS SCENARIO

1. Organisation request a report on the current spoken languages.
2. System provides requested data
3. User extracts current spoken languages information from the system.
4. User provides report to organisation.

### EXTENSIONS

3. **Database does not connect**:
    1. User informs developer that database is not connecting.

### SUB-VARIATIONS

None.

### SCHEDULE

**DUE DATE**: Release 1.0