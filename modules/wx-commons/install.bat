@echo off
echo [INFO] Install jar to local repository.

cd %~dp0
call mvn install -Dmaven.test.skip=true
pause