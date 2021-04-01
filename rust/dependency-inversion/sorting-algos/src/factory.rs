use crate::{isorting, merge_sort::MergeSort, quicksort::Quicksort};

/// Create an enum variant that uses MergeSort.
pub fn create_merge_sort() -> isorting::ISorting {
    let merge_sort = MergeSort::new();
    isorting::ISorting::MergeSort(merge_sort)
}

/// Create an enum variant that uses Quicksort.
pub fn create_quicksort() -> isorting::ISorting {
    let quicksort = Quicksort::new();
    isorting::ISorting::Quicksort(quicksort)
}
