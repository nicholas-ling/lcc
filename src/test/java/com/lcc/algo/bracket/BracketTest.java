//package com.lcc.algo.bracket;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//import java.time.LocalTime;
//import org.junit.Test;
//
//public class BracketTest {
//
//  @Test(expected = IllegalArgumentException.class)
//  public void time_should_not_null(){
//    new Bracket(null, true, true);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void should_not_compare_two_different_type(){
//    Bracket b =  new Bracket(LocalTime.of(23, 0), true, true);
//    b.compareTo("10:00");
//  }
//
//  @Test
//  public void should_be_equal_with_same_time(){
//    Bracket b1 =  new Bracket(LocalTime.of(23, 0), true, true);
//    Bracket b2 =  new Bracket(LocalTime.of(23, 0), false, false);
//    assertThat(b1.compareTo(b2)).isEqualTo(0);
//  }
//
//  @Test
//  public void should_bigger_if_time_is_bigger(){
//    Bracket b1 =  new Bracket(LocalTime.of(23, 1), true, true);
//    Bracket b2 =  new Bracket(LocalTime.of(23, 0), false, false);
//    assertThat(b1.compareTo(b2)).isEqualTo(1);
//  }
//
//  @Test
//  public void should_smaller_if_time_is_smaller(){
//    Bracket b1 =  new Bracket(LocalTime.of(23, 0), true, true);
//    Bracket b2 =  new Bracket(LocalTime.of(23, 1), false, false);
//    assertThat(b1.compareTo(b2)).isEqualTo(-1);
//  }
//
//
//}