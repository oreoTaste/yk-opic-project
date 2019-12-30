package yk.learn;

public class AbtString {
  public static void main(String[] args) throws Exception {
    // String 값을 넣는 방법
    String str1 = "Hello1 ";
    System.out.println("str1 : " + str1);
    String str2 = new String("Hello2 ");
    System.out.println("str2 : " + str2);

    //comparision : (- means smaller, + means bigger)
    System.out.println("* comparison : " + str1.compareTo(str2));

    // string에서 char 뽑아내기
    System.out.print("* str1's 1~3 point : ");
    System.out.print(str1.charAt(0) + ", ");
    System.out.print(str1.charAt(1) + ", ");
    System.out.print(str1.charAt(2) + ", ");
    System.out.println();
    
    //string에 해당 문구 있는지 확인하기
    System.out.println("* having llo? " + str2.contains("llo"));
    System.out.println("* having LLo? " + str2.contains("LLo"));

    // string 문자열 합치기
    System.out.println("* concat : " + str1.concat(str2));

    System.out.println();
    // string 값을 바이트 단위로 뽑아내기(UTF-8)
    System.out.println("----------------1st Par.------------------");
    byte[] byte1 = "ABC가나다".getBytes();
    for(int i : byte1) {
      System.out.print(i + " ");
    } System.out.println();
    System.out.println("----------------1st End------------------");

    // string 값을 바이트 단위로 뽑아내기(EUC-KR)
    System.out.println("----------------2nd Par.------------------");
    byte[] byte2 = "ABC가나다".getBytes("EUC-KR");
    for(int i : byte2) {
      System.out.print(i + " ");
    } System.out.println();
    System.out.println("----------------2nd End------------------");


    // 다른걸 String으로 변환하기
    String tmp1 = String.valueOf(3.14f);
    System.out.println("* float to string : " + tmp1);
    String tmp2 = String.valueOf(true);
    System.out.println("* boolean to string : " + tmp2);
    System.out.println("* concat : " + tmp1.concat(tmp2));

  }
}
