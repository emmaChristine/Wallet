#!/bin/bash

set -eo pipefail

echo "Executing tasks: [monkey runner]"


adb shell "monkey -p com.demo.wallet -v 1000"