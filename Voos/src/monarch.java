
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.experimental.theories.Theories;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public class monarch {
	
	String day;

	public static String run(String day, String depart, String arrive) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.monarch.co.uk");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		
		
		targetOrigem(driver, depart);
		
		targetDestino(driver, arrive);
		
		persons(driver);
		
		oneWay(driver);
		
		data(driver, day);
		
//		for(int proximo_Mes=1; proximo_Mes<1; proximo_Mes++)
//			//muda para proximo mes
//			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();

		
		
		
		return clickPeskisar(driver);
		
	}

	private static void targetOrigem(WebDriver driver, String depart) {
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[2]/div[1]/div[2]/div/a")).click();

		List <WebElement> List_Aero_Origem = driver.findElements(By.xpath(".//*[@id='flights-container']/div[2]/div[1]/div[2]/div/ul/li/a"));

		for(int i=0 ; i<List_Aero_Origem.size(); i++){

			WebElement element = List_Aero_Origem.get(i);

			String GOD= element.getAttribute("innerHTML");

			if(GOD.contentEquals(depart))
				element.click();

			//System.out.println("FINAL " + GOD);

		}
	}

	private static void targetDestino(WebDriver driver, String arrive) {
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[2]/div[2]/div[2]/div/a")).click();

		List <WebElement> List_Aero_Destino = driver.findElements(By.xpath("//*[@id='flights-container']/div[2]/div[2]/div[2]/div/ul/li/a"));

		for(int i=0 ; i<List_Aero_Destino.size(); i++){

			WebElement element = List_Aero_Destino.get(i);

			String GOD = element.getAttribute("innerHTML");

			if(GOD.contentEquals(arrive))
				element.click();

			//System.out.println("FINAL " + GOD);

		}
	}
	
	private static void data(WebDriver driver, String day) {
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[5]/div[1]")).click();
		for(int proximo_Mes=0; proximo_Mes<0; proximo_Mes++)
			//muda para proximo mes
			driver.findElement(By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a")).click();

		List <WebElement> List_Data = driver.findElements(By.xpath("//*[@id='ui-datepicker-div']/div[1]/table/tbody/tr/td/a"));
		boolean bool = false;
		int last = 0;
		for(int i=0 ; i<List_Data.size(); i++){

			WebElement element = List_Data.get(i);

			String GOD = element.getAttribute("innerHTML");

			if(GOD.contentEquals(day)){
				element.click();
				bool=true;
				break;
			}
			if(i==List_Data.size()-1)
				last = Integer.parseInt(GOD);

		}
		
		
		if(bool == false  && last >= Integer.parseInt(day)){
			System.out.println("maior" );
			day = Integer.toString((Integer.parseInt(day)+1));
			data(driver, day);
		}
		
	}

	private static void oneWay(WebDriver driver) {
		//apenas IDA
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[3]/a[2]")).click();
	}

	private static void persons(WebDriver driver) {
		//kuantos passageiros
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[7]/div[1]/div[2]/a[1]")).click();
	}

	private static void clickData(WebDriver driver) {
		//Data Dias
		driver.findElement(By.xpath(".//*[@id='flights-container']/div[5]/div[1]")).click();
	}
	
	private static String clickPeskisar(WebDriver driver) throws InterruptedException {
		
		
		Capabilities cp = ((RemoteWebDriver) driver).getCapabilities();
		if (cp.getBrowserName().equals("chrome")) {
			try {
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(".//*[@id='flights-container']/a")));
			} catch (Exception e) {

			}
		}

		// webElement.click();
		Thread.sleep(200);
		//click pesquisar
				driver.findElement(By.xpath(".//*[@id='flights-container']/a")).click();
		
		Thread.sleep(200);
		
		String voo = "";
//		//preco
//		System.out.println(driver.findElement(By.xpath(".//*[@id='SellKey_1_2_1']/span")).getAttribute("innerHTML"));
//		//hora destino origem
//		System.out.println(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[2]/span")).getAttribute("innerHTML"));
//		System.out.println(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[2]/span[2]")).getAttribute("innerHTML"));
//		System.out.println(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[4]/span")).getAttribute("innerHTML"));
//		System.out.println(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[4]/span[2]")).getAttribute("innerHTML"));
//		//Data
//		System.out.println(driver.findElement(By.xpath(".//*[@id='availability-market-1']/div/div[2]/h3")).getAttribute("innerHTML"));
//		
		voo = (driver.findElement(By.xpath(".//*[@id='SellKey_1_2_1']/span")).getAttribute("innerHTML"))	+ "\t . \t" +
						(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[4]/span")).getAttribute("innerHTML"))	+ "\t . \t" +
						(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[4]/span[2]")).getAttribute("innerHTML")) + "\t . \t" +
						(driver.findElement(By.xpath(".//*[@id='availability-market-1']/div/div[2]/h3")).getAttribute("innerHTML")+ "\t . \t" +
								(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[2]/span")).getAttribute("innerHTML")+ "\t . \t" +
										(driver.findElement(By.xpath(".//*[@id='running-total-market_1']/ul/li[2]/span[2]")).getAttribute("innerHTML")))
						
						);
		//System.out.println(voo);
		driver.close();
		return voo;
		
	}
}
