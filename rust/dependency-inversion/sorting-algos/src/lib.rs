//! This is a sample Rust library for pseudo dependency injection purposes.
//!
//! The crate contains sorting algorithm implementations, such as
//! MergeSort and Quicksort.
//! # Examples
//!
//! ```rust
//! #[test]
//! fn able_to_sort_5_unsorted_elements() {
//!     let mut arr = [14, 2, 44, 3, 7];
//!     let algo = sorting_algos::factory::create_merge_sort();
//!     let sorted_arr = sorting_algos::sort(algo, &mut arr);
//!     assert_eq!([2, 3, 7, 14, 44], sorted_arr);
//! }
//!```

pub mod factory;
pub mod i_sorting;

mod merge_sort;
mod quicksort;

/// Sort the `arr` using `sorting_algorithm` created by `factory`
pub fn sort<T>(sorting_algorithm: i_sorting::Sorting, arr: &mut [T]) -> &mut [T] {
    let arr = match sorting_algorithm {
        i_sorting::Sorting::MergeSort(merge_sort) => merge_sort.sort(arr),
        i_sorting::Sorting::Quicksort(quicksort) => quicksort.sort(arr),
    };
    arr
}

#[cfg(test)]
mod tests {
    use pretty_assertions::assert_eq;

    #[test]
    fn able_to_sort_5_unsorted_elements() {
        let mut arr = [14, 2, 44, 3, 7];
        let algo = crate::factory::create_merge_sort();
        let sorted_arr = crate::sort(algo, &mut arr);
        assert_eq!([2, 3, 7, 14, 44], sorted_arr);
    }
}
