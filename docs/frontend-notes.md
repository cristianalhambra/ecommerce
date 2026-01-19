# 🎨 Notas del Frontend (Angular)

Documentación del progreso, decisiones técnicas, pruebas y workarounds aplicados durante el desarrollo del frontend.

## 🟩 PASO 1 — Creación de todos los procesos básicos

- Auth login y register.

- Models product y user.

- Product list.

- Services.

## 🟩 PASO 2 — Sustitución de Signals por propiedades simples
 Se reemplazaron los signals por propiedades tradicionales en todos los componentes para asegurar el buen funcionamineto del proyecto y evitar complejidad innecesaria.

 Ejemplo de conversión:

  html
  <input [ngModel]="name()" (ngModelChange)="name.set($event)" />

## 🟩 PASO 3 — Configuración inicial de tests con Vitest

Se configuraron tests de comprobación de errores usando Vitest. Ventajas:

 - Cobertura rápida con motor V8.

 - Reportes modernos: lcov, text.

 - Instalado: @angular/platform-browser-dynamic.

## 🟩 PASO 4 — Tests para mensajes de error y éxito (Register/Login)

Se añadieron tests para verificar:

 - Mensajes de error en formularios.

 - Mensajes de éxito tras acciones correctas.

 - Uso de HttpClientTestingModule para controlar respuestas HTTP.

## 🟩 PASO 5 — Pruebas DOM en Register y Login

Se añadieron pruebas DOM para validar que los mensajes se renderizan correctamente.

Se verifican elementos visibles en la plantilla.

## 🟩 PASO 6 — Selectores estables para pruebas

Se añadieron atributos data-test="form-message" en las plantillas. Esto permite pruebas DOM más robustas y menos frágiles.

## 🟩 PASO 7 — Activación de cobertura en Angular

Se habilitó la cobertura en angular.json.

Se ejecutaron los tests para generar reportes.

## 🟩 PASO 8 — Resolución de errores de Vitest

Se solucionaron múltiples problemas que impedían ejecutar Vitest correctamente:

🔧 Workarounds aplicados

  - Inline de templates/styles en App y ProductList para evitar fallos de resolución de recursos.

  - Corrección de errores de DI (NG0202):

    ProductList: instancia manual con mock de servicio.

    Login y Register: mocks de HttpClient y Router usando TestBed.

    Sustitución de pruebas DOM por comprobaciones de estado cuando era necesario.

    Uso de resolveComponentResources en componentes con overrideComponent.

    Reemplazo de HttpTestingController por spies (throwError, of) para compatibilidad con Vitest.

✔ Resultado: Ejecución: npx vitest run --coverage. 19 tests pasan (100%). Cobertura generada con proveedor V8 (texto + lcov).

## 🟩 PASO 9 — Script npm para cobertura

  - Se añadió un script npm para ejecutar Vitest con cobertura y umbral configurable (ej. 80%).

  - Se actualizó package.json.

  - Se añadió nota en el README.

## 🟩 PASO 10 — Workflow de GitHub Actions

  Se añadió workflow para ejecutar:

  - npm run test:ci

  - Subida del reporte lcov

  - Se actualizó la lista de tareas (TODOs).

## 🟩 PASO 11 — Limpieza y comentarios en specs

  Se añadieron comentarios explicativos en: login.spec.ts, register.spec.ts, product-list.spec.ts y app.spec.ts.

  Se eliminaron imports y variables no usadas: ComponentFixture, HttpTestingController.

  Ejecución final: npm run test:ci y 19 tests pasan, cobertura OK.

## 🟩 PASO 12 — Documentación de workarounds

Los comentarios en los specs explican:

  - Por qué se inlinean templates/styles (Vitest no resuelve recursos externos).

  - Por qué algunos componentes se instancian manualmente (evitar NG0202).

  - Los workarounds están documentados para revertirlos cuando Angular/Vitest mejoren compatibilidad.

## 🟩 PASO 13 — Scripts de formateo y typecheck + TESTING.md

  Añadidos scripts: npm run format y npm run typecheck.

   Creado archivo TESTING.md con: Instrucciones de testing y Documentación de workarounds.

  Ejecutado Prettier y TypeScript check (sin errores).

  Tests con cobertura: 19 tests pasan.

## 🟩 PASO 14 — Servicio de usuario

  Generado servicio con: Código ng generate service services/user. Configurado el servicio.

  Actualizado app.ts para nuevas pruebas.

