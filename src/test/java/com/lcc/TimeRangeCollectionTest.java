package com.lcc;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Test;

public class TimeRangeCollectionTest {

  @Test
  public void should_be_empty_if_pass_null(){
    TimeRangeCollection tc = new TimeRangeCollection(null);
    assertThat(tc.getTimeRanges()).isNotNull();
    assertThat(tc.getTimeRanges().isEmpty()).isTrue();
  }

  @Test
  public void should_be_empty_with_default_constructor(){
    TimeRangeCollection tc = new TimeRangeCollection();
    assertThat(tc.getTimeRanges()).isNotNull();
    assertThat(tc.getTimeRanges().isEmpty()).isTrue();
  }

//  @Test
//  public void should_still_be_itself_if_minuend_is_empty(){
//    TimeRangeCollection minuend = new TimeRangeCollection();
//    TimeRangeCollection subtrahend = new TimeRangeCollection(new LinkedList(
//        Arrays.asList(new TimeRange(LocalTime.of(10, 0), LocalTime.of(10, 11)))
//    ));
//    minuend.minus(subtrahend);
//    assertThat(minuend.getTimeRanges()).isEmpty();
//  }
//
//  @Test
//  public void should_minus_the_part_they_both_have(){
//    TimeRangeCollection minuend = new TimeRangeCollection(new LinkedList(
//        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(10, 0)))
//    ));
//    TimeRangeCollection subtrahend = new TimeRangeCollection(new LinkedList(
//        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
//    ));
//    minuend.minus(subtrahend);
//    assertThat(minuend.getTimeRanges().size()).isEqualTo(1);
//    assertThat(minuend.getTimeRanges().get(0)).isEqualToComparingFieldByField(
//        new TimeRange(LocalTime.of(9, 30), LocalTime.of(10, 0))
//    );
//  }

}