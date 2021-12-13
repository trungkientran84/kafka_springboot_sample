package com.t.t.k.ims.model.common;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

/**
 * This base model store the create and update date time of all model in the system
 *
 * @author ttkien
 */
@Data
public class BasedModel {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
