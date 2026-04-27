# Lab 3: Hive Table Experiments on GCP Dataproc

## Objective
Deploy Apache Hive on Google Cloud Dataproc and compare query performance across managed, single-partitioned, and double-partitioned table structures.

## Architecture
## Cluster Setup
- **Platform:** Google Cloud Dataproc
- **Image:** 2.0-ubuntu18
- **Master:** e2-standard-2
- **Workers:** 2x e2-standard-2
- **Metastore:** Cloud SQL MySQL 8.0
- **Region:** us-central1

## Tasks & Results

### Task 1: Managed Table
- Full table scan on every query
- Cold start: 171.8s | Cached: ~13s

### Task 2: Partitioned by Title
- Single partition column: `title`
- First run: 17.9s | Cached: ~13s

### Task 3: Double-Partitioned (title + year)
- Two partition columns: `title` + `year`
- Best performance: ~12-13s consistently

### Task 3b: Title-Only Partition Queried by Year
- Year extracted dynamically via SUBSTRING
- Slowest: avg 15.8s (spike to 25.7s)

## Timing Summary
| Query | Run1 | Run2 | Run3 | Run4 | Run5 | Avg |
|-------|------|------|------|------|------|-----|
| 1a Managed | 171.8s | 13.6s | 13.8s | 13.6s | 12.8s | 45.1s |
| 2a Part.Title | 17.9s | 13.5s | 13.0s | 13.1s | 13.0s | 14.1s |
| 3a Part.Title+Year | 19.0s | 12.4s | 12.2s | 12.3s | 12.6s | 13.7s |
| 3b Title-only/Year | 12.6s | 13.2s | 13.1s | 25.7s | 14.3s | 15.8s |

## Key Takeaway
Partition design must align with query patterns. Double partitioning (title+year) reduced average query time by 70% compared to the managed table baseline.

## Technologies
- Apache Hive 3.1, HiveQL
- Google Cloud Dataproc, Cloud SQL (MySQL 8.0)
- Google Cloud Storage, KMS
- bash, gsutil, gcloud CLI
