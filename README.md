# Excercise Test Automation 

A test automation framework developed using Selenium, Java, Cucumber, Rest Assured

## Getting Started
To set up this project on your local machine for development and testing purposes. 
### Prerequisites

* IntelliJ Version: 2017.2.6 or higher
* Maven 3.3 or higher
* JDK 1.8 or higher

Check Maven Version by Below Command
```
mvn --version
```

Check Java Version by Below Command
```
java -version
```

### Installing

Download Github Repository to your PC/MAC

1. Open IntelliJ IDEA and close any existing project.
2. From the Welcome screen, click **Import Project**.
The Select File or Directory to Import dialog will open.
3. Navigate to your Maven project and select the top-level folder. For example, with a project named
4. Click **Open**.
5. The **Import Project** dialog will opens:
6. In the dialog For the **Import project from external model** value, select **Maven** and click **Next**.
7. Select checkbox **Import Maven projects automatically** and leave the other fields with default values.
8. Click **Next**.
9. Your Maven project should be selected for import: 
11. The **Please select project SDK screen** will opens.
12. Make sure the correct **SDK** is selected and click **Next**.
13. Change the **project name** and location if you like, and click **Finish**.
14. **Project** should be imported, In left Panel **pom.xml** should be shown
14. Select **pom.xml** (right click ) select **Maven** and then click **Reimport**
15. All **External Libraries** will be downloaded


## Running the tests

* Create a run configuration to test your project:

1. From the **Run** menu, select **Run**.
2. Click **Edit Configurations**….
3. Click **+** (Add New Configuration) and select **Maven** 
4. Give your configuration a name e.g **RunTest**.
5. Select **Working directory** of the project to the folder project is located
6. In the **Comand line**: field, enter maven command e.g. : Below command is passing variables which can be changed according to requirement.
```
clean test verify -Denv=Staging -Ddevice=mac -DappName=sunsaver -DdeviceName=localMac -Dos=MacOS -Dbrowser=chrome "-Dcucumber.options=--tags @sunsaver"
```
7. Click **OK** or (If **Run** appears instead of **OK** click **Run** and leave below step).
8. Click **Run** icon to Run Test


## Links to Download Pre-requisite

* [JAVA JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html)
* [Maven](https://maven.apache.org/) - Dependency Management
* [IntelliJ](https://www.jetbrains.com/idea/download/#section=mac) - Code Editor

## Authors

* **Asjad Tariq**
