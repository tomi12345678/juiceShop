<h1>
Juice shop test suite
</h1>


This is a project showcasing the test automation of 1-star challenges of the vulnerable site Juice Shop:
https://juice-shop.herokuapp.com/#/score-board

<h3>
Project structure
</h3>

The project focuses on Selenium and TestNG, and is written in java.
It follows the page-object model, and is divided into three major steps:

- pageObjectModel where all the pages and related elements/methods are stored
- test files where test logic is defined and written (test classes have the same name as the juice shop challenges)
- xml files which run tests

<h3>
Additional features
</h3>

- For driver initiation, web-driver manager dependency is used, and is defined in the DriverManager class. It also 
contains the @AfterMethod to end driver sessions after tests.
- Screenshots are set up to be taken on test failure using a screenshotListener 
(src/test/java/listeners/ScreenshotListener.java), and is managed in the ScreenshotManager class 
(src/main/java/setup/ScreenshotManager.java).
- For ease of use, the Waiter class (src/main/java/setup/Waiter.java) is created and simplifies the use of 
explicit wait, as well as a pause
- data is stored and extracted from the application.properties file (src/main/resources/application.properties),
the ConfigFileReader class is created to make accessing it easier (src/main/java/setup/ConfigFileReader.java)

<h3>
Notes
</h3>

- Some of the tests and PO solutions could have been simplified, but are not to showcase various methods of 
handling various cases.
- As of date, all 1-star challenges should be covered by the project.
- There is currently no reporting tool implemented, as there really is no need for it.
- Browser is defined in the application.properties file. Supported browsers: Chrome, Firefox and IE.
- As site vulnerability is expected, if the expected vulnerability is asserted, the test will pass.



