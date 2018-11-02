package ca.cours5b5.pavelzaharciuc.controleurs.interfaces;

import java.util.Map;

public interface ListenerServeur {
    void reagirCharge(Map<String, Object> objetJson);
}
