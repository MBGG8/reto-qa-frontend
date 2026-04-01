package pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CheckoutPage {
    private final Page page;

    public CheckoutPage(Page page) { this.page = page; }

    public void fillShippingInfo(String firstName, String lastName, String postalCode) {
        page.fill("[data-test='firstName']", firstName);
        page.fill("[data-test='lastName']", lastName);
        page.fill("[data-test='postalCode']", postalCode);
        page.click("[data-test='continue']");
    }

    public void clickFinish() { page.click("[data-test='finish']"); }

    public void verifyOrderConfirmation(String expectedMessage) {
        assertThat(page.locator(".complete-header")).hasText(expectedMessage);
    }
}