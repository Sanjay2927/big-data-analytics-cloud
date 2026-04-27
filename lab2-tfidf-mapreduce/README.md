# Lab 2: TF-IDF MapReduce on Fake News Dataset

## Objective
Build a two-job MapReduce pipeline from scratch in Java to compute TF-IDF scores across a fake news dataset.

## Architecture
## Results
| Metric | Value |
|--------|-------|
| Input files | 2 CSV files |
| Input size | 116 MB |
| Total documents | 38,251 |
| Unique terms | 223,655 |
| Output records | 7,984,309 |
| Output size | 789 MB |
| Job 1 time | ~25s |
| Job 2 time | ~25s |

## How to Run
```bash
# Build
cd lab2-tfidf-mapreduce
mvn clean package

# Upload input to HDFS
hdfs dfs -mkdir -p /user/sanjaynarayanan/fakenews/input
hdfs dfs -put fakenews_data.csv /user/sanjaynarayanan/fakenews/input/

# Run
hadoop jar target/wcmr-1.0-SNAPSHOT.jar TFIDFDriver \
  /user/sanjaynarayanan/fakenews/input \
  /user/sanjaynarayanan/fakenews/output
```

## Technologies
- Java 11, Maven
- Apache Hadoop 3.x (YARN, HDFS)
- MapReduce framework
