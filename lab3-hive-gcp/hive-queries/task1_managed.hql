-- Task 1: Create Managed Table and Load EMPSAL Data
CREATE TABLE empsal_managed (
  emp_no INT,
  first_name STRING,
  last_name STRING,
  gender STRING,
  salary DOUBLE,
  salary_start_date STRING,
  salary_end_date STRING,
  title_start_date STRING,
  title_end_date STRING,
  title STRING
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE;

LOAD DATA INPATH '/tmp/empsal.csv' INTO TABLE empsal_managed;

-- Task 1a: Average salary by job title
SELECT title, AVG(salary) as avg_salary
FROM empsal_managed
GROUP BY title;
