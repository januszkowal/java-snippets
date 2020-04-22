package org.blacksmith.jsnip.regex;

import java.util.Map;
import org.blacksmith.jsnip.Splitter;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class SplitterTest {

  @Test
  public void test1(){
    var result = Splitter.builder().trimResults(false).build().splitToMap(" key1 = value1x;key2=value2");
    System.out.println(result);

    Assertions.assertThat(result)
        .containsExactlyEntriesOf(Map.of("key1","value1","key2","value2"));
  }
  @Test
  public void test2(){
    var result = Splitter.builder().withKeyValueWhiteSpaceSeparator().build().splitToMap("key1 value1;key2 value2");
    System.out.println(result);
    Assertions.assertThat(result)
        .containsExactlyEntriesOf(Map.of("key1","value1","key2","value2"));
  }
}
