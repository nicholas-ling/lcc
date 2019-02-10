package com.lcc;

import com.lcc.algo.sequential.SequentialSubstractAlgorithm;
import java.util.LinkedList;
import java.util.List;

public class TimeRangeCollection {

  private LinkedList<TimeRange> timeRanges;
  private TimeRangeSubstractAlgorithm timeRangeSubstractAlgorithm = new SequentialSubstractAlgorithm(); //default algo

  public TimeRangeCollection(){
    this.timeRanges = new LinkedList<>();
  }

  //mutable, reuse the existing ranges in case it grows too big
  public TimeRangeCollection(LinkedList<TimeRange> ranges){
    if(ranges == null) ranges = new LinkedList<>();
    this.timeRanges = ranges;
  }

  public List<TimeRange> minus(TimeRangeCollection exclude){
    return timeRangeSubstractAlgorithm.minus(this, exclude);
  }

  public LinkedList<TimeRange> getTimeRanges() {
    return timeRanges;
  }

  //merge
  public void merge(){

  }

  private void sort(){

  }

}