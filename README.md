# super-parakeet
一种实现了对 TLE 参数进行解析，并基于 SGP4 模型对卫星轨道进行数值模拟并输出相关的轨道元素信息

1.定义了一个名为 TLEUtils 的类，其中包含一个名为 getTleParams 的公共静态方法。

2.该方法的作用是传入两个 TLE（Two-line Element）参数、开始时间和结束时间，然后根据 TLE 参数使用 SGP4 算法对卫星的轨道进行计算，并返回计算结果。

3.在该方法中，代码首先使用传入的 TLE 参数创建一个 TLE 对象，然后使用 SGP4.selectExtrapolator 方法创建一个 TLEPropagator 对象，用于计算轨道。接着，使用 while 循环对指定时间范围内的每一秒进行轨道计算，计算结果包括半长轴、偏心率、轨道倾角、升交点赤经、近地点幅角和平近点角，最终将这些结果存储在 StringBuilder 对象中并返回。

4.每次计算完轨道元素后，将结果格式化为字符串，并存储到 StringBuilder 中，最后将 StringBuilder 转换成字符串返回。

注意：以上TLEUtils类需引用orekit环境，具体方法查看orekit官网（https://www.orekit.org/）。
