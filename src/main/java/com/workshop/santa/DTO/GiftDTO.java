package com.workshop.santa.DTO;


import com.workshop.santa.model.GiftCategory;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public class GiftDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private  String name;

    @NotNull
    private GiftCategory category;

    @Min(value = 2)
    @Max(value = 99)
    private Integer targetAge;


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

    public void setId(Long giftId) {

    }

    public void setIsWrapped(boolean wrapped) {
    }

    public void setCreatedAt(LocalDateTime createdAt) {
    }
}
