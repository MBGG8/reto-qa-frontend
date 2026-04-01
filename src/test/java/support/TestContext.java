package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class TestContext {
    public Playwright playwright;
    public Browser browser;
    public BrowserContext browserContext;
    public Page page;
}