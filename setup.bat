@echo off
REM ========================================
REM   Gym Management - First Time Setup
REM ========================================
echo.
echo This script will set up Maven for running the Gym Management application.
echo.

cd /d "%~dp0"

REM Check if Maven is already installed globally
where mvn >nul 2>nul
if %errorlevel% equ 0 (
    echo Maven is already installed globally!
    echo You can run the application using: run.bat
    echo.
    pause
    exit /b 0
)

echo Maven is not installed. Setting up local Maven...
echo.

set MAVEN_VERSION=3.9.6
set MAVEN_HOME=%~dp0.maven\apache-maven-%MAVEN_VERSION%
set MAVEN_URL=https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip

if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    echo Local Maven already exists at: %MAVEN_HOME%
    echo.
    goto :RUN_APP
)

echo Downloading Apache Maven %MAVEN_VERSION%...
echo This may take a few minutes...
echo.

mkdir .maven 2>nul

REM Download Maven using PowerShell
powershell -Command "& {[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%MAVEN_URL%' -OutFile '.maven\maven.zip'}"

if %errorlevel% neq 0 (
    echo ERROR: Failed to download Maven!
    echo.
    echo Please manually install Maven from: https://maven.apache.org/download.cgi
    echo Or use the following package managers:
    echo   - Chocolatey: choco install maven
    echo   - Scoop: scoop install maven
    echo.
    pause
    exit /b 1
)

echo Extracting Maven...
powershell -Command "Expand-Archive -Path '.maven\maven.zip' -DestinationPath '.maven' -Force"

if %errorlevel% neq 0 (
    echo ERROR: Failed to extract Maven!
    pause
    exit /b 1
)

del .maven\maven.zip

echo.
echo Maven setup completed successfully!
echo.

:RUN_APP
echo Starting the application...
echo.

if exist "%MAVEN_HOME%\bin\mvn.cmd" (
    set "PATH=%MAVEN_HOME%\bin;%PATH%"
)

call "%~dp0run.bat"

exit /b 0
