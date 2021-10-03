echo "Starting H2 Database Server..."
start "h2-1.4.199.jar" cmd /k "java -jar h2-1.4.199.jar -webAllowOthers -tcpAllowOthers -tcpPort 10400 -webPort 8888"