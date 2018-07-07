# FlowProject
대구소프트웨어고등학교 생활 편의 앱

## Skills
* 네트워크 : Retrofit2 사용  
* 로컬DB : SQLite 사용 -> realm으로 바꿀 예정  

## EXCEPTION HANDLING TODO LIST  
* 앱이 꺼져도 세션 유지(자동 로그인)   
* 로컬 디비에 토큰 열 개 초과시 오래된 토큰부터 삭제(FIFO)   
* USER_TOKEN 스트링에 상수값 말고 token table 조회해서 가장 최근 값 가지고 오게 수정   
* 외박 신청 시 서버에서 날짜 하루 이상 차이나는지 검사 안해줌   
* RegisterActivity에도 OutActivity처럼 response 값 케이스 별 검사해서 NullPointerExeption 방지   
* SQLITE 쿼리문들 수정 -> row 10개 초과시 1번 row(가장 예전에 들어온 값) 부터 날려야 함.  

## 서버 
