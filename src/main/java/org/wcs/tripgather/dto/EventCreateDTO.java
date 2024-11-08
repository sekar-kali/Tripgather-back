package org.wcs.tripgather.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public class EventCreateDTO {

    @NotBlank(message = "Le titre ne doit pas être vide")
}
