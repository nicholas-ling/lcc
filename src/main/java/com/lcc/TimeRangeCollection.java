package com.lcc;

import com.lcc.algo.sequential.SequentialSubstractAlgorithm;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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

  public List<TimeRange> getTimeRanges() {
    return timeRanges.stream().filter(x->!x.isDisabled()).collect(Collectors.toList());
  }

  public void merge(){
    sort();
    TimeRange pre = null;
    for(TimeRange current : timeRanges){
      if(pre == null){
        pre = current;
      }else{
        Position position = Position.getPosition(current, pre);
        switch (position) {
          case abAB:
            break;
          case ab_AB:
            current.setStart(pre.getStart());
            pre.disable();
            break;
          case aAbB:
          case a_AbB:
            current.setStart(pre.getStart());
            pre.disable();
            break;
          case aABb:
          case a_ABb:
          case aAb_B:
          case a_Ab_B:
            current.setStart(pre.getStart());
            current.setEnd(pre.getEnd());
            pre.disable();
            break;
          case AabB:
            pre.disable();
            break;
          case AaBb:
          case Aab_B:
          case ABab:
          case AB_ab:
            throw new IllegalStateException("should be sort before merge");
          default:
            throw new IllegalStateException("not a correct relative position");
        }
        pre = current;
      }
    }
  }

  public void sort(){
    Collections.sort(timeRanges);
  }

}