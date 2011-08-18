package org.maxur.commons.service;

import org.aspectj.lang.Signature;
import org.springframework.util.StopWatch;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
public interface ServiceNotifier {

    void notifyBenchmark(Signature signature, StopWatch stopWatch);

    void notifyEnter(Signature signature);

    void notifyExit(Signature signature);
}
