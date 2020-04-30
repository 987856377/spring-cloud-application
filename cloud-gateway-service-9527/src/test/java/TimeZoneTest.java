import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package PACKAGE_NAME
 * @Author xuzhenkui
 * @Date 2020/4/30 21:20
 */
public class TimeZoneTest {
    public static void main(String[] args) {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime);
    }
}
