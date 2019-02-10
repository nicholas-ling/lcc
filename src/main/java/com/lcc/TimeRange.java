package com.lcc;

import java.time.LocalTime;

public class TimeRange implements Comparable{

  private LocalTime start;
  private LocalTime end;
  private boolean disabled = false;

  public TimeRange(LocalTime start, LocalTime end) {
    validate(start, end);
    this.start = start;
    this.end = end;
  }

  private void validate(LocalTime start, LocalTime end){
    if(start==null || end==null) throw new IllegalArgumentException("start/end should not be null");
    if(!start.isBefore(end)) throw new IllegalArgumentException("start should always before end");
  }

  public void disable() {
    this.disabled = true;
  }

  public void setStart(LocalTime start) {
    validate(start, this.end);
    this.start = start;
  }

  public void setEnd(LocalTime end) {
    validate(this.start, end);
    this.end = end;
  }

  public boolean isDisabled() {
    return disabled;
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