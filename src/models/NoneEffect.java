package models;

public class NoneEffect extends AreaEffect{

    public NoneEffect () {
        setEffectType(EffectType.NONE);
    }

    @Override
    public void applyEffect(Character character) {

    }
}
