

make some tests

dockerize

Jenkins it up





DESIGN LOGIC: for now, there is no commonSteps with login step. It is done in homepage with data from app.properties
if in future there are tests with more than 1 user, will make a login commonsteps and have homepagePO method
be like:     public void loginValidUser(String email, String password) {




TRY AFTERMETHOD IN DRIVERMANAGER ISNTEAD TEARDOWN METHODS IN TESTS