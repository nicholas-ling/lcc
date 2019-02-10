package com.lcc;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.time.LocalTime;
import org.junit.Test;

public class TimeRangeTest {

  @Test(expected = IllegalArgumentException.class)
  public void start_should_not_null_exist(){
    new TimeRange(null, LocalTime.of(10, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void end_should_not_null_exist(){
    new TimeRange(LocalTime.of(9, 0), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void start_end_should_not_null_exist(){
    new TimeRange(null, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void start_should_not_equal_end(){
    new TimeRange(LocalTime.of(10, 0), LocalTime.of(10,0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void start_should_not_after_end(){
    new TimeRange(LocalTime.of(23, 0), LocalTime.of(10,0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_not_compare_two_different_type(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 0), LocalTime.of(23,1));
    r1.compareTo("10:00");
  }

  @Test
  public void should_be_equal_with_same_start(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 0), LocalTime.of(23,1));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 0), LocalTime.of(23,55));
    assertThat(r1.compareTo(r2)).isEqualTo(0);
  }

  @Test
  public void should_bigger_if_start_is_bigger(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,2));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 0), LocalTime.of(23,2));
    assertThat(r1.compareTo(r2)).isEqualTo(1);
  }

  @Test
  public void should_smaller_if_start_is_smaller(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 2), LocalTime.of(23,20));
    assertThat(r1.compareTo(r2)).isEqualTo(-1);
  }

}