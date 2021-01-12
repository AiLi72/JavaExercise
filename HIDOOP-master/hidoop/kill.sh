#!/bin/bash

kill -9 "$(cat /tmp/pid_hidoop)"
rm -r /tmp/datanodefiles
rm /tmp/pid_hidoop