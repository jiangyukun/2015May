package log4j;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.testng.annotations.Test;

/**
 * log4j 集群测试
 * Created by jiangyukun on 2015/5/4.
 */
public class Log4jClient {
    private static Logger logger = Logger.getLogger(Log4jClient.class);

    @Test
    public static void testClient() {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }
}
