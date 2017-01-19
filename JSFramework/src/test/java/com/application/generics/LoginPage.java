package com.application.generics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id="u")
	private WebElement usernameTextbox;
	
	@FindBy(id="p")
	private WebElement passwordTextbox;
	
	@FindBy(css="button.WebHRSolidButton_Blue")
	private WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public void login(String un, String pw){
		usernameTextbox.sendKeys(un);
		passwordTextbox.sendKeys(pw);
		loginButton.click();
	}
}
