package ru.dmisb.photon.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Collection;

import ru.dmisb.photon.BuildConfig;

@SuppressWarnings("unused")
public class L {

    @SuppressWarnings("unused")
    private enum LogType {
        v, d, i, w, e
    }

    @SuppressWarnings("unused")
    interface LogReceiver {
        void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg);

        void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr);
    }


    private static final LogReceiver DEFAULT_LOG_RECEIVER = new LogReceiver() {

        @Override
        public void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg) {
            switch (type) {
                case v:
                    Log.v(tag, msg);
                    break;
                case d:
                    Log.d(tag, msg);
                    break;
                case i:
                    Log.i(tag, msg);
                    break;
                case w:
                    Log.w(tag, msg);
                    break;
                case e:
                    Log.e(tag, msg);
                    break;
            }
        }

        @Override
        public void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
            switch (type) {
                case v:
                    Log.v(tag, msg, tr);
                    break;
                case d:
                    Log.d(tag, msg, tr);
                    break;
                case i:
                    Log.i(tag, msg, tr);
                    break;
                case w:
                    Log.w(tag, msg, tr);
                    break;
                case e:
                    Log.e(tag, msg, tr);
                    break;
            }
        }
    };

    private static boolean logsEnable = AppConfig.LOG_ENABLED;
    @NonNull
    private static LogReceiver logReceiver = DEFAULT_LOG_RECEIVER;

    @SuppressWarnings("unused")
    private static class LogReceiverArray implements LogReceiver {

        @NonNull
        private LogReceiver[] logReceivers;

        LogReceiverArray(@NonNull LogReceiver... receivers) {
            logReceivers = receivers;
        }

        @Override
        public void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg) {
            for (LogReceiver receiver : logReceivers) {
                if (receiver != null) {
                    receiver.log(type, tag, msg);
                }
            }
        }

        @Override
        public void log(@NonNull LogType type, @Nullable String tag, @Nullable String msg, @Nullable Throwable tr) {
            for (LogReceiver receiver : logReceivers) {
                if (receiver != null) {
                    receiver.log(type, tag, msg, tr);
                }
            }
        }
    }

    // ---------- P U B L I C ----------

    @SuppressWarnings("unused")
    public static void configure(boolean enable, @Nullable LogReceiver receiver) {
        logsEnable = enable;
        logReceiver = receiver == null ? DEFAULT_LOG_RECEIVER : receiver;
    }

    @SuppressWarnings("unused")
    public static void configure(boolean enable, @Nullable LogReceiver... receiver) {
        logsEnable = enable;
        logReceiver = receiver == null ? DEFAULT_LOG_RECEIVER : new LogReceiverArray(receiver);
    }

    // --- v ---

    public static void v(@Nullable final Exception e) {
        log(LogType.v, e);
    }

    public static <T extends Collection> void v(@NonNull final T o) {
        log(LogType.v, o);
    }

    public static void v(@NonNull final Object... o) {
        log(LogType.v, o);
    }

    // --- d ---

    public static void d(@Nullable final Exception e) {
        log(LogType.d, e);
    }

    public static <T extends Collection> void d(@NonNull final T o) {
        log(LogType.d, o);
    }

    public static void d(@NonNull final Object... o) {
        log(LogType.d, o);
    }

    // --- i ---

    public static void i(@Nullable final Exception e) {
        log(LogType.i, e);
    }

    public static <T extends Collection> void i(@NonNull final T o) {
        log(LogType.i, o);
    }

    public static void i(@NonNull final Object... o) {
        log(LogType.i, o);
    }

    // --- w ---

    public static void w(@Nullable final Exception e) {
        log(LogType.w, e);
    }

    public static <T extends Collection> void w(@NonNull final T o) {
        log(LogType.w, o);
    }

    public static void w(@NonNull final Object... o) {
        log(LogType.w, o);
    }

    // --- e ---

    public static void e(@Nullable final Exception e) {
        log(LogType.e, e);
    }

    public static <T extends Collection> void e(@NonNull final T o) {
        log(LogType.e, o);
    }

    public static void e(@NonNull final Object... o) {
        log(LogType.e, o);
    }

    // ---------- P R I V A T E ----------

    private static void log(@NonNull LogType logType, @Nullable final Exception e) {
        if (logsEnable) {
            log(logType, "ERROR", e);
        }
    }

    private static <T extends Collection> void log(@NonNull LogType logType, @NonNull final T o) {
        if (logsEnable) {
            int i = 0;
            for (Object obj : o) {
                log(logType, i++, obj);
            }
        }
    }

    private static void log(@NonNull LogType logType, @NonNull final Object... o) {
        if (logsEnable) {
            StringBuilder builder = new StringBuilder();
            for (Object object : o) {
                builder.append(object).append(" ");
            }
            log(logType, builder.toString(), null);
        }
    }

    private static void log(@NonNull LogType logType, @Nullable final String message, @Nullable final Exception e) {
        if (logsEnable) {
            StackTraceElement element = trace();
            String className;
            StringBuilder builder = new StringBuilder();
            if (element != null) {
                builder.append(element.getMethodName()).append(" (line ").append(element.getLineNumber()).append(") -> ").append(message);
                className = element.getClassName();
            } else {
                className = L.class.getName();
            }
            final String tag = className.startsWith(BuildConfig.APPLICATION_ID) ?
                    className.substring(BuildConfig.APPLICATION_ID.length()) : className;
            final String msg = builder.toString();
            if (e == null) {
                logReceiver.log(logType, tag, msg);
            } else {
                logReceiver.log(logType, tag, msg, e);
            }
        }
    }

    @Nullable
    private static StackTraceElement trace() {
        StackTraceElement[] e = Thread.currentThread().getStackTrace();
        boolean found = false;
        for (StackTraceElement s : e) {
            if (s.getClassName().equals(L.class.getName())) {
                found = true;
            }
            if (found && !s.getClassName().equals(L.class.getName())) {
                return s;
            }
        }
        return null;
    }
}