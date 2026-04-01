package pages;

import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CatalogoPage {
    private final Page page;

    public CatalogoPage(Page page) { this.page = page; }

    public void verifyCatalogIsVisible() {
        assertThat(page.locator(".title")).hasText("Products");
    }

    public void addProductToCart(String productName) {
        String formattedName = productName.toLowerCase().replace(" ", "-");
        page.click("[data-test='add-to-cart-" + formattedName + "']");
    }

    public void goToCart() { page.click(".shopping_cart_link"); }
}