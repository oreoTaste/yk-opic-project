package yk.learn.practicenote.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Color {
  int red();
  int green();
  int blue();

}
