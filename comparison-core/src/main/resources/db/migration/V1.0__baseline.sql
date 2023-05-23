CREATE TABLE products (
  id CHAR(36) NOT NULL,
  provider_id INT UNSIGNED NOT NULL,
  provider_product_id INT UNSIGNED NOT NULL,

  link VARCHAR(255) NOT NULL,
  logo VARCHAR(255) NOT NULL,
  bank_id INT UNSIGNED NOT NULL,
  extra_info TEXT NOT NULL,
  bank_name TEXT NOT NULL,
  product_title VARCHAR(255) NOT NULL,
  rating DECIMAL(2,1) NULL,
  has_rating BOOLEAN NULL,
  apr DOUBLE NULL,
  yearly_fee DOUBLE NOT NULL,
  yearly_euro_fee DOUBLE NOT NULL,
  offers_bonus_program BOOLEAN NULL,
  offers_insurance BOOLEAN NULL,
  offers_benefits BOOLEAN NULL,
  offers_extra_services BOOLEAN NULL,
  application_requirements VARCHAR(255) NULL,
  participation_fee VARCHAR(255) NULL,
  participation_cost VARCHAR(255) NULL,
  first_year_fee DOUBLE NOT NULL,
  second_year_fee DOUBLE NOT NULL,
  national_atm_fee DOUBLE NULL,
  international_atm_fee DOUBLE NULL,
  national_atm_free_fee DOUBLE NULL,
  international_atm_free_fee DOUBLE NULL,
  apr_amount DOUBLE NOT NULL,
  interest_rate DOUBLE NULL,
  card_type ENUM('credit', 'debit', 'charge') NOT NULL,
  free_credit_fee_euro_atm DOUBLE NULL,
  PRIMARY KEY (id)
);

CREATE TABLE product_edits (
  product_id CHAR(36) NOT NULL,

  link VARCHAR(255) NULL,
  logo VARCHAR(255) NULL,
  extra_info TEXT NULL,
  bank_name TEXT NULL,
  product_title VARCHAR(255) NULL,
  application_requirements VARCHAR(255) NULL,
  participation_fee VARCHAR(255) NULL,
  participation_cost VARCHAR(255) NULL,

  CONSTRAINT products_pk PRIMARY KEY (product_id),
  CONSTRAINT products_fk FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE product_flags (
  id int NOT NULL AUTO_INCREMENT,
  product_id CHAR(36) NOT NULL,

  description TEXT NOT NULL,
  flag_type ENUM('positive', 'negative') NOT NULL,
--  is_positive BOOLEAN NOT NULL,


  CONSTRAINT product_flags_pk PRIMARY KEY (id),
  CONSTRAINT product_flags_fk FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

