## **Release Notes**
* Features in this Release
   * Unlocking the Box by Clicking Unlock.
   * Unlocking the Box through a Passcode.
   * Editing existing passcode.
* Known Bugs
  * No Known Bugs
* Previous Defects
  * Lack of support for Lock History has been remedied. Users can now monitor actions taken on a lock via our backend server.
  * Lack of support for different types of password, such as OTP, recurring, and permament passwords have been implemented. Users can now create many types of passwords to work with their needs.

## **Install Guide**

**Basic Repository Overview**

_High-level component architecture showing backend service, Android App, Smart Lock, and important dependencies_
![Alt](https://i.imgur.com/lL4rEwx.png)

For this product, Smart Lock functionality is powered by three custom pieces of software:
1. **Backend Service** - This service communicates with both the box and the app. It contains endpoints that allow users to manage, unlock, and add passwords to their Smart Locks.
2. **Android App** - This app provides a user-friendly UI that lets users unlock their locks remotely, add or update locks, and view action history.
3. **Smart Lock Embedded Software** - The Smart Lock can also operate autonomously if internet connection is lost by syncing with the backend service. Users can enter passwords into the keypad to unlock the device.

In addition, the software depends on three cloud-based services to operate:
1. **Google Firebase** - Firebase’s Real-time Datastore is how the server saves data. It contains all lock information, including history, hashed passwords, and user-lock ownership metadata. Furthermore, it stores basic user information such as name and user ID.
2. **Google Authenticator** - The phone app uses Google’s in-house authentication service to let them log in and make requests to our server.
3. **AWS EC2** - The backend server needs to be deployed on some backend service in order to be queried remotely on production. We chose AWS EC2 to host the service.

**Pre-requisites**
* Related Repos:
    * [Backend Service](https://github.com/JerAguilon/Junior-Design-Smart-Lock)
    * [Android](https://github.com/myothiha09/SmartBox)
* Softwares:
    * Android Studio
* Hardware
    * Raspberry Pi
    * A Smartbox (contains servo mortar)
* Dependent Libraries (Must be installed)
    * Software
        * Java 1.8
        * Android SDK
        * Python 3.6 or 3.7
        * [Python Virtualenv](https://docs.python-guide.org/dev/virtualenvs/) (Used to setup an environment to install Python dependencies)
        * [Python Pip](https://pypi.org/project/pip/) (Used to auto-install all Python dependencies, )
            * Major dependencies include Flask, Pyrebase, and bcrypt. A complete list of dependencies can be found [here](https://github.com/JerAguilon/Junior-Design-Smart-Lock/blob/master/requirements.txt).
        * [Node Package Manager (NPM)](https://www.npmjs.com/get-npm)
    * Hardware
        * Base64
        * requests
        * PyBluez


**Download Instructions**
* Clone the repositories under Related Repos and follow the instruction in README.md

**Build Instructions**
* For Android code:
    * Open the Project in Android Studio.
    * Just click Run. Gradle will build it automatically
* For Raspberry code:
    * Run the python script called control.py.
* For Backend code:
    * Almost all build components are automated during installation except for extremely security-sensitive environment variables. Follow the prerequisites [here](https://github.com/JerAguilon/Junior-Design-Smart-Lock/blob/master/readme.md#prerequisites) to set these variables.

**Installation**
* For Android:
    * Enable adb on your Anroid phone. Follow the instructions [here](https://www.howtogeek.com/125769/how-to-install-and-use-abd-the-android-debug-bridge-utility/). 
    * Click Run in Android Studio and choose your phone as the target device. This will install the app onto your phone.
* For Hardware:
    * Run `sudo nano /etc/rc.local`.
    * Add a line to run the python script. Append ‘ &’ to the line.
* For Backend: 
    * After installing the prerequisite packages (Python Pip, Virtualenv, and NPM) and setting up environment variables, follow the installation instructions [here](https://github.com/JerAguilon/Junior-Design-Smart-Lock/blob/master/readme.md#linuxmacos).


**Run Instructions**
* For Android:
    * Follow the instructions under Installation.
* For Backend:
    * The repository comes with a .sh file that is compatible with UNIX systems:
        * Run the server: `chmod +x dev_flask.sh && ./dev_flask.sh`
        * Run the tests: `chmod +x run_tests.sh && ./run_tests.sh`
    * To manually play with endpoints after running, go to `/docs` to view an interactive UI with endpoints. 

**Troubleshooting**
* Installing and running Android Studio or running Android applications correctly can get tricky. [Here](https://code.tutsplus.com/tutorials/how-to-solve-androids-most-common-error-messages--cms-28706) are some of the most common errors and how to deal with them. If your error is not on this list, please consult StackOverFlow.
* The backend service contains a complex set of dependencies. If you encounter issues, consult the troubleshooting section of the Readme in the backend repo [here](https://github.com/JerAguilon/Junior-Design-Smart-Lock/blob/master/readme.md#troubleshooting).

