package com.lcc;


/**
 * the relational position between two timeRange has only 13 combinations as below
 * AB means two point of one timeRange, A means start point, B means end point,
 * same as ab which is another timeRange.
 */
public enum Position {
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

  public static Position getPosition(TimeRange AB, TimeRange ab){
    if(AB == null || ab == null) throw new IllegalStateException("cannot get the position if any of them is null");
    if(ab.getEnd().isBefore(AB.getStart())) {
      return Position.abAB;
    }else if(AB.getStart().equals(ab.getEnd())){
      return Position.ab_AB;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getStart().isBefore(ab.getEnd())
        && ab.getEnd().isBefore(AB.getEnd())){
      return Position.aAbB;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getStart().isBefore(ab.getEnd())
        && ab.getEnd().isBefore(AB.getEnd())){
      return Position.a_AbB;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getEnd().isBefore(ab.getEnd())){
      return Position.aABb;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getEnd().isBefore(ab.getEnd())){
      return Position.a_ABb;
    }else if((ab.getStart().isBefore(AB.getStart()))
        && AB.getEnd().equals(ab.getEnd())){
      return Position.aAb_B;
    }else if((ab.getStart().equals(AB.getStart()))
        && AB.getEnd().equals(ab.getEnd())){
      return Position.a_Ab_B;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getEnd().isBefore(AB.getEnd())){
      return Position.AabB;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getStart().isBefore(AB.getEnd())
        && AB.getEnd().isBefore(ab.getEnd())){
      return Position.AaBb;
    }else if(AB.getStart().isBefore(ab.getStart())
        && ab.getStart().isBefore(AB.getEnd())
        && AB.getEnd().equals(ab.getEnd())){
      return Position.Aab_B;
    }else if(AB.getEnd().isBefore(ab.getStart())){
      return Position.ABab;
    }else if(AB.getEnd().equals(ab.getStart())){
      return Position.AB_ab;
    }else{
      throw new IllegalStateException("not a correct position");
    }
  }
}