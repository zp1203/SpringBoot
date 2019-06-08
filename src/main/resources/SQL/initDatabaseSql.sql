# 建立第一张表user1
CREATE TABLE `user1` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `name_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户类型',
  `user_password` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码',
#   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_time` DATE NOT NULL COMMENT '创建时间',
#   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_time` DATE NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_urn` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

# 建立 文件存储表 document_storage
CREATE TABLE `document_storage` (
     `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
     `documentName` varchar(50) NOT NULL DEFAULT '' COMMENT '文件名',
     `encryptionDocumentName` varchar(50) NOT NULL DEFAULT '' COMMENT '加密文件名',
     `documentUrl` varchar(50) NOT NULL DEFAULT '' COMMENT '文件路径',
     PRIMARY KEY (`id`),
     UNIQUE KEY `idx_urn` (`documentName`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文件存储信息表';
