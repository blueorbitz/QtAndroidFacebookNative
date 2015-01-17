TEMPLATE = app

QT += qml quick androidextras network

ANDROID_PACKAGE_SOURCE_DIR = $$PWD/android-sources

SOURCES += main.cpp \
    fblogin.cpp

RESOURCES += qml.qrc

# Additional import path used to resolve QML modules in Qt Creator's code model
QML_IMPORT_PATH =

# Default rules for deployment.
include(deployment.pri)

HEADERS += \
    fblogin.h

OTHER_FILES += \
    android-sources/src/com/qtjni/qfacebook.java \
    android-sources/AndroidManifest.xml \
    android-sources/project.properties
