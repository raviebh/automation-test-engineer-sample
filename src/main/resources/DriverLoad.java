import org.openqa.selenium.chrome.ChromeDriver;

public class DriverLoad {

	
	WebDriver driver;
	public WebDriver intializeDriver()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\z385902\\chromedriver.exe");
		driver = new ChromeDriver();	
		
		return driver;
		
	}
}
