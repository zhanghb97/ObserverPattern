class ForecastDisplay
  attr_reader :temperature
  attr_reader :humidity
  attr_reader :pressure
  # 将传入的 weather_data 登记为观察者
  def initialize(weather_data)
    weather_data.add_observer(self)
  end
  # 当 update 被调用时，保存传入的temperature和humidity，调用 display
  def update(changed_data)
    @temperature = changed_data.temperature
    @humidity = changed_data.humidity
    @pressure = changed_data.pressure
    display
  end
  # 打印预测信息
  def display
    if @temperature > 28 && @humidity < 60
      puts("Forecast: Sunny")
    elsif  @temperature > 24 && @humidity < 70
      puts("Forecast: Cloudy")
    elsif @temperature < 20 && @humidity > 80
      puts("Forecast: Rainy")
    else
      puts("Please check the weather information online")
    end
  end
end