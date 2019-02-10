package com.lcc.algo.sequential;

import com.lcc.TimeRange;

enum Position {
  abAB,
  aAbB,
  aABb,
  AabB,
  AaBb,
  ABab,

  ab_AB, //_means equal position
  a_AbB,
  a_ABb,
  aAb_B,
  a_Ab_B,
  AB_ab,
  Aab_B;


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