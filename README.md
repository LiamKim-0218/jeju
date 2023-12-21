# jeju

# 

## 프로젝트 목적
 - 제주옵서양(제주도 관광지 추천사이트)
 - 만든 사이트 : [13.209.5.68](http://13.209.5.68/)
 - 문서 : [https://docs.google.com/document/d/e/2PACX-1vQf-AImPbUJxnhO7MRLWYcPMDVmAKksKXcz_CaG8TyyzEt9ZXOGH1ctJUGSdP5qCYVMUmNvH9ZxwPY-/pub](https://docs.google.com/document/d/1NK41BjPFFus_X3NFZlAe5puN-lUnAcHbjJJk7o7Mj60/edit)
<br><br><br>
## 인원
  - 김남균(개인 프로젝트)
<br><br><br>
## 프로젝트 기간
  12/04(월) ~ 12/15(금)  
  <11/13 발표>
<br><br><br>
## 프로젝트 내용
 공공데이터 포털에서 제공하는 비짓제주 api를 받아서
관광지, 행사 장소, 숙소, 음식점 등의 정보를 알려주고,<br />
카카오맵의 위치표시 기능으로 해당 위치와 상세정보를 알려주는 홈페이지
<br><br><br>
## 진행일정
### 12/04  
  - 기획
  - 일정짜기
<br><br><br>
### 12/05
  - page틀 구성
  - APIKEY 요청 
<br><br><br>
### 12/06
  - header,footer,favicon ui
  - logo 생성
<br><br><br>  
### 12/07
  - 메인페이지 UI  
  - API 데이터 Test
<br><br><br>  
### 12/08
  - 맵 api UI
  - api domain, service, controller 생성
<br><br><br>
### 12/09
  - 배너 생성 및 이미지 배치 
<br><br><br> 
### 12/10
  - 관광지 api UI
 <br><br><br>
### 12/11 
  - 관광지 api service, controller 수정
<br><br><br>
### 12/12
  - 맵 api와 관광지 api 연결
<br><br><br>
### 12/13
  - 관광지에 대한 상세정보 페이지 구현
<br><br><br>
### 12/14
  -  관광지 API를 상세정보 페이지에 연결
<br><br><br>
### 12/15
  - 문서작성
<br><br><br>
## 이슈
### 이슈목록 
1.첫번째 이슈
- 발생 이슈 : postman을 사용해 데이터값을 제대로 받아오는 확인하였지만, api데이터를 받아와 사용해보는것이 처음여서 원하는 데이터를 추출하지못하는 오류 발.
- 해결 : RestTemplate로 외부 API로부터 가져온 데이터를 클래스 객체로 매핑 후 리스트를 반환하여 다시 시도하였더니 해결 할 수 있었습니다.
   
2.두번째 이슈
- 발생 이슈 : 두개의 API데이터를 연결하는 과정에서 지도가 나오지않는 오류가 발생.
- 해결 : 서버에서 json데이터를 제대로 받아 올 수 있게 수정 후 해결 할 수 있었습니다. 

3.세번째 이슈
- 발생 이슈 : api 데이터값에 따른 상세정보 url이 있는 줄 알고 데이터 클릭시 해당 상세페이지 링크로 연결할 생각으로 기획함에 따라 발생.
- 해결 : 상세페이지를 만들어 데이터 값을 넘겨 따로 페이지를 만들어 해결 할 수 있었습니다.

