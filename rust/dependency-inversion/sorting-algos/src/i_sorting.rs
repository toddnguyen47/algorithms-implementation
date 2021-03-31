use super::{merge_sort, quicksort};

/// Choose the algorithm that you would like to use for sorting.
pub enum Sorting {
    MergeSort(merge_sort::MergeSort),
    Quicksort(quicksort::Quicksort),
}
