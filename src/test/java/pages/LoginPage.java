package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    public LoginPage(Page page) { this.page = page; }

    public void navigate(String url) { page.navigate(url); }

    public void fillUsername(String username) { page.fill("[data-test='username']", username); }

    public void fillPassword(String password) { page.fill("[data-test='password']", password); }

    public void clickLogin() { page.click("[data-test='login-button']"); }

    public String getErrorMessage() { return page.locator("[data-test='error']").innerText(); }
}