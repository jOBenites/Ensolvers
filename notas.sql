/*
 Navicat Premium Data Transfer

 Source Server         : PostgrestLocal
 Source Server Type    : PostgreSQL
 Source Server Version : 130002
 Source Host           : localhost:5432
 Source Catalog        : notas
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130002
 File Encoding         : 65001

 Date: 27/01/2024 10:08:49
*/


-- ----------------------------
-- Sequence structure for sec_category
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sec_category";
CREATE SEQUENCE "public"."sec_category" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for sec_note
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."sec_note";
CREATE SEQUENCE "public"."sec_note" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS "public"."category";
CREATE TABLE "public"."category" (
  "id" int4 NOT NULL DEFAULT nextval('sec_category'::regclass),
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "color" varchar(500) COLLATE "pg_catalog"."default" NOT NULL,
  "active" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
)
;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO "public"."category" VALUES (1, 'Prioridad', '#fefefe', '1');
INSERT INTO "public"."category" VALUES (2, 'Deprisa', '#fefefej', '1');
INSERT INTO "public"."category" VALUES (6, 'rfdd', '#fefefej', '1');
INSERT INTO "public"."category" VALUES (28, 'rfdd', '#fefefej', '1');

-- ----------------------------
-- Table structure for note
-- ----------------------------
DROP TABLE IF EXISTS "public"."note";
CREATE TABLE "public"."note" (
  "id" int4 NOT NULL DEFAULT nextval('sec_note'::regclass),
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "description" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "category_id" int4 NOT NULL,
  "active" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1,
  "flag_archived" char(1) COLLATE "pg_catalog"."default" DEFAULT 0
)
;

-- ----------------------------
-- Records of note
-- ----------------------------
INSERT INTO "public"."note" VALUES (1, 'prueba', 'Mi description', 1, '1', '1');
INSERT INTO "public"."note" VALUES (22, 'fdsdff', 'sdfsdf', 2, '1', '0');
INSERT INTO "public"."note" VALUES (24, 'aaa', 'ccccc', 1, '0', '0');
INSERT INTO "public"."note" VALUES (17, 'prueba x4', 'Mi description x4', 1, '1', '1');
INSERT INTO "public"."note" VALUES (23, 'bdfgdf', 'sdggg', 2, '1', '1');
INSERT INTO "public"."note" VALUES (18, 'prueba x2', 'Mi description', 1, '1', '0');

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sec_category"', 29, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."sec_note"', 25, true);

-- ----------------------------
-- Primary Key structure for table category
-- ----------------------------
ALTER TABLE "public"."category" ADD CONSTRAINT "categoria_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table note
-- ----------------------------
ALTER TABLE "public"."note" ADD CONSTRAINT "note_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Keys structure for table note
-- ----------------------------
ALTER TABLE "public"."note" ADD CONSTRAINT "fk_note_category" FOREIGN KEY ("category_id") REFERENCES "public"."category" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
