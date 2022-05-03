package com.bocom.common.trace;

import com.bocom.common.trace.entity.Trace;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * trace传递
 *
 * @author sliu
 * @date 2022/4/11 20:12
 */
public class TraceContext {
    private static final ThreadLocal<Trace> CONTEXT = new ThreadLocal<>();

    public static Trace get() {
        return CONTEXT.get();
    }

    public static Trace getOrDefault() {
        Trace trace = CONTEXT.get();
        if (trace == null) {
            trace = new Trace(Trace.generateTraceId());
            CONTEXT.set(trace);
        }
        return trace;
    }

    public static String getTraceId() {
        return Optional.ofNullable(CONTEXT.get()).map(Trace::getTraceId).orElse("");
    }

    public static void setTrace(Trace trace) {
        CONTEXT.set(trace);
    }

    public static void setTraceId() {
        CONTEXT.get().setTraceId(UUID.randomUUID().toString());
    }

    public static void remove() {
        CONTEXT.remove();
    }


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println(Trace.generateTraceId());
            Thread.sleep(200);
        }

    }
}
