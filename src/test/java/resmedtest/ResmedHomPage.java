package resmedtest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ResmedHomPage {
	
	
	@Test
	public void testLogin(){
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.resmed.com/au/en/consumer.html");
		driver.findElement(By.linkText("Tips for healthy sleep"));
		System.out.printf("Navigated to sleep Apnea Page%n");
		driver.close();
	}
}
