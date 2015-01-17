#include <QtAndroidExtras>
#include <QDebug>
#include "fblogin.h"

static void myQtDebug(JNIEnv *, jclass /*clazz*/, jstring s)
{
    QAndroidJniObject javaRetObj = (QAndroidJniObject)s;

    QString javastring = javaRetObj.toString();

    qDebug()<< javastring;
}

static JNINativeMethod methods[] = {
    {"qtDebug", "(Ljava/lang/String;)V", (void *)myQtDebug},
};

jint JNICALL JNI_OnLoad(JavaVM *vm, void *)
{
    JNIEnv *env;
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_4) != JNI_OK)
        return JNI_FALSE;

    jclass clazz = env->FindClass("com/waiwaibaka/qtandroidfacebooknative/qfacebook");
    if (env->RegisterNatives(clazz, methods, sizeof(methods) / sizeof(methods[0])) < 0)
        return JNI_FALSE;

    return JNI_VERSION_1_4;
}

FBLogin::FBLogin(QObject *parent) :
    QObject(parent)
{
}

void FBLogin::login()
{
    QAndroidJniObject::callStaticMethod<void>("com.waiwaibaka.qtandroidfacebooknative.qfacebook", "login");
    return;
}
