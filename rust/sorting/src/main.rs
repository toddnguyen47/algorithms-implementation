fn main() {}

#[allow(dead_code)]
fn sort_vector(vec_input: &Vec<i32>, sorting_algo: impl SortingAlgorithm) -> Vec<i32> {
    sorting_algo.sort(vec_input)
}

pub trait SortingAlgorithm {
    fn sort(&self, vec_input: &Vec<i32>) -> Vec<i32>;

    fn swap_in_place(&self, vec_input: &mut Vec<i32>, index1: usize, index2: usize) {
        let temp = vec_input[index1];
        vec_input[index1] = vec_input[index2];
        vec_input[index2] = temp;
    }
}

struct BubbleSort {}
impl SortingAlgorithm for BubbleSort {
    fn sort(&self, vec_input: &Vec<i32>) -> Vec<i32> {
        let mut new_vec = vec_input.to_vec();
        loop {
            let mut is_swapped = false;
            let length = new_vec.len();
            for i in 1..length {
                if new_vec[i] < new_vec[i - 1] {
                    is_swapped = true;
                    self.swap_in_place(&mut new_vec, i, i - 1);
                }
            }
            if !is_swapped {
                break;
            }
        }
        new_vec
    }
}

struct Quicksort {}
impl SortingAlgorithm for Quicksort {
    fn sort(&self, vec_input: &Vec<i32>) -> Vec<i32> {
        self.quicksort_recursively(vec_input, 0, (vec_input.len() as i32) - 1)
    }
}

impl Quicksort {
    fn quicksort_recursively(&self, vec_input: &Vec<i32>, lo: i32, hi: i32) -> Vec<i32> {
        let mut new_vec = vec_input.to_vec();
        // Base case, if partition size is 0 or 1
        if hi - lo < 1 {
            new_vec
        } else {
            self.move_median_pivot_to_last_pos_in_place(&mut new_vec, lo, hi);
            let mut left_index = lo as usize;
            let mut pivot_index = hi as usize;
            while pivot_index > left_index {
                // Move left index until the element is greater than the element at pivotIndex
                while new_vec[left_index] < new_vec[pivot_index] {
                    left_index += 1;
                }
                // Found something! Do 2 swaps to get pivot to the correct index
                if pivot_index > left_index {
                    self.swap_in_place(&mut new_vec, left_index, pivot_index);
                    pivot_index -= 1;
                    self.swap_in_place(&mut new_vec, left_index, pivot_index);
                }
            }
            // Pivot index is now in correct location
            // Sort left partition and right partition recursively
            let pivot_index = pivot_index as i32;
            new_vec = self.quicksort_recursively(&mut new_vec, lo, pivot_index - 1);
            new_vec = self.quicksort_recursively(&mut new_vec, pivot_index + 1, hi);
            new_vec
        }
    }

    fn move_median_pivot_to_last_pos_in_place(&self, vec_input: &mut Vec<i32>, lo: i32, hi: i32) {
        let lo = lo as usize;
        let hi = hi as usize;
        let middle_index = lo + ((hi - lo) / 2);
        if vec_input[middle_index] < vec_input[lo] {
            self.swap_in_place(vec_input, lo, middle_index);
        }
        if vec_input[hi] < vec_input[lo] {
            self.swap_in_place(vec_input, lo, hi);
        }
        if vec_input[middle_index] < vec_input[hi] {
            self.swap_in_place(vec_input, middle_index, hi);
        }
    }
}

struct InstantiatedAlgos {
    bubble_sort: BubbleSort,
    quick_sort: Quicksort,
}

fn get_instantiated_algos() -> InstantiatedAlgos {
    InstantiatedAlgos {
        bubble_sort: BubbleSort {},
        quick_sort: Quicksort {},
    }
}

#[cfg(test)]
mod tests {
    use super::*;
    use rand::Rng;

    #[test]
    fn sort_zero_elem() {
        let algos = get_instantiated_algos();

        let vec1 = vec![];
        assert_eq!(sort_vector(&vec1, algos.quick_sort), []);
    }

    #[test]
    fn sort_one_elem() {
        let algos = get_instantiated_algos();

        let vec1 = vec![1000];
        assert_eq!(sort_vector(&vec1, algos.quick_sort), [1000]);
    }

    #[test]
    fn sort_two_elems() {
        let algos = get_instantiated_algos();
        let vec1 = vec![2, 1];
        assert_eq!(sort_vector(&vec1, algos.quick_sort), [1, 2]);
    }

    #[test]
    fn sort_ten_elems() {
        let algos = get_instantiated_algos();
        let vec1 = vec![6, 7, 8, 9, 10, 1, 2, 3, 4, 5];
        assert_eq!(
            sort_vector(&vec1, algos.quick_sort),
            [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        )
    }

    #[test]
    fn sort_1000_random_elems() {
        let algos = get_instantiated_algos();

        let mut vec1 = Vec::<i32>::new();
        let max_int = 1000;
        for _ in 0..max_int {
            vec1.push(rand::thread_rng().gen_range(1, max_int + 1));
        }
        let mut vec2 = vec1.to_vec();
        vec2.sort();
        assert_eq!(vec2, sort_vector(&vec1, algos.quick_sort));
    }
}
