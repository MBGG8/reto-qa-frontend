# language: es
Característica: Inicio de Sesión en Sauce Demo

  Como cliente de Sauce Demo
  Quiero poder iniciar sesión
  Para poder adquirir los productos que necesito

  Antecedentes:
    Dado que el usuario navega a la pagina de Sauce Demo

  Escenario: El usuario puede iniciar sesión con credenciales válidas
    Cuando ingresa el usuario "standard_user" y su password
    Y hace clic en el boton Login
    Entonces debería visualizar el catálogo de productos

  Escenario: El usuario no puede iniciar sesión con credenciales bloqueadas
    Cuando ingresa el usuario "locked_out_user" y su password
    Y hace clic en el boton Login
    Entonces debería ver un mensaje de error indicando que el usuario esta bloqueado