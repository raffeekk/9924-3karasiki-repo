const mapDiv = document.getElementById('map');
const eventsDiv = document.getElementById('events'); // Для отображения списка происшествий

function loadMap() {
    // Подключение Yandex Maps API
    const apiUrl = 'https://api-maps.yandex.ru/2.1/?lang=ru_RU';
    const script = document.createElement('script');
    script.src = apiUrl;

    script.onload = () => {
        ymaps.ready(() => {
            // Инициализация карты
            const map = new ymaps.Map('map', {
                center: [45.03547, 38.975313], // Координаты центра Краснодара
                zoom: 12,
            });

            // Слой пробок
            const trafficLayer = new ymaps.traffic.provider.Actual({}, { infoLayerShown: true });
            trafficLayer.setMap(map);

            // Обновление событий
            trafficLayer.events.add('update', () => {
                const trafficData = trafficLayer.getProvider().getTraffic();
                renderTrafficEvents(trafficData);
            });
        });
    };

    script.onerror = () => {
        console.error('Ошибка загрузки Yandex Maps API');
    };

    document.head.appendChild(script);
}

// Функция для отображения дорожных событий
function renderTrafficEvents(trafficData) {
    eventsDiv.innerHTML = '<h2>Дорожные происшествия</h2>';

    if (trafficData && trafficData.events.length > 0) {
        trafficData.events.forEach((event, index) => {
            const eventDiv = document.createElement('div');
            eventDiv.className = 'event';
            eventDiv.innerHTML = `
                <strong>Адрес:</strong> <p>${event.address || 'Неизвестно'}</p>
                <strong>Происшествие:</strong> <p>${event.type || 'Неизвестно'}</p>
                <a href="#" id="event-${index}">Перейти к событию</a>
            `;

            const link = eventDiv.querySelector(`#event-${index}`);
            link.addEventListener('click', () => {
                if (event.coordinates) {
                    const placemark = new ymaps.Placemark(event.coordinates, {
                        balloonContent: `${event.type || 'Происшествие'}: ${event.address || 'Адрес не указан'}`
                    });
                    const map = ymaps.getMap('map');
                    map.geoObjects.add(placemark);
                    map.setCenter(event.coordinates, 15);
                    placemark.balloon.open();
                }
            });

            eventsDiv.appendChild(eventDiv);
        });
    } else {
        eventsDiv.innerHTML += '<p>Нет данных о дорожных происшествиях</p>';
    }
}

// Загружаем карту автоматически при запуске
loadMap();