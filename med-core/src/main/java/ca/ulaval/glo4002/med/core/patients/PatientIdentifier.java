package ca.ulaval.glo4002.med.core.patients;

public class PatientIdentifier {

    public final String number;

    public PatientIdentifier(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof PatientIdentifier) {
            return ((PatientIdentifier) obj).number.equals(number);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }

}
