import RPi.GPIO as GPIO
import time
import subprocess

def main():
    GPIO.setwarnings(False)
    GPIO.setmode(GPIO.BOARD)
    GPIO.setup(11, GPIO.IN)         #Read output from PIR motion sensor
    GPIO.setup(3, GPIO.OUT)         #LED output pin
    tvstatus = 1                    #tv starts on
    count = 0
    while True:
        i=GPIO.input(11)
        if i==0:                 #When output from motion sensor is LOW
            print "No intruders",i
            if tvstatus==1 and count==200:  #tv stays on for about 30 sec. adjust count to change
                turn_off()
                tvstatus=0
            count=count+1
            time.sleep(0.1)
        elif i==1:               #When output from motion sensor is HIGH
            print "Intruder detected",i
            if tvstatus==0:
                turn_on()
                tvstatus=1
            count=0
            time.sleep(0.1)

def turn_on():
    subprocess.call("sh /usr/local/sbin/SmartMirror/piPower/power.sh on", shell=True)

def turn_off():
    subprocess.call("sh /usr/local/sbin/SmartMirror/piPower/power.sh off", shell=True)

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        io.cleanup()
