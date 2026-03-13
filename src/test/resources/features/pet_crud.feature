# language: es

Característica: Gestión del ciclo de vida de una mascota en la PetStore
Como administrador del inventario de la tienda
Quiero gestionar el registro de una mascota
Para asegurar la consistencia de los datos en el sistema

Escenario: Registro, actualización y eliminación exitosa de una mascota
Dado que el administrador prepara el registro para la mascota "Nube"
Cuando la registra en el sistema de la tienda
Y consulta su información para confirmar la creación
Entonces verifica que la mascota fue creada exitosamente
Y actualiza el estado de la mascota a "sold"
Y finalmente elimina a la mascota del sistema
Entonces el registro completo de la mascota ya no debe existir en la tienda