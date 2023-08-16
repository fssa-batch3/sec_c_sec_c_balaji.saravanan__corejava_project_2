CREATE TABLE Party (
    partyId INT AUTO_INCREMENT PRIMARY KEY,
    partyName VARCHAR(100) NOT NULL,
    partyImageUrl VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Election (
    id INT AUTO_INCREMENT PRIMARY KEY,
    electionYear INT NOT NULL,
    electionType VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);



CREATE TABLE Constituency (
    constituencyID INT AUTO_INCREMENT PRIMARY KEY,
    constituencyName VARCHAR(100)  NOT NULL,
    districtName VARCHAR(100) NOT NULL,
    constituencyNumber INT NOT NULL,
    electionTypeId INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (electionTypeId) REFERENCES Election(id)
);

CREATE TABLE Leader (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    position VARCHAR(100) NOT NULL,
    partyId INT NOT NULL,
    experience DOUBLE NOT NULL,
    occupation VARCHAR(100) NOT NULL,
    counstuencyId INT NOT NULL,
    descriptionOfBirth TEXT,
    descriptionOfEducation TEXT,
    descriptionOfPastWorkExperience TEXT,
    descritionOfpolitics TEXT,
    descriptionOffamily TEXT,
    descriptionOfIncome TEXT,
    imageUrl VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (partyId) REFERENCES Party(partyId),
    FOREIGN KEY (counstuencyId) REFERENCES Constituency(constituencyID)
  
);


CREATE TABLE Affidavit (

    id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    electionId INT NOT NULL,
    LeaderId INT NOT NULL,
    affidateUrl VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (electionId) REFERENCES Election(id),
    FOREIGN KEY (LeaderId) REFERENCES Leader(id)
);



INSERT INTO Election (electionYear, electionType) VALUES
    (2023, 'LOCAL_ELECTION'),
    (2023, 'GENERAL_ELECTION'),
    (2023, 'ASSEMBLY_ELECTION');