package base.abstracts;

import base.interfaces.IModel;


public abstract class Model<EntityType extends Entity, Key> implements IModel<EntityType, Key> {
    protected abstract void create(EntityType entity);
}
