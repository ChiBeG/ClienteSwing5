package services.domain.persistence;

import java.util.UUID;

public abstract class EntityDTO {
    public final UUID id;

    public EntityDTO(UUID id) {
        this.id = id;
    }
}
