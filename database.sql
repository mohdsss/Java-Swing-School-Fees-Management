-- =========================================================
-- Database Script for School Management System (school1)
-- Database Name: school
-- =========================================================

CREATE DATABASE IF NOT EXISTS `school`;
USE `school`;

-- ---------------------------------------------------------
-- 1. Table structure for table `student`
-- ---------------------------------------------------------
DROP TABLE IF EXISTS `fees`;
DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `rid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `class` INT NOT NULL,
  `city` VARCHAR(100) DEFAULT NULL,
  `Address` TEXT DEFAULT NULL,
  `totalfees` INT DEFAULT 0,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ---------------------------------------------------------
-- 2. Table structure for table `fees`
-- ---------------------------------------------------------
CREATE TABLE `fees` (
  `fid` INT NOT NULL AUTO_INCREMENT,
  `Feesinstalment` INT DEFAULT 0,
  PRIMARY KEY (`fid`),
  CONSTRAINT `fk_student_fees` FOREIGN KEY (`fid`) REFERENCES `student` (`rid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ---------------------------------------------------------
-- Sample Data Insertion
-- ---------------------------------------------------------

INSERT INTO `student` (`name`, `class`, `city`, `Address`, `totalfees`) VALUES
('Rahul Sharma', 10, 'Delhi', 'Connaught Place, Delhi', 50000),
('Priya Singh', 12, 'Mumbai', 'Andheri West, Mumbai', 60000),
('Aman Verma', 9, 'Jaipur', 'Raja Park, Jaipur', 45000);

INSERT INTO `fees` (`Feesinstalment`) VALUES
(15000),
(20000),
(10000);

-- ---------------------------------------------------------
-- Sample Verification Queries with SQL Joins
-- ---------------------------------------------------------

-- Query 1: View all students with fee installment details using LEFT JOIN
SELECT 
    s.rid, 
    f.fid, 
    s.name, 
    s.class, 
    s.Address, 
    s.city, 
    s.totalfees, 
    f.Feesinstalment 
FROM student s 
LEFT JOIN fees f ON s.rid = f.fid;

-- Query 2: View matched student fee payment record using INNER JOIN
SELECT 
    s.rid, 
    s.name, 
    s.totalfees, 
    f.Feesinstalment, 
    (s.totalfees - f.Feesinstalment) AS due_fees 
FROM student s 
INNER JOIN fees f ON s.rid = f.fid;
