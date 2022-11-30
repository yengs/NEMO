package nemo.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private int reviewNum;
    private String reviewWriter;
    private String reviewId;
    private int reviewProductIdx;
    private String reviewContents;
    private int reviewSatisfaction;
    private String reviewFiles;
    private String reviewItemfiles;
    private String reviewItemname;
    private int reviewItemprice;
};
