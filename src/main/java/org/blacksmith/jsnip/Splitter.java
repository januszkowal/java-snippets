package org.blacksmith.jsnip;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

  public Map<String,String> split(String text) {
    Matcher m = pattern.matcher(text);
    Map<String,String> result = new HashMap<>();
    while (m.find()) {
      String key = trimResults?m.group(1).trim():m.group(1);
      String value = trimResults?m.group(2).trim():m.group(2);
      result.put(key,value);
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
      return new Splitter(Pattern.compile("(?:(\\w*)" + keyValueSeparator + "(\\w*\\s*))(?=" + separator +"|$)"),
          trimResults);
    }
  }
}
