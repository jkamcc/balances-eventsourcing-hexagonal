package org.devengineering.balances.entities;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {
  UUID getEventId();
}
