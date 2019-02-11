package com.lcc;

import static org.assertj.core.api.Assertions.assertThat;

import com.lcc.algo.position.TimeRangePosition;
import java.time.LocalTime;
import org.junit.Test;

public class PositionTest {

  @Test
  public void should_return_to_the_left(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(22, 2), LocalTime.of(23,0));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.abAB);
  }

  @Test
  public void should_return_to_the_left_same(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r3 =  new TimeRange(LocalTime.of(22, 2), LocalTime.of(23,1));
    assertThat(TimeRangePosition.getPosition(r1, r3)).isEqualTo(TimeRangePosition.ab_AB);
  }

  @Test
  public void should_return_to_the_left_middle(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(22, 2), LocalTime.of(23,5));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.aAbB);
  }

  @Test
  public void should_return_to_the_left_middle_same(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r3 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,5));
    assertThat(TimeRangePosition.getPosition(r1, r3)).isEqualTo(TimeRangePosition.a_AbB);
  }

  @Test
  public void should_return_to_the_outter_middle(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(22, 2), LocalTime.of(23,25));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.aABb);
  }

  @Test
  public void should_return_to_the_outter_middle_same(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r3 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,25));
    assertThat(TimeRangePosition.getPosition(r1, r3)).isEqualTo(TimeRangePosition.a_ABb);
  }

  @Test
  public void should_return_to_the_middle(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(22, 2), LocalTime.of(23,20));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.aAb_B);
  }

  @Test
  public void should_return_to_the_middle_same(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r3 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    assertThat(TimeRangePosition.getPosition(r1, r3)).isEqualTo(TimeRangePosition.a_Ab_B);
  }

  @Test
  public void should_return_to_the_inner_middle(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 2), LocalTime.of(23,19));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.AabB);
  }

  @Test
  public void should_return_to_the_inner_middle_right(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 2), LocalTime.of(23,29));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.AaBb);
  }

  @Test
  public void should_return_to_the_right(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r2 =  new TimeRange(LocalTime.of(23, 21), LocalTime.of(23,30));
    assertThat(TimeRangePosition.getPosition(r1, r2)).isEqualTo(TimeRangePosition.ABab);
  }

  @Test
  public void should_return_to_the_right_same(){
    TimeRange r1 =  new TimeRange(LocalTime.of(23, 1), LocalTime.of(23,20));
    TimeRange r3 =  new TimeRange(LocalTime.of(23, 20), LocalTime.of(23,30));
    assertThat(TimeRangePosition.getPosition(r1, r3)).isEqualTo(TimeRangePosition.AB_ab);
  }

}