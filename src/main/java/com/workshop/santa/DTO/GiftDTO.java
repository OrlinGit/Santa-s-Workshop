package com.workshop.santa.DTO;


import com.workshop.santa.model.GiftCategory;
import com.workshop.santa.model.GiftStatus;
import jakarta.validation.constraints.*;

public class GiftDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private  String name;

    @NotNull
    private GiftCategory category;

    @Min(value = 2)
    @Max(value = 99)
    private Integer targetAge;

    @NotNull
    private GiftStatus status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GiftCategory getCategory() {
        return category;
    }

    public void setCategory(GiftCategory category) {
        this.category = category;
    }

    public Integer getTargetAge() {
        return targetAge;
    }

    public void setTargetAge(Integer targetAge) {
        this.targetAge = targetAge;
    }

    public GiftStatus getStatus() {
        return status;
    }

    public void setStatus(GiftStatus status) {
        this.status = status;
    }
}
