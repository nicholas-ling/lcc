package com.lcc;

import java.util.List;

public interface TimeRangeSetAlgorithm {

  void substract(List<TimeRange> includes, List<TimeRange> excludes);

}