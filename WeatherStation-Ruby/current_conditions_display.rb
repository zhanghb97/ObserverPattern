class CurrentConditionsDisplay
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
  # 打印当前温度湿度
  def display
    puts("Current conditions: #{@temperature} F degrees and  #{@humidity} % humidity!")
  end
end
