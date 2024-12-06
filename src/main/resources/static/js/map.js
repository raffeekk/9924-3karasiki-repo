const mapDiv = document.getElementById('map');
const eventsDiv = document.getElementById('events');
const trafficDiv = document.createElement('div'); // Блок для загруженных участков
trafficDiv.id = 'traffic';
eventsDiv.insertAdjacentElement('afterend', trafficDiv); // Добавляем блок для загруженных участков

function loadMap() {
    // Подключение Yandex Maps API
    const apiUrl = 'https://api-maps.yandex.ru/2.1/?lang=ru_RU';
    const script = document.createElement('script');
    script.src = apiUrl;

    script.onload = () => {
        ymaps.ready(() => {
            // Инициализация карты
            const map = new ymaps.Map('map', {
                center: [45.03547, 38.975313], // Центр Краснодара
                zoom: 12,
            });

            // Слой пробок
            const trafficProvider = new ymaps.traffic.provider.Actual({}, { infoLayerShown: true });
            trafficProvider.setMap(map);

            // Обновление данных о пробках
            trafficProvider.events.add('update', () => {
                const trafficData = trafficProvider.getTrafficData();

                if (trafficData) {
                    renderTrafficCongestions(trafficData);
                    renderTrafficEvents(trafficData);
                } else {
                    console.error('Нет данных о пробках');
                }
            });
        });
    };

    script.onerror = () => {
        console.error('Ошибка загрузки Yandex Maps API');
    };

    document.head.appendChild(script);
}

// Функция для отображения событий
function renderTrafficEvents(trafficData) {
    eventsDiv.innerHTML = '<h2>Дорожные происшествия</h2>';

    // Выводим данные о событиях в консоль для отладки
    console.log('Данные о происшествиях:', trafficData);

    if (trafficData && trafficData.events && trafficData.events.length > 0) {
        trafficData.events.forEach((event) => {
            const eventDiv = document.createElement('div');
            eventDiv.className = 'event';
            eventDiv.innerHTML = `
                <strong>Адрес:</strong> <p>${event.address || 'Неизвестно'}</p>
                <strong>Происшествие:</strong> <p>${event.type || 'Неизвестно'}</p>
            `;
            eventsDiv.appendChild(eventDiv);
        });
    } else {
        eventsDiv.innerHTML += '<p>Нет данных о дорожных происшествиях</p>';
    }
}

// Функция для отображения загруженных участков дороги
function renderTrafficCongestions(trafficData) {
    trafficDiv.innerHTML = '<h2>Загруженные участки</h2>';

    if (trafficData.jams && trafficData.jams.length > 0) {
        trafficData.jams.forEach((jam) => {
            const { name, length, time } = jam;
            const congestionDiv = document.createElement('div');
            congestionDiv.className = 'congestion';

            congestionDiv.innerHTML = `
                <strong>Улица:</strong> <p>${name || 'Неизвестно'}</p>
                <strong>Длина:</strong> <p>${(length / 1000).toFixed(2)} км</p>
                <strong>Время:</strong> <p>${Math.round(time / 60)} минут</p>
            `;
            trafficDiv.appendChild(congestionDiv);
        });
    } else {
        trafficDiv.innerHTML += '<p>Нет данных о загруженных участках</p>';
    }
}

// Загружаем карту автоматически при запуске
loadMap();
