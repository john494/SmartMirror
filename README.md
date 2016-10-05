# Sensor Branch #
*This branch is for the code to control the motion sensor
*Once the sensor detects motion the TV sleep timer will reset to 30 seconds
*After 30 seconds of no motion the TV will turn off until motion is detected again
*30 second sleep time can be adjusted
*Implemented by Alex Kirschner

## Documentation ##
*[Wiring diagram and sample python code](https://diyhacking.com/raspberry-pi-gpio-control/)
*[How to install libcec for HDMI TV control](https://github.com/Pulse-Eight/libcec/wiki/Raspberry-Pi-set-up)

## Usage ##
*Run from the command line located in hardware folder
```
>python pir.py

```