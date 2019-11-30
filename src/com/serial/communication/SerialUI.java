package com.serial.communication;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class SerialUI implements SerialPortDataListener {

	SerialPortNode serialPortNode;

	JTextArea recievedMessage = new JTextArea("");

	public SerialUI(SerialPortNode serialPortNode) {
		this.serialPortNode = serialPortNode;
		this.serialPortNode.serialPort.addDataListener(this);
	}

	public void createAndShowGUI() {

		JFrame frame = new JFrame(serialPortNode.serialPort.getSystemPortName());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(4, 2));

		JTextArea inputTextArea = new JTextArea("");
		panel.add(inputTextArea);

		frame.setPreferredSize(new Dimension(500, 200));

		JButton button = new JButton("Gönder");

		button.setPreferredSize(new Dimension(40, 40));

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(inputTextArea.getText());
//				serialPortNode.sendData(inputTextArea.getText());
//				byte[] bytes = { 80, 65, 78, 75, 65, 74 };
				serialPortNode.sendDataAsByteArray(getFileAsByteArray());

			}
		});
		panel.add(button);

		JLabel header = new JLabel("Gelen Mesaj");
		panel.add(header);

		panel.add(recievedMessage);

		frame.setContentPane(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_RECEIVED;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		byte[] newData = event.getReceivedData();
		System.out.println("Received data of size: " + newData.length);
		String PATH_TO_OUTPUT_FILE = "/home/burak/Desktop/ADAM/adam_copy";
		try {
			OutputStream os = new FileOutputStream(PATH_TO_OUTPUT_FILE);
			os.write(newData);
			os.close();
		} catch (IOException e) {
			System.out.println("Output File oluşturulamadı.");
		}
		 
		String message = "";
		for (int i = 0; i < newData.length; ++i) {
//			System.out.print(Character.getNumericValue((char) newData[i]));
			message = message + (char) newData[i];
		}
		recievedMessage.setText(message);

	}

	public byte[] getFileAsByteArray() {
		String PATH_TO_INPUT_FILE = "/home/burak/Desktop/ADAM/adam";
		File file = new File(PATH_TO_INPUT_FILE);
		System.out.println(file.exists());
		byte[] fileContent = null;
		try {
			fileContent = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			System.out.println("Dosya okunamadı");
		}
		return fileContent;

	}
}
