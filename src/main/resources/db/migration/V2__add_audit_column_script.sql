ALTER TABLE `book`
ADD COLUMN `create_date` TIMESTAMP NULL AFTER `publisher_id`,
ADD COLUMN `last_modified_date` TIMESTAMP NULL AFTER `create_date`;