package com.serial.communication;

import com.fazecast.jSerialComm.SerialPort;

public class SerialPortNode {

	public SerialPort serialPort;
	String portName;

	public SerialPortNode(String portName) {
		this.portName = portName;
		this.serialPort = this.getPort();
		this.serialPort.openPort();
	}

	public SerialPort getPort() {
		SerialPort[] myPorts = SerialPort.getCommPorts();
		for (SerialPort serialPort : myPorts) {
			if (serialPort.getSystemPortName().equals(this.portName)) {
				System.out.println("Port Detected : " + serialPort.getSystemPortName());
				return serialPort;
			}
		}
		System.out.println("This Port does Not exist");
		return null;
	}

	public void sendData(String message) {
		this.serialPort.writeBytes(message.getBytes(), message.getBytes().length);
	}

	public void sendDataAsByteArray(byte[] bytes) {
		this.serialPort.writeBytes(bytes, bytes.length);
	}
}
