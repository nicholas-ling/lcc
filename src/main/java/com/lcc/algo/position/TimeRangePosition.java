package com.lcc.algo.position;


import com.lcc.TimeRange;

/**
 * the relational position between two timeRange has only 13 combinations as below
 * AB means two point of one timeRange, A means start point, B means end point,
 * same as ab which is another timeRange.
 */
public enum TimeRangePosition {
  abAB, // ab sits to the left side of AB
  aAbB, // ab intersects with AB, and the intersection is Ab
  aABb, // ab includes AB
  AabB, // ab is included by AB
  AaBb, // ab intersects with AB, and the intersection is aB
  ABab, // ab sits to the right side of AB

  ab_AB, //_means equal position
  a_AbB, //a is coinside with A
  a_ABb, //a is coinside with A
  aAb_B, //b is coinside with B
  a_Ab_B, //ab and AB is exactly the same
  AB_ab, //B is coinside with a
  Aab_B; //b is coinside with B

  public static TimeRangePosition getPosition(TimeRange AB, TimeRange ab){
    if(AB == null || ab == null) throw new IllegalStateException("cannot get the position if any of them is null");
    if(ab.getEnd().isBefore(AB.getStart())) {
      return TimeRangePosition.abAB;
    }else if(AB.getStart().equals(ab.getEnd())){
      return TimeRangePosition.ab_AB;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getStart().isBefore(ab.getEnd())
        && ab.getEnd().isBefore(AB.getEnd())){
      return TimeRangePosition.aAbB;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getStart().isBefore(ab.getEnd())
        && ab.getEnd().isBefore(AB.getEnd())){
      return TimeRangePosition.a_AbB;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getEnd().isBefore(ab.getEnd())){
      return TimeRangePosition.aABb;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getEnd().isBefore(ab.getEnd())){
      return TimeRangePosition.a_ABb;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getEnd().equals(ab.getEnd())){
      return TimeRangePosition.aAb_B;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getEnd().equals(ab.getEnd())){
      return TimeRangePosition.a_Ab_B;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getEnd().isBefore(AB.getEnd())){
      return TimeRangePosition.AabB;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getStart().isBefore(AB.getEnd())
        && AB.getEnd().isBefore(ab.getEnd())){
      return TimeRangePosition.AaBb;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getStart().isBefore(AB.getEnd())
        && AB.getEnd().equals(ab.getEnd())){
      return TimeRangePosition.Aab_B;
    }else if(AB.getEnd().isBefore(ab.getStart())){
      return TimeRangePosition.ABab;
    }else if(AB.getEnd().equals(ab.getStart())){
      return TimeRangePosition.AB_ab;
    }else{
      throw new IllegalStateException("not a correct position");
    }
  }
}