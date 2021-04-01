pub struct Quicksort;

impl Quicksort {
    pub fn new() -> Self {
        Self
    }

    pub fn sort<'a, T>(&self, arr: &'a mut [T]) -> &'a mut [T]
    where
        T: core::cmp::PartialOrd + core::marker::Copy + core::fmt::Debug,
    {
        self.sort_helper(arr, 0, arr.len() - 1)
    }

    fn sort_helper<'a, T>(&self, mut arr: &'a mut [T], lo: usize, hi: usize) -> &'a mut [T]
    where
        T: core::cmp::PartialOrd + core::marker::Copy + core::fmt::Debug,
    {
        if lo < hi {
            let (partitioned_arr, index_of_pivot) = self.lomuto_partition(arr, lo, hi);
            arr = partitioned_arr;
            arr = self.sort_helper(arr, lo, index_of_pivot);
            arr = self.sort_helper(arr, index_of_pivot + 1, hi);
        }
        arr
    }

    fn lomuto_partition<'a, T>(
        &self,
        arr: &'a mut [T],
        lo: usize,
        hi: usize,
    ) -> (&'a mut [T], usize)
    where
        T: core::cmp::PartialOrd + core::marker::Copy + core::fmt::Debug,
    {
        self.move_median_of_three_pivot_to_hi(arr, lo, hi);
        let pivot = arr[hi];
        let mut desired_pivot_index = lo;
        for i in lo..hi {
            if arr[i] < pivot {
                self.swap(arr, desired_pivot_index, i);
                desired_pivot_index += 1;
            }
        }

        // We found the desired index for our pivot at [hi], so
        // Switch desired pivot index with hi!
        self.swap(arr, desired_pivot_index, hi);
        (arr, desired_pivot_index)
    }

    fn move_median_of_three_pivot_to_hi<'a, T>(&self, arr: &'a mut [T], lo: usize, hi: usize)
    where
        T: core::cmp::PartialOrd + core::marker::Copy + core::fmt::Debug,
    {
        let mid: usize = (lo + hi) / 2;
        // Move lowest to [lo]
        if arr[mid] < arr[lo] {
            self.swap(arr, mid, lo);
        }
        if arr[hi] < arr[lo] {
            self.swap(arr, hi, lo);
        }
        // Move highest to mid now. Median should be at 'hi'!
        if arr[hi] > arr[mid] {
            self.swap(arr, hi, mid);
        }
    }

    fn swap<'a, T>(&self, arr: &'a mut [T], lo: usize, hi: usize)
    where
        T: Copy,
    {
        if lo != hi {
            let temp = arr[lo];
            arr[lo] = arr[hi];
            arr[hi] = temp;
        }
    }
}
