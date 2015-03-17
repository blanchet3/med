package ca.ulaval.glo4002.med.core.prescriptions;

import java.util.UUID;

public class PrescriptionIdentifier {

    public final UUID number;

    public PrescriptionIdentifier(UUID number) {
        this.number = number;
    }

    public static PrescriptionIdentifier create() {
        return new PrescriptionIdentifier(UUID.randomUUID());
    }

}
