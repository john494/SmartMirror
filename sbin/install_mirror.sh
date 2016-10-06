#!/bin/bash

#############################################################################################
#Used to install correct scripts to locations necessary to run and start mirror app on boot #
#############################################################################################

echo "Welcome to Smart Mirror"
sudo mkdir -p /usr/local/sbin/SmartMirror/
if [ $? -ne 0 ];
then
	echo "INSTALL FAILED: could not create SmartMirror directory in /usr/local/sbin"
	exit 1
fi

sudo cp -r piPower /usr/local/sbin/SmartMirror
if [ $? -ne 0 ];
then 
	echo "INSTALL FAILED: could not copy required scripts"
	exit 1
fi 

if [ -f smartmirror ];
then
	sudo cp smartmirror /usr/local/sbin/SmartMirror
else 
	echo "INSTALL FAILED: smartmirror app file not found"
	exit 1
fi

if [ -f /etc/rc.local ];
then	
	sudo sed -i 's/\"exit 0/\"exit_0/g' /etc/rc.local
	sudo sed -i '/\/usr\/local\/sbin\/SmartMirror\/piPower\/pir.py/d' /etc/rc.local
	sudo sed -i 's"exit 0"pyton /usr/local/sbin/SmartMirror/piPower/pir.py\nexit 0"g' /etc/rc.local
	sudo sed -i '/\/usr\/local\/sbin\/SmartMirror\/smartmirror/d' /etc/rc.local
	sudo sed -i 's"exit 0"/usr/local/sbin/SmartMirror/smartmirror\nexit 0"g' /etc/rc.local
fi
