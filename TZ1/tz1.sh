#!/bin/bash

# Получаем на вход 2 директории
if [[ $# -ne 2 ]]; then
    echo "Требуется ввести: $0 input_dir output_dir"
    exit 1
fi

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
        if [[ -f "$file" ]]; then
            files_in_input_dir+=("$file")
        fi
    done
done

# Копируем файлы из массива в выходную директорию
for file in "${files_in_input_dir[@]}"; do
    file_name=$(basename -- "$file")
    end_file_path="$output_dir/$file_name"
    counter=1
    # Пока существует файл с таким именем в выходной директории, увеличиваем счетчик; добавляем его к названию
    while [ -e "$end_file_path" ]; do
        name="${file_name%.*}"
        extension="${file_name##*.}"
        # Если файл содежит точку (у него есть расширение), то к имени добавляем счетчик и расширение
        if [[ "$file_name" == *.* ]]; then
            end_file_path="$output_dir/${name}_$counter.$extension"
        # Если расширения нет, то к имени добавляем только счетчик
        else
            end_file_path="$output_dir/${name}_$counter"
        fi
        ((counter++))
    done
    cp "$file" "$end_file_path"
done
