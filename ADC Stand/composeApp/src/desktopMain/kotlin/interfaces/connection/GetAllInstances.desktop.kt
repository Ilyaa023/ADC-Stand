package interfaces.connection

import SerialPort.SerialPortCommunication
import jssc.SerialPortList
import models.Connection

actual fun getAllInstances(out: (List<Connection>) -> Unit) {
    val COMPorts = SerialPortList.getPortNames().toList()
    val instances = mutableListOf<Connection>()
    COMPorts.forEach {
        instances.add(SerialPortCommunication(it, null))
    }
    out(instances)
}