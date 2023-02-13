package com.spring.bookdream.service;

import java.util.List;

import com.spring.bookdream.vo.AnswerVO;
import com.spring.bookdream.vo.QnAVO;

public interface QnAService {
	
	//문의 등록
	public void QnAInsert(QnAVO qnaVO);
	
	//나의 문의 목록 리스트
	public List<QnAVO> getMyQnAList(QnAVO qnaVO);
	
	//문의 가져오기
	public QnAVO getQnA(QnAVO qnaVO);
	
	//문의 수정
	public void updateQnA(QnAVO qnaVO);
	
	//문의 삭제
	public void deleteQnA(QnAVO qnaVO);
	
	//전체 문의 리스트
	public List<QnAVO> getAllQnAList();
	
	//답변 insert
	public void insertAnswer(AnswerVO answerVO);
	
	//마이페이지 나의 문의 목록 리스트 (3개)
	public List<QnAVO> getRecentMyQnAList(QnAVO qnaVO);	
}
