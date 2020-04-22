package org.blacksmith.jsnip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.tuple.Pair;

public class Splitter {
  private final Pattern pattern;
  private final boolean trimResults;

  public Splitter(Pattern pattern, boolean trimResults) {
    this.pattern = pattern;
    this.trimResults = trimResults;
  }

  public static SplitterBuilder builder() {
    return new SplitterBuilder();
  }

  public Map<String,String> splitToMap(String text) {
    Matcher m = pattern.matcher(text);
    Map<String,String> result = new HashMap<>();
    while (m.find()) {
      String key = trimResults?m.group(1).trim():m.group(1);
      String value = trimResults?m.group(2).trim():m.group(2);
      result.put(key,value);
    }
    return result;
  }

  public List<Pair<String,String>> splitToList(String text) {
    Matcher m = pattern.matcher(text);
    List<Pair<String,String>> result = new ArrayList<>();
    while (m.find()) {
      String key = trimResults?m.group(1).trim():m.group(1);
      String value = trimResults?m.group(2).trim():m.group(2);
      result.add(Pair.of(key,value));
    }
    return result;
  }

  public static class SplitterBuilder {

    private String keyValueSeparator="=";
    private String separator=";";
    private boolean trimResults=false;

    public SplitterBuilder() {
    }

    public SplitterBuilder withKeyValueSeparator(String keyValueSeparator) {
      this.keyValueSeparator = keyValueSeparator;
      return this;
    }

    public SplitterBuilder withKeyValueWhiteSpaceSeparator() {
      this.keyValueSeparator = "\\s+";
      return this;
    }

    public SplitterBuilder trimResults(boolean trimResults) {
      this.trimResults = trimResults;
      return this;
    }

    public Splitter build() {
      String pattern = "";
      if (keyValueSeparator.equals(" ") || keyValueSeparator.equals("\\s+"))
        pattern = "(?:(\\w*)" + keyValueSeparator + "(\\w*\\s*))(?=" + separator +"|$)";
      else
        pattern = "(?:(\\s*\\w*\\s*)" + keyValueSeparator + "(\\s*\\w*\\s*))(?=" + separator +"|$)";
      return new Splitter(Pattern.compile(pattern),trimResults);
    }
  }
}
