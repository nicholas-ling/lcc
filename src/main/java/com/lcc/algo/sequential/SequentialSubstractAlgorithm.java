package com.lcc.algo.sequential;

import com.lcc.TimeRange;
import com.lcc.TimeRangeCollection;
import com.lcc.TimeRangeSubstractAlgorithm;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class SequentialSubstractAlgorithm implements TimeRangeSubstractAlgorithm {

  @Override
  public List<TimeRange> minus(TimeRangeCollection includes, TimeRangeCollection excludes) {

    if(includes == null) return Collections.emptyList();
    if(excludes == null) excludes = new TimeRangeCollection();

    merge(includes);
    merge(excludes);

    TimeRange[] include= new TimeRange[includes.getTimeRanges().size()];
    TimeRange[] exclude= new TimeRange[excludes.getTimeRanges().size()];

    includes.getTimeRanges().toArray(include);
    excludes.getTimeRanges().toArray(exclude);

    return minus(include, exclude);

  }

  private void merge(TimeRangeCollection collection){
    collection.sort();
    TimeRange pre = null;
    for(TimeRange current : collection.getTimeRanges()){
      if(pre == null){
        pre = current;
      }else{

      }
    }
  }

  private List<TimeRange> minus(TimeRange[] include, TimeRange[] exclude){

    List<TimeRange> partition = new LinkedList<>();

    for(int i=0,j=0; i<include.length && j<exclude.length;){
      Position position = Position.getPosition(include[i], exclude[j]);
      switch (position) {
        case abAB:
        case ab_AB:
          j++;
          break;
        case aAbB:
        case a_AbB:
          include[i].setStart(exclude[j].getEnd());
          j++;
          break;
        case aABb:
        case a_ABb:
          exclude[j].setStart(include[i].getEnd());
          include[i] = null;
          i++;
          break;
        case aAb_B:
        case a_Ab_B:
          include[i] = null;
          i++;
          j++;
          break;
        case AabB:
          partition.add(new TimeRange(include[i].getStart(), exclude[j].getStart()));
          include[i].setStart(exclude[j].getEnd());
          j++;
          break;
        case AaBb:
          LocalTime end = include[i].getEnd();
          include[i].setEnd(exclude[j].getStart());
          exclude[j].setStart(end);
          i++;
          break;
        case Aab_B:
          include[i].setEnd(exclude[j].getStart());
          i++;
          j++;
          break;
        case ABab:
        case AB_ab:
          i++;
          break;
        default:
          throw new IllegalStateException("not a correct relative position");
      }
    }

    List<TimeRange> major = Arrays.stream(include).filter(x->x!=null).collect(Collectors.toList());
    major.addAll(partition);
    return major;
  }
}