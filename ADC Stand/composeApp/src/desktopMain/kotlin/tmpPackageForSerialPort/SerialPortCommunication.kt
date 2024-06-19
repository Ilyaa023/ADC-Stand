package tmpPackageForSerialPort

import interfaces.IAppInitialData
import jssc.SerialPortList

class SerialPortCommunication: IAppInitialData{

    override fun getPorts(): List<String> {
        var portNames = SerialPortList.getPortNames().toList()
        return portNames
    }

}