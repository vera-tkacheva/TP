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
    file_name=$(basename "$file")
    if [ -e "$output_dir/$file_name" ]; then
        hash_code=$(md5 "$file" | cut -d ' ' -f 1)
        new_file_name="${file_name%.*}_$hash_code.${file_name##*.}"
        cp "$file" "$output_dir/$new_file_name"
    else
        cp "$file" "$output_dir/$file_name"
    fi
done

