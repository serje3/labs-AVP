package base.interfaces;

import base.abstracts.Entity;

import java.util.List;

public interface IModel<EntityType extends Entity, Key> {
    EntityType get(Key key);
    void delete(Key key);
    List<EntityType> list();
}
