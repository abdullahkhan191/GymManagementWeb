# Gym Management Web Application

## Quick Start Guide

### FIRST TIME SETUP

**Run this ONCE before using the application:**
```
.\setup.bat
```
This will automatically download and configure Maven for you.

### How to Run the Application

After setup, simply double-click `run.bat` or use:

**Option 1: Using Batch Script (Recommended)**
```
.\run.bat
```

**Option 2: Using PowerShell Script**
```
.\run.ps1
```

**Option 3: Using Maven Directly (if installed globally)**
```
mvn spring-boot:run
```

### Prerequisites

1. **Java 21** - Already configured in your project
   - Check version: `java -version`
   
2. **Maven** - Will be auto-installed by setup.bat
   - Or install manually from: https://maven.apache.org/download.cgi

3. **MongoDB** - MongoDB server must be running
   - Download from: https://www.mongodb.com/try/download/community
   - Start MongoDB: `mongod` or use MongoDB Compass
   - Required on: localhost:27017

### Accessing the Application

Once the application starts successfully:
- Open your browser and go to: **http://localhost:8080**
- The application will run on port 8080

### What the Setup Does

The `setup.bat` script will:
1. Check if Maven is already installed
2. If not, download Apache Maven 3.9.6 to a local `.maven` folder
3. Configure the project to use this local Maven
4. Start the application automatically

**Note:** The local Maven installation is only for this project and won't affect your system.

### Troubleshooting

**Problem: Application won't start**
- Run `.\setup.bat` first if you haven't already
- Make sure MongoDB is running (see MongoDB setup section)

**Problem: "Maven is not installed"**
- Run `.\setup.bat` to install Maven locally
- Or install Maven globally from https://maven.apache.org/download.cgi

**Problem: "Connection refused" or MongoDB errors**
- Make sure MongoDB is running on port 27017
- Start MongoDB service: Open MongoDB Compass or run `mongod` in terminal
- Check MongoDB status: Open MongoDB Compass

**Problem: Port 8080 already in use**
- Stop any other application using port 8080
- Or change the port in `src/main/resources/application.properties`

### Features

- ✅ Member Management (Add, View, Delete members)
- ✅ Trainer Management (Add, View, Delete trainers)
- ✅ Membership Plans Management (Add, View, Delete plans)
- ✅ Dashboard with Real-time Statistics
- ✅ MongoDB Integration
- ✅ Auto-setup with local Maven

### Project Structure

```
GymWebApp/
├── src/
│   ├── main/
│   │   ├── java/com/gym/           # Java source code
│   │   │   ├── GymApplication.java # Main Spring Boot app
│   │   │   ├── controller/         # Web controllers
│   │   │   ├── model/              # Data models
│   │   │   └── repository/         # MongoDB repositories
│   │   └── resources/
│   │       ├── application.properties # Configuration
│   │       ├── static/             # CSS, JS files
│   │       └── templates/          # HTML templates
├── pom.xml                         # Maven dependencies
├── setup.bat                       # First-time setup script
├── run.bat                         # Windows run script
└── run.ps1                         # PowerShell run script
```

## Important Notes

⚠️ **DO NOT** try to run this with `javac` or plain `java` commands!  
This is a Spring Boot application and must be run using Maven.

✅ **CORRECT WAY:**
1. Run `setup.bat` (first time only)
2. Run `run.bat` every time you want to start the app

### MongoDB Setup (If not installed)

1. Download MongoDB Community Server from: https://www.mongodb.com/try/download/community
2. Install MongoDB
3. Start MongoDB:
   - **Option 1:** Use MongoDB Compass (GUI application)
   - **Option 2:** Run `mongod` in a terminal
4. MongoDB should be running on `localhost:27017`
