#!/bin/sh
while true; do echo -e "HTTP/1.1 200 OK\r\n\r\nGrafana Agent OK" | nc -l -p 10000; done
