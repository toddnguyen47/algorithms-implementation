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

use std::fmt::Debug;

pub mod factory;
pub mod isorting;

mod merge_sort;
mod quicksort;

/// Sort the `arr` using `sorting_algorithm` created by `factory`
pub fn sort<T>(sorting_algorithm: isorting::ISorting, arr: &mut [T]) -> &mut [T]
where
    T: core::cmp::PartialOrd + core::marker::Copy + Debug,
{
    let arr = match sorting_algorithm {
        isorting::ISorting::MergeSort(merge_sort) => merge_sort.sort(arr),
        isorting::ISorting::Quicksort(quicksort) => quicksort.sort(arr),
    };
    arr
}

#[cfg(test)]
mod tests {
    use pretty_assertions::assert_eq;
    use rand::prelude::*;
    use rand_pcg::Pcg64;

    fn generate_n_random_elements(n: usize) -> Vec<usize> {
        let mut rng = Pcg64::from_rng(rand::thread_rng()).unwrap();
        let mut vec = Vec::with_capacity(n);

        for _ in 0..n {
            vec.push(rng.gen_range(0..(n / 2)));
        }

        vec
    }

    #[test]
    fn able_to_sort_5_unsorted_elements() {
        let mut arr = [14, 2, 44, 3, 7];
        let algo = crate::factory::create_quicksort();
        let sorted_arr = crate::sort(algo, &mut arr);
        assert_eq!([2, 3, 7, 14, 44], sorted_arr);
    }

    #[test]
    fn able_to_sort_5_sorted_elements() {
        let mut arr = [2, 3, 7, 14, 44];
        let algo = crate::factory::create_quicksort();
        let sorted_arr = crate::sort(algo, &mut arr);
        assert_eq!([2, 3, 7, 14, 44], sorted_arr);
    }

    #[test]
    fn able_to_sort_20_random_elements() {
        let mut arr = generate_n_random_elements(20);
        let mut arr_clone = arr.clone();
        let algo = crate::factory::create_quicksort();
        let sorted_arr = crate::sort(algo, &mut arr_clone);
        arr.sort_unstable();
        assert_eq!(arr, sorted_arr);
    }

    #[test]
    fn able_to_sort_10_000_random_elements() {
        let mut arr = generate_n_random_elements(10_000);
        let mut arr_clone = arr.clone();
        let algo = crate::factory::create_quicksort();
        let sorted_arr = crate::sort(algo, &mut arr_clone);
        arr.sort_unstable();
        assert_eq!(arr, sorted_arr);
    }
}
