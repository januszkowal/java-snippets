package org.blacksmith.jsnip.arrays;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArrrayConv {
  @Test
  public void stringArray() {
    String[] arr = {"Ala","Pies","kot"};
    List<String> list1 = List.of(arr);
    List<String> list2 = Arrays.asList(arr);
    List<String> list3 = Arrays.stream(arr).collect(Collectors.toList());
    Assertions.assertEquals(list1,list2);
    Assertions.assertEquals(list1,list3);
  }

  @Test
  public void intArrayToList() {
    int[] arr = { 1, 2, 3, 4, 5 };
    IntStream intStream1 = Arrays.stream(arr);
    //Stream of int[] must be flattened
    Stream<int[]> intStream2 = Stream.of(arr);
    IntStream intStream2F = intStream2.flatMapToInt(Arrays::stream);
  }

}
