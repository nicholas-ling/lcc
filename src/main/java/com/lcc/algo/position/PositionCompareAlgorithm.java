package com.lcc.algo.position;

import com.lcc.TimeRange;
import com.lcc.TimeRangeSetAlgorithm;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Substract original list in place based on position of sorted two TimeRange sets
 */
public class PositionCompareAlgorithm implements TimeRangeSetAlgorithm {

  @Override
  public void substract(List<TimeRange> includes, List<TimeRange> excludes) {
    if(includes == null || excludes == null) throw new IllegalArgumentException("includes/excludes should not be null");
    merge(includes);
    merge(excludes);
    substractMerged(includes, excludes);
  }

  private void merge(List<TimeRange> timeRanges){
    shuffle(timeRanges);
    sort(timeRanges);
    TimeRange pre = null;
    for(TimeRange current : timeRanges){
      if(pre == null){
        pre = current;
      }else{
        TimeRangePosition position = TimeRangePosition.getPosition(current, pre);
        switch (position) {
          case abAB:
            break;
          case ab_AB:
          case aAbB:
          case a_AbB:
            current.setStart(pre.getStart()); //LocalTime is immutable
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
    shuffle(timeRanges);
  }

  //get rid of disabled timeRange in the collection
  private void shuffle(List<TimeRange> timeRanges){
    timeRanges.removeIf(x->x.isDisabled());
  }

  private void sort(List<TimeRange> timeRanges){
    Collections.sort(timeRanges);
  }

  private void substractMerged(List<TimeRange> includes, List<TimeRange> excludes){

    List<TimeRange> partition = new LinkedList<>();
    TimeRange currentInclude = null, currentExclude = null;
    boolean nextI = true, nextJ = true;

    for(Iterator<TimeRange> i = includes.iterator(), j = excludes.iterator();
        (i.hasNext() || !nextI) && (j.hasNext() || !nextJ);){
      if(nextI){
        currentInclude = i.next();
        nextI = false;
      }
      if(nextJ){
        currentExclude = j.next();
        nextJ = false;
      }
      TimeRangePosition position = TimeRangePosition.getPosition(currentInclude, currentExclude);
      switch (position) {
        case abAB:
        case ab_AB:
          nextJ = true;
          break;
        case aAbB:
        case a_AbB:
          currentInclude.setStart(currentExclude.getEnd());
          nextJ = true;
          break;
        case aABb:
        case a_ABb:
          currentExclude.setStart(currentInclude.getEnd());
          currentInclude.disable();
          nextI = true;
          break;
        case aAb_B:
        case a_Ab_B:
          currentInclude.disable();
          nextI = true;
          nextJ = true;
          break;
        case AabB:
          partition.add(new TimeRange(currentInclude.getStart(), currentExclude.getStart()));
          currentInclude.setStart(currentExclude.getEnd());
          nextJ = true;
          break;
        case AaBb:
          LocalTime end = currentInclude.getEnd();
          currentInclude.setEnd(currentExclude.getStart());
          currentExclude.setStart(end);
          nextI = true;
          break;
        case Aab_B:
          currentInclude.setEnd(currentExclude.getStart());
          nextI = true;
          nextJ = true;
          break;
        case ABab:
        case AB_ab:
          nextI = true;
          break;
        default:
          throw new IllegalStateException("not a correct relative position");
      }
    }
    includes.addAll(partition);
    merge(includes);
  }
}