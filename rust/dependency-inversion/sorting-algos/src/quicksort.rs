pub struct Quicksort;

impl Quicksort {
    pub fn new() -> Self {
        Self
    }

    pub fn sort<'a, T>(&self, arr: &'a mut [T]) -> &'a mut [T] {
        println!("I am in quicksort!");
        arr
    }
}
