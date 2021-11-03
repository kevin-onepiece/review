package com.foo.redisexpirecallback;

class StingCache {

    static final String[] STRING_CACHE = new String[1024];

    static String getCachedString(String s) {
        int index = s.hashCode() & (STRING_CACHE.length - 1);
        String cached = STRING_CACHE[index];
        if (s.equals(cached)) {
            return cached;
        } else {
            STRING_CACHE[index] = s;
            return s;
        }
    }

    public static void main(String... args) {

        String a = "x" + new Integer(1);
        System.out.println("a is: String@" + System.identityHashCode(a));

        String b = "x" + new Integer(1);
        System.out.println("b is: String@" + System.identityHashCode(b));

        String c = getCachedString(a);
        System.out.println("c is: String@" + System.identityHashCode(c));

        String d = getCachedString(b);
        System.out.println("d is: String@" + System.identityHashCode(d));

    }

}