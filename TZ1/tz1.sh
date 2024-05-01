#!/bin/bash

# Получаем на вход 2 директории
input_dir="$1"
output_dir="$2"

# Предоставляем права на чтение и выполнение для входной директории и ее содержимого
chmod -R +xr "$input_dir"

files_in_input_dir=()
subdirs=()

# Перебираем содержимое входной директории, получаем список файлов во входной директории и список поддиректорий
for file in "$input_dir"/*; do
    # Если файл, то добавляем в массив файлов
    if [[ -f "$file" ]]; then 
        files_in_input_dir+=("$file")
    # Если директория, то добавляем в массив поддиректорий
    elif [[ -d "$file" ]]; then
        subdirs+=("$file")
    fi
done

# Добавляем в соответствующий массив файлы из каждой поддиректории
for subdir in "${subdirs[@]}"; do
    for file in "$subdir"/*; do
        # Если файл, то добавляем в массив файлов
        if [[ -f "$file" ]]; then
            files_in_input_dir+=("$file")
        fi
    done
done

# Копируем файлы из массива в выходную директорию
for file in "${files_in_input_dir[@]}"; do
    real_path=$(realpath "$file")
    file_name=$(basename "$real_path")
    # Если файл с таким именем уже есть в выходной директории, добавляем к имени хэш-код
    if [ -e "$output_dir/$file_name" ]; then
        hash_code=$(md5 "$real_path" | cut -d ' ' -f 1)
        new_file_name="${file_name%.*}_$hash_code.${file_name##*.}"
        cp "$real_path" "$output_dir/$new_file_name"
    # Если файла с таким именем нет, то оставляем как есть
    else
        cp "$real_path" "$output_dir/$file_name"
    fi
done
