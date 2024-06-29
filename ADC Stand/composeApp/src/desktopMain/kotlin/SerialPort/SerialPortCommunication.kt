package SerialPort

import interfaces.connection.IConnectListener
import interfaces.connection.IConnectionDataflow
import interfaces.connection.InputDataModel
import jssc.SerialPort
import jssc.SerialPortEvent
import jssc.SerialPortEventListener
import jssc.SerialPortException
import jssc.SerialPortList
import kotlin.concurrent.thread

class SerialPortCommunication: SerialPortEventListener, IConnectionDataflow {

    var serialPort: SerialPort? = null
//    fun getAvailableCOMPorts(): List<String> = SerialPortList.getPortNames().toList()

    private var listener: IConnectListener? = null

    override fun Connect(data: InputDataModel, out: IConnectListener) {
        TODO("Not yet implemented")
    }

    override fun Disconnect(data: InputDataModel) {
        TODO("Not yet implemented")
    }

    override fun SendData(data: InputDataModel, out: IConnectListener) {
        TODO("Not yet implemented")
    }

    override fun CheckStands(callback: (MutableList<String>) -> Unit) {
        val ports = SerialPortList.getPortNames().toList()
        val availablePorts = emptyList<String>().toMutableList()
        for (port in ports){
            thread {
                println("$port checking...")
                try {
                    val serial = SerialPort(port)
                    serial.openPort()
                    serial.setParams(
                        SerialPort.BAUDRATE_115200,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE)
                    serial.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
                    val localListener = LocalListener(serial) {
                        if (it)
                            availablePorts.add(port)
                        println("$port  $it")
                        callback(availablePorts)
                    }
                    serial.addEventListener(localListener, SerialPort.MASK_RXCHAR)
                    serial.writeString("stand")
                    Thread.sleep(100)
                    serial.closePort()
                    println("$port closed")
                } catch (e: Exception){
                    println(e)
                }
            }
        }
    }

    private class LocalListener(val serialPort: SerialPort, val callback: (Boolean) -> Unit): SerialPortEventListener {
        var received = false
        override fun serialEvent(serialPortEvent: SerialPortEvent?) {
            if (serialPortEvent != null) {
                if (serialPortEvent.isRXCHAR && serialPortEvent.eventValue > 0) {
                    try {
                        val receivedData: String = serialPort.readString(serialPortEvent.eventValue)
                        println("Received Data : $receivedData")
                        if (receivedData.equals("{ \"request\": \"Bad request\" }"))
                            received = true
                    } catch (e: SerialPortException) {
                        println("Error in receiving response from port: $e")
                    } finally {
                        callback(received)
                    }
                }
            }
        }

    }

//    override fun Connect(data: InputDataModel, out: IConnectListener) {
//        try{
//            thread {
//                serialPort = SerialPort(data.strParam)
//                serialPort?.let{
//                    it.openPort()
//                    it.setParams(
//                        SerialPort.BAUDRATE_115200,
//                        SerialPort.DATABITS_8,
//                        SerialPort.STOPBITS_1,
//                        SerialPort.PARITY_NONE)
//                    it.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
//                    it.addEventListener(this@SerialPortCommunication, SerialPort.MASK_RXCHAR)
//                    listener = out
//                    out.callback(data.strParam, it.isOpened)
//                }
//            }
//        } catch (e: SerialPortException){
//            System.out.println("Error in writing data to port: " + e)
//            e.message?.let { out.callback(it, false) }
//        } catch (e: Exception){
//            System.out.println(e)
//            e.message?.let { out.callback(it, false) }
//        }
//    }
//
//    override fun Disconnect(data: InputDataModel) {
//        TODO("Not yet implemented")
//    }
//
//    override fun SendData(data: InputDataModel, out: IConnectListener){
//        try {
//            thread {
//                serialPort?.writeString(data.strParam)
//            }
//        } catch (e: Exception){
//            System.out.println(e)
//            e.message?.let { listener?.callback(it, false) }
//        }
//    }

    override fun serialEvent(serialPortEvent: SerialPortEvent?) {
        if (serialPortEvent != null) {
            if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                try {
                    val receivedData: String = serialPort!!.readString(serialPortEvent.getEventValue())
                    println("Received Data : $receivedData")
//                    listener?.receivedMessage(receivedData)
                } catch (e: SerialPortException) {
                    println("Error in receiving response from port: $e")
                    e.message?.let { listener?.callback(it, false) }
                }
            }
        }
    }
}