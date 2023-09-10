CREATE DATABASE StudentManagement;
GO
USE StudentManagement;
GO
CREATE TABLE dbo.[Notification]
    (
        NotificationId     INT           IDENTITY(1, 1) PRIMARY KEY,
        Content            NVARCHAR(MAX) NOT NULL,
        InitializationDate DATE          NOT NULL,
        Author             NVARCHAR(100) NOT NULL,
        ModifiedDate       DATE          NOT NULL
    );
GO
CREATE TABLE dbo.LevelAccount
    (
        LevelAccountId INT         IDENTITY(1, 1) PRIMARY KEY,
        LevelDescribe  VARCHAR(20) NOT NULL,
        ModifiedDate   DATE        NOT NULL
    );
GO
CREATE TABLE dbo.[AdminAccount]
    (
        AdminAccountId INT         IDENTITY(1, 1) PRIMARY KEY,
        Username       VARCHAR(50) NOT NULL,
        Password       VARCHAR(50) NOT NULL,
        Email          CHAR(50),
        PhoneNumber    CHAR(10),
        LevelAccountId INT         NOT NULL,
        ModifiedDate   DATE        NOT NULL
    );
GO
CREATE TABLE dbo.UserAccount
    (
        UserAccountId  INT         IDENTITY(1, 1) PRIMARY KEY,
        Username       VARCHAR(50) NOT NULL,
        Password       VARCHAR(50) NOT NULL,
        LevelAccountId INT         NOT NULL,
        ModifiedDate   DATE        NOT NULL
    );
GO
CREATE TABLE dbo.Rating
    (
        RatingId     INT          IDENTITY(1, 1) PRIMARY KEY,
        RatingName   NVARCHAR(50) NOT NULL,
        Certificate  BIT          NOT NULL,
        ModifiedDate DATE         NOT NULL
    );
GO

CREATE TABLE dbo.Class
    (
        ClassId            INT          IDENTITY(1, 1) PRIMARY KEY,
        ClassName          NVARCHAR(50) NOT NULL,
        MonitorId          INT,
        ClassHeadTeacherId INT,
        ModifiedDate       DATE         NOT NULL
    );
GO
CREATE TABLE dbo.Student
    (
        StudentId      INT            IDENTITY(1, 1) PRIMARY KEY,
        Photo          VARBINARY(MAX) NOT NULL,
        FirstName      NVARCHAR(50)   NOT NULL,
        MiddleName     NVARCHAR(50)   NOT NULL,
        LastName       NVARCHAR(50)   NOT NULL,
        BirthDate      DATE           NOT NULL,
        Gender         BIT            NOT NULL,
        [Address]      NVARCHAR(100)  NOT NULL,
        PhoneNumber    CHAR(12)       NOT NULL,
        Email          CHAR(30)       NOT NULL,
        Identifier     CHAR(12)
            UNIQUE NOT NULL,
        AdmissionDate  DATE           NOT NULL,
        GraduationDate DATE           NOT NULL,
        Activity       BIT            NOT NULL,
        YearsOfStudyId INT            NOT NULL,
        ClassId        INT            NOT NULL,
        UserAccountId  INT            NOT NULL,
        RatingId       INT            NOT NULL,
        ModifiedDate   DATE           NOT NULL
    );
GO

CREATE TABLE dbo.FamilyMembersOfTheStudent
    (
        StudentId                 INT         IDENTITY(1, 1) PRIMARY KEY,
        FatherName                NVARCHAR(50),
        MotherName                NVARCHAR(50),
        FirstBrotherOrSisterName  NVARCHAR(50),
        SecondBrotherOrSisterName NVARCHAR(50),
        ThirdBrotherOrSisterName  NVARCHAR(50),
        FourthBrotherOrSisterName NVARCHAR(50),
        FifthBrotherOrSisterName  NVARCHAR(50),
        ModifiedDate              DATE        NOT NULL
    );
GO

CREATE TABLE dbo.Semester
    (
        SemesterID       INT          IDENTITY(1, 1) PRIMARY KEY,
        SemesterDescribe NVARCHAR(50) NOT NULL,
        ModifiedDate     DATE         NOT NULL
    );
GO

CREATE TABLE dbo.[Subject]
    (
        SubjectId    INT          IDENTITY(1, 1) PRIMARY KEY,
        SubjectName  NVARCHAR(50) NOT NULL,
        ModifiedDate DATE         NOT NULL
    );
GO
CREATE TABLE dbo.YearsOfStudy
    (
        YearsOfStudyId       INT     IDENTITY(1, 1) PRIMARY KEY,
        YearsOfStudyDescribe CHAR(4) NOT NULL,
        ModifiedDate         DATE    NOT NULL
    );
GO
CREATE TABLE dbo.ExamScore
    (
        StudentId                 INT           NOT NULL,
        SemesterId                INT           NOT NULL,
        SubjectId                 INT           NOT NULL,
        YearsOfStudyId            INT           NOT NULL,
        FirstFifteenMinuteExam    DECIMAL(2, 1) CHECK (FirstFifteenMinuteExam
                                                       BETWEEN 0 AND 10
                                                      ),
        SecondFifteenMinuteExam   DECIMAL(2, 1) CHECK (SecondFifteenMinuteExam
                                                       BETWEEN 0 AND 10
                                                      ),
        ThirdFifteenMinuteExam    DECIMAL(2, 1) CHECK (ThirdFifteenMinuteExam
                                                       BETWEEN 0 AND 10
                                                      ),
        FirstFortyFiveMinuteExam  DECIMAL(2, 1) CHECK (FirstFortyFiveMinuteExam
                                                       BETWEEN 0 AND 10
                                                      ),
        SecondFortyFiveMinuteExam DECIMAL(2, 1) CHECK (SecondFortyFiveMinuteExam
                                                       BETWEEN 0 AND 10
                                                      ),
        FinalExam                 DECIMAL(2, 1) CHECK (FinalExam
                                                       BETWEEN 0 AND 10
                                                      ),
        ModifiedDate              DATE          NOT NULL
    );
GO
CREATE TABLE dbo.Teacher
    (
        TeacherId     INT            IDENTITY(1, 1) PRIMARY KEY,
        Photo         VARBINARY(MAX) NOT NULL,
        FirstName     NVARCHAR(50)   NOT NULL,
        MiddleName    NVARCHAR(50)   NOT NULL,
        LastName      NVARCHAR(50)   NOT NULL,
        BirthDate     DATE           NOT NULL,
        Gender        BIT            NOT NULL,
        [Address]     NVARCHAR(100)  NOT NULL,
        PhoneNumber   CHAR(12)       NOT NULL,
        Email         CHAR(30)       NOT NULL,
        Identifier    CHAR(12)
            UNIQUE NOT NULL,
        HireDate      DATE           NOT NULL,
        EvictionDate  DATE,
        SubjectId     INT            NOT NULL,
        UserAccountId INT            NOT NULL,
        RatingId      INT            NOT NULL,
        ModifiedDate  DATE           NOT NULL
    );
GO


CREATE TABLE dbo.TeacherSalary
    (
        TeacherId    INT  IDENTITY(1, 1) PRIMARY KEY,
        BasicSalary  INT  CHECK (BasicSalary > 0) NOT NULL,
        BonusSalary  INT  CHECK (BonusSalary > 0) NOT NULL,
        ModifiedDate DATE NOT NULL
    );
GO

INSERT INTO dbo.LevelAccount
    (
        LevelDescribe,
        ModifiedDate
    )
VALUES
    (
        'Admin', GETDATE() -- LevelDescribe - varchar(20)
    ),
    (
        'Principal', GETDATE()
    ),
    (
        'Teacher', GETDATE()
    ),
    (
        'Student', GETDATE()
    );

INSERT INTO dbo.AdminAccount
    (
        Username,
        Password,
        Email,
        PhoneNumber,
        LevelAccountId,
        ModifiedDate
    )
VALUES
    (
        'admin', -- Username - varchar(50)
        'admin', -- Password - varchar(50)
        NULL,    -- Email - char(50)
        NULL,    -- PhoneNumber - char(10)
        1,       -- LevelAccountId - int
        GETDATE()
    );
GO

INSERT INTO dbo.Semester
    (
        SemesterDescribe,
        ModifiedDate
    )
VALUES
    (
        N'First Semester', -- SemesterDescribe - nvarchar(50)
        GETDATE()          -- ModifiedDate - date
    ),
    (
        N'Second Semester', -- SemesterDescribe - nvarchar(50)
        GETDATE()           -- ModifiedDate - date
    );
GO

INSERT INTO dbo.Subject
    (
        SubjectName,
        ModifiedDate
    )
VALUES
    (
        N'Mathematics', -- SubjectName - nvarchar(50)
        GETDATE()       -- ModifiedDate - date
    ),
    (
        N'Literature', -- SubjectName - nvarchar(50)
        GETDATE()      -- ModifiedDate - date
    ),
    (
        N'English', -- SubjectName - nvarchar(50)
        GETDATE()   -- ModifiedDate - date
    ),
    (
        N'Physics', -- SubjectName - nvarchar(50)
        GETDATE()   -- ModifiedDate - date
    ),
    (
        N'Chemistry', -- SubjectName - nvarchar(50)
        GETDATE()     -- ModifiedDate - date
    ),
    (
        N'Biology', -- SubjectName - nvarchar(50)
        GETDATE()   -- ModifiedDate - date
    ),
    (
        N'History', -- SubjectName - nvarchar(50)
        GETDATE()   -- ModifiedDate - date
    ),
    (
        N'Civic Education', -- SubjectName - nvarchar(50)
        GETDATE()           -- ModifiedDate - date
    ),
    (
        N'Geography', -- SubjectName - nvarchar(50)
        GETDATE()     -- ModifiedDate - date
    ),
    (
        N'Physical Education', -- SubjectName - nvarchar(50)
        GETDATE()              -- ModifiedDate - date
    ),
    (
        N'Informatics', -- SubjectName - nvarchar(50)
        GETDATE()       -- ModifiedDate - date
    ),
    (
        N'Technology', -- SubjectName - nvarchar(50)
        GETDATE()      -- ModifiedDate - date
    );
GO

INSERT INTO dbo.YearsOfStudy
    (
        YearsOfStudyDescribe,
        ModifiedDate
    )
VALUES
    (
        '2010',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2011',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2012',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2013',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2014',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2015',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2016',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2017',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2018',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2019',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2020',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2021',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2022',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    ),
    (
        '2023',   -- YearsOfStudyDescribe - char(4)
        GETDATE() -- ModifiedDate - date
    );
GO

INSERT INTO dbo.Rating
    (
        RatingName,
        Certificate,
        ModifiedDate
    )
VALUES
    (
        N'Average', -- RatingName - nvarchar(50)
        0,          -- Certificate - bit
        GETDATE()   -- ModifiedDate - date
    ),
    (
        N'Fair',  -- RatingName - nvarchar(50)
        1,        -- Certificate - bit
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'Good',  -- RatingName - nvarchar(50)
        1,        -- Certificate - bit
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'Excellent Level', -- RatingName - nvarchar(50)
        1,                  -- Certificate - bit
        GETDATE()           -- ModifiedDate - date
    );
GO

INSERT INTO dbo.Class
    (
        ClassName,
        MonitorId,
        ClassHeadTeacherId,
        ModifiedDate
    )
VALUES
    (
        N'10A1',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A2',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A3',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A4',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A5',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A6',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A7',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A8',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A9',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'10A10', -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A1',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A2',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A3',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A4',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A5',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A6',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A7',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A8',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A9',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'11A10', -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A1',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A2',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A3',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A4',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A5',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A6',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A7',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A8',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A9',  -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    ),
    (
        N'12A10', -- ClassName - nvarchar(50)
        NULL,     -- MonitorId - int
        NULL,     -- ClassHeadTeacherId - int
        GETDATE() -- ModifiedDate - date
    );
GO

UPDATE
    dbo.UserAccount
SET
    LevelAccountId = 3
WHERE
    UserAccountId IN
        (
            SELECT
                UserAccountId
            FROM
                dbo.Teacher
        );
GO

UPDATE
    dbo.UserAccount
SET
    LevelAccountId = 4
WHERE
    UserAccountId IN
        (
            SELECT
                UserAccountId
            FROM
                dbo.Student
        );
GO


--------------[AdminAccount]--------------------------------------------------------------
ALTER TABLE dbo.AdminAccount
ADD
    CONSTRAINT FK_AdminAccount_LevelAccount_LevelAccountId
    FOREIGN KEY (LevelAccountId)
    REFERENCES dbo.LevelAccount (LevelAccountId);
GO

--------------[UserAccount]--------------------------------------------------------------
ALTER TABLE dbo.UserAccount
ADD
    CONSTRAINT FK_UserAccount_LevelAccount_LevelAccountId
    FOREIGN KEY (LevelAccountId)
    REFERENCES dbo.LevelAccount (LevelAccountId);
GO

--------------[ExamScore]--------------------------------------------------------------
ALTER TABLE dbo.ExamScore
ADD
    CONSTRAINT FK_ExamScore_Studen_StudenId
    FOREIGN KEY (StudentId)
    REFERENCES dbo.Student (StudentId);
GO

ALTER TABLE dbo.ExamScore
ADD
    CONSTRAINT FK_ExamScore_Semester_SemesterId
    FOREIGN KEY (SemesterId)
    REFERENCES dbo.Semester (SemesterID);
GO

ALTER TABLE dbo.ExamScore
ADD
    CONSTRAINT FK_ExamScore_Subject_SubjectId
    FOREIGN KEY (SubjectId)
    REFERENCES dbo.Subject (SubjectId);
GO

ALTER TABLE dbo.ExamScore
ADD
    CONSTRAINT FK_ExamScore_YearsOfStudy_YearsOfStudyId
    FOREIGN KEY (YearsOfStudyId)
    REFERENCES dbo.YearsOfStudy (YearsOfStudyId);
GO

--------------[Teacher]---------------------------------------------------------------------------------------------------------------------
ALTER TABLE dbo.Teacher
ADD
    CONSTRAINT FK_Teacher_Subject_SubjectId
    FOREIGN KEY (SubjectId)
    REFERENCES Subject (SubjectId);

GO

ALTER TABLE dbo.Teacher
ADD
    CONSTRAINT FK_Teacher_UserAccount_UserAccountId
    FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId);

GO

ALTER TABLE dbo.Teacher
ADD
    CONSTRAINT FK_Teacher_Rating_RatingId
    FOREIGN KEY (RatingId)
    REFERENCES Rating (RatingId);

GO

--------------[FamilyMembersOfTheStudent]---------------------------------------------------------------------------------------------------
ALTER TABLE dbo.FamilyMembersOfTheStudent
ADD
    CONSTRAINT FK_FamilyMembersOfTheStudent_Student_StudentId
    FOREIGN KEY (StudentId)
    REFERENCES dbo.Student (StudentId);
GO

---------------[TeacherSalary]--------------------------------------------------------------------------------------------------------------
ALTER TABLE dbo.TeacherSalary
ADD
    CONSTRAINT FK_TeacherSalary_Teacher_TeacherId
    FOREIGN KEY (TeacherId)
    REFERENCES dbo.Teacher (TeacherId);
GO

-----------------[Class]----------------------------------------------------------------------------------------------------------------------
ALTER TABLE dbo.Class
ADD
    CONSTRAINT FK_Class_Student_StudentId
    FOREIGN KEY (MonitorId)
    REFERENCES Student (StudentId);
GO

ALTER TABLE dbo.Class
ADD
    CONSTRAINT FK_Class_Teacher_TeacherId
    FOREIGN KEY (ClassHeadTeacherId)
    REFERENCES Teacher (TeacherId);

GO

-----------------[Student]----------------------------------------------------------------------------------------------------------------------
ALTER TABLE dbo.Student
ADD
    CONSTRAINT FK_Student_YearsOfStudy_YearsOfStudyId
    FOREIGN KEY (YearsOfStudyId)
    REFERENCES YearsOfStudy (YearsOfStudyId);

GO

ALTER TABLE dbo.Student
ADD
    CONSTRAINT FK_Student_Class_ClassId
    FOREIGN KEY (ClassId)
    REFERENCES Class (ClassId);

GO

ALTER TABLE dbo.Student
ADD
    CONSTRAINT FK_Student_UserAccount_UserAccountId
    FOREIGN KEY (UserAccountId)
    REFERENCES UserAccount (UserAccountId);

GO

ALTER TABLE dbo.Student
ADD
    CONSTRAINT FK_Student_Rating_RatingId
    FOREIGN KEY (RatingId)
    REFERENCES Rating (RatingId);

GO

USE master;
DROP DATABASE StudentManagement;
