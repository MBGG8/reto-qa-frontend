package steps;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CatalogoPage;
import support.TestContext;

public class CheckoutSteps {
    private final CatalogoPage catalogoPage;
    private final CartPage cartPage;
    private final CheckoutPage checkoutPage;

    public CheckoutSteps(TestContext context) {
        this.catalogoPage = new CatalogoPage(context.page);
        this.cartPage = new CartPage(context.page);
        this.checkoutPage = new CheckoutPage(context.page);
    }

    @Dado("que el usuario agregó el producto {string} al carrito")
    public void usuarioAgregoProductoAlCarrito(String producto) {
        catalogoPage.addProductToCart(producto);
    }

    @Cuando("agrega el producto {string} al carrito desde el catalogo")
    public void agregarProducto(String producto) {
        catalogoPage.addProductToCart(producto);
    }

    @Cuando("navega al carrito de compras")
    public void irAlCarrito() {
        catalogoPage.goToCart();
    }

    @Entonces("el producto {string} debe estar visible en el carrito")
    public void verificarProductoEnCarrito(String producto) {
        cartPage.verifyProductInCart(producto);
    }

    @Cuando("procede al checkout")
    public void procederCheckout() {
        cartPage.clickCheckout();
    }

    @Cuando("completa el formulario de envio con Nombre {string}, Apellido {string} y Codigo {string}")
    public void completarFormulario(String nombre, String apellido, String postal) {
        checkoutPage.fillShippingInfo(nombre, apellido, postal);
    }

    @Cuando("finaliza el proceso de compra")
    public void finalizarCompra() {
        checkoutPage.clickFinish();
    }

    @Entonces("deberia ver el mensaje de confirmacion {string}")
    public void verificarConfirmacionOrden(String mensaje) {
        checkoutPage.verifyOrderConfirmation(mensaje);
    }

    @Dado("que el usuario tiene el producto {string} en el carrito")
    public void usuarioTieneProductoEnCarrito(String producto) {
        catalogoPage.addProductToCart(producto);
        catalogoPage.goToCart();
        cartPage.verifyProductInCart(producto);
    }
}