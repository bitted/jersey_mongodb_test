@echo off
cd ..
echo [INFO] clean一下src/main/webapp/WEB-INF/classes目录，然后编译文件到该目录
call mvn clean compile
cd bin
pause