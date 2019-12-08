## 观察者模式 -- 以气象信息应用为例

当我们在实现一个气象信息应用的时候，应用的后端要拿到气象站实时监测天气信息，与此同时终端各种用户界面要实时更新天气信息。整个应用的逻辑如下图所示：

![WeatherApp](https://github.com/zhanghb97/ObserverPattern/raw/master/images-folder/WeatherApp.png)

### 1. 用户界面如何更新天气信息

简单来说，该应用的目的就是将一份天气信息，显示在多个不同的用户界面上。很明显这是一对多的模型，在这种情况下，我们可以使用观察者模式，来实现对天气信息的分发。我们将各用户界面看作是观察者，天气信息看作是被观察者，当天气信息被修改的时候，通知各用户界面，更新各界面的天气信息值。

### 2. 观察者模式的逻辑结构

针对气象信息应用这样的场景，观察者模式的逻辑结构如下所示：

- 在天气信息类中，设置一个观察者列表数据结构。
- 初始化天气信息类对象时，观察者列表为空。
- 初始化用户界面类对象时，将该对象加入到观察者列表。
- 当天气信息类对象的信息数值发生改变，则遍历观察者列表，把最新的信息推送给每一个用户界面。

- 用户界面收到通知，更新天气信息数值，显示在终端。

### 3. 观察者模式的实现

在气象信息应用的场景下，我们需要三个大类：WeatherStation类、WeatherData类和各种Display类。

![DataStracture](https://github.com/zhanghb97/ObserverPattern/raw/master/images-folder/DataStracture.png)

（1）实例化WeatherData类和各种Display类

![Initialize](https://github.com/zhanghb97/ObserverPattern/raw/master/images-folder/Initialize.png)

实例化WeatherData类时，初始化观察者列表，此时观察者列表为空。

实例化各种Display类时，传入WeatherData类对象，调用添加观察者方法，把各种Display类对象加入观察者列表。

```java
// 实例化 WeatherData
WeatherData weatherData = new WeatherData();
// 实例化 CurrentConditionsDisplay, ForecastDisplay
CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);
```

（2）更新天气信息数据，触发观察者刷新页面

![Display](https://github.com/zhanghb97/ObserverPattern/raw/master/images-folder/Display.png)

调用WeatherData类对象的更新天气数据方法`setMeasurements`，该方法会调用它的子方法`notifyObserver`，遍历观察者列表。

```java
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
```

`notifyObserver`再调用观察者的`update`方法，使每个观察者更新天气信息。

```java
// 遍历观察者列表，让每个观察者都更新信息
public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
				Observer observer = (Observer)observers.get(i);
				observer.update(temperature, humidity, pressure);
		}
}
```

最后，`update`方法调用`display`方法，打印各自不同的信息。

```java
// 当 update 被调用时，保存传入的temperature和humidity，调用 display
public void update(float temperature, float humidity, float pressure) {
    this.temperature = temperature;
    this.humidity = humidity;
    display();
}
// 打印当前温度湿度
public void display() {
		System.out.println("Current conditions: " + temperature + "C degrees and " + humidity + "% humidity");
}
```

（3）打印结果

当WeatherStation更改天气信息数据的时候，各个用户界面类对象同步更改信息，并分别打印各自内容。

```java
// 调用 setMeasurements 打印更新的数据
weatherData.setMeasurements(26, 65, 30.4f);
weatherData.setMeasurements(30, 50, 29.2f);
weatherData.setMeasurements(18, 90, 29.2f);
```

![Result](https://github.com/zhanghb97/ObserverPattern/raw/master/images-folder/Result.png)

### 4. 观察者模式的优缺点

首先，观察者模式的优点是保证了对象间的低耦合，并保证了高度的协作。在我们的场景下，天气信息类和用户界面类是解耦的，在新增用户界面类的时候，天气信息类不需要进行任何修改。在天气信息更新后，会确保每个用户界面都会收到通知，更新显示数值。

观察者模式也有缺点，当拥有大量观察者时，被观察对象如果改变，通知所有观察者会花费很多时间，信息的实时性值得商榷。除此之外，如果有一个观察者更新信息时出现阻塞，后续观察者需要在队列中等待，针对这种情况，应当采用异步的方式。