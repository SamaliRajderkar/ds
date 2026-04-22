import time
import datetime
import socket

clients = []
HOST = ''
PORT = 8081

server = socket.socket()
server.bind((HOST, PORT))
server.listen(5)

print("Server Started")

conn, addr = server.accept()
print("Connected :", addr)
clients.append(conn)

while True:
    times = []

    for c in clients:
        t = float(c.recv(1024).decode())
        times.append(t)

    server_time = datetime.datetime.now().timestamp()
    times.append(server_time)

    avg = sum(times)/len(times)

    for c in clients:
        c.send(str(avg).encode())
    
    print("Synchronization done")

    time.sleep(5)