ALTER TABLE QUEUE_JOB ADD COLUMN `OID_DEBTS` bigint(20) DEFAULT NULL, ADD COLUMN `OID_EXEMPTIONS` bigint(20) DEFAULT NULL, ADD COLUMN `OID_TRANSACTIONS` bigint(20) DEFAULT NULL;
ALTER TABLE FILE ADD COLUMN `OID_EVENT_REPORT_QUEUE_JOB_FOR_DEBTS` bigint(20) DEFAULT NULL, ADD COLUMN `OID_EVENT_REPORT_QUEUE_JOB_FOR_EXEMPTIONS` bigint(20) DEFAULT NULL, ADD COLUMN `OID_EVENT_REPORT_QUEUE_JOB_FOR_TRANSACTIONS` bigint(20) DEFAULT NULL;