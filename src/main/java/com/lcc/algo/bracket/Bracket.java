package com.lcc.algo.bracket;

import java.time.LocalTime;

public class Bracket implements Comparable {

  private LocalTime time;
  private boolean include;
  private boolean begin;

  public Bracket(LocalTime time, boolean include, boolean begin) {
    if(time == null) throw new IllegalArgumentException("time should not be null");
    this.time = time;
    this.include = include;
    this.begin = begin;
  }

  @Override
  public int compareTo(Object o) {
    if(this == o) return 0;
    if(o instanceof Bracket){
      Bracket second = (Bracket) o;
      if(this.time.equals(second.time)) return 0;
      return (this.time.isBefore(second.time)) ? -1 : 1;
    }
    throw new IllegalArgumentException("cannot compare object with different type");
  }

  public boolean isBeginExcludeBracket(){
    return begin && !include;
  }

  public boolean isEndExcludeBracket(){
    return !begin && !include;
  }

  public boolean isBeginIncludeBracket(){
    return begin && include;
  }

  public boolean isEndIncludeBracket(){
    return !begin && include;
  }

}