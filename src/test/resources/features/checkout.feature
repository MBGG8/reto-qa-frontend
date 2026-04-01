# language: es
Característica: Compra de productos en Sauce Demo

  Antecedentes:
    Dado que el usuario inicia sesion con "standard_user"

  Escenario: Agregar un producto al carrito desde el catálogo
    Cuando agrega el producto "Sauce Labs Backpack" al carrito desde el catalogo
    Entonces el producto "Sauce Labs Backpack" debe estar visible en el carrito

  Escenario: Ver productos agregados en el carrito
    Dado que el usuario agregó el producto "Sauce Labs Backpack" al carrito
    Cuando navega al carrito de compras
    Entonces el producto "Sauce Labs Backpack" debe estar visible en el carrito

  Escenario: Completar el proceso de compra hasta la confirmación
    Dado que el usuario tiene el producto "Sauce Labs Backpack" en el carrito
    Cuando procede al checkout
    Y completa el formulario de envio con Nombre "Juan", Apellido "Perez" y Codigo "12345"
    Y finaliza el proceso de compra
    Entonces deberia ver el mensaje de confirmacion "Thank you for your order!"