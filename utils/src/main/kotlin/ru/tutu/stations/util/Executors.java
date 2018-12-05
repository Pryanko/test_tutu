package ru.tutu.stations.util;

import ru.digipeople.logger.Logger;
import ru.digipeople.logger.LoggerFactory;

import java.util.concurrent.*;

/**
 * Фабричные методы для генерации {@link Executor}.
 *
 * @author Grigoriy Pryamov
 */
public class Executors {

    /**
     * Возвращает логируемый {@link ExecutorService} c указанными параметрами.
     * Альтернатива {@link ThreadPoolExecutor}
     */
    public static ExecutorService newLoggableThreadPoolExecutor(String logTag,
                                                                int corePoolSize,
                                                                int maximumPoolSize,
                                                                long keepAliveTime,
                                                                TimeUnit unit,
                                                                BlockingQueue<Runnable> workQueue,
                                                                ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, unit,
                workQueue,
                threadFactory) {
            private final Logger logger = LoggerFactory.getLogger(logTag);

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Throwable throwable = obtainThrowable(r, t);
                if (throwable != null) {
                    logger.error("Task Error", t);
                }
            }
        };
    }

    /**
     * Возвращает логируемый {@link ExecutorService} c одним потоком.
     * Альтернатива {@link java.util.concurrent.Executors#newSingleThreadExecutor()}
     */
    public static ExecutorService newLoggableSingleThreadExecutor(String logTag, ThreadFactory threadFactory) {
        return newLoggableThreadPoolExecutor(logTag, 1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                threadFactory);
    }

    /**
     * Возвращает логируемый {@link ScheduledExecutorService} c одним потоком.
     * Альтернатива {@link java.util.concurrent.Executors#newSingleThreadScheduledExecutor()}
     */
    public static ScheduledExecutorService newLoggableSingleThreadScheduledExecutor(String logTag, ThreadFactory threadFactory) {
        return new ScheduledThreadPoolExecutor(1, threadFactory) {
            private final Logger logger = LoggerFactory.getLogger(logTag);

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Throwable throwable = obtainThrowable(r, t);
                if (throwable != null) {
                    logger.error("Task Error", t);
                }
            }
        };
    }

    private static Throwable obtainThrowable(Runnable r, Throwable t) {
        if (t == null
                && r instanceof Future<?>
                && ((Future<?>) r).isDone()) {
            try {
                ((Future<?>) r).get();
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                // ignore/reset
                Thread.currentThread().interrupt();
            }
        }
        return t;
    }
}
