# 콘솔게시판 프로그램

이 프로젝트는 Java로 작성된 간단한 콘솔게시판 애플리케이션입니다.<br>
사용자는 회원가입을 통해 계정을 생성하고 이용할 수 있으며,<br> 
로그인하여 게시글을 작성하거나 게시글을 둘러보고 다른 사람의 게시글을 추천할 수 있습니다.<br>
게시글은 추천수를 기준으로 추천수가 많은 게시글부터 정렬되어 표기되고,<br> 
작성했던 게시글은 수정 및 삭제가 가능하며 작성자의 이름 혹은 게시글의 제목으로 검색이 가능합니다.<br>
관리자 계정으로 로그인 시 기존 사용자에게는 보이지 않던 관리자 메뉴가 표기되며,<br>
전체 유저 확인, 특정 유저 차단, 공지사항 작성 등의 기능을 지원합니다.<br>

## UML (상속이 이루어진 부분들만)
![제목 없는 다이어그램 (1)](https://github.com/CHOI-YUN-SEUNG/NoticeBoard/assets/154594253/8f452ff1-3a01-4bf4-ace2-b089763725ba)

## [프로그램 기능 소개]

**기본 기능**
  * 회원가입
  * 로그인
  * 로그아웃
  * 회원탈퇴
  * 게시판 둘러보기
  * 내 게시글 모아보기

**게시판 둘러보기 서브메뉴**
 * 게시글 작성
 * 게시글 상세보기
 * 제목으로 게시글 검색
 * 작성자명으로 게시글 검색
 * 게시글 수정
 * 게시글 삭제
 * 게시글 추천하기

**관리자 기능**
 * 전체 유저 정보 확인
 * 공지사항 작성
 * 유저 활동 정지
