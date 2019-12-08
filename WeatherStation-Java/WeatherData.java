import java.util.ArrayList;

public class WeatherData implements Subject {
    private ArrayList observers;
    private float temperature;
    private float humidity;
    private float pressure;
    // 为每一个WeatherData对象，创建一个ArrayList存放观察者
    public WeatherData() {
        observers = new ArrayList();
    }
    // 每登记一个观察者，向观察者列表中插入一个Observer对象
    public void registerObserver(Observer o) {
        observers.add(o);
    }
    // 在观察者列表中删除指定观察者
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }
    // 遍历观察者列表，让每个观察者都更新信息
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer)observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }
    // 当观测数据改变时，调用 notifyObservers，通知所有观察者改变数据
    public void measurementsChanged() {
        notifyObservers();
    }
    // 修改观测数据，改变 WeatherData 对象的字段值，再调用 measurementsChanged，通知所有观察者
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
