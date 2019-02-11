package com.lcc.algo.position;

import com.lcc.Position;
import com.lcc.TimeRange;
import com.lcc.TimeRangeCollection;
import com.lcc.TimeRangeSetAlgorithm;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PositionCompareAlgorithm implements TimeRangeSetAlgorithm {

  @Override
  public void substract(TimeRangeCollection includes, TimeRangeCollection excludes) {

    if(includes == null || excludes == null) throw new IllegalArgumentException("includes/excludes should not be null");

    includes.merge();
    excludes.merge();

    substractMerged(includes, excludes);
  }

  private void substractMerged(TimeRangeCollection includes, TimeRangeCollection excludes){

    List<TimeRange> partition = new LinkedList<>();
    TimeRange currentInclude = null, currentExclude = null;
    boolean nextI = true, nextJ = true;

    for(Iterator<TimeRange> i = includes.getTimeRanges().iterator(), j = excludes.getTimeRanges().iterator();
        (i.hasNext() || !nextI) && (j.hasNext() || !nextJ);){
      if(nextI){
        currentInclude = i.next();
        nextI = false;
      }
      if(nextJ){
        currentExclude = j.next();
        nextJ = false;
      }
      Position position = Position.getPosition(currentInclude, currentExclude);
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
    includes.append(partition);
    includes.merge();
  }
}