@echo off

cd src
javac -d ../bin com/boot/main/Main.java

cd ../bin
java com.boot.main.Main
