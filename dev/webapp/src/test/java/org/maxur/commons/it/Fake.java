package org.maxur.commons.it;

import org.maxur.commons.annotation.Benchmark;
import org.maxur.commons.annotation.Trace;
import org.springframework.stereotype.Service;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/23/11
 */
@Service
public class Fake {

    @Trace public void trace() {}

    @Trace public int traceWithReturn() {return 0;}

    @Trace public void traceWithArguments(final int i) {}

    @Benchmark public void benchmark() {}

    @Benchmark public int benchmarkWithReturn() {return 0;}

    @Benchmark public void benchmarkWithArguments(final int i) {}


}
