#!/usr/bin/env bash
set -e

SCRIPT_PATH=$( cd "$(dirname "${BASH_SOURCE[0]}")" ; pwd -P )
cd "${SCRIPT_PATH}/.."

# Build Plugin
./gradlew checkLicense
./gradlew entity-gen:run build
