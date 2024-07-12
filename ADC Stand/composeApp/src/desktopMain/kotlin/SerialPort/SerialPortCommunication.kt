package SerialPort

import interfaces.connection.IConnectListener
import interfaces.connection.IConnectionDataflow
import jssc.SerialPort
import jssc.SerialPortEvent
import jssc.SerialPortEventListener
import jssc.SerialPortException
import jssc.SerialPortList
import models.ConnectionType
import kotlin.concurrent.thread

class SerialPortCommunication() : SerialPortEventListener, IConnectionDataflow {
    private var comName = ""
    private var serialPort: SerialPort? = null
    private var listener: IConnectListener? = null
    override var connectionName: String = ""
    override val connectionType: ConnectionType = ConnectionType.COM

    override fun Connect(data: String, out: IConnectListener) {
        try {
            thread {
                println("${data} connecting...")
                serialPort = SerialPort(data)
                serialPort!!.let {
                    it.openPort()
                    it.setParams(
                        SerialPort.BAUDRATE_115200,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE
                    )
                    it.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
                    it.addEventListener(this@SerialPortCommunication, SerialPort.MASK_RXCHAR)
                    listener = out
                    it.writeString("models")
                }
            }
        } catch (e: Exception){ println(e) }
    }
    override fun Disconnect(data: String) {
        try {
            thread {
                serialPort!!.closePort()
                println("${data} closed")
            }
        } catch (e: Exception){ println(e) }
    }
    override fun SendString(data: String, out: IConnectListener) {
        try {
            listener = out
            thread {
                serialPort!!.writeString(data)
                println("${data} sent")

            }
        } catch (e: Exception){ println(e) }
    }

    override fun SendByte(data: String, out: IConnectListener) {
        TODO("Not yet implemented")
    }

    override fun CheckStands(callback: (MutableList<IConnectionDataflow>) -> Unit) {

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
//                    val localListener = LocalListener(serial) {
//                        if (it)
//                            availablePorts.add(port)
//                        println("$port  $it")
//                        callback(availablePorts)
//                    }
//                    serial.addEventListener(localListener, SerialPort.MASK_RXCHAR)
                    serial.writeString("models")
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
                    if (receivedData.equals("{ \"request\": \"Bad request\" }")){
                        listener?.callback(comName, receivedData, false)
                    } else if (receivedData.equals("ADC")){
                        listener?.callback(comName, receivedData, true)
                    }
//                    listener?.receivedMessage(receivedData)
                } catch (e: SerialPortException) {
                    println("Error in receiving response from port: $e")
                    e.message?.let { listener?.callback(comName, it, false) }
                }
            }
        }
    }
}