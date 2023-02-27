# API-Cucumber

## **Overview:**
API Testing Framework helps automating e2e execution of APIs

Technologies/Libraries Used - RestAssured, Cucumber, Maven and log4j

### **Some of the key features of this framework:**

1. It generates Cucumber Extent report with all the step details in PDF as well HTML format under target folder
2. Generates execution logs, with detailed request and response details.
3. This also has an example to validate response body using json schema and java pojo classes.
4. Test execution can be triggered form command line. 

## **Running Test:**

Open the command prompt and navigate to the folder in which pom.xml file is present.
Run the below Maven command.

    mvn clean test


Once the execution completes report & log will be generated in below folder.

**Report:** 		*target/report*<br>
**Log:** 		*target/logs*