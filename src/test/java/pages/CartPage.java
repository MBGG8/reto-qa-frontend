package pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CartPage {
    private final Page page;

    public CartPage(Page page) { this.page = page; }

    public void verifyProductInCart(String productName) {
        assertThat(page.locator(".inventory_item_name", new Page.LocatorOptions().setHasText(productName))).isVisible();
    }

    public void clickCheckout() { page.click("[data-test='checkout']"); }
}