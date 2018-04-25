/*
package com.example.game_project.Accessories;

import android.os.SystemClock;

*/
/**
 * Created by arl5500 on 3/22/18.
 *//*


public class Timer {
    private long startThreadMillis;
    private long startRealtimeMillis;
    private long startUptimeMillis;

    */
/**
     * Result of com.example.game_project.Accessories.Timer.getElapsedTime()
     *//*

    public static class ElapsedTime {
        private final long elapsedThreadMillis;
        private final long elapsedRealtimeMillis;
        private final long elapsedUptimeMillis;

        */
/**
         * Constructor
         *
         * @param timer
         *            instance from which to calculate elapsed time
         *//*

        public ElapsedTime(Timer timer) {
            elapsedThreadMillis = SystemClock.currentThreadTimeMillis() - timer.startThreadMillis;
            elapsedRealtimeMillis = SystemClock.elapsedRealtime() - timer.startRealtimeMillis;
            elapsedUptimeMillis = SystemClock.uptimeMillis() - timer.startUptimeMillis;
        }

        */
/**
         * Get milliseconds running in current thread
         *
         * This result is only valid if com.example.game_project.Accessories.Timer.getElapsedTime() is called from the same
         * thread as the com.example.game_project.Accessories.Timer constructor, or the last call to com.example.game_project.Accessories.Timer.reset().
         *
         * @return milliseconds
         *//*

        public long getElapsedThreadMillis() {
            return elapsedThreadMillis;
        }

        */
/**
         * Get elapsed milliseconds, including time spent in sleep
         *
         * @return milliseconds
         *//*

        public long getElapsedRealtimeMillis() {
            return elapsedRealtimeMillis;
        }

        */
/**
         * Get elapsed milliseconds, not counting time spent in deep sleep
         *
         * @return milliseconds
         *//*

        public long getElapsedUptimeMillis() {
            return elapsedUptimeMillis;
        }

        @Override
        public String toString() {
            return "realtime: " + elapsedRealtimeMillis
                    + " ms; uptime: " + elapsedUptimeMillis
                    + " ms; thread: " + elapsedThreadMillis + " ms";
        }
    }

    */
/**
     * Constructor
     *//*

    public Timer() {
        reset();
    }

    */
/**
     * Set Timer's start time to the current time
     *//*

    public void reset() {
        startThreadMillis = SystemClock.currentThreadTimeMillis();
        startRealtimeMillis = SystemClock.elapsedRealtime();
        startUptimeMillis = SystemClock.uptimeMillis();
    }

    */
/**
     * Get elapsed time since construction or last call to reset()
     *
     * @return Timer.ElapsedTime
     *//*

    public ElapsedTime getElapsedTime() {
        return new ElapsedTime(this);
    }

    */
/**
     * Get elapsed time as a human-readable string
     *
     * If time is less than one second, it will be rendered as a number of milliseconds.
     * Otherwise, it will be rendered as a number of seconds.
     *
     * @return String
     *//*

    public String getElapsedTimeString() {
        double seconds = (double)getElapsedTime().getElapsedRealtimeMillis() / 1000.0;
        if (seconds < 1.0) {
            return String.format("%.0f ms", seconds * 1000);
        }
        else {
            return String.format("%.2f s", seconds);
        }
    }

    @Override
    public String toString() {
        return "Timer " + getElapsedTimeString();
    }
}*/
