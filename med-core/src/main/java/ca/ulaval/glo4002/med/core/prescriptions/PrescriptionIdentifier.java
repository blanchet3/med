package ca.ulaval.glo4002.med.core.prescriptions;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PrescriptionIdentifier implements Serializable {

    @Column
    private UUID number;

    protected PrescriptionIdentifier() {
    }

    public PrescriptionIdentifier(UUID number) {
        this.number = number;
    }

    public static PrescriptionIdentifier create() {
        return new PrescriptionIdentifier(UUID.randomUUID());
    }

    public String describe() {
        return number.toString();
    }

    private static final long serialVersionUID = 1L;
}
