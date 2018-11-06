CREATE TABLE classification
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  code VARCHAR(255),
  value VARCHAR(255),

  domain_id INT NULL,
  FOREIGN KEY (domain_id) REFERENCES classification(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

CREATE TABLE workout_plan
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name        VARCHAR(255)    NOT NULL,
  totalWeeks  VARCHAR(255)    NULL,
  startDate   Date            NULL,
  endDate     Date            NULL,

  owner_id INT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;


CREATE TABLE training
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(255)    NOT NULL,
  weeks         VARCHAR(255)    NULL,
  timesPerWeek  INT             NULL,

  owner_id INT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

CREATE TABLE exercise
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255),
  amount VARCHAR(255),

  owner_id INT NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES user(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

CREATE TABLE workout_plan_training
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  workout_plan_id INT NOT NULL,
  training_id INT NOT NULL,

  FOREIGN KEY (workout_plan_id) REFERENCES workout_plan(id) ON DELETE CASCADE,
  FOREIGN KEY (training_id) REFERENCES training(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

CREATE TABLE training_exercise
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  training_id INT NOT NULL,
  exercise_id INT NOT NULL,

  FOREIGN KEY (training_id) REFERENCES training(id) ON DELETE CASCADE,
  FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

CREATE TABLE workout_plan_trainee
(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  exercise_id INT NOT NULL,
  trainee_id INT NOT NULL,

  FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE,
  FOREIGN KEY (trainee_id) REFERENCES user(id) ON DELETE CASCADE,

  created_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  updated_at   DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
  deleted_at   DATETIME                           NULL
)ENGINE = MyISAM;

