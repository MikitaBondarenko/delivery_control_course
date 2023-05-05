package com.example.delivery_control.dto;

import com.example.delivery_control.models.Restaurant;
import com.example.delivery_control.models.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long id;
    @NotEmpty(message = "Должен быть оставлен комментарий!")
    private String comment;
    private int rating;
    private String review_imgurl;
    @NotEmpty(message = "Должны быть указаны плюсы!")
    private String advantages;
    @NotEmpty(message = "Должны быть указаны минусы!")
    private String disadvantages;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private Restaurant restaurant;
    private UserEntity created_by;

}
