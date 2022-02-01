#!/bin/bash

# Usage: ./run-executable -flag <input_image_in_images_folder>

rm filter
make
mkdir target
./filter "$1" images/"$2" target/"$2"
