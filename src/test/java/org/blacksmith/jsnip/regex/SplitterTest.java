package org.blacksmith.jsnip.regex;

import java.util.Map;
import org.blacksmith.jsnip.Splitter;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class SplitterTest {

  @Test
  public void test1(){
    var result = Splitter.builder().trimResults(true).build().split("key1=value1 ;key2=value2");
    Assertions.assertThat(result)
        .containsExactlyEntriesOf(Map.of("key1","value1","key2","value2"));
  }
  @Test
  public void test2(){
    var result = Splitter.builder().withKeyValueWhiteSpaceSeparator().build().split("key1 value1;key2 value2");
    Assertions.assertThat(result)
        .containsExactlyEntriesOf(Map.of("key1","value1","key2","value2"));
  }
}
