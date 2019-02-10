package com.lcc;

import java.util.List;

public interface TimeRangeSubstractAlgorithm {

  List<TimeRange> minus(TimeRangeCollection includes, TimeRangeCollection excludes);

}
