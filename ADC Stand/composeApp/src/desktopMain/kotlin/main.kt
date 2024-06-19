import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import tmpPackageForSerialPort.SerialPortCommunication

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication,
        title = "ADC Stand",
    ) {
        App(SerialPortCommunication())
    }
}