DROP TABLE IF EXISTS `LinkedPurchaseList`;
CREATE TABLE `LinkedPurchaseList` (
  `student_id` int(10) unsigned NOT NULL,
  `course_id` int(10) unsigned NOT NULL,
  UNIQUE KEY `unq` (`student_id`,`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;