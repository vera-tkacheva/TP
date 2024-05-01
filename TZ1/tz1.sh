#!/bin/bash

input_dir="$1"
output_dir="$2"
files_in_input_dir=()

for file in "$input_dir"/*; do
    if [ -f "$file" ]; then
        files_in_input_dir+=("$file")
    fi
done

while IFS= read -r -d '' file; do
    files_in_input_dir+=("$file")
done < <(find "$input_dir" -mindepth 1 -type f -print0)

for file in "${files_in_input_dir[@]}"; do
    real_path=$(realpath "$file")
    file_name=$(basename "$real_path")
    if [ -e "$output_dir/$file_name" ]; then
        hash_code=$(md5 "$real_path" | cut -d ' ' -f 1)
        new_file_name="${file_name%.*}_$hash_code.${file_name##*.}"
        cp "$real_path" "$output_dir/$new_file_name"
    else
        cp "$real_path" "$output_dir/$file_name"
    fi
done
