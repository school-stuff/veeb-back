CREATE TABLE user
(
  id           INT                                NOT NULL
    PRIMARY KEY AUTO_INCREMENT,
  email        VARCHAR(255)                       NOT NULL,
  password     VARCHAR(255)                       NOT NULL,
  first_name   VARCHAR(255)                       NULL,
  last_name    VARCHAR(255)                       NULL,
  day_of_birth DATE                               NULL,
  is_trainer   BIT DEFAULT b'0'                   NOT NULL,
  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)
  ENGINE = MyISAM;
