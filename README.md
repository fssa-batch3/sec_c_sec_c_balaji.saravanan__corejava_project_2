                                                   **POLITIFACT**

##Table: Party


| Column         | Data Type     | Constraints                                |
|----------------|---------------|--------------------------------------------|
| partyId        | INT           | AUTO_INCREMENT, PRIMARY KEY                |
| partyName      | VARCHAR(100)  | NOT NULL                                   |
| partyImageUrl  | VARCHAR(255)  | NOT NULL                                   |
| created_at     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP                  |
| updated_at     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE        |



##Table: Election

| Column         | Data Type     | Constraints                                |
|----------------|---------------|--------------------------------------------|
| id             | INT           | AUTO_INCREMENT, PRIMARY KEY                |
| electionYear   | INT           | NOT NULL                                   |
| electionType   | VARCHAR(100)  | NOT NULL                                   |
| created_at     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP                 |
| updated_at     | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE       |



##Table: Constituency


| Column             | Data Type     | Constraints                                    |
|--------------------|---------------|------------------------------------------------|
| constituencyID     | INT           | AUTO_INCREMENT, PRIMARY KEY                    |
| constituencyName   | VARCHAR(100)  | NOT NULL                                       |
| districtName       | VARCHAR(100)  | NOT NULL                                       |
| constituencyNumber | INT           | NOT NULL                                       |
| electionTypeId     | INT           | NOT NULL, FOREIGN KEY to Election(id)          |
| created_at         | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP                     |
| updated_at         | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE           |

##Table: Leaders

| Column                  | Data Type     | Constraints                                        |
|-------------------------|---------------|----------------------------------------------------|
| id                      | INT           | AUTO_INCREMENT, PRIMARY KEY                        |
| name                    | VARCHAR(255)  | NOT NULL                                           |
| position                | VARCHAR(100)  | NOT NULL                                           |
| partyId                 | INT           | NOT NULL, FOREIGN KEY to Party(partyId)            |
| experience              | DOUBLE        | NOT NULL                                           |
| occupation              | VARCHAR(100)  | NOT NULL                                           |
| counstuencyId           | INT           | NOT NULL, FOREIGN KEY to Constituency(constituencyID) |
| descriptionOfBirth      | TEXT          |                                                    |
| descriptionOfEducation  | TEXT          |                                                    |
| descriptionOfPastWorkExperience | TEXT  |                                                    |
| descriptionOfPolitics   | TEXT          |                                                    |
| descriptionOffamily     | TEXT          |                                                    |
| descriptionOfIncome     | TEXT          |                                                    |
| imageUrl                | VARCHAR(255)  | NOT NULL                                           |
| affidavitId             | INT           | NOT NULL                                           |
| created_at              | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP                          |
| updated_at              | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE                |


##Table: Affidavit

| Column       | Data Type     | Constraints                                  |
|--------------|---------------|----------------------------------------------|
| id           | INT           | AUTO_INCREMENT, PRIMARY KEY                  |
| electionId   | INT           | NOT NULL, FOREIGN KEY to Election(id)        |
| LeaderId     | INT           | NOT NULL, FOREIGN KEY to Leaders(id)         |
| affidateUrl  | VARCHAR(255)  | NOT NULL                                     |
| created_at   | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP                    |
| updated_at   | TIMESTAMP     | DEFAULT CURRENT_TIMESTAMP ON UPDATE          |
