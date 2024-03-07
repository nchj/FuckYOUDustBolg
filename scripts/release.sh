#!/bin/bash

SCRIPT_DIR=$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)

if ! command -v javac &> /dev/null; then
    echo "javac could not be found, please ensure Java JDK is installed and in your PATH."
    exit 1
fi

echo "Compiling Release.java..."
javac "${SCRIPT_DIR}/Release.java"

if [ $? -eq 0 ]; then
    echo "Compilation successful."
    
    if ! command -v java &> /dev/null; then
        echo "java could not be found, please ensure Java JDK is installed and in your PATH."
        exit 1
    fi
    
    echo "Running Release..."
    java -cp "${SCRIPT_DIR}" Release
else
    echo "Compilation failed."
    exit 1
fi
