// URL 요청하기
package yk.learn.practicenote.net.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class URLReadTest {

  void learningURL() throws MalformedURLException {
    URL url = new URL("http://"     // 프로토콜
        + "www.bitcamp.co.kr"       // 서버주소
        + ""                     // 포트번호 (80은 생략가능)
        + "/main/courseD/detail.php"// 자원경로
        + "?aid=17&page=1"          // Query String
        + "#footer"                 // 참조경로
        );
    
    System.out.printf("프로토콜: %s\n", url.getProtocol());
    System.out.printf("서버주소: %s\n", url.getHost());
    System.out.printf("포트번호: %d\n", url.getPort()); // 생략하면 -1이 리턴된다.
    System.out.printf("자원경로: %s\n", url.getPath());
    System.out.printf("Query String : %s\n", url.getQuery());
    System.out.printf("참조경로(내부위치): %s\n", url.getRef());// 참조경로 (자동스크롤한다)

  }
  
  public static void main(String[] args) throws Exception {
    
    URL url = new URL("https://www.daum.net");
    
    // 1) using URL.openStream()
    BufferedReader in1 = new BufferedReader(new InputStreamReader(url.openStream()));
    
    while(true) {
      String str = in1.readLine();
      if(str == null) {
        break;
      }
      System.out.println(str);
    }
    
    in1.close();
    
    
    // 2) using URLconnection
    URLConnection conn = url.openConnection();
    conn.connect();
    BufferedReader in2 = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    
    while(true) {
      String str = in2.readLine();
      if(str == null) {
        break;
      }
      System.out.println(str);
    }
    in2.close();
    
    
    // 3) using httpclient from Apache
    CloseableHttpClient httpClient = HttpClients.createDefault();
    CloseableHttpResponse response = httpClient.execute(
        new HttpGet(url.toString()/*url.getProtocol() + "://" + url.getHost()*/));
    HttpEntity entity = response.getEntity();
    
    if(entity != null) {
      System.out.printf("Responded Data length : %d\n", entity.getContentLength());
      System.out.printf("Responded Data Type : %s\n", entity.getContentType());
      System.out.println("==================================================");
      String content = EntityUtils.toString(entity);
      System.out.println(content);
    }
  }

}






