@echo off
echo [INFO] ===========Install release .===========
cd %~dp0
cd ../../../
call mvn clean package -Prelease -Dmaven.test.skip=true 
pause