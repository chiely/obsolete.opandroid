Thank you for downloading Hookflash's Open Peer Android SDK.

For a quick introduction to the code please read the following. For more detailed instructions please go to http://docs.hookflash.com.

From your terminal, please clone the “opandroid” git repository:

    git clone --recursive https://github.com/openpeer/opandroid.git

Note the `--recursive` option is key to make sure you get all the necessary libraries

This repository will yield the Android Java SDK, Native sample application and dependency libraries like the C++ Open Peer core, stack, media and libraries needed to support the underlying SDK.

Directory structure:
+ `opandroid/` - contains the project files for building the Open Peer Android SDK
+ `opandroid/openpeer-android-sdk/` - contains the Open Peer Android Java project files and source folders
+ `opandroid/openpeer-android-sdk/jni/` - contains the Java SDK JNI wires
+ `opandroid/OpenPeerSampleApp/` - contains the sample application 

How to build:
Prerequisites: 
+ [Android SDK](https://developer.android.com/sdk/index.html)
+ Android NDK  Android NDK r8e (http://dl.google.com/android/ndk/android-ndk-r10c-darwin-x86_64.bin for mac) is required. Path to the NDK is mandatory for building 3rd party libraries. 
+ [ninja](http://martine.github.io/ninja/) build sysytem required for WebRTC library build. You can install ninja using MacPorts or Homebrew.

Building step by step:

1) Download and build required native 3rd party libraries by running the `buildall_android.sh` script from your terminal:

    pushd opandroid/
    ./buildall_android.sh {PATH_TO_NDK}
    popd

This will take a long time but should only need to be done whenever there's change in the `libs/op` repository. Use `buildall_android.sh` to build the C++ libraries first. The libraries' binary files are placed in `libs/op/libs/build` and `libs/op/libs/ortc-lib/libs/build/` and are linked into library project.

2) From Eclipse ADT, import the SDK project:

    opandroid/openpeer—android-sdk (Android Java SDK project)

Then import the sample project:

    opandroid/OpenPeerSampleApp

3) Build the imported project using Eclipse with ADT plugin

Exploring the dependency libraries:
+ `libs/op/libs/ortc-lib/libs/zsLib` - asynchronous communication library for C++
+ `libs/op/libs/ortc-lib/libs/udns` - C language DNS resolution library
+ `libs/op/libs/ortc-lib/libs/cryptopp` – C++ cryptography language
+ `libs/op/libs/ortc-lib/libs/openssl` - C++ Hookflash Open Peer communication services layer
+ `libs/op/libs/ortc-lib/libs/op-services-cpp` - C++ Hookflash Open Peer communication services layer
+ `libs/op/libs/op-stack-cpp` – C++ Hookflash Open Peer stack
+ `libs/op/libs/op-core-cpp` – C++ Hookflash Open Peer core API (works on the Open Peer stack)
+ `libs/op/libs/ortc-lib/libs/WebRTC` – Android port of the WebRTC media stack

During development we use symbolic links to link the SDK source folder from sample app so we can invoke native application debugging in Eclipse. You should not need to debug native libaries but in case you do, executing `setup-native-debug-proj.sh` will set up all symbolic links so you can do native debugging. Executing `setup-library-proj.sh` will clean all the links and use the SDK as library. You can also use gdb command line debugging that way you don't need this script.

We're also aware many developers use Android Studio instead of Eclipse. Due to the constraint that Android Studio doesn't have nice support for native debugging, we're doing our own development with Eclipse. But we will explore the way to set up native debugging in Android Studio and add project configurations for Android Studio as well.

Build with one script (ant 1.8 or newer is required):
We have a simple script `buillall.sh` at root. The script will simply invoke the `buildall_android.sh`, then invoke `ant` to build the sample app. To use this script, you will need to export `ANDROID_HOME` and `ANDROID_NDK_HOME`

    export ANDROID_HOME={PATH-TO-YOUR-ANDROID-SDK}
    export ANDROID_NDK_HOME={PATH-TO-YOUR-ANDROID-NDK}
    ./buildall.sh

For convenience I put these settings in my `.bash_profile` (suppose you're using Mac OS or Linux).

Branches:

Our current activity is being performed on [20140401-dev-stable]( https://github.com/openpeer/opandroid/tree/20140401-dev-stable). Individual activity is on other branches from this branch.

To see all branches go to:
https://github.com/openpeer/opandroid/branches

Contact info:

Please contact robin@hookflash.com if you have any suggestions to improve the API. Please use support@hookflash.com for any bug reports. New feature requests should be directed to erik@hookflash.com.

Thank you for your interest in the Hookflash Open Peer Android SDK.

License:
```
 /*******************************************************************************
 *
 *  Copyright (c) 2014 , Hookflash Inc.
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *  
 *  1. Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *  
 *  The views and conclusions contained in the software and documentation are those
 *  of the authors and should not be interpreted as representing official policies,
 *  either expressed or implied, of the FreeBSD Project.
 *******************************************************************************/
```
