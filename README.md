## SerialFileTransfer
   SerialFileTransfer is a simple project for demostration of "how to send a file with serial communication". This transfer is realized on
  only one computer with the help of virtual serial ports.
  
## Connect serial ports virtually before run
  
  **If you are using Windows** 
  
  Two serial ports connected virtually with a tool like [Virtual Serial Port Driver by eltima](https://www.eltima.com/products/vspdxp/)
  
   **If you are using Linux** 
  
   Virtual Serial Ports can be connected with socat command on linux device
   
    socat -d -d pty,raw,echo=0 pty,raw,echo=0
    sudo ln -s /dev/pts/1 /dev/ttyUSB07
    sudo ln -s /dev/pts/2 /dev/ttyUSB08
  
