@echo off
cd ..
echo [INFO] 执行copy项目依赖的jar包到src/main/webapp/WEB-INF/lib目录
call mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
cd bin
pause