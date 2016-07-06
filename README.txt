Please make sure you have the below settings in your execution machine befoe executing the scripts

1. JDK/JRE installed
2. Open 'Environmental Variables' and set classpath as below.
Variable Name: classpath
Vairable Value: <Path to bin folder>;<path to the lib folder>\*;
Example: C:\Users\Shiva\workspace\MartinJechEShopAutomation\bin;C:\Users\Shiva\workspace\MartinJechEShopAutomation\lib\*;
**** If you save the Project folder in "C" drive then use below path
Variable Name: classpath
Variable Class: C:\MartinJechEShopsAutomation\bin;C:\MartinJechEShopsAutomation\lib\*;

3. Open console and go to Project folder in console and run below command to execute the script
C:\MartinJechEShopsAutomation>java org.testng TestNG testng.xml

***********************************************************************************************************************************************
Input URL's taks from InputData folder and stores the output(Stock count) in OutputData folder. Each eshot scripts generates its
own output file with in stock count
***********************************************************************************************************************************************