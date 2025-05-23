# Aplicación de Gestión de Empleados

## Descripción
Esta aplicación Android desarrollada en Java implementa un sistema de gestión de empleados utilizando conceptos de programación orientada a objetos. Permite visualizar diferentes tipos de empleados (Gerentes, Técnicos y Técnicos Senior) en una lista con diseños personalizados según el tipo, y ver sus detalles específicos.

## Características
- **Menú principal** con navegación a diferentes secciones
- **Listado de empleados** utilizando RecyclerView con adaptador personalizado
- **Diferentes tipos de empleados** con herencia de clases:
  - Clase base `Empleado` 
  - Subclases `Gerente`, `Tecnico` y `TecnicoSenior`
- **Diseños personalizados** con diferentes colores según el tipo de empleado
- **Vista detallada** al seleccionar un empleado específico


## Estructura del proyecto

### Modelos
- **Empleado.java**: Clase base con atributos comunes (id, nombre, apellido, salarioBase, fechaContratación)
- **Gerente.java**: Subclase con atributos específicos (departamento, bonoAnual, cantidadSubordinados)
- **Tecnico.java**: Subclase con atributos específicos (especialidad, nivelCertificación, horasExtra)
- **TecnicoSenior.java**: Hereda de Técnico con atributos adicionales (proyectosCompletados, clientesAtendidos)
- **MenuItem.java**: Modelo para elementos del menú principal

### Actividades
- **MainActivity.java**: Pantalla principal con menú de opciones
- **ListaEmpleadosActivity.java**: Muestra el listado de empleados
- **DetalleEmpleadoActivity.java**: Muestra detalles del empleado seleccionado

### Adaptadores
- **MenuAdapter.java**: Adaptador para el grid del menú principal
- **EmpleadosAdapter.java**: Adaptador personalizado para la lista de empleados con diferentes tipos de vistas

## Requisitos
- Android Studio
- SDK mínimo: Android 8.0 (API 26)
- SDK objetivo: Android 14 (API 34)

## Instalación
1. Clona el repositorio:
   ```
   git clone https://github.com/tu-usuario/gestion-empleados.git
   ```
2. Abre el proyecto en Android Studio
3. Sincroniza con Gradle y compila el proyecto
4. Ejecuta la aplicación en un emulador o dispositivo físico

## Uso
1. Al abrir la aplicación, se muestra el menú principal
2. Toca en "Lista de Empleados" para ver todos los empleados disponibles
3. Selecciona cualquier empleado para ver sus detalles específicos
4. Para regresar a la pantalla anterior, usa el botón Atrás

## Desarrollado con
- Java
- Android SDK
- RecyclerView y CardView
- Arquitectura basada en herencia de clases
