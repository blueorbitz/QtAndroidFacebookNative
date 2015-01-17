#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>
#include "fblogin.h"

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    engine.load(QUrl(QStringLiteral("qrc:///main.qml")));

    QQmlContext *ctxt = engine.rootContext();
    ctxt->setContextProperty("QFacebook", new FBLogin);

    return app.exec();
}
