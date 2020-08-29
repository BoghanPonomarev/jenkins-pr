ALTER TABLE jenkins_pr.order
ADD COLUMN consumer VARCHAR(45) NOT NULL AFTER producer;