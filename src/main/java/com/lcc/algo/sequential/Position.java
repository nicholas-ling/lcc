package com.lcc.algo.sequential;

import com.lcc.TimeRange;

enum Position {
  LEFT,
  LEFT_MIDDLE,
  OUTTER_MIDDLE,
  MIDDLE,
  INNER_MIDDLE,
  MIDDLE_RIGHT,
  RIGHT;

  public static Position getPosition(TimeRange first, TimeRange second){
    if(first.getStart().isAfter(second.getEnd()) || first.getStart().equals(second.getEnd())){
      return Position.LEFT;
    }else if((second.getStart().isBefore(first.getStart()) || second.getStart().equals(first.getStart()))
        && first.getStart().isBefore(second.getEnd())
        && second.getEnd().isBefore(first.getEnd())){
      return Position.LEFT_MIDDLE;
    }else if((second.getStart().isBefore(first.getStart()) || second.getStart().equals(first.getStart()))
        && first.getEnd().isBefore(second.getEnd())){
      return Position.OUTTER_MIDDLE;
    }else if((second.getStart().isBefore(first.getStart()) || second.getStart().equals(first.getStart()))
        && first.getEnd().equals(second.getEnd())){
      return Position.MIDDLE;
    }else if(first.getStart().isBefore(second.getStart())
        && second.getEnd().isBefore(first.getEnd())){
      return Position.INNER_MIDDLE;
    }else if(first.getStart().isBefore(second.getStart())
        && second.getStart().isBefore(first.getEnd())
        && first.getEnd().isBefore(second.getEnd())){
      return Position.MIDDLE_RIGHT;
    }else if(first.getEnd().isBefore(second.getStart()) || first.getEnd().equals(second.getStart())){
      return Position.RIGHT;
    }else{
      throw new IllegalStateException("not a correct position");
    }
  }
}