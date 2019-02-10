package com.lcc;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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

  @Test
  public void should_still_be_itself_if_exclude_is_null(){
    TimeRangeCollection include = new TimeRangeCollection();
    assertThat(include.minus(null)).isEmpty();
  }

  @Test
  public void should_still_be_itself_if_exclude_is_empty(){
    TimeRangeCollection include = new TimeRangeCollection();
    TimeRangeCollection exclude = new TimeRangeCollection();
    assertThat(include.minus(exclude)).isEmpty();
  }

  @Test
  public void should_still_be_itself_if_include_is_empty(){
    TimeRangeCollection include = new TimeRangeCollection();
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    assertThat(include.minus(exclude)).isEmpty();
  }

//  @Test
//  public void should_merge_overlap(){
//    TimeRangeCollection collection = new TimeRangeCollection(new LinkedList(Arrays.asList(
//        new TimeRange(LocalTime.of(1, 0), LocalTime.of(3, 0)),
//        new TimeRange(LocalTime.of(2, 0), LocalTime.of(7, 0)),
//        new TimeRange(LocalTime.of(8, 0), LocalTime.of(10, 0)),
//        new TimeRange(LocalTime.of(9, 0), LocalTime.of(11, 0))
//    )));
//    collection.merge();
//    assertThat(collection.getTimeRanges()).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
//        new TimeRange(LocalTime.of(1, 0), LocalTime.of(7, 0)),
//        new TimeRange(LocalTime.of(8, 0), LocalTime.of(11, 0))
//    );
//  }

  @Test
  public void should_still_itself_with_exclude_left(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(8, 0), LocalTime.of(8, 30)),
        new TimeRange(LocalTime.of(8, 20), LocalTime.of(9, 0))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)));
  }

  @Test
  public void should_exclude_left_middle(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(8, 0), LocalTime.of(9, 10))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)));
  }

  @Test
  public void should_exclude_left_middle_with_one_end_same(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 10))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)));
  }

  @Test
  public void should_excludes_outter_middle(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 1), LocalTime.of(9, 31))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).isEmpty();
  }

  @Test
  public void should_excludes_outter_middle_with_one_end_same(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 31))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).isEmpty();
  }

  @Test
  public void should_excludes_middle(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 8), LocalTime.of(9, 30))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).isEmpty();
  }

  @Test
  public void should_excludes_middle_with_both_ends_same(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).isEmpty();
  }

  @Test
  public void should_exclude_inner_middle(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 15), LocalTime.of(9, 25))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(2);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 25), LocalTime.of(9, 30)));
    assertThat(actual.get(1)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 15)));
  }

  @Test
  public void should_exclude_middle_right(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 15), LocalTime.of(9, 35))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 15)));
  }

  @Test
  public void should_exclude_middle_right_with_one_end_same(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 15), LocalTime.of(9, 30))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 15)));
  }

  @Test
  public void should_exclude_right(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 35), LocalTime.of(9, 40))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 10), LocalTime.of(9, 30)));
  }

  @Test
  public void should_minus_the_part_they_both_have(){
    TimeRangeCollection minuend = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(10, 0)))
    ));
    TimeRangeCollection subtrahend = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    minuend.minus(subtrahend);
    assertThat(minuend.getTimeRanges().size()).isEqualTo(1);
    assertThat(minuend.getTimeRanges().get(0)).isEqualToComparingFieldByField(
        new TimeRange(LocalTime.of(9, 30), LocalTime.of(10, 0))
    );
  }

  @Test
  public void should_return_empty_if_they_are_same(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 10)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 10))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).isEmpty();
  }

  @Test
  public void should_still_itself_if_they_not_have_intersection(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(
        Arrays.asList(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)))
    ));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 30), LocalTime.of(15, 0))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(1);
    assertThat(actual.get(0)).isEqualToComparingFieldByField(new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)));
  }

  @Test
  public void should_exclude_if_2_minus_1(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 30)),
        new TimeRange(LocalTime.of(10,0), LocalTime.of(10, 30))
    )));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 15), LocalTime.of(10, 15))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(2);
    assertThat(actual).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 15)),
        new TimeRange(LocalTime.of(10, 15), LocalTime.of(10, 30))
    );
  }

  @Test
  public void should_exclude_if_2_minus_3(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(11, 0)),
        new TimeRange(LocalTime.of(13,0), LocalTime.of(15, 30))
    )));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(9, 0), LocalTime.of(9, 15)),
        new TimeRange(LocalTime.of(10, 0), LocalTime.of(10, 15)),
        new TimeRange(LocalTime.of(12, 30), LocalTime.of(16, 0))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual.size()).isEqualTo(2);
    assertThat(actual).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
        new TimeRange(LocalTime.of(9, 15), LocalTime.of(10, 0)),
        new TimeRange(LocalTime.of(10, 15), LocalTime.of(11, 0))
    );
  }

  @Test
  public void should_exclude_if_3_minus_2_with_partition(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(1, 0), LocalTime.of(3, 0)),
        new TimeRange(LocalTime.of(6, 0), LocalTime.of(9, 0)),
        new TimeRange(LocalTime.of(10,0), LocalTime.of(15,0))
    )));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(1, 30), LocalTime.of(2, 0)),
        new TimeRange(LocalTime.of(4, 0), LocalTime.of(11, 0))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
        new TimeRange(LocalTime.of(1, 0), LocalTime.of(1, 30)),
        new TimeRange(LocalTime.of(2, 0), LocalTime.of(3, 0)),
        new TimeRange(LocalTime.of(11, 0), LocalTime.of(15, 0))
    );
  }

  @Test
  public void should_exclude_if_3_minus_2_unsorted_with_partition(){
    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(6, 0), LocalTime.of(9, 0)),
        new TimeRange(LocalTime.of(1, 0), LocalTime.of(3, 0)),
        new TimeRange(LocalTime.of(10,0), LocalTime.of(15,0))
    )));
    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
        new TimeRange(LocalTime.of(4, 0), LocalTime.of(11, 0)),
        new TimeRange(LocalTime.of(1, 30), LocalTime.of(2, 0))
    )));
    List<TimeRange> actual = include.minus(exclude);
    assertThat(actual).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
        new TimeRange(LocalTime.of(1, 0), LocalTime.of(1, 30)),
        new TimeRange(LocalTime.of(2, 0), LocalTime.of(3, 0)),
        new TimeRange(LocalTime.of(11, 0), LocalTime.of(15, 0))
    );
  }

//  @Test
//  public void should_exclude_if_3_minus_2_overlapped_with_partition(){
//    TimeRangeCollection include = new TimeRangeCollection(new LinkedList(Arrays.asList(
//        new TimeRange(LocalTime.of(1, 0), LocalTime.of(3, 0)),
//        new TimeRange(LocalTime.of(2, 0), LocalTime.of(7, 0)),
//        new TimeRange(LocalTime.of(6, 0), LocalTime.of(9, 0))
//    )));
//    TimeRangeCollection exclude = new TimeRangeCollection(new LinkedList(Arrays.asList(
//        new TimeRange(LocalTime.of(4, 0), LocalTime.of(5, 0)),
//        new TimeRange(LocalTime.of(8, 0), LocalTime.of(10, 0))
//    )));
//    List<TimeRange> actual = include.minus(exclude);
//    assertThat(actual).usingFieldByFieldElementComparator().containsExactlyInAnyOrder(
//        new TimeRange(LocalTime.of(1, 0), LocalTime.of(4, 0)),
//        new TimeRange(LocalTime.of(5, 0), LocalTime.of(8, 0))
//    );
//  }

}