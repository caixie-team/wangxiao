@echo off
echo [INFO] ===========Install mine .===========
cd %~dp0
cd ../../../
call mvn  compile war:war -Pmine -Dmaven.test.skip=true
pause