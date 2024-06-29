package interfaces


import SerialPort.SerialPortCommunication
import interfaces.connection.IConnectionDataflow

//actual fun getAvailableCOMPorts(): List<String> = SerialPortList.getPortNames().toList()
//actual fun checkCOMStand(
//        list: List<String>, checked: (MutableMap<String, Boolean>) -> Unit
//) {
//    for (port in list){
//        val map: MutableMap<String, Boolean> = mutableMapOf()
//        thread {
//            println(port)
//            try {
//                val serial = SerialPort(port)
//                serial.openPort()
//                serial.setParams(
//                    SerialPort.BAUDRATE_115200,
//                    SerialPort.DATABITS_8,
//                    SerialPort.STOPBITS_1,
//                    SerialPort.PARITY_NONE)
//                serial.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
//                val localListener = LocalListener(serial) {
//                    map[port] = it
//                    println(port)
//                    println("\t$map")
//                    checked(map)
//                }
//                serial.addEventListener(localListener, SerialPort.MASK_RXCHAR)
//                serial.writeString("stand")
//                Thread.sleep(100)
//                if (!map.keys.contains(port)) map[port] = false
//                serial.closePort()
//            } catch (e: Exception){
//                println(e)
////                map[port] = false
////                println("\t$map")
////                checked(map)
//            }
//        }
//    }
//}

//actual fun getConnectionImpl(
//        str: String, listener: IConnectListener, callback: (IConnectionDataflow) -> Unit
//) {
//    thread {
//        try {
//            val serial = SerialPort(str)
//            serial.openPort()
//            serial.setParams(
//                SerialPort.BAUDRATE_115200,
//                SerialPort.DATABITS_8,
//                SerialPort.STOPBITS_1,
//                SerialPort.PARITY_NONE)
//            serial.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
//            val globalListener = GlobalListener(serial) { message, success ->
//                listener.callback(message, success)
//            }
//            serial.addEventListener(globalListener, SerialPort.MASK_RXCHAR)
//        } catch (e: Exception){
//            System.out.println(e)
//        }
//    }
//}
//
//class GlobalListener(val serialPort: SerialPort, val listener: (String, Boolean) -> Unit): SerialPortEventListener {
//    override fun serialEvent(serialPortEvent: SerialPortEvent?) {
//        if (serialPortEvent != null) {
//            if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
//                try {
//                    val receivedData: String = serialPort.readString(serialPortEvent.getEventValue())
//                    println("Received Data : $receivedData")
//                    listener(receivedData, !receivedData.equals("{ \"request\": \"Bad request\" }"))
//                } catch (e: SerialPortException) {
//                    println("Error in receiving response from port: $e")
//                    listener(e.message.toString(), false)
//                }
//            }
//        }
//    }
//}
actual fun getCOMConnectionInstance(): IConnectionDataflow? {
    return SerialPortCommunication()
}