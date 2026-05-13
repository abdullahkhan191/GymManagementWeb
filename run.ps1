Write-Host "========================================" -ForegroundColor Cyan
Write-Host "  Gym Management Web Application" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

Set-Location $PSScriptRoot

# Check for local Maven first
$localMaven = Join-Path $PSScriptRoot ".maven\apache-maven-3.9.6"
if (Test-Path "$localMaven\bin\mvn.cmd") {
    Write-Host "Using local Maven installation..." -ForegroundColor Green
    $env:PATH = "$localMaven\bin;$env:PATH"
    $env:MAVEN_HOME = $localMaven
}

Write-Host "Checking Maven installation..." -ForegroundColor Yellow
$mvnCmd = Get-Command mvn -ErrorAction SilentlyContinue
if (-not $mvnCmd) {
    Write-Host "ERROR: Maven is not installed!" -ForegroundColor Red
    Write-Host ""
    Write-Host "Please run 'setup.bat' first to install Maven locally." -ForegroundColor Yellow
    Write-Host "Or install Maven globally from: https://maven.apache.org/download.cgi" -ForegroundColor Yellow
    Write-Host ""
    Read-Host "Press Enter to exit"
    exit 1
}

Write-Host "Maven found!" -ForegroundColor Green
Write-Host ""
Write-Host "Starting Spring Boot application..." -ForegroundColor Yellow
Write-Host "The application will be available at: http://localhost:8080" -ForegroundColor Green
Write-Host ""
Write-Host "NOTE: Make sure MongoDB is running on localhost:27017" -ForegroundColor Yellow
Write-Host "Press Ctrl+C to stop the application" -ForegroundColor Yellow
Write-Host ""

mvn spring-boot:run
