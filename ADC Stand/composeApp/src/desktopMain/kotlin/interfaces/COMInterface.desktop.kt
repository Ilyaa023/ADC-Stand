package interfaces


import SerialPort.SerialPortCommunication
import interfaces.connection.IConnectionDataflow

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

actual fun getAllInstances(out: (List<IConnectionDataflow>) -> Unit) {
    SerialPortCommunication().CheckStands(out)
}