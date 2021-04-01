use super::{merge_sort, quicksort};

/// Choose the algorithm that you would like to use for sorting.
pub enum ISorting {
    MergeSort(merge_sort::MergeSort),
    Quicksort(quicksort::Quicksort),
}
