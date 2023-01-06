cd build/install/mp01
java -Dserver.port=8080 -cp lib/* com.distribuida.Servidor
java -Dserver.port=7001 -cp lib/* com.distribuida.Servidor
java -Dserver.port=49152 -cp lib/* com.distribuida.Servidor
java -Dserver.port=49153 -cp lib/* com.distribuida.Servidor
pause