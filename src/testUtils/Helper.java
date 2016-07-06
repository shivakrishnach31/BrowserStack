package testUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class Helper {
	public static WebDriver driver;
	public static String numberOnly;;
	public static String inStockCount;
	public static int itemsInStock;
	public static String inputFile;
	public static String outputFile;
	public static FileReader reader;
	public static BufferedReader bufferedReader;
	public static FileWriter writer;
	String dbUrl;
	String username;
	String password;
	public static Connection con;
	public Statement stmt;
	public String query;
	public ResultSet rs,rs1;
	public java.sql.ResultSetMetaData metaData;
	public int count;
	public String columnName[];
	public static final String USERNAME = "martinjech1";
	public static final String AUTOMATE_KEY = "7mN67BaxJYpUpiDHU9Dp";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub.browserstack.com/wd/hub";

	public static void deleteFile(String filePath){
		{	
			try{
				File file = new File(filePath);
				if (file.exists()) {
					file.delete();
					System.out.println(file.getName() + " is deleted!");
				}
				else{
					System.out.println("File Not exist");
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void waitFor(By by){
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	@BeforeSuite
	public void connectionEstablish() throws ClassNotFoundException, SQLException, MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Firefox");
		caps.setCapability("browser_version", "43.0");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "7");
		caps.setCapability("resolution", "1024x768");
		driver = new RemoteWebDriver(new URL(URL), caps);
		Class.forName("com.mysql.jdbc.Driver");      
		dbUrl = "jdbc:mysql://eu-cdbr-azure-west-d.cloudapp.net/";     
		username = "b796079dbc85a6";       
		password = "b232490b";      
		con = DriverManager.getConnection(dbUrl,username,password);    
		stmt = con.createStatement();
		System.out.println("Hello");
	}
	

}
