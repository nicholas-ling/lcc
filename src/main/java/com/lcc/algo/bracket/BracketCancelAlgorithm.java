//package com.lcc.algo.bracket;
//
//import com.lcc.TimeRange;
//import com.lcc.TimeRangeSubstractAlgorithm;
//import java.util.Collections;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Stack;
//
//public class BracketCancelAlgorithm implements TimeRangeSubstractAlgorithm {
//
//  @Override
//  public List<TimeRange> substract(List<TimeRange> includes, List<TimeRange> excludes) {
//    if(includes == null || includes.isEmpty()) return Collections.emptyList();
//    List<Bracket> brackets = buildBrackets(includes, excludes);
//    Collections.sort(brackets);
//    return substract(brackets);
//  }
//
//  private List<TimeRange> substract(List<Bracket> sortedBrackets){
//
//    List<TimeRange> ret = new LinkedList();
//
//    Stack<Bracket> stack = new Stack();
//    Iterator<Bracket> iterator = sortedBrackets.iterator();
//    stack.push(iterator.next());
//    while(iterator.hasNext()){
//      Bracket current = iterator.next();
//      Bracket last = stack.peek();
//      if(last.isBeginExcludeBracket()){
//
//
//      }
//
//    }
//    return null;
//  }
//
//  private List<Bracket> buildBrackets(List<TimeRange> includes, List<TimeRange> excludes){
//    List<Bracket> brackets = new LinkedList<>();
//    if(includes != null){
//      for(TimeRange range : includes){
//        brackets.add(new Bracket(range.getStart(), true, true));
//        brackets.add(new Bracket(range.getEnd(), true, false));
//      }
//    }
//    if(excludes != null){
//      for(TimeRange range : excludes){
//        brackets.add(new Bracket(range.getStart(), false, true));
//        brackets.add(new Bracket(range.getEnd(), false, false));
//      }
//    }
//    return brackets;
//  }
//
//}