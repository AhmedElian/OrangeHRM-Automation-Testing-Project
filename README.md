🧪 OrangeHRM – Automation Testing
---------
<img width="447" height="113" alt="orangehrm logo" src="https://github.com/user-attachments/assets/7cdb646d-c6fd-4725-8b19-cd86642d87a3" />

----------

📌 Overview

- This project is a Selenium + TestNG automated testing suite for OrangeHRM.

- It validates critical workflows across the Admin and User Management modules, including employee data, organization info, and access controls.

- The suite includes positive/negative, regression, boundary, and functional tests with reporting and bug tracking.

----------

✨ Features

✅ Automated UI Testing with Selenium WebDriver

✅ TestNG-based suite with assertions & reports

✅ Functional & Regression test coverage

✅ Negative & Boundary case validation

✅ Modular Page Object Model (POM) design

✅ Bug reporting & Test Summary Report

✅ RTM mapping requirements to tests

----------

📂 Project Structure

📂 OrangeHRM_Automation_Testing

├── 📄 README.md

├── 📄 pom.xml

├── 📄 testng.xml

├── 📁 src

│   ├── 📁 main

│   │   └── 📁 java

│   │       └── 📁 OrangeHRM

│   │           └── 📁 Admin_Module

│   │               ├── AdminPage.java

│   │               ├── EducationPage.java

│   │               ├── LanguagesPage.java

│   │               ├── OrangeHRM_Login_Page.java

│   │               ├── OrangeHRM_User_Management.java

│   │               └── SkillsPage.java

│   └── 📁 test

│       └── 📁 java

│           └── 📁 OrangeHRM

│               └── 📁 Admin_Module

│                   ├── EducationTest.java

│                   ├── LanguagesTest.java

│                   ├── OrangeHRM_Login_Page_Test.java

│                   ├── OrangeHRM_Read_CSV.java

│                   ├── OrangeHRM_Test_Base.java

│                   ├── OrangeHRM_User_Management_Test.java

│                   ├── OrangeHRM_XML.xml

│                   └── SkillsTest.java

├── 📁 OrangeHRM_Data

│   ├── OrangeHRM_Add.CSV

│   ├── OrangeHRM_Edit.CSV

│   ├── OrangeHRM_Login.CSV

│   ├── OrangeHRM_Reser_Password.CSV

│   └── OrangeHRM_Search.CSV

├── 📁 reports

│   └── OrangeHRM_TestNG_reports.zip

├── 📄 OrangeHRM - Automation Testing - Bug Report.pdf

├── 📄 OrangeHRM - Automation Testing - Requirements Traceability Matrix (RTM).xlsx

├── 📄 OrangeHRM - Automation Testing - SRS.pdf

├── 📄 OrangeHRM - Automation Testing - Test Cases.xlsx

├── 📄 OrangeHRM - Automation Testing - Test Plan.pdf

├── 📄 OrangeHRM - Automation Testing - Test Summary Report.xlsx

├── 📄 OrangeHRM_Automation_Presentation.pptx

└── 📄 OrangeHRM_Qualifications_Proj.zip


----------

🛠️ Technologies Used

- Java 8+

- Selenium WebDriver

- TestNG Framework

- Maven

- Extent Reports / TestNG Reports

- IDE (IntelliJ IDEA / Eclipse / VS Code)

----------

📦 Installation & Running Tests

1- Clone the repository:

  - git clone [OrangeHRM_Automation_Testing](https://github.com/AhmedElian/OrangeHRM_Automation_Testing.git)


2- Open the project in your IDE (IntelliJ / Eclipse / VS Code)

3- Run the TestNG suite:

  - Option 1: Run any test class directly from IDE

  - Option 2: Using Maven: mvn clean test

  - Option 3: Run via testng.xml from IDE: Right-click → Run As → TestNG Suite

----------

📊 Test Coverage

1- Modules Tested

🏢 Admin Module – Organization Info, Job Titles, User Roles

👤 User Management – Add, Edit, Delete Users

🔐 Authentication – Login, Invalid Login, Session Handling

2- Test Types

✅ Functional Testing

⚠️ Negative & Boundary Testing

🔄 Regression Testing

📝 Exploratory Testing

----------

⚠️ Disclaimer

This project is for educational and practice purposes only.

It is not affiliated with or intended for production use of OrangeHRM.

----------
⭐ If you like this project, consider giving it a star on GitHub!
----------
