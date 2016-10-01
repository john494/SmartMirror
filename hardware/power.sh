#!/bin/bash

if [ $# -eq 0 ]; then
	echo usage: $(basename $0) "on|off|status"
	exit 1
fi

if [ $1 = "off" ]; then
	vcgencmd display_power 0
elif [ $1 = "on" ]; then
	vcgencmd display_power 1
else
	echo usage: $(basename $0) "on|off|status"
fi
