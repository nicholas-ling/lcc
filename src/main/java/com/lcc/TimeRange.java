package com.lcc;

import java.time.LocalTime;

public class TimeRange implements Comparable{

  private LocalTime start;
  private LocalTime end;

  public TimeRange(LocalTime start, LocalTime end) {
    if(start==null || end==null) throw new IllegalArgumentException("start/end should not be null");
    if(!start.isBefore(end)) throw new IllegalArgumentException("start should before end");
    this.start = start;
    this.end = end;
  }

  public LocalTime getStart() {
    return start;
  }

  public LocalTime getEnd() {
    return end;
  }

  @Override
  public int compareTo(Object o) {
    if(this == o) return 0;
    if(o instanceof TimeRange){
      TimeRange second = (TimeRange) o;
      if(this.start.equals(second.start)) return 0;
      return (this.start.isBefore(second.start)) ? -1 : 1;
    }
    throw new IllegalArgumentException("cannot compare object with different type");
  }


}