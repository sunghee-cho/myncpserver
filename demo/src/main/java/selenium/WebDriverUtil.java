package selenium;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class WebDriverUtil {	    
	private static String WEB_DRIVER_PATH = "C:/Users/Notebook9/Downloads/chromedriver-win64/chromedriver-win64/chromedriver.exe"; // WebDriver 경로        
	//private static WebDriver driver;
	public static WebDriver getChromeDriver() {
		System.out.println("WebDriverUtil - getChromeDriver() 호출");
		if (ObjectUtils.isEmpty(System.getProperty("webdriver.chrome.driver"))) {    		
			System.setProperty("webdriver.chrome.driver", WEB_DRIVER_PATH);    	
		}
		System.out.println(System.getProperty("webdriver.chrome.driver") + " 사용 ");
		
		//if(driver == null || ObjectUtils.isEmpty(driver)) {
		// webDriver 옵션 설정    	
		ChromeOptions chromeOptions = new ChromeOptions();    	
		//chromeOptions.setHeadless(true); 
		chromeOptions.addArguments("--headless=new");//브라우저창 열기없이 크롤링
		chromeOptions.addArguments("--lang=ko");    	
		chromeOptions.addArguments("--no-sandbox");    	
		chromeOptions.addArguments("--disable-dev-shm-usage");    	
		chromeOptions.addArguments("--disable-gpu");
		chromeOptions.addArguments("--disable-popup-blocking");       //팝업안띄움
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
        
		//chromeOptions.addArguments("--remote-allow-origins=*");
		
		//chromeOptions.setCapability("ignoreProtectedModeSettings", true);    	    	
		
		WebDriver driver = new ChromeDriver(chromeOptions);    	
		//driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5)); 
		//}

		System.out.println(driver);
		return driver;    
	}        
	
	//@Value("#{resource['driver.chrome.driver_path']}")    
	//public void initDriver(String path) {    	
	//	WEB_DRIVER_PATH = path;    
	//}     
	
	public static void quit(WebDriver driver) {    	//웹크롤링 종료
		if (!ObjectUtils.isEmpty(driver)) {    		
			driver.quit();    	
		}
		try {
		//for(int i = 1; i <=5; i++) {
		//	Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
		//}
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("WebDriverUtil - quit(driver) 호출");
	}        

	public static void quit() {    	//웹크롤링 종료
		//if (!ObjectUtils.isEmpty(driver)) {    		
		//	driver.quit();    	
		//}
		try {
		for(int i = 1; i <=5; i++) { //1번ㅁ taskkill 실행시 아직 chrome.exe 몇개는 남아있음. 그래서 5번정도 반복함
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe /t");
		}
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("WebDriverUtil - quit() 호출");
	}        
	
	public static void close(WebDriver driver) {    //브라우저 열고 웹크롤링한다면 브라우저탭 종료.	
		if (!ObjectUtils.isEmpty(driver)) {    		
			driver.close();    	
		}  
		System.out.println("WebDriverUtil - close() 호출");
		
	}    
}


