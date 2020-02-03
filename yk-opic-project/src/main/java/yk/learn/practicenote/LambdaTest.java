package yk.learn.practicenote;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaTest {
  public static void main(final String[] args) {

    final Function<Integer, Integer> f = (a) -> a * a * a;
    System.out.println(f.apply(3));

    final Runnable r = () -> System.out.println("Test");
    r.run();

    final Predicate<String> isEmptyStr = s -> s.length() == 0;
    System.out.println(isEmptyStr.test("AAAAA"));

    final BiFunction<Integer, Integer, Integer> b = (b1, b2) -> b1 + b2;
    System.out.println(b.apply(1, 3));

  }
}
