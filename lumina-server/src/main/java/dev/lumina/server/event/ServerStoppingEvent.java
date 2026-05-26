package dev.lumina.server.event;

import dev.lumina.event.Event;

import java.time.Instant;

public record ServerStoppingEvent(Instant timestamp) implements Event {}
