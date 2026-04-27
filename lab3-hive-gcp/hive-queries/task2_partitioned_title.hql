-- Task 2: Create Partitioned Table by Title
CREATE EXTERNAL TABLE empsal_part_title (
  emp_no INT,
  first_name STRING,
  last_name STRING,
  gender STRING,
  salary DOUBLE,
  salary_start_date STRING,
  salary_end_date STRING,
  title_start_date STRING,
  title_end_date STRING
)
PARTITIONED BY (title STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'gs://YOUR_BUCKET/empsal_part_title/';

MSCK REPAIR TABLE empsal_part_title;

-- Task 2a: Average salary by title using partitioned table
SELECT title, AVG(salary) as avg_salary
FROM empsal_part_title
GROUP BY title;
