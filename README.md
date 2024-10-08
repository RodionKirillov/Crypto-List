# CryptoList Application

## Описание проекта
CryptoList - это Android-приложение, отображающее список криптовалют с возможностью просматривать подробную информацию о каждой из них. Приложение было разработано в рамках тестового задания для стажировки "Нашкодим - Android". Основная цель - показать навыки работы с современным Android-стеком, следование архитектурным принципам и внимание к деталям.
Ссылки на задание, дизайн и API криптобиржи с которым работает приложение проекта:
- [Задание](https://github.com/MobileUpLLC/trainee-test-android-2024)
- [Figma](https://www.figma.com/design/jq1CJfQRYSjIGiGZmabeaV/MobileUp-Trainee-Test-Task)
- [CoinGecko API (v3.0.1)](https://docs.coingecko.com/v3.0.1/reference/endpoint-overview)

## Стек
- Kotlin
- Single Activity, MVVM
- View
- Retrofit
- Gson
- Coroutines
- Jetpack Navigation Component

## Процесс реализации
Для реализации проекта я создал Project и разделил задачи на четыре эпика: Tech, Search, Details, Bug. В каждом эпике создал таски в которых ставил временные рамки, таски прикреплял к коммитам.
-[Project](https://github.com/users/RodionKirillov/projects/3)

- Tech - Подготовка структуры проекта: Создал структуру проекта с разделением на фичи (search,details) и слои (data, domain, presentation) для обеспечения чистой архитектуры.Внедрение DI с использованием Koin: Настроил Dependency Injection с использованием Koin для упрощения управления зависимостями.
- Search -Реализовал функционал получения данных о криптовалютах через API, а также их отображение в списке.
- Details - Реализовал экран детальной информации о выбранной криптовалюте, включающий изображение, описание и категории.
- Bug - Занимался исправлением багов, которые возникали в процессе разработки, чтобы приложение работало стабильно и без ошибок.

## Скриншоты
<p float="left">
    <img src="screenshot_1.png" width="250"> 
    <img src="screenshot_2.png" width="250"> 
    <img src="screenshot_3.png" width="250"> 
    <img src="screenshot_4.png" width="250"> 
    <img src="screenshot_5.png" width="250"> 
    <img src="screenshot_6.png" width="250"> 
</p> 