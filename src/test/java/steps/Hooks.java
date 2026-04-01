package steps;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import support.TestContext;

import java.nio.file.Paths;
import java.util.Arrays;

public class Hooks {
    private final TestContext context;

    public Hooks(TestContext context) { this.context = context; }

    @Before
    public void setUp(Scenario scenario) {
        context.playwright = Playwright.create();

        context.browser = context.playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized", "--disable-extensions"))
        );
        context.browserContext = context.browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null)
                .setRecordVideoDir(Paths.get("target/videos/"))
                .setRecordVideoSize(1920, 1080)
        );

        context.browserContext.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        context.page = context.browserContext.newPage();
    }

    @AfterStep
    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed() && context.page != null) {
            byte[] screenshot = context.page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(screenshot, "image/png", "Screenshot fallido");
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed() && context.browserContext != null) {
            String traceName = "target/traces/trace-" + scenario.getName().replace(" ", "-") + ".zip";
            context.browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get(traceName)));
        } else if (context.browserContext != null) {
            context.browserContext.tracing().stop();
        }

        if (context.page != null) context.page.close();
        if (context.browserContext != null) context.browserContext.close();
        if (context.browser != null) context.browser.close();
        if (context.playwright != null) context.playwright.close();
    }
}