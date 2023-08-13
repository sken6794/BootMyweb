package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductUploadVO {
	/*
	 * CREATE TABLE PRODUCT_UPLOAD (
	UPLOAD_NO INT PRIMARY KEY auto_increment,
	FILENAME varchar(100) not null, ##실제파일명
	FILEPATH varchar(100) not null, ##폴더명
	UUID varchar(50) not null, ##UUID명
	REGDATE TIMESTAMP default now(),
	PROD_ID INT, ##FK
	PROD_WRITER VARCHAR(20) ##FK
	 */
	private Integer upload_no; //pk
	private String filename; //실제 파일명
	private String filepath; //폴더명
	private String uuid; //난수값
	private LocalDateTime regdate; //등록일
	private Integer prod_id; // fk
	private String prod_writer; // fk
}
