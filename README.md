🧪 OrangeHRM – Automation Testing
---------
<img width="446" height="113" alt="orangehrm logo" src="https://github.com/user-attachments/assets/4a9b9a78-0192-48e4-a063-ae0312933c59" />

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

📁 OrangeHRM_Automation_Testing

├── .gitignore # Ignored files/folders

├── 📄 LICENSE # License file

├── 📄 pom.xml # Maven Project Object Model

├── 📄 testng.xml # TestNG Suite Configuration

├── 📄 README.md # Project documentation

├── 📁 src/test/java # Test source code

│ ├── OrangeHRM/Admin_Module # Admin-related test cases

│ ├── OrangeHRM/User_Module # User-related test cases

│ ├── OrangeHRM/Base # Base test setup & utilities

│ └── OrangeHRM/Utils # Helper methods & reporting

└── 📁 reports # TestNG & Extent reports

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
