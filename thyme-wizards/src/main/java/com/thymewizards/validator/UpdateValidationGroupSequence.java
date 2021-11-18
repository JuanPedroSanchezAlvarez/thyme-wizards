package com.thymewizards.validator;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, ValidationGroupOne.class})
public interface UpdateValidationGroupSequence {

}
