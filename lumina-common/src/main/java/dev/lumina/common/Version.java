// lumina-common/src/main/java/dev/lumina/common/Version.java
package dev.lumina.common;

public record Version(int major, int minor, int patch) implements Comparable<Version> {
    @Override
    public int compareTo(Version other) {
        int a = Integer.compare(major, other.major);
        if (a != 0) return a;
        int b = Integer.compare(minor, other.minor);
        if (b != 0) return b;
        return Integer.compare(patch, other.patch);
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }
}
