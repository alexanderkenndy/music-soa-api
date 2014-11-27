@echo off

set versionFile="src\main\webapp\WEB-INF\version.properties"

rem get last verion number
for /F "usebackq tokens=1,2,3,4* delims==_. " %%i in (%versionFile%) do (set t1=%%j
set t2=%%k
set t3=%%l
)
set /A t4=%t3%+1
set majorVersion=%t1%.%t2%.%t4%
rem get svn revison number
for /f "tokens=1,* delims=:" %%i in ('findstr /n .* .svn\entries') do (
	if "%%i" == "4" set revision=%%j
)
set version=%majorVersion%_S%revision%_%date:~0,4%%date:~5,2%%date:~8,2%
rem %time:~0,2%%time:~3,2%
echo %version%
echo version=%version% > %versionFile%

call mvn clean package
if exist "target\music2-api3-1.0.0.war" (
	move "target\music2-api3-1.0.0.war" "target\music-api2-%version%.war"
rem 	copy "doc\music-api3_版本更新发布跟踪单.doc" "target\music-api2_版本更新发布跟踪单-%version%.doc"
)

pause