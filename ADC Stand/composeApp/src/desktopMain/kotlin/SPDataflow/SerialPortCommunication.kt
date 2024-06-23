package SPDataflow

import interfaces.IConnectListener
import interfaces.connection.InputDataModel
import interfaces.IConnectionDataflow
import jssc.SerialPort
import jssc.SerialPortEvent
import jssc.SerialPortEventListener
import jssc.SerialPortException
import jssc.SerialPortList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SerialPortCommunication: SerialPortEventListener, IConnectionDataflow {
    var serialPort: SerialPort? = null
    private var listener: IConnectListener? = null
    fun getPorts(): List<String> = SerialPortList.getPortNames().toList()

    override fun Connect(data: InputDataModel, out: IConnectListener) {
        try{
            runBlocking {
                launch {
                    serialPort = SerialPort(data.strParam)
                    serialPort?.let{
                        it.openPort()
                        it.setParams(
                            SerialPort.BAUDRATE_115200,
                            SerialPort.DATABITS_8,
                            SerialPort.STOPBITS_1,
                            SerialPort.PARITY_NONE)
                        it.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN + SerialPort.FLOWCONTROL_RTSCTS_OUT)
                        it.addEventListener(this@SerialPortCommunication, SerialPort.MASK_RXCHAR)
                        listener = out
                        out.callback(data.strParam, it.isOpened)
                    }
                }
            }

        } catch (e: SerialPortException){
            System.out.println("Error in writing data to port: " + e)
            e.message?.let { out.callback(it, false) }
        } catch (e: Exception){
            System.out.println(e)
            e.message?.let { out.callback(it, false) }
        }
    }

    override fun SendData(data: InputDataModel, out: IConnectListener){
        try {
            runBlocking {
                launch {
                    serialPort?.writeString(data.strParam)
                }
            }
        } catch (e: Exception){
            System.out.println(e)
            e.message?.let { listener?.callback(it, false) }
        }
    }

    override fun serialEvent(serialPortEvent: SerialPortEvent?) {
        if (serialPortEvent != null) {
            if (serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0) {
                try {
                    val receivedData: String = serialPort!!.readString(serialPortEvent.getEventValue())
                    println("Received Data : $receivedData")
                    listener?.receivedMessage(receivedData)
                } catch (e: SerialPortException) {
                    println("Error in receiving response from port: $e")
                    e.message?.let { listener?.callback(it, false) }
                }
            }
        }
    }
}