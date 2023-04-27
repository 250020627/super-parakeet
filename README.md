# super-parakeet
一种实现了对 TLE 参数进行解析，并基于 SGP4 模型对卫星轨道进行数值模拟并输出相关的轨道元素信息

1.定义了一个名为 TLEUtils 的类，其中包含一个名为 getTleParams 的公共静态方法。

2.该方法的作用是传入两个 TLE（Two-line Element）参数、开始时间和结束时间，然后根据 TLE 参数使用 SGP4 算法对卫星的轨道进行计算，并返回计算结果。

3.在该方法中，代码首先使用传入的 TLE 参数创建一个 TLE 对象，然后使用 SGP4.selectExtrapolator 方法创建一个 TLEPropagator 对象，用于计算轨道。接着，使用 while 循环对指定时间范围内的每一秒进行轨道计算，计算结果包括半长轴、偏心率、轨道倾角、升交点赤经、近地点幅角和平近点角，最终将这些结果存储在 StringBuilder 对象中并返回。

4.每次计算完轨道元素后，将结果格式化为字符串，并存储到 StringBuilder 中，最后将 StringBuilder 转换成字符串返回。

注意：以上TLEUtils类需引用orekit环境，具体方法查看orekit官网（https://www.orekit.org/）。

A program that implements the parsing of TLE parameters and performs numerical simulation of satellite orbits based on the SGP4 model, outputting related orbital element information.

1.A class named TLEUtils is defined, which includes a public static method named getTleParams.

2.The purpose of this method is to pass in two TLE (Two-line Element) parameters, a start time and an end time, and then use the SGP4 algorithm to calculate the satellite's orbit based on the TLE parameters, returning the calculation results.

3.In this method, the code first creates a TLE object using the passed-in TLE parameters, then uses the SGP4.selectExtrapolator method to create a TLEPropagator object for orbit calculation. Then, a while loop is used to calculate the orbit for every second within the specified time range. The calculation results include the semi-major axis, eccentricity, inclination, right ascension of the ascending node, argument of periapsis, and mean anomaly, which are stored in a StringBuilder object and returned.

4.After each orbit element is calculated, the result is formatted as a string and stored in the StringBuilder, which is then converted into a string and returned.

Note: The TLEUtils class above requires the use of the Orekit environment, for more information, please refer to the Orekit official website (https://www.orekit.org/).
