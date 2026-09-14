drop database IF EXISTS PatternPilotdb;
drop user IF EXISTS PatternPilot;

create user PatternPilot with password 'password';
create database PatternPilotdb with template=template0 owner=PatternPilot;
\connect PatternPilotdb;

alter default privileges grant all on tables to PatternPilot;
alter default privileges grant all on sequences to PatternPilot;

create table PP_users (
    userId SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);

create table PP_topics (
    topicId SERIAL PRIMARY KEY,
    userId INTEGER NOT NULL,
    topicName VARCHAR(100) NOT NULL,
    confidenceScore DECIMAL(10, 2) NOT NULL
);
alter table PP_topics ADD CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES PP_users(userId);

create table PP_problem (
    problemId SERIAL PRIMARY KEY,
    userId INTEGER NOT NULL,
    topicId INTEGER NOT NULL,
    problemName VARCHAR(100) NOT NULL,
    confidenceScore DECIMAL(10, 2) NOT NULL
);
alter table PP_problem ADD CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES PP_users(userId);
alter table PP_problem ADD CONSTRAINT fk_topic_id FOREIGN KEY (topicId) REFERENCES PP_topics(topicId);

create table PP_History (
    historyId SERIAL PRIMARY KEY,
    userId INTEGER NOT NULL,
    topicId INTEGER NOT NULL,
    problemId INTEGER NOT NULL,
    action VARCHAR(100) NOT NULL,
    timetaken INTEGER NOT NULL,
    isOptimal BOOLEAN,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
alter table PP_History ADD CONSTRAINT fk_user_id FOREIGN KEY (userId) REFERENCES PP_users(userId);
alter table PP_History ADD CONSTRAINT fk_topic_id FOREIGN KEY (topicId) REFERENCES PP_topics(topicId);
alter table PP_History ADD CONSTRAINT fk_problem_id FOREIGN KEY (problemId) REFERENCES PP_problem(problemId);