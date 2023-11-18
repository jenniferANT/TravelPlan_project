package com.app.travelplan.service;

import com.app.travelplan.model.dto.ShareDto;
import com.app.travelplan.model.form.ShareForm;

import java.util.List;

public interface ShareService {

    String share(ShareForm shareForm);
    //del share for receiver
    String delReceiver(long shareId, String receiverId);
    //user want delete share
    String delShare(long shareId);
    List<ShareDto> getMyShareForOther();
    List<ShareDto> getOtherShareForMe();
}
