package com.lcc;

import com.lcc.algo.position.PositionCompareAlgorithm;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TimeRangeCollection {

  private List<TimeRange> timeRanges;
  private TimeRangeSetAlgorithm timeRangeSetAlgorithm = new PositionCompareAlgorithm(); //default algorithm

  public TimeRangeCollection(){
    this.timeRanges = new LinkedList<>();
  }

  //mutable, reuse the existing ranges in case it grows too big
  public TimeRangeCollection(LinkedList<TimeRange> ranges){
    if(ranges == null) ranges = new LinkedList<>();
    this.timeRanges = ranges;
  }

  public void substract(TimeRangeCollection exclude){
    if(exclude == null) return;
    timeRangeSetAlgorithm.substract(this, exclude);
  }

  public void merge(){
    shuffle();
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
            throw new IllegalStateException("should be sorted before merge");
          default:
            throw new IllegalStateException("not a correct relative position");
        }
        pre = current;
      }
    }
    shuffle();
  }

  //get rid of disabled timeRange in the collection
  private void shuffle(){
    timeRanges.removeIf(x->x.isDisabled());
  }

  public void sort(){
    Collections.sort(timeRanges);
  }

  public void append(List<TimeRange> timeRanges){
    this.timeRanges.addAll(timeRanges);
  }

  public List<TimeRange> getTimeRanges() {
    return this.timeRanges;
  }

}