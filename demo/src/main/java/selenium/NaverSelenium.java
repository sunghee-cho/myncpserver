package selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.ObjectUtils; 

public class NaverSelenium {    
	public static void main(String[] args) {        
	/*WebDriver driver = new ChromeDriver();         
	driver.get("https://selenium.dev");         
	driver.quit();   */
		NaverSelenium sele = new NaverSelenium();
		sele.test1();
		System.out.println("===============================================");
		sele.test2();
		WebDriverUtil.quit();
	}
	
	//클래스명, id명, 태그명으로 동적 페이지 크롤링
	public void test1() {
		WebDriver driver = WebDriverUtil.getChromeDriver();
	    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));   //드라이버가 실행된 뒤 최대 10초 기다리겠다.
	    
	    // 네이버에서 '공인ip'로 검색어 입력한 후의 결과 화면은 페이지 title이 "공인ip : 네이버 검색" 이다
		// 자바 크롤링으로 확인하자
		String url = "http://naver.com";
		String query = "query";//네이버는 검색어 입력태그의 id가 "query"이다 
		if (!ObjectUtils.isEmpty(driver)) {    
			driver.get(url);    
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			webDriverWait.until(	 ExpectedConditions.presenceOfElementLocated(By.id(query))   ); //cssSelector로 선택한 부분이 존재할때까지 기다려라
			
			WebElement webElement = driver.findElement(By.id(query));
			webElement.sendKeys("공인ip");
			webElement.submit();//submit 동작으로 네이버 검색어 크롤링 
			
			System.out.println(driver.getTitle());//"공인ip : 네이버 검색"
			
		//이제 "공인ip"의 검색결과를 가져오자
		//브라우저 개발자도구 - 요소(elements)에서 확인해보면 <div class="ip_chk_box">1.234.103.29</div> 가 있다. 
		//class명이 ip_chk_box 태그내의 공인ip 텍스트를 크롤링하자. 동일 class 값 중복 가능하므로 복수개 있다고 가정하고 크롤링하자.
			
			List<WebElement> myipElements = driver.findElements(By.cssSelector(".ip_chk_box"));
			String myip = myipElements.get(0).getText();
			System.out.println(driver.getTitle() + " ===> " + myip);
		}
		
		//WebDriverUtil.close(driver);
		//driver.close();
		WebDriverUtil.quit(driver);
		
		}
	
	//버튼 이벤트등으로 동적 페이지 크롤링
	public void test2() {
		WebDriver driver = WebDriverUtil.getChromeDriver();
		List<WebElement> webElementList = new ArrayList<>();
		String url = "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&q=%EA%B3%B5%EC%9D%B8ip";
		String query = ".info_ip";//"#id"; 
		if (!ObjectUtils.isEmpty(driver)) {    
			driver.get(url);    
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));    
			webElementList = driver.findElements(By.cssSelector(query));
			System.out.println(webElementList.size()+"-"+webElementList.get(0).getTagName() +"태그 -" + webElementList.get(0).getText());
			
			// 이제 크롤링해온 데이터를 가지고 내가 원하는 데이터들(값, css속성, 클래스명등)을 뽑기 가능
			WebElement parentElement = webElementList.get(0);
			List<WebElement> childElement = parentElement.findElements(By.tagName("strong")); 
			System.out.println(childElement.get(0).getText());
			System.out.println(parentElement.getAttribute("class"));
			System.out.println(parentElement.getCssValue("text-align"));  //브라우저-개발자도구-styles 요소 보면 현재 선택 요소의 css 속성들 확인 가능하니 확인하면서 테스트
			
			
			//혹은 클릭(click)이나 전송(submit) 같은 동작도 수행할 수 있다.
			//이번에는 '공인ip' 입력시 ip주소확인 결과 위쪽 관련 항목에서 '공인ip확인'부분이 <span class="wsn" 내의 <a 태그인 것을 확인하고 클릭한 결과를 크롤림해보자
			
			//1><span class="wsn" 내부의 a 태그들 크롤링
			query = ".wsn";
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));    
			webElementList = driver.findElements(By.cssSelector(query));
			List<WebElement> targetElements = webElementList.get(0).findElements(By.tagName("a"));
			
			//2> 첫번째 a태그 클릭
			System.out.println(targetElements.get(0).getText());//true
			System.out.println(targetElements.get(0).isDisplayed());//true
			System.out.println(targetElements.get(0).isEnabled());//true
			System.out.println(targetElements.get(0).isSelected());//false
			targetElements.get(0).click();
			
			//parentElement.submit();
			

		}
		
		//WebDriverUtil.close(driver);
		//driver.close();
		WebDriverUtil.quit(driver);
	}
}




