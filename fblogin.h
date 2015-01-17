/*
Author: WaiwaiBaka
Description:
This project is a workaround to login facebook using native facebook
android API on Java layer.
Thus, the invoker function is to call to the to prompt for the login.

HOW TO:
1) In the resource strings.xml, insert your facebook ApplicationID.
2) In case you have the keyhash issue, look at the debug code, copy and
   paste the value into the facebook developer application keyhash.
*/

#ifndef FBLOGIN_H
#define FBLOGIN_H

#include <QObject>

class FBLogin : public QObject
{
    Q_OBJECT
public:
    explicit FBLogin(QObject *parent = 0);

    Q_INVOKABLE void login();

signals:

public slots:

};

#endif // FBLOGIN_H
