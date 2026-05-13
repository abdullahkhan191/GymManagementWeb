@echo off
echo ========================================
echo   Gym Management Web Application
echo ========================================
echo.

cd /d "%~dp0"

REM Check for local Maven first
set "LOCAL_MAVEN=%~dp0.maven\apache-maven-3.9.6"
if exist "%LOCAL_MAVEN%\bin\mvn.cmd" (
    echo Using local Maven installation...
    set "PATH=%LOCAL_MAVEN%\bin;%PATH%"
    set "MAVEN_HOME=%LOCAL_MAVEN%"
)

echo Checking Maven installation...
where mvn >nul 2>nul
if %errorlevel% neq 0 (
    echo ERROR: Maven is not installed!
    echo.
    echo Please run 'setup.bat' first to install Maven locally.
    echo Or install Maven globally from: https://maven.apache.org/download.cgi
    echo.
    pause
    exit /b 1
)

echo Maven found!
echo.
echo Starting Spring Boot application...
echo The application will be available at: http://localhost:8080
echo.
echo NOTE: Make sure MongoDB is running on localhost:27017
echo Press Ctrl+C to stop the application
echo.

mvn spring-boot:run

pause
