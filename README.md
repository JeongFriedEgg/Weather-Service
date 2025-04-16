# 🌤️ 날씨 기반 일기 작성 서비스

날씨 정보를 기반으로 사용자 일기를 작성하고 수정할 수 있는 RESTful API 서비스입니다.  
외부 Open API(OpenWeatherMap)를 활용해 날씨 데이터를 수집하며, Swagger를 통해 API 문서를 제공합니다.


## 📌 주요 기능

- 사용자는 날씨 정보와 함께 일기를 작성하고 관리함.
- 외부 API(OpenWeatherMap)에서 날씨 데이터를 수집하여 DB에 저장.
- 매일 새벽 1시에 자동으로 날씨 데이터를 업데이트. (스케쥴링 기능)
- Swagger를 활용한 API 문서 제공.


## 🌐 외부 API 정보

- **API 제공처**: [OpenWeatherMap](https://openweathermap.org/)
- **사용 목적**: 현재 날씨 정보 수집 (지역 기반)
- **데이터 저장**: 매일 1시, 스케쥴러를 통해 최신 날씨 데이터를 가져와 로컬 DB에 저장



## 🔄 스케쥴링

- 매일 새벽 1시 정각에 OpenWeatherMap API를 호출
- 받아온 날씨 데이터는 DB에 저장되어 이후 일기 작성 시 활용

```java
@Scheduled(cron = "0 0 1 * * *")
@Transactional
public void saveWeatherDate() {
    dateWeatherRepository.save(openWeatherMapClient.getWeatherFromApi());
}
```