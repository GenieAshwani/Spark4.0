// Weather Icons Configuration
const weatherIcons = {
    'sunny': `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <circle cx="50" cy="50" r="20" fill="#FFD700" stroke="#FFA500" stroke-width="2"/>
        <g stroke="#FFD700" stroke-width="3" stroke-linecap="round">
            <line x1="50" y1="10" x2="50" y2="20"/>
            <line x1="50" y1="80" x2="50" y2="90"/>
            <line x1="10" y1="50" x2="20" y2="50"/>
            <line x1="80" y1="50" x2="90" y2="50"/>
            <line x1="21.21" y1="21.21" x2="28.28" y2="28.28"/>
            <line x1="71.72" y1="71.72" x2="78.79" y2="78.79"/>
            <line x1="78.79" y1="21.21" x2="71.72" y2="28.28"/>
            <line x1="28.28" y1="71.72" x2="21.21" y2="78.79"/>
        </g>
    </svg>`,
    
    'partly-cloudy': `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <circle cx="35" cy="35" r="15" fill="#FFD700" stroke="#FFA500" stroke-width="1.5"/>
        <g stroke="#FFD700" stroke-width="2" stroke-linecap="round">
            <line x1="35" y1="8" x2="35" y2="15"/>
            <line x1="8" y1="35" x2="15" y2="35"/>
            <line x1="18.93" y1="18.93" x2="23.64" y2="23.64"/>
            <line x1="55" y1="35" x2="62" y2="35"/>
            <line x1="46.36" y1="23.64" x2="51.07" y2="18.93"/>
        </g>
        <path d="M45 55 Q38 45 28 45 Q18 45 18 55 Q18 65 28 65 L65 65 Q75 65 75 55 Q75 45 65 45 Q58 45 55 50 Q52 45 45 45 Q45 50 45 55 Z" 
              fill="#E0E0E0" stroke="#CCCCCC" stroke-width="1"/>
    </svg>`,
    
    'cloudy': `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 55 Q18 45 28 45 Q38 45 45 55 Q52 45 55 45 Q65 45 65 55 Q75 45 75 55 Q75 65 65 65 L28 65 Q18 65 18 55 Z" 
              fill="#E0E0E0" stroke="#CCCCCC" stroke-width="2"/>
        <path d="M35 45 Q28 35 38 35 Q48 35 55 45 Q62 35 65 35 Q75 35 75 45 Q75 55 65 55 L38 55 Q28 55 28 45 Z" 
              fill="#F0F0F0" stroke="#DDDDDD" stroke-width="1"/>
    </svg>`,
    
    'rainy': `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 45 Q18 35 28 35 Q38 35 45 45 Q52 35 55 35 Q65 35 65 45 Q75 35 75 45 Q75 55 65 55 L28 55 Q18 55 18 45 Z" 
              fill="#8E8E93" stroke="#6D6D70" stroke-width="2"/>
        <g stroke="#4A90E2" stroke-width="2" stroke-linecap="round">
            <line x1="30" y1="65" x2="32" y2="75"/>
            <line x1="40" y1="60" x2="42" y2="70"/>
            <line x1="50" y1="65" x2="52" y2="75"/>
            <line x1="60" y1="60" x2="62" y2="70"/>
            <line x1="35" y1="70" x2="37" y2="80"/>
            <line x1="55" y1="70" x2="57" y2="80"/>
        </g>
    </svg>`,
    
    'stormy': `<svg viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
        <path d="M25 40 Q18 30 28 30 Q38 30 45 40 Q52 30 55 30 Q65 30 65 40 Q75 30 75 40 Q75 50 65 50 L28 50 Q18 50 18 40 Z" 
              fill="#4A4A4A" stroke="#333333" stroke-width="2"/>
        <path d="M48 55 L42 70 L50 70 L45 85 L58 65 L50 65 L55 55 Z" 
              fill="#FFD700" stroke="#FFA500" stroke-width="1"/>
        <g stroke="#4A90E2" stroke-width="2" stroke-linecap="round">
            <line x1="25" y1="60" x2="27" y2="70"/>
            <line x1="35" y1="65" x2="37" y2="75"/>
            <line x1="65" y1="60" x2="67" y2="70"/>
        </g>
    </svg>`
};

// API Configuration
const API_CONFIG = {
    baseUrl: 'http://localhost:8080',
    endpoint: '/weather/forecast'
};

// Current search parameters
let currentSearch = {
    city: '',
    days: 7
};

/**
 * Get appropriate weather icon based on condition
 * @param {string} condition - Weather condition description
 * @returns {string} SVG icon string
 */
function getWeatherIcon(condition) {
    const conditionLower = condition.toLowerCase();
    
    if (conditionLower.includes('sunny') || conditionLower.includes('clear')) {
        return weatherIcons.sunny;
    } else if (conditionLower.includes('partly') || conditionLower.includes('partial')) {
        return weatherIcons['partly-cloudy'];
    } else if (conditionLower.includes('rain') || conditionLower.includes('shower') || conditionLower.includes('drizzle')) {
        return weatherIcons.rainy;
    } else if (conditionLower.includes('storm') || conditionLower.includes('thunder')) {
        return weatherIcons.stormy;
    } else if (conditionLower.includes('cloud') || conditionLower.includes('overcast')) {
        return weatherIcons.cloudy;
    } else {
        return weatherIcons['partly-cloudy']; // Default fallback
    }
}

/**
 * Format date string to day abbreviation
 * @param {string} dateString - Date in YYYY-MM-DD format
 * @returns {string} Day abbreviation (Mon, Tue, etc.)
 */
function formatDate(dateString) {
    const date = new Date(dateString);
    const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
    return days[date.getDay()];
}

/**
 * Get current time formatted for display
 * @returns {string} Formatted current time
 */
function getCurrentTime() {
    const now = new Date();
    return now.toLocaleString('en-US', { 
        weekday: 'long', 
        hour: '2-digit', 
        minute: '2-digit',
        hour12: true 
    });
}

/**
 * Render weather data to the DOM
 * @param {Object} data - Weather data from API
 */
function renderWeatherData(data) {
    const { weatherResponse, dayTemp } = data;
    
    const content = `
        <button class="refresh-btn" onclick="refreshCurrentSearch()">Refresh</button>
        
        <div class="header">
            <div class="location-info">
                <h1>Results for <span class="city">${weatherResponse.city}, ${weatherResponse.region}</span></h1>
            </div>
            <div class="weather-title">
                <h2>Weather</h2>
                <div class="time">${getCurrentTime()}</div>
                <div class="time">${weatherResponse.condition}</div>
            </div>
        </div>

        <div class="main-weather">
            <div class="weather-icon">${getWeatherIcon(weatherResponse.condition)}</div>
            <div class="temperature-info">
                <div class="temperature">${Math.round(weatherResponse.temperature)}°<span style="font-size: 0.6em;">C</span></div>
            </div>
        </div>

        <div class="weather-details">
            <div class="detail-item">
                <div class="label">Location</div>
                <div class="value">${weatherResponse.city}, ${weatherResponse.country}</div>
            </div>
            <div class="detail-item">
                <div class="label">Condition</div>
                <div class="value">${weatherResponse.condition}</div>
            </div>
            <div class="detail-item">
                <div class="label">Region</div>
                <div class="value">${weatherResponse.region}</div>
            </div>
        </div>

        <div class="forecast-section">
            <div class="section-title">${dayTemp.length}-Day Forecast</div>
            <div class="forecast-container">
                ${dayTemp.map((day, index) => {
                    const dayName = index === 0 ? 'Today' : formatDate(day.date);
                    return `
                        <div class="forecast-day">
                            <div class="day">${dayName}</div>
                            <div class="forecast-icon">${getWeatherIcon(weatherResponse.condition)}</div>
                            <div class="temp-range">
                                <span class="temp-max">${Math.round(day.maxTemp)}°</span>
                                <span class="temp-min">${Math.round(day.minTemp)}°</span>
                            </div>
                        </div>
                    `;
                }).join('')}
            </div>
        </div>
    `;

    document.getElementById('weather-content').innerHTML = content;
}

/**
 * Show error message in the UI
 * @param {string} message - Error message to display
 */
function showError(message) {
    document.getElementById('weather-content').innerHTML = `
        <div class="error">
            <h3>Error loading weather data</h3>
            <p>${message}</p>
            <button onclick="loadWeatherData()">Try Again</button>
        </div>
    `;
}

/**
 * Show loading state in the UI
 */
function showLoading() {
    document.getElementById('weather-content').innerHTML = '<div class="loading">Loading weather data...</div>';
}

/**
 * Build API URL with parameters
 * @param {string} city - City name
 * @param {number} days - Number of forecast days
 * @returns {string} Complete API URL
 */
function buildApiUrl(city, days) {
    const params = new URLSearchParams({
        city: city,
        days: days
    });
    return `${API_CONFIG.baseUrl}${API_CONFIG.endpoint}?${params.toString()}`;
}

/**
 * Validate form inputs
 * @param {string} city - City name
 * @param {number} days - Number of days
 * @returns {Object} Validation result
 */
function validateInputs(city, days) {
    const errors = [];
    
    if (!city || city.trim().length === 0) {
        errors.push('City name is required');
    }
    
    if (!days || days < 1 || days > 14) {
        errors.push('Days must be between 1 and 14');
    }
    
    return {
        isValid: errors.length === 0,
        errors: errors
    };
}

/**
 * Load weather data from API
 * @param {string} city - City name
 * @param {number} days - Number of forecast days
 */
async function loadWeatherData(city, days) {
    try {
        // Validate inputs
        const validation = validateInputs(city, days);
        if (!validation.isValid) {
            throw new Error(validation.errors.join(', '));
        }
        
        showLoading();
        
        // Update current search parameters
        currentSearch = { city: city.trim(), days: parseInt(days) };
        
        const apiUrl = buildApiUrl(currentSearch.city, currentSearch.days);
        console.log('Fetching weather data from:', apiUrl);
        
        // Disable form during request
        setFormEnabled(false);
        
        const response = await fetch(apiUrl);
        
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status} - ${response.statusText}`);
        }
        
        const data = await response.json();
        console.log('Weather data received:', data);
        
        // Validate required data structure
        if (!data.weatherResponse || !data.dayTemp) {
            throw new Error('Invalid API response structure');
        }
        
        renderWeatherData(data);
        
    } catch (error) {
        console.error('Error fetching weather data:', error);
        
        let errorMessage = 'Unable to fetch weather data. ';
        
        if (error.message.includes('fetch') || error.message.includes('NetworkError')) {
            errorMessage += 'Please check if the weather service is running on localhost:8080.';
        } else if (error.message.includes('HTTP error')) {
            errorMessage += `Server responded with error: ${error.message}`;
        } else {
            errorMessage += error.message;
        }
        
        showError(errorMessage);
    } finally {
        // Re-enable form
        setFormEnabled(true);
    }
}

/**
 * Refresh current search
 */
function refreshCurrentSearch() {
    if (currentSearch.city) {
        loadWeatherData(currentSearch.city, currentSearch.days);
    }
}

/**
 * Handle form submission
 * @param {Event} event - Form submit event
 */
function handleFormSubmit(event) {
    event.preventDefault();
    
    const cityInput = document.getElementById('city-input');
    const daysInput = document.getElementById('days-input');
    
    const city = cityInput.value.trim();
    const days = parseInt(daysInput.value);
    
    loadWeatherData(city, days);
}

/**
 * Enable/disable form inputs
 * @param {boolean} enabled - Whether form should be enabled
 */
function setFormEnabled(enabled) {
    const form = document.getElementById('weather-form');
    const inputs = form.querySelectorAll('input, select, button');
    
    inputs.forEach(input => {
        input.disabled = !enabled;
    });
}

/**
 * Initialize the application
 */
function initializeApp() {
    console.log('Weather Dashboard initializing...');
    
    // Set up form event listener
    const form = document.getElementById('weather-form');
    if (form) {
        form.addEventListener('submit', handleFormSubmit);
    }
    
    // Focus on city input
    const cityInput = document.getElementById('city-input');
    if (cityInput) {
        cityInput.focus();
    }
}

// Event Listeners
document.addEventListener('DOMContentLoaded', initializeApp);

// Global error handler for unhandled promise rejections
window.addEventListener('unhandledrejection', function(event) {
    console.error('Unhandled promise rejection:', event.reason);
    showError('An unexpected error occurred. Please refresh the page.');
});

// Expose functions to global scope for button onclick events
window.refreshCurrentSearch = refreshCurrentSearch;