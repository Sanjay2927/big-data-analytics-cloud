HIVE_DATA_BUCKET=bda-lab3-central-sanjay
PROJECT_ID=project-d8c046c6-baab-4fd1-8a8
REGION=us-central1
SQL_INSTANCE_NAME=hive-metastore-central
CLUSTER_NAME=hive-cluster
SECRETS_BUCKET=bda-lab3-secrets-sanjay
KEYRING=bda-key-ring
KMS_KEY=bda-hive-key
KEY_URI=projects/${PROJECT_ID}/locations/global/keyRings/${KEYRING}/cryptoKeys/${KMS_KEY}

gcloud dataproc clusters create ${CLUSTER_NAME} \
--region ${REGION} \
--zone us-central1-b \
--image-version=2.0-ubuntu18 \
--master-machine-type e2-standard-2 \
--master-boot-disk-size 100 \
--num-workers 2 \
--worker-machine-type e2-standard-2 \
--worker-boot-disk-size 100 \
--scopes cloud-platform \
--initialization-actions gs://goog-dataproc-initialization-actions-${REGION}/cloud-sql-proxy/cloud-sql-proxy.sh \
--properties "hive:hive.metastore.warehouse.dir=gs://${HIVE_DATA_BUCKET}/hive-warehouse,hive:datanucleus.schema.autoCreateAll=true,hive:hive.metastore.schema.verification=false" \
--metadata "hive-metastore-instance=${PROJECT_ID}:${REGION}:${SQL_INSTANCE_NAME}" \
--metadata "enable-cloud-sql-proxy-on-workers=false" \
--metadata "kms-key-uri=${KEY_URI}" \
--metadata "db-admin-password-uri=gs://${SECRETS_BUCKET}/hive-admin-password.encrypted" \
--metadata "db-hive-password-uri=gs://${SECRETS_BUCKET}/hive-password.encrypted"
