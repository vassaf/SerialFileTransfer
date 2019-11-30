package com.serial.communication;

//Before Run
//socat -d -d pty,raw,echo=0 pty,raw,echo=0
//sudo ln -s /dev/pts/1 /dev/ttyUSB07
//sudo ln -s /dev/pts/2 /dev/ttyUSB08

//echo "Test" > /dev/pts/1

public class Run {
	private static final String SERIAL_NODE_ONE = "ttyUSB07";
	private static final String SERIAL_NODE_TWO = "ttyUSB08";

	public static void main(String[] args) {

		SerialPortNode serialNodeOne = new SerialPortNode(SERIAL_NODE_ONE);
		SerialPortNode serialNodeTwo = new SerialPortNode(SERIAL_NODE_TWO);
				
		
		SerialUI serialUI_1 = new SerialUI(serialNodeOne);
		serialUI_1.createAndShowGUI();

		SerialUI serialUI_2 = new SerialUI(serialNodeTwo);
		serialUI_2.createAndShowGUI();

	}
}
