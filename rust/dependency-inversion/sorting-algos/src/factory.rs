use crate::{i_sorting, merge_sort::MergeSort, quicksort::Quicksort};

/// Create an enum variant that uses MergeSort.
pub fn create_merge_sort() -> i_sorting::Sorting {
    let merge_sort = MergeSort::new();
    i_sorting::Sorting::MergeSort(merge_sort)
}

/// Create an enum variant that uses Quicksort.
pub fn create_quicksort() -> i_sorting::Sorting {
    let quicksort = Quicksort::new();
    i_sorting::Sorting::Quicksort(quicksort)
}
