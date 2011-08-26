package org.maxur.commons.advice;

import org.springframework.util.StopWatch;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/24/11
 */
public class StopWatchFactory {

    public StopWatch make() {
        return new StopWatch();
    }

}
