package com.app.travelplan.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sessions {
    private long timeMinutesBegin; //số thời gian bắt đầu
    private long numberTimeMinutes; //tổng thời gian diễn ra
    //0: đêm - 1: ăn sáng - 2: buổi sáng - 3: buổi trưa - 4: buổi chiều - 5: tắm rửa - 6: ăn tối - 7: buổi tối
    private int index; //chỉ số phiên
    private boolean isOptional;

    private boolean isEnd;
    private boolean isEnable;

}
