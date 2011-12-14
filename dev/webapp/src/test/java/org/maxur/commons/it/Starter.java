package org.maxur.commons.it;

import org.maxur.commons.annotation.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Starter {

    @Autowired
    private Fake fake;

    public void doTrace() {
        fake.trace();
    }

    public void doTraceWithReturn() {
        fake.traceWithReturn();
    }

    public void doTraceWithArguments() {
        fake.traceWithArguments(0);
    }

    @Trace
    public void doDirectTrace() {
    }


    public void doBenchmark() {
        fake.benchmark();
    }

    public void doBenchmarkWithReturn() {
        fake.benchmarkWithReturn();
    }

    public void doBenchmarkWithArguments() {
        fake.benchmarkWithArguments(0);
    }


}