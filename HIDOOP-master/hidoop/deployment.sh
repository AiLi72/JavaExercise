#!/bin/bash

if [ "$1" = RM ]; then
    nohup java -cp bin resourcemanager.ResourceManager > foo.out 2> foo.err < /dev/null &
    echo $! > /tmp/pid_hidoop
fi

if [ "$1" = NM ]; then
    nohup java -cp bin nodemanager.NodeManager > foo.out 2> foo.err < /dev/null &
    echo $! > /tmp/pid_hidoop
fi


