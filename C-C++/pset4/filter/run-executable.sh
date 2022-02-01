#!/bin/bash

set -euxo pipefail

# Usage: ./run-executable -flag <input_file>

rm filter
make
mkdir -p target/images
./filter "$1" "$2" target/"$2"
