DROP SCHEMA IF EXISTS jenkins_pr;

CREATE SCHEMA jenkins_pr;

CREATE TABLE jenkins_pr.order
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    hash     VARCHAR(255) NOT NULL,
    producer VARCHAR(45)  NOT NULL,
    consumer VARCHAR(45)  NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX id_UNIQUE (id ASC) VISIBLE,
    UNIQUE INDEX hash_UNIQUE (hash ASC) VISIBLE
);