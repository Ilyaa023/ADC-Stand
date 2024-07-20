package SerialPort

import interfaces.connection.IConnectListener
import jssc.SerialPort
import jssc.SerialPortEvent
import jssc.SerialPortEventListener
import jssc.SerialPortException
import models.Connection
import models.ConnectionType
import kotlin.concurrent.thread

class SerialPortCommunication(connectionName: String, listener: IConnectListener?) : SerialPortEventListener,
                                                        Connection(connectionName, listener) {
    private var serialPort: SerialPort = SerialPort(connectionName)
    override var connectionType: ConnectionType? = ConnectionType.COM

    override fun Connect() {
//        thread {
            try {
                println("${connectionName} connecting...")
                serialPort.openPort()
                serialPort.setParams(
                    SerialPort.BAUDRATE_115200,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
                )
                serialPort.addEventListener (this)
                serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
                listener?.callback(connectionName, connectionTestString, serialPort.isOpened)
                println("$connectionName opened")
            } catch (e: Exception){
                println(e.message)
                listener?.callback(connectionName, connectionTestString, serialPort.isOpened)
            }
//        }
    }
    override fun Disconnect() {
//        thread {
            try {
                    serialPort.closePort()
                    println("${connectionName} closed")
                    listener?.callback(connectionName, connectionTestString, serialPort.isOpened)
            } catch (e: Exception){
                println(e.message)
                listener?.callback(connectionName, connectionTestString, serialPort.isOpened)
            }
//        }
    }
    override fun SendString(data: String) {
        try {
            thread {
                serialPort.writeString(data)
                println("${data} sent to $connectionName")
            }
        } catch (e: Exception){ println(e) }
    }
    override fun SendByte(data: Byte) {
        TODO("Not yet implemented")
    }
//    override fun CheckStand(callback: (Connection) -> Unit) {
//
//        val ports = SerialPortList.getPortNames().toList()
//        val availablePorts = emptyList<String>().toMutableList()
//        for (port in ports){
//            thread {
//                println("$port checking...")
//                try {
//                    val serial = SerialPort(port)
//                    serial.openPort()
//                    serial.setParams(
//                        SerialPort.BAUDRATE_115200,
//                        SerialPort.DATABITS_8,
//                        SerialPort.STOPBITS_1,
//                        SerialPort.PARITY_NONE)
//                    serial.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
////                    val localListener = LocalListener(serial) {
////                        if (it)
////                            availablePorts.add(port)
////                        println("$port  $it")
////                        callback(availablePorts)
////                    }
////                    serial.addEventListener(localListener, SerialPort.MASK_RXCHAR)
//                    serial.writeString("models")
//                    Thread.sleep(100)
//                    serial.closePort()
//                    println("$port closed")
//                } catch (e: Exception){
//                    println(e)
//                }
//            }
//        }
//    }
    override fun serialEvent(serialPortEvent: SerialPortEvent?) {
        var received = false
        var receivedData = ""
        if (serialPortEvent != null) {
            if (serialPortEvent.isRXCHAR && serialPortEvent.eventValue > 0) {
                try {
                    receivedData = serialPort.readString(serialPortEvent.eventValue)
                    println("Received Data : $receivedData")
                    received = receivedData.isNotEmpty()
                    //                                    if (receivedData.equals("{ \"request\": \"Bad request\" }"))
                    //                                        received = true
                } catch (e: SerialPortException) {
                    println("Error in receiving response from port: $e")
                } finally {
                    listener?.callback(connectionName, receivedData, received)
                }
            }
        }
    }
}
