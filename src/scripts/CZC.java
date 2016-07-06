package scripts;

import org.openqa.selenium.By;
import testUtils.Helper;

public class CZC extends Helper{

	//	@Test
	//	public void czc() throws Exception {
	//		
	//		inputFile = System.getProperty("user.dir")+"\\InputData\\czc.txt";
	//		outputFile = System.getProperty("user.dir")+"\\OutputData\\czc.txt";
	//		deleteFile(outputFile);
	//		reader = new FileReader(inputFile);
	//		bufferedReader = new BufferedReader(reader);
	//		String line;
	//		while((line=bufferedReader.readLine())!=null){
	//			System.out.println(line);
	//			writer = new FileWriter(outputFile,true);
	//			writer.write(line);
	//			writer.write("  ");
	//			automate(line);
	//			writer.write(numberOnly);
	//			writer.write("\r\n");
	//			writer.close();
	//			driver.quit();
	//		}
	//		reader.close();	
	//	}

	public static void automate(String URL) throws Exception{

		driver.get(URL);
		driver.manage().window().maximize();
		String inStockInfoWhenProductTerminated;
		if (driver.findElements(By.cssSelector("button.btn.btn-buy.ga-direct.ico-disabled")).size()>0) {
			inStockInfoWhenProductTerminated = driver.findElement(By.cssSelector("span.dot.stores-and-delivery.availability-state-on-stock")).getText();
			numberOnly = inStockInfoWhenProductTerminated.replaceAll("[^0-9]","");
		} else {
			waitFor(By.cssSelector("button.btn.btn-buy.ga-direct.ico-basket"));
			driver.findElement(By.cssSelector("button.btn.btn-buy.ga-direct.ico-basket")).click();
			Thread.sleep(10000);
			waitFor(By.xpath(".//*[@id='popup-buy-mode']/div/div[3]/a"));
			driver.findElement(By.xpath(".//*[@id='popup-buy-mode']/div/div[3]/a")).click();

			driver.findElement(By.cssSelector("i.ico-basket")).click();
			//		driver.findElement(By.cssSelector("a.btn.btn-purchase")).click();
			waitFor(By.name("quantity"));
			driver.findElement(By.name("quantity")).sendKeys("10000");
			driver.findElement(By.className("up")).click();
			Thread.sleep(1000);
			if (driver.findElements(By.cssSelector("div.popup-content")).size()==0) {
				inStockCount = driver.findElement(By.name("quantity")).getAttribute("value");
			} else {

				inStockCount = driver.findElement(By.cssSelector("div.popup-content")).getText();
			}
			System.out.println(inStockCount);
			if (inStockCount.contains(",")) {
				inStockCount= inStockCount.substring(0, inStockCount.indexOf(",")); 	
			}


			numberOnly= inStockCount.replaceAll("[^0-9]", "");
			itemsInStock = Integer.parseInt(numberOnly);
			System.out.println(itemsInStock);

		}
		driver.manage().deleteAllCookies();
	}



}
