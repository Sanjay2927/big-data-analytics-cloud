-- Task 3: Create Double-Partitioned Table (title + year)
-- Step 1: Add year column to CSV
-- awk -F',' 'NR>1 { split($6, d, "-"); print $0 "," d[1] }' empsal.csv > empsal_year.csv

CREATE EXTERNAL TABLE empsal_part_year_title (
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
PARTITIONED BY (title STRING, year STRING)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
LOCATION 'gs://YOUR_BUCKET/empsal_part_year_title/';

MSCK REPAIR TABLE empsal_part_year_title;

-- Task 3a: Average salary by title and year
SELECT title, year, AVG(salary) as avg_salary
FROM empsal_part_year_title
GROUP BY title, year;

-- Task 3b: Year query on title-only partition (slower - no year partition)
SELECT title,
       SUBSTRING(salary_start_date, 1, 4) AS year,
       AVG(salary) AS avg_salary
FROM empsal_part_title
GROUP BY title, SUBSTRING(salary_start_date, 1, 4);
