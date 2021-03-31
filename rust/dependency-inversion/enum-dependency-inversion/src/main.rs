use sorting_algos::i_sorting;

fn main() {
    println!("Hello, world!");
    let sorting_algo = sorting_algos::factory::create_quicksort();
    let mut arr = [14, 23, 5, 11, 7];
    sort_me(sorting_algo, &mut arr);
}

fn sort_me(sorting_algo: i_sorting::Sorting, arr: &mut [usize]) {
    match sorting_algo {
        i_sorting::Sorting::MergeSort(algo) => {
            algo.sort(arr);
        }
        i_sorting::Sorting::Quicksort(algo) => {
            algo.sort(arr);
        }
    }
}
