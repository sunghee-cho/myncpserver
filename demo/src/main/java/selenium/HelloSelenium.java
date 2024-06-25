package selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.util.ObjectUtils; 

public class HelloSelenium {    
	public static void main(String[] args) {        
	/*WebDriver driver = new ChromeDriver();         
	driver.get("https://selenium.dev");         
	driver.quit();   */
		HelloSelenium sele = new HelloSelenium();
		sele.test1();
		System.out.println("===============================================");
		sele.test2();
		WebDriverUtil.quit();
	}
	
	//클래스명, id명, 태그명으로 동적 페이지 크롤링
	public void test1() {
		WebDriver driver = WebDriverUtil.getChromeDriver();
		List<WebElement> webElementList = new ArrayList<>();
		// 다음에서 '공인ip'로 검색어 입력후 브라우저 개발자도구에서 요소 확인하면 class=info_ip 부분이 원하는 ip 값이다
		// 자바 크롤링으로 확인하자
		String url = "https://search.daum.net/search?w=tot&DA=YZR&t__nil_searchbox=btn&q=%EA%B3%B5%EC%9D%B8ip";
		String query = ".info_ip";//"#id"; 
		if (!ObjectUtils.isEmpty(driver)) {    
			driver.get(url);    
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));    
			webElementList = driver.findElements(By.cssSelector(query));
			System.out.println(webElementList.size()+"-"+webElementList.get(0).getTagName() +"태그 -" + webElementList.get(0).getText());
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
			System.out.println(targetElements.get(0).getText());//공인ip 확인
			System.out.println(targetElements.get(0).isDisplayed());//true
			System.out.println(targetElements.get(0).isEnabled());//true
			System.out.println(targetElements.get(0).isSelected());//false
			//클릭할 문자열 출력
			System.out.print(targetElements.get(0).getText() + " 클릭한 후의 페이지 타이틀과 url ");
			targetElements.get(0).click();
			
			//클릭후 이동한 페이지 타이틀과 url 확인하자
			System.out.println(":"+driver.getTitle()+":"+driver.getCurrentUrl());
			//parentElement.submit();
			

		}
		
		//WebDriverUtil.close(driver);
		//driver.close();
		WebDriverUtil.quit(driver);
	}
}




