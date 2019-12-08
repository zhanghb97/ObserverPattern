public class ForecastDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private Subject weatherData;
    // 将传入的 weatherData 登记为观察者
    public ForecastDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }
    // 当 update 被调用的时，保存传入的temperature和humidity，调用 display
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }
    // 打印预测信息
    public void display() {
        if(temperature > 28 && humidity < 60) {
            System.out.println("Forecast: Sunny");
        }
        else if(temperature > 24 && humidity < 70) {
            System.out.println("Forecast: Cloudy");
        }
        else if(temperature < 20 && humidity > 80) {
            System.out.println("Forecast: Rainy");
        }
        else {
            System.out.println("Please check the weather information online");
        }
    }
}
