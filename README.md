# Covid-19 API
[![CircleCI](https://circleci.com/gh/yamilmedina/coronavirusapi/tree/master.svg?style=svg&circle-token=4259c346ba69eb646248ad392ee493bfb8f1a9b2)](https://circleci.com/gh/yamilmedina/coronavirusapi/tree/master)

`Covid-19 API` Habilita API REST para la recolección de datos (mediante scraping del historico de datos del Minsal) de las personas infectadas por el virus en Chile.
También sirve como ejercicio real para experimentar con los servicios de la infra GCP.

## Endpoints


* Lista de último reporte: GET https://coronavirus-app-271721.appspot.com/api/v1/coronavirus/today
* Lista histórica: GET https://coronavirus-app-271721.appspot.com/api/v1/coronavirus

## Pendientes:

- [x] Crear proyecto base spring + gcp. 
- [x] Endpoints básicos funcionales con scraper. 
- [x] Dockerizacion de servicio. 
- [x] Habilitar CI/CD para el proyecto con CircleCI. 
- [x] Crear proceso mediante pub/sub? + cloud functions? para actualizar registros diarios. 
- [ ] Agregar capa de caché para disminuir consultas a Datastore. 
- [ ] Seguridad para endpoints sensibles. 
- [ ] Swagger doc para endpoints. 
- [ ] Arquitectura en Compute Engine con Kubernetes o Swarm. 
 
