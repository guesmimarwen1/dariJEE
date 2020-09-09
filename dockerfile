FROM wuodan/wildfly-windows
COPY dari-ear.ear c:/wilfly/standalone/deployements
CMD ["C:/wildfly/bin/standalone.bat","-b","0.0.0.0"]