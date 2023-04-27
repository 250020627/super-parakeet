import org.hipparchus.util.FastMath;
import org.orekit.errors.OrekitException;
import org.orekit.frames.FramesFactory;
import org.orekit.orbits.KeplerianOrbit;
import org.orekit.propagation.analytical.tle.SGP4;
import org.orekit.propagation.analytical.tle.TLE;
import org.orekit.propagation.analytical.tle.TLEPropagator;
import org.orekit.time.AbsoluteDate;


import org.orekit.utils.Constants;
import org.orekit.utils.TimeStampedPVCoordinates;



public class TLEUtils {
    // 封装的方法，返回 TLE 参数
    public static String getTleParams(String line1, String line2, AbsoluteDate startDate, AbsoluteDate endDate) {
        StringBuilder result = new StringBuilder();
        try {
            TLE tle = new TLE(line1, line2);

            AbsoluteDate date = tle.getDate();

            result.append("TLE轨道时间: ").append(date).append("\n");
            result.append("TLE line 1: ").append(line1).append("\n");
            result.append("TLE line 2: ").append(line2).append("\n");

            TLEPropagator sgp4 = SGP4.selectExtrapolator(tle);
            double stepSize = 60.0;
            AbsoluteDate currentDate = startDate;
            while (currentDate.compareTo(endDate) <= 0) {
                TimeStampedPVCoordinates pv = sgp4.getPVCoordinates(currentDate, FramesFactory.getEME2000());
                KeplerianOrbit orbit = new KeplerianOrbit(pv, FramesFactory.getEME2000(), Constants.WGS84_EARTH_MU);
                double A = orbit.getA(); // 半长轴
                double E = orbit.getE(); // 偏心率
                double I = FastMath.toDegrees(orbit.getI()); // 轨道倾角
                double RAAN = Math.toDegrees(orbit.getRightAscensionOfAscendingNode()); // 升交点赤经
                if (RAAN < 0) {
                    RAAN += 360.0;
                }
                double W = Math.toDegrees(orbit.getPerigeeArgument()); // 近地点幅角
                if (W < 0) {
                    W += 360.0;
                }
                double AOP = Math.toDegrees(orbit.getMeanAnomaly()); // 平近点角
                if (AOP < 0) {
                    AOP += 360.0;
                }

                String a = String.format("%.9f", A);
                String e = String.format("%.9f", E);
                String i = String.format("%.9f", I);
                String raan = String.format("%.9f", RAAN);
                String w = String.format("%.9f", W);
                String aop = String.format("%.9f", AOP);

                result.append(currentDate)
                        .append("   "+"半长轴：").append(a).append(" ")
                        .append("   "+"偏心率：").append(e).append(" ")
                        .append("   "+"轨道倾角：").append(i).append(" ")
                        .append("   "+"升交点赤经：").append(raan).append(" ")
                        .append("   "+"近地点幅角：").append(w).append(" ")
                        .append("   "+"平近点角：").append(aop).append("\n");
                currentDate = currentDate.shiftedBy(stepSize);
            }

        } catch (OrekitException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}