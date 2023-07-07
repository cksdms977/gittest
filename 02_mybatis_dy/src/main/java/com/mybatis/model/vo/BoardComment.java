package com.mybatis.model.vo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"boardCommentWriter"})
public class BoardComment {
	private int boardCommentNo;
	private int boardCommentLevel;
//	private String boardCommentWriter;
	private String boardCommentContent;
	private int boardRef;
	private Member boardCommentWriter;
	private int boardCommentRef;
	private Date boardCommentDate;
}
