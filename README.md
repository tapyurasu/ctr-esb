# CORBA to REST ESBモジュール

### IDLコンパイル
```
cd projects/eclipse_ws/ctr-esb/src/main/resources/IDL
/Library/Java/JavaVirtualMachines/temurin-8.jdk/Contents/Home/bin/idlj -fall -td ../../../ ESB.idl
```

### ネームサーバ起動
```
cd projects/eclipse_ws/ctr-esb/src/main/resources/ordb
orbd -ORBInitialPort 1050 -ORBInitialHost localhost
```

### wiremock
```
cd projects/wiremock
java -jar wiremock-standalone-4.0.0-beta.2.jar --port 8080 --verbose
```
