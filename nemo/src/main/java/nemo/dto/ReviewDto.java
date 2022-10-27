package nemo.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int reviewNum;
    private String reviewWriter;
    private String reviewId;
    private String reviewImage;
    private int reviewProductIdx;
    private String reviewContents;
    private int reviewSatisfaction;
};
