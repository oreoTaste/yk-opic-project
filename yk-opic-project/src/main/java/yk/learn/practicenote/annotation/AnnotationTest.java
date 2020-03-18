package yk.learn.practicenote.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Color(blue = 154, green = 154, red = 30)
class Skyblue { }

public class AnnotationTest {
  public static void main(String[] args) throws Exception {

    Color clazz = Skyblue.class.getAnnotation(Color.class);
    System.out.println("blue : " + clazz.blue());
    System.out.println("green : " + clazz.green());
    System.out.println("red : " + clazz.red());
    Annotation[] annos = Skyblue.class.getAnnotations();
    for(Annotation anno : annos) {
      System.out.println(anno);
    }

    System.out.println("=================================");
    for(Method m : clazz.getClass().getDeclaredMethods()) {
      System.out.printf("%s <- %s (%s)\n",
          m.getReturnType().getSimpleName(), m.getName(), m.getDefaultValue());
    }
  }
}
