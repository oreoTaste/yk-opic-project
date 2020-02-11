// URL(Uniform Resource Locator) - 포트번호 생략
package com.eomcs.net.ex08중요;

import java.net.URL;

public class Exam0001기본 {

  public static void main(String[] args) throws Exception {
    // 웹 상에서 자원의 위치를 표현하는 방법
    // 프로토콜://서버주소:포트번호/자원의경로
    // 프로토콜 : http, https, ftp
    // 서버주소 : IP주소(192.168.1.10), 도메인명(www.naver.com)
    // 포트번호 : 80(생략가능) 또는 8080(프록시서버)
    // 자원의 경로 : /index.html, /board/list/jsp
    
    URL url = new URL("http://"     // 프로토콜
        + "www.bitcamp.co.kr"       // 서버주소
        + ""                     // 포트번호 (80은 생략가능)
        + "/main/courseD/detail.php"// 자원경로
        + "?aid=17&page=1"          // Query String
        + "#footer"                 // 참조경로
        );
    
    // URL 분석
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort()); // 생략하면 -1이 리턴된다.
    System.out.printf("자원경로: %s\n", url.getPath());
    System.out.printf("Query String : %s\n", url.getQuery());
    System.out.printf("참조경로(내부위치): %s\n", url.getRef());// 참조경로 (자동스크롤한다)

    // 웹브라우저에서는 포트번호를 생략하면 80번으로 간주한다.
  }

}






