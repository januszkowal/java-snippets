package org.blacksmith.jsnip.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KeyValueExtractTest {

  @Test
  public void testStringStringKeyValues() {
    String text = "key1=value1,key2=value2";
    Pattern p = Pattern.compile("(?:(\\w*)=(\\w*))(?=,|$)");
    Matcher m = p.matcher(text);
    List<Pair<String,String>> pairs = new ArrayList<>();
     while (m.find()) {
      pairs.add(Pair.of(m.group(1),m.group(2)));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("key1","value1"),
        Pair.of("key2","value2")),pairs);
  }

  @Test
  public void testStringNumberKeyValues() {
    String text = "key1=1000&key2=2000";
    Pattern p = Pattern.compile("(?:(\\w*)=(\\d*)(?=&|$))");
    Matcher m = p.matcher(text);
    List<Pair<String,Long>> pairs = new ArrayList<>();
    while (m.find()) {
      pairs.add(Pair.of(m.group(1),Long.parseLong(m.group(2))));
    }
    Assertions.assertIterableEquals(List.of(
        Pair.of("key1",1000L),
        Pair.of("key2",2000L)),pairs);
  }
}
