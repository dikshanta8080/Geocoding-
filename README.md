# Geocoding Service

A Spring Boot REST API that takes an address as input and returns its **latitude and longitude coordinates**. The project uses **Swagger** for API testing and includes a **global exception handler** for consistent error responses.

---

## Features

- Accepts address input (city, district, province, country) and returns coordinates.  
- Uses **OpenStreetMap Nominatim API** for geocoding.  
- Configured **global exception handler** to handle errors centrally.  
- Provides **standardized API responses** for all errors.  
- Integrated **Swagger UI** for API testing.  
- Supports **DTOs** for request and response.

---


## Requirements

- JDK 17 or higher  
- Maven  
- Internet connection (for Nominatim API requests)  

---

## Installation & Running

1. Run step by step:
```bash
git clone https://github.com/yourusername/geocoding-service.git
cd geocoding-service
./mvnw clean install
./mvnw spring-boot:run
```
## Accessing
1. Go to the url
   ```bash
   http://localhost:8090/swagger-ui.html
   ```
## API Usage

**Request Sample**
```json
{
  "province": "koshi",
  "district": "sunsari",
  "city": "itahari"
}
```
**Response Sample**
```json
{
  "status": true,
  "httpStatus": "OK",
  "message": "Successfully parsed the coordinates",
  "responseObject": {
    "lat": 26.6622505,
    "lon": 87.2748964,
    "display_name": "इटहरी उपमहानगरपालिका, सुनसरी जिल्ला, कोशी प्रदेश, नेपाल"
  }
}
```

