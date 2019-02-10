package com.lcc;

import java.util.List;

public interface TimeRangeSetAlgorithm {

  List<TimeRange> substract(TimeRangeCollection includes, TimeRangeCollection excludes);

}