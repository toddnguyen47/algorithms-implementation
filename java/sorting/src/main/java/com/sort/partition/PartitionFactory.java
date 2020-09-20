package com.sort.partition;

import com.sort.Swap;

public class PartitionFactory {
  private static Swap swapObj_ = new Swap();

  public static Partition createLomutoPartition() {
    Partition partition = new LomutoPartition(swapObj_);
    return partition;
  }

  public static Partition createHoarePartition() {
    Partition partition = new HoarePartition(swapObj_);
    return partition;
  }
}
