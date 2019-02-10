package com.lcc.algo.sequential;

import com.lcc.TimeRange;
import com.lcc.TimeRangeCollection;
import com.lcc.TimeRangeSubstractAlgorithm;
import java.util.List;
import java.util.ListIterator;

public class SequentialSubstractAlgorithm implements TimeRangeSubstractAlgorithm {

  @Override
  public List<TimeRange> minus(TimeRangeCollection includes, TimeRangeCollection excludes) {

    includes.merge();
    excludes.merge();

    ListIterator<TimeRange> minIterator = includes.getTimeRanges().listIterator();
    ListIterator<TimeRange> subIterator = excludes.getTimeRanges().listIterator();

    while(minIterator.hasNext() || subIterator.hasNext()){
      TimeRange minRange = minIterator.next();
      TimeRange subRange = subIterator.next();


    }

    return null;
  }
}