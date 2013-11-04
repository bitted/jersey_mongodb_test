@echo off
cd ..
echo [INFO] 首先clean一下src/main/webapp/WEB-INF/classes目录，然后编译文件到该目录
echo [INFO] 其次执行copy项目依赖的jar包到src/main/webapp/WEB-INF/lib目录
call mvn clean compile
call mvn dependency:tree
call mvn dependency:copy-dependencies -DoutputDirectory=src/main/webapp/WEB-INF/lib  -DincludeScope=runtime
cd bin
pause