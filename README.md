# Big Data Analytics with Cloud Platforms
### INFO 602 | Virginia Commonwealth University | Spring 2026
**Student:** Sanjay Narayanan Sankaranarayanan | Master's in Decision Analytics

---

## Overview
This repository contains three end-to-end Big Data Analytics labs covering Hadoop MapReduce, TF-IDF text processing, and Hive data warehousing on Google Cloud Platform. Each lab builds on the previous, progressing from local distributed computing to cloud-scale data analytics.

## Tech Stack
![Hadoop](https://img.shields.io/badge/Apache_Hadoop-3.x-66CCFF?style=flat&logo=apache)
![Hive](https://img.shields.io/badge/Apache_Hive-3.1-FDEE21?style=flat&logo=apache)
![GCP](https://img.shields.io/badge/Google_Cloud-Dataproc-4285F4?style=flat&logo=googlecloud)
![Java](https://img.shields.io/badge/Java-11-ED8B00?style=flat&logo=java)
![Python](https://img.shields.io/badge/Python-3.x-3776AB?style=flat&logo=python)

---

## Labs

### Lab 1 — Hadoop Pseudo-Distributed Mode Setup
> Local single-node Hadoop cluster on macOS

- Configured Hadoop in pseudo-distributed mode on MacBook Pro (Apple Silicon)
- Set up HDFS NameNode/DataNode and YARN ResourceManager
- Verified cluster via Hadoop Web UI and HDFS file operations
- **Grade: 100%**

### Lab 2 — TF-IDF MapReduce on Fake News Dataset
> Custom two-job MapReduce pipeline in Java

- Built a 2-job MapReduce pipeline from scratch in Java
- **Job 1 (TFJob):** Tokenizes documents and computes term frequency per document
- **Job 2 (TFIDFJob):** Calculates inverse document frequency and final TF-IDF scores
- Processed 44,956 news articles (116 MB) across 2 input files
- Output: 7,984,309 TF-IDF scores for 38,251 unique terms
- Tools: Java, Maven, Hadoop YARN, HDFS

### Lab 3 — Hive Table Experiments on GCP Dataproc
> Cloud-scale data warehousing with partition optimization

- Deployed Hive cluster on Google Cloud Dataproc with Cloud SQL metastore
- Compared 3 table structures: Managed, Single-Partition, Double-Partition
- Analyzed 4.6M employee salary records (378.9 MB)
- Demonstrated partition pruning benefits through 5-run timing analysis
- Tools: Apache Hive, HiveQL, GCP Dataproc, Cloud SQL (MySQL), Google Cloud Storage

---

## Key Results

| Lab | Dataset | Scale | Key Finding |
|-----|---------|-------|-------------|
| Lab 2 | Fake News (44K articles) | 116 MB | TF-IDF pipeline completed in ~25s |
| Lab 3 | Employee Salaries (4.6M rows) | 379 MB | Double partitioning reduced query time by 92% vs cold start |

---

## Repository Structure
---

## About Me
Master's in Decision Analytics student at VCU with experience in manufacturing analytics, supply chain optimization, and AI/ML. Former Analytics Intern at Gokaldas Exports and Celebrity Fashions.

[LinkedIn](https://www.linkedin.com/in/sanjay-narayanan-s-179233241/) | [GitHub](https://github.com/Sanjay2927)
