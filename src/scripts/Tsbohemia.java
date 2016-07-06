package scripts;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import testUtils.Helper;

public class Tsbohemia extends Helper {


	@Test
	public void tsbohemia() throws Exception {
		inputFile= System.getProperty("user.dir")+"\\InputData\\tsbohemia.txt";
		outputFile = System.getProperty("user.dir")+"\\OutputData\\tsbohemia.txt";
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
			driver.quit();
		}
		reader.close();	
	}
	public static void automate(String URL) throws Exception{

		driver.get(URL);
		driver.manage().window().maximize();
		waitFor(By.cssSelector("div.apekinet.sat_1"));
		driver.findElement(By.cssSelector("div.apekinet.sat_1")).click();
		System.out.println(driver.findElements(By.tagName("iframe")).size());
		if (driver.findElements(By.tagName("iframe")).size()==2) {
			String s = driver.findElements(By.tagName("iframe")).get(1).getAttribute("src");
			driver.get(s);
			inStockCount= driver.findElement(By.xpath(".//*[@id='apekavailinfo']/div/div[1]/div/table/tbody/tr[2]/td[2]")).getText();
			numberOnly=inStockCount.replaceAll("[^0-9]", "");
			itemsInStock = Integer.parseInt(numberOnly);
			System.out.println(itemsInStock);
		} else {
			driver.switchTo().frame(0);
			waitFor(By.xpath(".//*[@id='apekavailinfo']/div/div/div/table/tbody/tr[2]/td[2]"));
			inStockCount = driver.findElement(By.xpath(".//*[@id='apekavailinfo']/div/div/div/table/tbody/tr[2]/td[2]")).getText();
			numberOnly= inStockCount.replaceAll("[^0-9]", "");
			itemsInStock = Integer.parseInt(numberOnly);
			System.out.println(itemsInStock);		
		}
		driver.manage().deleteAllCookies();
	}


}