package org.blacksmith.jsnip.regex;

import static java.util.regex.Pattern.compile;

import com.google.common.base.Splitter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NamedKeyValueExtractTest {
  private static final Pattern patternWithWhiteSpaceCharacter =
      compile("(?<key>\\w+)\\s+(?<value>\\w+)(?=&|$)");
  private static final Pattern patternWithWhiteEqCharacter1 =
      compile("(?<key>\\w+)=(?<value>\\w+)(?=&|$)");
  private static final Pattern patternWithWhiteEqCharacter2 =
      compile("(?<key>\\w+)\\=(?<value>\\w+)(?=&|$)");
  @Test
  public void extractKeyValueSpacesTest() {
    Matcher m = patternWithWhiteSpaceCharacter.matcher("type1 value1&type2 value2");
    List<Pair<String,String>> pairs = new ArrayList<>();
    while (m.find()) {
      pairs.add(Pair.of(m.group("key"),m.group("value")));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("type1","value1"),
        Pair.of("type2","value2")),pairs);
  }

  @Test
  public void extractKeyValueEq1Test() {
    Matcher m = patternWithWhiteEqCharacter1.matcher("type1=value1&type2=value2");
    List<Pair<String,String>> pairs = new ArrayList<>();
    while (m.find()) {
      pairs.add(Pair.of(m.group("key"),m.group("value")));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("type1","value1"),
        Pair.of("type2","value2")),pairs);
  }

  @Test
  public void extractKeyValueEq2Test() {
    Matcher m = patternWithWhiteEqCharacter2.matcher("type1=value1&type2=value2");
    List<Pair<String,String>> pairs = new ArrayList<>();
    while (m.find()) {
      pairs.add(Pair.of(m.group("key"),m.group("value")));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("type1","value1"),
        Pair.of("type2","value2")),pairs);
  }
}
