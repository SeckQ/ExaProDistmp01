cd build/install/mp01
Start Chrome http://localhost:8080/books/findAll
java -Dserver.port=8080 -cp lib/* com.distribuida.Servidor
CTRL^C
java -Dserver.port=7001 -cp lib/* com.distribuida.Servidor
java -Dserver.port=49152 -cp lib/* com.distribuida.Servidor
java -Dserver.port=49153 -cp lib/* com.distribuida.Servidor
pause