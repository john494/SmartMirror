## Materials

1. HDMI-CEC-ready television (read more about it [here](http://www.howtogeek.com/207186/how-to-enable-hdmi-cec-on-your-tv-and-why-you-should))
2. Raspberry Pi computer, [model 2](https://www.adafruit.com/products/2380) or [model 3](https://www.adafruit.com/products/3058) will work well here.
3. Raspberry Pi motion sensor module (check out this one on [Amazon](https://www.amazon.com/dp/B00M1H7KBW/ref=asc_df_B00M1H7KBW4658951?smid=A1FGA0O3ZR5NRK&tag=shopz0d-20&ascsubtag=shopzilla_mp_1214-20;14813168609710505000010080301008005&linkCode=df0&creative=395105&creativeASIN=B00M1H7KBW))
4. GPIO jumper wires, which can be found at https://www.adafruit.com/products/1949 
5. Piece of 1-way glass (cut to the size of your television)
6. HDMI cable
7. The SmartMirror application [EXECUTABLE](https://github.com/john494/SmartMirror/tree/master/sbin/smartmirror-linux-armv7l)
8. The SmartMirror Mobile [APK](https://github.com/john494/SmartMirror/tree/master/appApk)
9. Web server space.

##Preparing the Raspberry Pi

* Plug one end of the HDMI cable into the pi and the other end into the HDMI-CEC enabled TV.
* Plug the included SD card into the slot on the bottom of the raspberry pi, see image.
* Plug in the included wifi adapter.

<img src="http://core0.staticworld.net/images/article/2014/08/raspberry-pi-b-microsd-photo-100409820-orig.jpg" width="300">

* Boot up the pi by plugging it into an outlet.
* Follow the onscreen prompts to install Raspbian.
* Now in order for the pi to be able to turn the TV on and off you will need to install libcec:
    * Open up a terminal window and type the following
```sh
sudo apt-get update
sudo apt-get install cmake liblockdev1-dev libudev-dev libxrandr-dev python-dev swig git
cd
git clone https://github.com/Pulse-Eight/platform.git
mkdir platform/build
cd platform/build
cmake ..
make
sudo make install
cd
git clone https://github.com/Pulse-Eight/libcec.git
mkdir libcec/build
cd libcec/build
cmake -DRPI_INCLUDE_DIR=/opt/vc/include -DRPI_LIB_DIR=/opt/vc/lib ..
make -j4
sudo make install
sudo ldconfig
```

* Now you are ready to wire the sensor to the GPIO pins
    * Take 3 GPIO jumper wires and plug them into the motion sensor.
        * Plug the red one into the VCC slot
        * Plug the yellow one into the OUT slot                                                                                            
        * Plug the green one into the GND slot

<img src="https://images-na.ssl-images-amazon.com/images/I/51kc6K4e4mL._SY355_.jpg" width="400"> 

* As per the wiring diagram below (you can ignore the LED, resistor, and breadboard)
    * Plug the red wire into pin 2
    * Plug the yellow wire into pin 11
    * Plug the green wire into pin 6

<img src="https://301o583r8shhildde3s0vcnh-wpengine.netdna-ssl.com/wp-content/uploads/2015/04/PIRconn1.jpg" width="300"> 
<img src="https://301o583r8shhildde3s0vcnh-wpengine.netdna-ssl.com/wp-content/uploads/2015/04/Raspberry-Pi-GPIO-compressed.jpg" width="500"> 

And that is it for setting up the raspberry pi!

##Setting Up Your TV

* Now that the hard part is out of the way, let’s set up your HDMI-CEC-ready television. For now, unplug your Raspberry Pi if you haven’t already, and set it to the side. How you go about attaching your sheet of 1-way glass is up to you, and how you go about it will probably depend on the TV you chose (taking into account the bezel size, etc.). You could use an [adhesive] (http://tinyurl.com/jjleo9t). Alternatively (and we would recommend) building a frame that holds your TV/glass in position. This method lends itself to easier disassembly down the road, should the need to do so arise. Note that, regardless of which method you choose, you have to account for the motion sensor module.
* Now that you have your glass mounted in front of your TV, all that’s left to do is to plug the Raspberry Pi back into the television via the HDMI cable, boot up the Pi, and run the app. Position your SmartMirror in whatever room/position you like, and you’re good to go!

##Preparing the web server

1. Either purchase server space from an online retailer or setup your own web server
     * To setup the server go to 'apache web server's web page and follow their installation guide
     * If technical skills are in doubt then please buy server space.
2. Download the server files [here] (https://github.com/john494/SmartMirror/tree/master/shittyServerWebPage)
3. Add all these files to the root directory of the web server
4. Use the http url to the web server root in the login screen in the phone app

##Install the Companion App

* This step is super simple; just open this document on your Android smartphone or tablet and [click here](https://github.com/john494/SmartMirror/tree/master/appApk) to download the android apk for your device. Once the installation completes, open the app, and sign into the various services (Twitter, Google, etc.). Be sure to select a theme and supply any relevant input to personalize your SmartMirror just the way you like.  If for some reason a stock you inputted doesn’t work, please check to ensure the correct stock is typed in.
* At this point, your SmartMirror should be ready to use! If you have any questions, or for troubleshooting, please feel free to contact the SmartMirror team through our GitHub page.
## Weather Docs
- [Weather Icons] (https://erikflowers.github.io/weather-icons/)
- Compatible [API-List] (https://erikflowers.github.io/weather-icons/api-list.html) for the icons

## News
- [News Headlines] (http://hosted2.ap.org/APDEFAULT/APNewsFeeds)

## Clock/ Date
- [DateJS] (http://www.datejs.com/) which is also protected under the MIT License

## Layout
![UI Layout](/UI/img/layout.png)
