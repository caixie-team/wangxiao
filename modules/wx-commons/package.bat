@echo off
echo [INFO] Install jar to local repository.

cd %~dp0
call mvn package -Dmaven.test.skip=true
pause