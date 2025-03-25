## Steps to run the Test Suite

1. Go to [integration-test-framework](..%2Fintegration-test-framework) and build the project using `mvn clean install`
2. Go to [accelerator-test-framework](accelerator-test-framework) and build the project using `mvn clean install`
3. Go to [resources](accelerator-test-framework%2Fsrc%2Fmain%2Fresources) folder which resides in 
financial-services-accelerator-test-suite/accelerator-test-framework/src/main/resources.
4. Get a copy [TestConfigurationExample.xml](accelerator-test-framework%2Fsrc%2Fmain%2Fresources%2FTestConfigurationExample.xml) file 
and rename it as TestConfiguration.xml. Update the values in the file according to your environment.
You can refer to the sample values given in the file itself when configuring the values.
5. Go to [accelerator-test-suite](accelerator-test-suite) and build the project using `mvn clean install -Dmaven.test.skip=true`
6. Go to the relevant test and run the test using green arrow in-front of the test method. 