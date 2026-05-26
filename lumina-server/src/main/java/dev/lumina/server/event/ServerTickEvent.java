package dev.lumina.server.event;

import dev.lumina.event.Event;

import java.time.Instant;

public record ServerTickEvent(long tick, Instant timestamp) implements Event {}
