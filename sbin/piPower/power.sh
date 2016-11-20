#!/bin/bash

if [ $# -eq 0 ]; then
	echo usage: $(basename $0) "on|off"
	exit 1
fi

if [ $1 = "off" ]; then
	echo 'standby 0' | cec-client -s RPI
	xset dpms force off
elif [ $1 = "on" ]; then
	echo 'on 0' | cec-client -s RPI
	xset dpms force on
else
	echo usage: $(basename $0) "on|off"
fi
