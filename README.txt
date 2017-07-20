1. This project assumes the maven is installed in the system where the project is being tested
2. Go to the main folder and run `mvn package`. This will run the test and compile the code.
3. Run mvn `mvn exec:java` to run the main file of the application.
4. You can type exit to exit the application.
5. Messages are expected to follow the format: Person = <PERSON>; Relation = <Relation>
6. Currently the program does not handle validation for invalid inputs
7. Any relation with spaces (like grand daughter) should be separated by hyphen (-).
8. It accepts the family tree in JSON format.