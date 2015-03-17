Add Prescription

Narrative:
In order to provide patients with medication during their stay and to keep a record
As a physician
I want to add prescriptions to a patient's record

Scenario:  Adding a prescription with missing information
Given an existing patient
And a prescription with missing information
When I submit the form to add to the patient's record
Then I receive an error
And the error code is "PRES001"

Scenario: Adding a valid prescription
Given an existing patient
And a valid prescription form
When I submit the form to add to the patient's record
Then I receive an answer saying it was created succesfully

Scenario: Adding a valid prescription is stored
Given an existing patient
And a valid prescription form
When I add this prescription to the patient's record
Then the prescription is stored