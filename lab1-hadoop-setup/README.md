# Lab 1: Hadoop Pseudo-Distributed Mode Setup

## Objective
Configure and verify a single-node Hadoop cluster in pseudo-distributed mode on macOS (Apple Silicon).

## Setup Summary
- **OS:** macOS (Apple Silicon - ARM64)
- **Hadoop Version:** 3.x
- **Java Version:** 11
- **Installation:** Homebrew

## Configuration Files Modified
- `core-site.xml` — HDFS default filesystem
- `hdfs-site.xml` — Replication factor = 1
- `mapred-site.xml` — MapReduce framework = yarn
- `yarn-site.xml` — NodeManager aux services

## Verification
- HDFS NameNode UI: http://localhost:9870
- YARN ResourceManager UI: http://localhost:8088
- Successfully ran built-in WordCount example

## Grade: 100%
