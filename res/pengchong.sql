-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        Microsoft SQL Server 2014 (SP2) (KB3171021) - 12.0.5000.0
-- 服务器操作系统:                      Windows NT 6.3 <X64> (Build 16299: )
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 pengchong 的数据库结构
CREATE DATABASE IF NOT EXISTS "pengchong";
USE "pengchong";

-- 导出  表 pengchong.department 结构
CREATE TABLE IF NOT EXISTS "department" (
	"职务号" VARCHAR(50) NOT NULL,
	"部门" VARCHAR(50) NULL DEFAULT NULL,
	"职务" VARCHAR(50) NULL DEFAULT NULL,
	"基本工资" MONEY(19,4) NULL DEFAULT NULL
);

-- 正在导出表  pengchong.department 的数据：-1 rows
/*!40000 ALTER TABLE "department" DISABLE KEYS */;
INSERT INTO "department" ("职务号", "部门", "职务", "基本工资") VALUES
	('000x001', '董事会', '总裁', 88888880),
	('000x000', '董事会', '首席执行官', 88888888),
	('001x002', '技术部', '运维组长', 88888000),
	('001x000', '技术部', '首席技术官', 88888800),
	('001x001', '技术部', '开发组长', 88888000),
	('001x003', '技术部', '开发工程师', 88880000),
	('001x004', '技术部', '运维工程师', 88880000),
	('002x000', '财务部', '首席财务官', 88800000),
	('002x001', '财务部', '财务经理', 88800000),
	('002x002', '财务部', '财务主管', 88800000),
	('002x003', '财务部', '材料会计', 88800000),
	('002x004', '财务部', '成本会计', 88800000);
/*!40000 ALTER TABLE "department" ENABLE KEYS */;

-- 导出  表 pengchong.hardworking 结构
CREATE TABLE IF NOT EXISTS "hardworking" (
	"职工号" NCHAR(13) NOT NULL,
	"累计时间" NCHAR(10) NULL DEFAULT NULL,
	"加班级别" INT(10,0) NULL DEFAULT NULL COMMENT '',
	"加班日期" DATETIME2(7) NULL DEFAULT NULL COMMENT ''
);

-- 正在导出表  pengchong.hardworking 的数据：-1 rows
/*!40000 ALTER TABLE "hardworking" DISABLE KEYS */;
INSERT INTO "hardworking" ("职工号", "累计时间", "加班级别", "加班日期") VALUES
	('10002y002x003', '24h       ', 3, '2018-01-03 10:00:00.0000000'),
	('10002y002x003', '24h       ', 3, '2017-12-03 10:00:00.0000000'),
	('10001y001x001', '24h       ', 3, '2017-12-03 10:00:00.0000000');
/*!40000 ALTER TABLE "hardworking" ENABLE KEYS */;

-- 导出  表 pengchong.leave 结构
CREATE TABLE IF NOT EXISTS "leave" (
	"职工号" VARCHAR(50) NOT NULL,
	"请假日期" DATETIME(3) NULL DEFAULT NULL,
	"请假时长" VARCHAR(50) NULL DEFAULT NULL,
	"原由" VARCHAR(50) NULL DEFAULT NULL
);

-- 正在导出表  pengchong.leave 的数据：-1 rows
/*!40000 ALTER TABLE "leave" DISABLE KEYS */;
INSERT INTO "leave" ("职工号", "请假日期", "请假时长", "原由") VALUES
	('10002y000x000', '2018-01-03 10:01:00.000', '8h', '未知'),
	('10002y000x000', '2018-01-05 10:01:00.000', '8h', '未知'),
	('10002y000x000', '2018-01-02 00:00:00.000', '8h', '未知'),
	('10002y000x000', '2018-01-01 10:00:00.000', '8h', '未知'),
	('10002y002x001', '2018-01-03 10:00:00.000', '8h', '未知'),
	('10005y001x002', '2018-01-03 10:00:00.000', '3h', '未知'),
	('10002y002x003', '2018-01-03 10:00:00.000', '5h', '未知'),
	('10005y001x002', '2017-12-13 10:00:00.000', '3h', '未知');
/*!40000 ALTER TABLE "leave" ENABLE KEYS */;

-- 导出  表 pengchong.organization 结构
CREATE TABLE IF NOT EXISTS "organization" (
	"部门号" NCHAR(10) NOT NULL,
	"部门名称" NCHAR(10) NOT NULL COMMENT ''
);

-- 正在导出表  pengchong.organization 的数据：-1 rows
/*!40000 ALTER TABLE "organization" DISABLE KEYS */;
INSERT INTO "organization" ("部门号", "部门名称") VALUES
	('000x      ', '董事会       '),
	('001x      ', '技术部       '),
	('002x      ', '财务部       ');
/*!40000 ALTER TABLE "organization" ENABLE KEYS */;

-- 导出  表 pengchong.staff 结构
CREATE TABLE IF NOT EXISTS "staff" (
	"职工号" VARCHAR(13) NOT NULL,
	"姓名" VARCHAR(20) NULL DEFAULT NULL,
	"性别" VARCHAR(4) NULL DEFAULT NULL,
	"年龄" INT(10,0) NULL DEFAULT NULL,
	"工资级别" INT(10,0) NULL DEFAULT NULL
);

-- 正在导出表  pengchong.staff 的数据：-1 rows
/*!40000 ALTER TABLE "staff" DISABLE KEYS */;
INSERT INTO "staff" ("职工号", "姓名", "性别", "年龄", "工资级别") VALUES
	('10001y001x001', '彭聪', '男', 18, 10),
	('10002y000x000', '老马', '男', 18, 4),
	('10003y001x000', '老王', '男', 88, 6),
	('10004y001x000', '老张', '男', 88, 6),
	('10005y001x002', '老宋', '男', 88, 6),
	('10002y002x003', '老彭', '女', 1, 0);
/*!40000 ALTER TABLE "staff" ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
