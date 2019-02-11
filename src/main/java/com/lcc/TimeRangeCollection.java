package com.lcc;

import com.lcc.algo.position.PositionCompareAlgorithm;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeRangeCollection {

  private List<TimeRange> timeRanges;
  private TimeRangeSetAlgorithm timeRangeSetAlgorithm = new PositionCompareAlgorithm(); //default algorithm

  public TimeRangeCollection(){
    this.timeRanges = new LinkedList<>();
  }

  //mutable, reuse the existing ranges in case it grows too big
  public TimeRangeCollection(List<TimeRange> ranges){
    if(ranges == null) ranges = new LinkedList<>();
    this.timeRanges = ranges;
  }

  public void substract(TimeRangeCollection exclude){
    if(exclude == null) return;
    timeRangeSetAlgorithm.substract(this.timeRanges, exclude.timeRanges);
  }

  //only used by test
  //return a new list in case someone change the internal states
  List<TimeRange> getTimeRanges() {
    return this.timeRanges.stream().collect(Collectors.toList());
  }

  public void print(){
    System.out.print("( ");
    for(TimeRange timeRange : timeRanges){
      StringBuffer sb = new StringBuffer();
      sb.append(timeRange.getStart()).append("-").append(timeRange.getEnd()).append(" ");
      System.out.print(sb.toString());
    }
    System.out.print(")");
  }

}