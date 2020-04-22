package org.blacksmith.jsnip.regex;

import static java.util.regex.Pattern.compile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamekKeyValueExtractTest {
  private static final Pattern pattern =
      compile("(?<key>\\w+)\\s+(?<value>\\w+)(?=&|$)");

  @Test
  public void extractKeyValueTest() {
    Matcher m = pattern.matcher("type1 value1&type2 value2");
    List<Pair<String,String>> pairs = new ArrayList<>();
    while (m.find()) {
      pairs.add(Pair.of(m.group("key"),m.group("value")));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("type1","value1"),
        Pair.of("type2","value2")),pairs);
  }
}
