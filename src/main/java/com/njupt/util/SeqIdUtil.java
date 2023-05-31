package com.njupt.util;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author liangw
 * @create 2021-03-26 10:35
 */

public class SeqIdUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeqIdUtil.class);

    private static SnowflakeIdWorker snowflakeIdWorker;

    static {

        LOGGER.info("初始化SerializeIdUtil开始");
        int snowflakeNo = RandomUtils.nextInt(0, 10);
        // 右移5位，然后对超过5位的数，清除
        int datacenterId = (snowflakeNo >> 5) & 0x1F;
        int workerId = snowflakeNo & 0x1F;
        LOGGER.info("datacenterId=" + datacenterId + ", workerId=" + workerId);
        snowflakeIdWorker = new SnowflakeIdWorker(workerId, datacenterId);
        LOGGER.info("初始化SerializeIdUtil结束");
    }

    /**
     * 获取ID
     *
     * @return
     */
    public static long getId() {
        return snowflakeIdWorker.nextId();
    }

    public static void main(String[] args) {
        getId();
    }

}
