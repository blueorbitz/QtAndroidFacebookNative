# QtAndroidFacebookNative
This is a test project to load a native library for android. For this case, we are using Facebook library to include into the qt C++ code.

The sample code just project the basic jni interface to login to facebook. 

Added: Complete wrapper refer to this. https://github.com/theScrabi/TizFbExample

# Key learning
I notice android directory has this file called "project.properties". Copy that, and add this statement in.
```
target=android-19
android.library.reference.1=../../facebook-sdk
```

Create a "local.properties" file at the same directory as the lib folder. Then add the following statement:
```
sdk.dir=C:\\Android\\android-sdk
```

Update the app id in the res/value
```
<string name="app_id">your app id here</string>
```

# My thought
1. There is a better and complete solution for Facebook Library for android now. https://github.com/theScrabi/TizFbExample
2. You can stil use the changes of linking library as in my tutoial. (to avoid checking in the whole library into your source code).
