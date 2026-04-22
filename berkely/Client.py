import time
import socket
import datetime

HOST = '127.0.0.1'
PORT = 8081

client = socket.socket()
client.connect((HOST, PORT))

while True:

    try:
        now = datetime.datetime.now().timestamp()
        client.send(str(now).encode())

        data = client.recv(1024).decode()

        if not data :
            print("Server Connection Closed")
            break

        print("Synchronized time : ", data)
        time.sleep(5)
    except ConnectionResetError :
        print("Server Disconnected")
        break

client.close()

# 1) run python3/python Server.py
# 2) run python3/python Client.py