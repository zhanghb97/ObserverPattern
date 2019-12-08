public class WeatherStation {
    public static void main(String[] args) {
        // 实例化 WeatherData
        WeatherData weatherData = new WeatherData();
        // 实例化 CurrentConditionsDisplay, ForecastDisplay
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
        // 调用 setMeasurements 打印更新的数据
        weatherData.setMeasurements(26, 65, 30.4f);
        weatherData.setMeasurements(30, 50, 29.2f);
        weatherData.setMeasurements(18, 90, 29.2f);
    }
}
