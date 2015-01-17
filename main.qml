import QtQuick 2.2
import QtQuick.Window 2.1
import QtQuick.Controls 1.1

Window {
    visible: true
    width: 360
    height: 360

    Button {
        text: "Facebook Login"
        anchors.centerIn: parent
        onClicked: {
            console.log("click login")
            QFacebook.login()
        }
    }
}
