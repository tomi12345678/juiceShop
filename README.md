<h1>
Juice shop test suite
</h1>


This is a project showcasing the test automation of 1star challenges of the vulnerable site Juice Shop:
https://juice-shop.herokuapp.com/#/score-board

<h3>
Project structure
</h3>

The project focuses on Selenium and TestNG, and is written in java.
It follows the page-object model, and is divided into three major steps:

- pageObjectModel where all the pages and related elements/methods are stored
- test files where test logic is defined and written (test files are named after the juice shop challenges)
- xml files which run tests

<h3>
Additional features
</h3>



<h2>
TODO
also explain driver manager 
screenshots, waiters, app.properties, missing stuff (docker, reporting tool, logging tool, jenkins)
</h2>






DESIGN LOGIC: for now, there is no commonSteps with login step. It is done in homepage with data from app.properties
if in future there are tests with more than 1 user, will make a login commonsteps and have homepagePO method
be like:     public void loginValidUser(String email, String password) {
