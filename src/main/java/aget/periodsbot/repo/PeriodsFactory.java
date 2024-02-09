package aget.periodsbot.repo;

import org.jdbi.v3.core.Handle;

import java.util.UUID;

public interface PeriodsFactory {
    Periods periods(Handle handle, UUID usId);
}
