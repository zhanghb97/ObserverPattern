class WeatherData
  attr_reader :temperature
  attr_reader :humidity
  attr_reader :pressure
  # 为每一个WeatherData对象，创建一个列表存放观察者
  def initialize
    @observers = []
  end
  # 遍历观察者列表，让每个观察者都更新信息
  def notify_observers
    @observers.each do |observer|
      observer.update(self)
    end
  end
  # 增加一个观察者，向观察者列表中插入一个Observer对象
  def add_observer(observer)
    @observers << observer
  end
  # 修改观测数据，改变 WeatherData 对象的字段值，再调用 notify_observers，通知所有观察者
  def set_measurements(temperature, humidity, pressure)
    @temperature = temperature
    @humidity = humidity
    @pressure = pressure
    notify_observers;
  end
end
