-- Task 5: Custom Dataset - Garment Worker Productivity
-- Source: https://archive.ics.uci.edu/dataset/597/productivity+prediction+of+garment+employees

CREATE TABLE garment_productivity (
  date_col STRING,
  quarter STRING,
  department STRING,
  day STRING,
  team INT,
  targeted_productivity DOUBLE,
  smv DOUBLE,
  wip STRING,
  over_time INT,
  incentive INT,
  idle_time DOUBLE,
  idle_men INT,
  no_of_style_change INT,
  no_of_workers DOUBLE,
  actual_productivity DOUBLE
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ','
STORED AS TEXTFILE
TBLPROPERTIES ("skip.header.line.count"="1");

LOAD DATA INPATH '/tmp/garment.csv' INTO TABLE garment_productivity;

-- Query 1: Average actual productivity by department
SELECT department, AVG(actual_productivity) as avg_productivity
FROM garment_productivity
GROUP BY department;

-- Query 2: Average incentive by day of week
SELECT day, AVG(incentive) as avg_incentive
FROM garment_productivity
GROUP BY day;
