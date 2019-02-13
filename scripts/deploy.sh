#!/usr/bin/env bash
set -e

SCRIPT_PATH=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "${SCRIPT_PATH}/.."

if [[ "$1" == "master" ]]; then
    ./gradlew -Prelease publishToRips
fi

if [[ "$1" == "dev" ]]; then
    ./gradlew publishToRips
fi
