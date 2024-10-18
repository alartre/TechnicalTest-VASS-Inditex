# TechnicalTest-VASS-Inditex

### Requisitos
	•	Docker: 20.10.0 o superior
	•	Docker Compose: 1.27.0 o superior
	•	Maven: 3.8.1 o superior
	•	Java: 17 (LTS)

### Pasos para levantar la aplicación con Docker

1. Clona el repositorio:
   ```bash
   git clone <URL-del-repositorio>

2. Navegar al directorio del proyecto
    ```bash
   cd <carpeta-del-repositorio>
   
3. Construir y levantar el contenedor
    ```bash
   docker-compose up --build
   
4. La aplicación estará disponible en http://localhost:8080.



5. Para verificar que la aplicación funciona correctamente, accede a la siguiente URL:
    ```bash
    curl -X GET "http://localhost:8080/products/35455/prices?brandId=1&applicationDate=2020-06-14%2010:00:00"

### Ejecución de la colección de Postman

La colección de Postman para facilitar las pruebas de la API está situada en la raiz del proyecto. Para ejecutarla, sigue los pasos:

	1.	Importa la colección de Postman disponible en la carpeta raiz del proyecto con el nombre PRODUCT_RATE_MANAGER.postman_collection.json.
	2.	Asegúrate de que la aplicación esté corriendo (docker-compose up).
	3.	Ejecuta los distintos tests que incluyen peticiones de prueba para distintos escenarios.

### Documentación de la API

La documentación de la API está disponible mediante Swagger. Puedes acceder a la interfaz de Swagger en la siguiente URL:

http://localhost:8080/swagger-ui.html

### Base de datos y datos de ejemplo

En la base de datos de comercio electrónico de la compañía, disponemos de la tabla PRICES, que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. Durante la ejecución, se utilizará una base de datos H2 en memoria, que será pre-poblada automáticamente con los datos de ejemplo especificados en src/main/resources/data.sql.

Estructura de directorios
PRICES
-------


| BRAND_ID |     START_DATE      |      END_DATE       | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|:--------:|:-------------------:|:-------------------:|:----------:|:----------:|:--------:|:-----:|:----:|
|    1     | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 |     1      |   35455    |    0     | 35.50 | EUR  |
|    1     | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 |     2      |   35455    |    1     | 25.45 | EUR  |
|    1     | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 |     3      |   35455    |    1     | 30.50 | EUR  |
|    1     | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 |     4      |   35455    |    1     | 38.95 | EUR  |
 
Campos: 

 - **BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).
 - **START_DATE** , END_DATE: rango de fechas en el que aplica el precio tarifa indicado.
 - **PRICE_LIST**: Identificador de la tarifa de precios aplicable.
 - **PRODUCT_ID**: Identificador código de producto.
 - **PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
 - **PRICE**: Precio final de venta.
 - **CURR**: iso de la moneda.
 
Se pide:
 
Construir una aplicación/servicio en **SpringBoot** que provea una end point rest de consulta  tal que:
 
 - Acepte como parámetros de entrada: **fecha de aplicación**, **identificador de producto**, **identificador de cadena**.
Devuelva como datos de salida: **identificador de producto**, **identificador de cadena**, **tarifa a aplicar**, **fechas de aplicación** y **precio final a aplicar**.
 - Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).
              
Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:
                                                                                       
-          Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
-          Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
-          Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
