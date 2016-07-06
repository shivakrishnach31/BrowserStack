package scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Alza extends Helper {

	@Test
	public void alza() throws Exception {
		driver = new FirefoxDriver();
		inputFile = System.getProperty("user.dir")+"\\InputData\\alza.txt";
		outputFile = System.getProperty("user.dir")+"\\OutputData\\alza.txt";
		deleteFile(outputFile);
		reader = new FileReader(inputFile);
		bufferedReader = new BufferedReader(reader);
		String line;
		while((line=bufferedReader.readLine())!=null){
			System.out.println(line);
			writer = new FileWriter(outputFile,true);
			writer.write(line);
			writer.write("  ");
			automate(line);
			writer.write(numberOnly);
			writer.write("\r\n");
			writer.close();
			driver.manage().deleteAllCookies();
		}
		reader.close();
	}

	public static void automate(String URL) throws Exception{
		
		driver.get(URL);
		driver.manage().window().maximize();
		if (driver.findElements(By.cssSelector("span.openbox")).size()>0) {
			driver.findElement(By.cssSelector("span.openbox")).click();
		}

		try {
			waitFor(By.cssSelector("a.btnx.normal.green.buy"));
			driver.findElement(By.cssSelector("a.btnx.normal.green.buy")).click();
		} catch (Exception e) {
			driver.findElement(By.cssSelector("div.box.cangift.first.firstRow")).findElement(By.className("price")).findElement(By.cssSelector("a.btnk1")).click();

		}

		//}
		waitFor(By.cssSelector("a.btnx.normal.green.arrowedRight.floatRight.toOrder1"));
		driver.findElement(By.cssSelector("a.btnx.normal.green.arrowedRight.floatRight.toOrder1")).click();
		waitFor(By.className("countEdit"));
		driver.findElement(By.className("countEdit")).findElement(By.tagName("input")).sendKeys("10000");
		driver.findElement(By.className("countPlus")).click();
		Thread.sleep(1000);
		inStockCount = driver.findElement(By.xpath("html/body/div[2]/div/div/div/div[2]/div/div/div[2]/div[4]/div/table/tbody/tr/td[4]/span/span")).getText();
		System.out.println(inStockCount);
		if (!inStockCount.matches(".*\\d+.*") && !driver.findElements(By.id("infodialog")).isEmpty()) {
			String limitedItemstoSelect = driver.findElement(By.id("odinfo")).getText();
			numberOnly= limitedItemstoSelect.replaceAll("[^0-9]", "");
			itemsInStock = Integer.parseInt(numberOnly);
			System.out.println(itemsInStock);
		} else {
			numberOnly= inStockCount.replaceAll("[^0-9]", "");
			itemsInStock = Integer.parseInt(numberOnly);
			System.out.println(itemsInStock);
		}
		driver.manage().deleteAllCookies();
	}
	@AfterTest
	public void closeDriver(){
		driver.quit();
	}


}
