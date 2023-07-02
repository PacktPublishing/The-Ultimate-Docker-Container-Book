#!/bin/sh
set -e
echo $LOGGING_FILE
if [ -n "$LOGGING_FILE" ]; then
  mkdir -p $(dirname "$LOGGING_FILE")
  touch "$LOGGING_FILE"
  exec npm start 2>&1 | tee -a "$LOGGING_FILE"
else
  exec npm start 2>&1
fi
