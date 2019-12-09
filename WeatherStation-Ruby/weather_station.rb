require_relative 'weather_data'
require_relative 'current_conditions_display'
require_relative 'forecast_display'
# 实例化 WeatherData
data = WeatherData.new
# 实例化 CurrentConditionsDisplay, ForecastDisplay
conditions_display = CurrentConditionsDisplay.new(data)
forecast_display = ForecastDisplay.new(data)
# 调用 set_measurements 打印更新的数据
data.set_measurements(26, 65, 30.4)
data.set_measurements(30, 50, 29.2)
data.set_measurements(18, 90, 30.6)
